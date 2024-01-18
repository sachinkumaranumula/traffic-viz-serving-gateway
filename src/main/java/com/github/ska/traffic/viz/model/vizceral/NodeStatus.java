package com.github.ska.traffic.viz.model.vizceral;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NodeStatus {

  NORMAL,
  WARNING,
  DANGER;

  @JsonValue
  public String getNodeStatus() {
    return name().toLowerCase();
  }
}
