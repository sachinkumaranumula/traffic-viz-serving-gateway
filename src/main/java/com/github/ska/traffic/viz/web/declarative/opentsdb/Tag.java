package com.github.ska.traffic.viz.web.declarative.opentsdb;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Tag {

  ENVIRONMENT("environment"),
  HOST_NAME("host-name"),
  ASSET_NAME("asset-name"),
  ASSET_TYPE("asset-type"),
  DOMAIN_NAME("domain-name"),
  UNKNOWN("unknown"),
  METRIC("metric");

  private final String value;

  private Tag(String value) {
	this.value = value;
  }

  @JsonCreator
  public static Tag forValue(String value) {
	for (Tag tag : values()) {
	  if (tag.value.equals(value)) {
		return tag;
	  }
	}
	return UNKNOWN;
  }

  public String value() {
	return value;
  }

}
