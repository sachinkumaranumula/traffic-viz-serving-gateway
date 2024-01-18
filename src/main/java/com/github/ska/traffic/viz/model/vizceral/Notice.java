package com.github.ska.traffic.viz.model.vizceral;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@AutoProperty
public class Notice {

  @JsonProperty("title")
  private String title;

  @JsonProperty("link")
  private String link;

  @JsonProperty("severity")
  private NoticeSeverity severity;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String linkUrl) {
    this.link = linkUrl;
  }

  public NoticeSeverity getSeverity() {
    return severity;
  }

  public void setSeverity(NoticeSeverity severity) {
    this.severity = severity;
  }

  @Override
  public boolean equals(Object other) {
    return Pojomatic.equals(this, other);
  }

  @Override
  public String toString() {
    return Pojomatic.toString(this);
  }

  @Override
  public int hashCode() {
    return Pojomatic.hashCode(this);
  }
}
