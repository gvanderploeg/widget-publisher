package org.surfnet.widgets;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.surfnet.widgets.model.Widget;
import org.surfnet.widgets.repo.WidgetFeedRepository;

public class WidgetFeedUpdater {

//  private static final Logger LOG = LoggerFactory.getLogger(FeedUpdater.class);
  private static final Logger LOG = LoggerFactory.getLogger(WidgetFeedUpdater.class);

  private WidgetFeedRepository repo;
  private List<Map<String, String>> feeds;
  private FeedFetchingStrategy feedFetcher;

  public WidgetFeedUpdater(FeedFetchingStrategy feedFetcher, WidgetFeedRepository repo, List<Map<String,
      String>> feeds) {
    this.feedFetcher = feedFetcher;
    this.repo = repo;
    this.feeds = feeds;
  }

  public void syncAll() {
    for (Map<String, String> feedConfig : feeds) {
      sync(feedConfig.get("name"), URI.create(feedConfig.get("uri")));
    }
  }
  public void sync(String sourceId, URI sourceUri) {
    LOG.debug("About to sync source '{}' with URI: {}", sourceId, sourceUri);
    final List<Widget> widgets = feedFetcher.get(sourceUri);
    LOG.debug("URI {} returned these widgets: ", sourceUri, widgets);
    repo.setFeed(sourceId, widgets);
  }
}
