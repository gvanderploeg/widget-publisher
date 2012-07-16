package org.surfnet.widgets.edukapp.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.surfnet.widgets.model.Widget;

@XmlRootElement
public class EdukappWidget extends Widget {
  @JsonProperty
  private String name;

  @JsonProperty
  private String description;

  @JsonProperty
  private String type;

  @JsonProperty
  private boolean featured;


  @JsonProperty
  private String[] tags;

  @JsonProperty
  private String[] activities;

  @Override
  public String getTitle() {
    return name;
  }
}
