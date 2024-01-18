package com.github.ska.traffic.viz.model.vizceral;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NodeDepth {

  GLOBAL("global"),
  REGION("region"),
  DNS("dns"),
  LOCAL("focusedChild");

  final String renderer;

  private NodeDepth(String renderer) {
    this.renderer = renderer;
  }

  @JsonValue
  public String getNodeStatus() {
    return renderer; 
  }

}
