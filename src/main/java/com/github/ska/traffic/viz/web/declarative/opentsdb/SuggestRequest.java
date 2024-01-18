package com.github.ska.traffic.viz.web.declarative.opentsdb;

public class SuggestRequest {

  private String type = "tagv";
  private String q;
  private Integer max = 100;

  public String getType() {
	return type;
  }

  public void setType(String type) {
	this.type = type;
  }

  public String getQ() {
	return q;
  }

  public void setQ(String q) {
	this.q = q;
  }

  public Integer getMax() {
	return max;
  }

  public void setMax(Integer max) {
	this.max = max;
  }

}
