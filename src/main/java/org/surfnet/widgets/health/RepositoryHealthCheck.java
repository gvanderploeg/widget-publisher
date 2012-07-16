package org.surfnet.widgets.health;

import com.yammer.metrics.core.HealthCheck;

import org.surfnet.widgets.repo.WidgetFeedRepository;

public class RepositoryHealthCheck extends HealthCheck {


  private WidgetFeedRepository repository;

  public RepositoryHealthCheck(WidgetFeedRepository repository) {
    super("repo health check");
    this.repository = repository;
  }

  @Override
  protected Result check() throws Exception {
    if (repository.containsFeeds()) {
      return Result.healthy("All is well");
    } else {
      return Result.unhealthy("repo does not contain any items.");
    }
  }
}
