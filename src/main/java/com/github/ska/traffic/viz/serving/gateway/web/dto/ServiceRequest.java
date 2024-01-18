package com.github.ska.traffic.viz.serving.gateway.web.dto;

import java.util.List;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class ServiceRequest extends MetricsBoundary {

  private List<String> services;

  public List<String> getServices() {
	return services;
  }

  public void setServices(List<String> services) {
	this.services = services;
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
