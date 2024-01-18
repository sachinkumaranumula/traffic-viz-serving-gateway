package com.github.ska.traffic.viz.model.vizceral;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@AutoProperty
public class Metrics {

  private Double normal = 0d;
  private Double warning = 0d;
  private Double danger = 0d;

  public Double getNormal() {
	return normal;
  }

  public void setNormal(Double normal) {
	this.normal = normal;
  }

  public Double getWarning() {
	return warning;
  }

  public void setWarning(Double warning) {
	this.warning = warning;
  }

  public Double getDanger() {
	return danger;
  }

  public void setDanger(Double danger) {
	this.danger = danger;
  }

  public void addNormal(Double normal) {
	this.normal += normal;
  }

  public void addWarning(Double warning) {
	this.warning += warning;
  }

  public void addDanger(Double danger) {
	this.danger += danger;
  }

  @JsonIgnore
  public Double getMaxVolume() {
	return Math.max(Math.max(normal, warning), danger);
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

  public static Metrics withFlags(Double normal, Double warning, Double danger) {
	Metrics metrics = new Metrics();
	metrics.setDanger(danger);
	metrics.setNormal(normal);
	metrics.setWarning(warning);
	return metrics;
  }

}
