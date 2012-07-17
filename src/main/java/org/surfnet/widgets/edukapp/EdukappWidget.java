package org.surfnet.widgets.edukapp;

import org.codehaus.jackson.annotate.JsonProperty;
import org.surfnet.widgets.model.Widget;

public class EdukappWidget extends Widget {

  @JsonProperty
  private String type;

  @JsonProperty
  private boolean featured;


  @JsonProperty
  private String[] tags;

  @JsonProperty
  private String[] activities;

}
