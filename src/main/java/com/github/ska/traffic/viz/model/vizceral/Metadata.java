package com.github.ska.traffic.viz.model.vizceral;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@AutoProperty
public class Metadata {

  private Integer streaming;

  public Integer getStreaming() {
    return streaming;
  }

  public void setStreaming(Integer streaming) {
    this.streaming = streaming;
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
