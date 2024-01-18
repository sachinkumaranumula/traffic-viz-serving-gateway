package com.github.ska.traffic.viz.serving.gateway.web.dto;

import javax.validation.constraints.NotNull;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class ViewRequest extends MetricsBoundary {

  @NotNull
  private String tier;

  public String getTier() {
	return tier;
  }

  public void setTier(String tier) {
	this.tier = tier;
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
