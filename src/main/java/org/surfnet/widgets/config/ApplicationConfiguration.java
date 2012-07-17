package org.surfnet.widgets.config;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.yammer.dropwizard.config.Configuration;

import org.codehaus.jackson.annotate.JsonProperty;

public class ApplicationConfiguration extends Configuration {

  @JsonProperty
  @NotNull
  private List<FeedConfiguration> feeds;

  public List<FeedConfiguration> getFeeds() {
    return feeds;
  }
}
