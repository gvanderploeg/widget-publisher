package org.surfnet.widgets.config;

import java.net.URI;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

public class FeedConfiguration {

  @NotNull
  @JsonProperty
  private String name;

  @NotNull
  @JsonProperty
  private String type;

  @NotNull
  @JsonProperty
  private URI uri;

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public URI getUri() {
    return uri;
  }
}
