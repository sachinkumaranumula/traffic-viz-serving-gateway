package com.github.ska.traffic.viz.web.declarative.opentsdb;

import java.util.List;

public class QueryResponse {

  private List<Result> metrics;

  public void setMetrics(List<Result> metrics) {
	this.metrics = metrics;
  }

  public List<Result> getMetrics() {
	return metrics;
  }

}
