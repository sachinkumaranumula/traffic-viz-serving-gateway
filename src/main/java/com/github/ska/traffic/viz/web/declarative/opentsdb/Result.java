package com.github.ska.traffic.viz.web.declarative.opentsdb;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Streams;

@AutoProperty
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

  private String metric;
  private Map<Tag, String> tags;
  private Map<String, BigInteger> dps;

  public String getMetric() {
	return metric;
  }

  public void setMetric(String metric) {
	this.metric = metric;
  }

  public Map<Tag, String> getTags() {
	return tags;
  }

  public void setTags(Map<Tag, String> tags) {
	this.tags = tags;
  }

  public Map<String, BigInteger> getDps() {
	return dps;
  }

  public void setDps(Map<String, BigInteger> dps) {
	this.dps = dps;
  }

  public Optional<BigInteger> diffAggregation() {
	if (dps != null) {
	  List<BigInteger> fsDps = dps.values()
	                              .stream()
	                              .filter(p -> p.signum() == 1)
	                              .sorted()
	                              .collect(Collectors.toList());
	  BigInteger first = fsDps.stream()
	                          .findFirst()
	                          .orElse(BigInteger.ZERO);
	  BigInteger last = Streams.findLast(fsDps.stream())
	                           .orElse(first);
	  return Optional.of(last.subtract(first));
	}
	return Optional.empty();
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
