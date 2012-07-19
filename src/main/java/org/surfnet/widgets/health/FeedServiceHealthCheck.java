package org.surfnet.widgets.health;

import java.util.List;

import com.yammer.metrics.core.HealthCheck;

import org.surfnet.widgets.service.FeedService;

public class FeedServiceHealthCheck extends HealthCheck {

  private FeedService feedService;
  private static final int widgetsToRequest = 4;

  public FeedServiceHealthCheck(String name) {
    super(name);
  }

  public FeedServiceHealthCheck feedService(FeedService service) {
    this.feedService = service;
    return this;
  }

  @Override
  protected Result check() throws Exception {
    List widgets = feedService.getWidgets(widgetsToRequest);
    if (widgets == null) {
      return Result.unhealthy(String.format("FeedService %s does not respond with list of widgets: %s",
          feedService, widgets));
    } else {
      return Result.healthy(String.format("FeedService %s responds with %d widgets (%d requested).",
          feedService, widgets.size(), widgetsToRequest));
    }
  }
}
