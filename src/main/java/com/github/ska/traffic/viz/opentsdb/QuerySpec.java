package com.github.ska.traffic.viz.opentsdb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.ska.traffic.viz.Environment;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Tag;

@AutoProperty
public class QuerySpec {

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING,
              pattern = "MM/dd/yyyy hh:mm")
  private Date startDate;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING,
              pattern = "MM/dd/yyyy hh:mm")
  private Date endDate;
  @NotNull
  private List<MetricSpec> metrics;
  private Environment environment;
  private Map<Tag, List<String>> tags;

  public Date getStartDate() {
	return startDate;
  }

  public void setStartDate(Date startDate) {
	this.startDate = startDate;
  }

  public Date getEndDate() {
	return endDate;
  }

  public void setEndDate(Date endDate) {
	this.endDate = endDate;
  }

  public List<MetricSpec> getMetrics() {
	return metrics;
  }

  public void setMetrics(List<MetricSpec> metrics) {
	this.metrics = metrics;
  }

  public Environment getEnvironment() {
	return environment;
  }

  public void setEnvironment(Environment environment) {
	this.environment = environment;
  }

  public Map<Tag, List<String>> getTags() {
	return tags;
  }

  public void setTags(Map<Tag, List<String>> tags) {
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
