package com.github.ska.traffic.viz.model.vizceral;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NoticeSeverity {

  INFO(0), WARNING(1), ERROR(2);
  
  private int level;
  
  private NoticeSeverity(int level) {
    this.level = level;
  }
  
  @JsonValue
  public int getNodeStatus() {
    return level;
  }
}
