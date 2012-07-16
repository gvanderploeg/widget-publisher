package org.surfnet.widgets;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.NotFoundException;
import com.sun.syndication.feed.synd.SyndFeed;

import org.surfnet.widgets.model.Widget;
import org.surfnet.widgets.repo.WidgetFeedRepository;
import org.surfnet.widgets.synd.SyndFeedBuilder;

@Path("/widgets")
@Produces(MediaType.APPLICATION_XML)
public class WidgetResource {

  private WidgetFeedRepository repository;

  public WidgetResource(WidgetFeedRepository repository) {
    this.repository = repository;
  }

  @GET
  @Path("/{source}")
  @Produces()
  public SyndFeed getall(@PathParam("source") String source) {
    final List<Widget> feed = repository.getBySource(source);
    if (feed == null) {
      throw new NotFoundException("Feed not found");
    } else {
      return new SyndFeedBuilder(feed)
          .setSource(source)
          .build();
    }
  }
}
