package com.github.ska.traffic.viz.web.declarative.opentsdb;

import java.util.Map;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class Query {

  private String aggregator;
  private String metric;
  private String downsample;
  private Map<String, String> tags;

  public String getAggregator() {
	return aggregator;
  }

  public void setAggregator(String aggregator) {
	this.aggregator = aggregator;
  }

  public String getMetric() {
	return metric;
  }

  public void setMetric(String metric) {
	this.metric = metric;
  }

  public String getDownsample() {
	return downsample;
  }

  public void setDownsample(String downsample) {
	this.downsample = downsample;
  }

  public Map<String, String> getTags() {
	return tags;
  }

  public void setTags(Map<String, String> tags) {
	this.tags = tags;
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
