package org.surfnet.widgets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.NotFoundException;
import com.sun.syndication.feed.synd.SyndFeed;

import org.surfnet.widgets.model.Widget;
import org.surfnet.widgets.repo.FeedService;
import org.surfnet.widgets.synd.SyndFeedBuilder;

@Path("/widgets")
@Produces(MediaType.APPLICATION_XML)
public class WidgetResource {


  private Map<String, FeedService> feedServices;

  public WidgetResource(Map<String, FeedService> feedServices) {
    this.feedServices = feedServices;
  }

  @GET
  public SyndFeed getAllFeeds(@QueryParam(value="limit") @DefaultValue("10") int limit) {
    final List<Widget> feed = new ArrayList<Widget>();

    for (FeedService<?> fs : feedServices.values()) {
      feed.addAll(fs.getWidgets());
    }
    Collections.sort(feed);
    Collections.reverse(feed);

    return new SyndFeedBuilder(feed)
        .setSource("all sources")
        .limit(limit)
        .build();
  }

  @GET
  @Path("/{source}")
  public SyndFeed getOneFeed(@PathParam("source") String source,
                             @QueryParam(value="limit") @DefaultValue("10") int limit) {
    FeedService feedService = feedServices.get(source);

    if (feedService == null) {
      throw new NotFoundException("Feed not found");
    }

    @SuppressWarnings("unchecked")
    List<Widget> feed = feedService.getWidgets();

    Collections.sort(feed);
    Collections.reverse(feed);

    return new SyndFeedBuilder(feed)
        .setSource(source)
        .limit(limit)
        .build();
  }
}
