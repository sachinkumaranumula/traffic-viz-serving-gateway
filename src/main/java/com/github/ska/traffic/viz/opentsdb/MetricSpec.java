package com.github.ska.traffic.viz.opentsdb;

import javax.validation.constraints.NotNull;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class MetricSpec {

  @NotNull
  private String name;
  private boolean counter;
  private String aggregator;
  private String downsample;

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public boolean isCounter() {
	return counter;
  }

  public void setCounter(boolean counter) {
	this.counter = counter;
  }

  public String getAggregator() {
	return aggregator;
  }

  public void setAggregator(String aggregator) {
	this.aggregator = aggregator;
  }

  public String getDownsample() {
	return downsample;
  }

  public void setDownsample(String downsample) {
	this.downsample = downsample;
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
