package org.surfnet.widgets.model;

import java.net.URI;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public abstract class Widget implements Comparable<Widget> {

  @JsonProperty
  private String name;

  @JsonProperty
  private String description;

  @JsonProperty
  private URI uri;

  @JsonProperty
  private long id;

  @JsonProperty
  private String icon;

  @JsonProperty
  private Date updated;


  @JsonProperty
  private Date created;


  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public URI getUri() {
    return uri;
  }

  public long getId() {
    return id;
  }

  public String getIcon() {
    return icon;
  }

  public Date getUpdated() {
    return updated;
  }

  public Date getCreated() {
    return created;
  }

  @Override
  public int compareTo(Widget o) {
    if (this.updated == o.updated) {
      return 0;
    }

    if (this.updated == null) {
      return -1;
    }
    if (o.updated == null) {
      return 1;
    }

    if (this.updated.after(o.getUpdated())) {
      return 1;
    } else if (this.updated.equals(o.getUpdated())) {
      return 0;
    } else {
      return -1;
    }
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}
