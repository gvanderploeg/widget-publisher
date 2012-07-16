package org.surfnet.widgets.model;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public abstract class Widget {

  @JsonProperty
  private String title;

  @JsonProperty
  private URI uri;

  @JsonProperty
  private long id;

  @JsonProperty
  private String icon;

  public abstract String getTitle();
}
