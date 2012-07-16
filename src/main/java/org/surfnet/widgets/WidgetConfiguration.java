package org.surfnet.widgets;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.yammer.dropwizard.config.Configuration;

import org.codehaus.jackson.annotate.JsonProperty;

public class WidgetConfiguration extends Configuration {

  @JsonProperty
  @NotNull
  private List<Map<String, String>> feeds;

  public List<Map<String, String>> getFeeds() {
    return feeds;
  }

  public void setFeeds(List<Map<String, String>> feeds) {
    this.feeds = feeds;
  }
}
