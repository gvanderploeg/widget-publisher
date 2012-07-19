package org.surfnet.widgets.model;

import java.net.URI;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.surfnet.widgets.synd.JsonDateSerializer;

@XmlRootElement
public class Widget implements Comparable<Widget> {

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
  @JsonSerialize(using=JsonDateSerializer.class)
  private Date updated;


  @JsonProperty
  @JsonSerialize(using=JsonDateSerializer.class)
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

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null) { return false; }
    if (obj == this) { return true; }
    if (obj.getClass() != getClass()) {
      return false;
    }
    Widget rhs = (Widget) obj;

    return new EqualsBuilder()
        .append(this.id, rhs.id)
        .append(this.uri, rhs.uri)
        .append(this.icon, rhs.icon)
        .append(this.name, rhs.name)
        .append(this.description, rhs.description)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(3, 5)
        .append(id)
        .append(uri)
        .append(icon)
        .append(name)
        .append(description)
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append(id)
        .append(name)
        .append(uri)
        .toString();
  }
}
