package com.github.ska.traffic.viz.serving.gateway.web.dto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.ska.traffic.viz.Environment;

public class MetricsBoundary {

  @JsonFormat(shape = JsonFormat.Shape.STRING,
              pattern = "MM/dd/yyyy HH:mm",
              timezone = "CST")
  private Date from = Date.from(Instant.now().minus(1L, ChronoUnit.HOURS));
  @JsonFormat(shape = JsonFormat.Shape.STRING,
              pattern = "MM/dd/yyyy HH:mm",
              timezone = "CST")
  private Date to = Date.from(Instant.now());
  @NotNull
  private Environment environment;

  public Date getFrom() {
	return from;
  }

  public void setFrom(Date from) {
	this.from = from;
  }

  public Date getTo() {
	return to;
  }

  public void setTo(Date to) {
	this.to = to;
  }

  public Environment getEnvironment() {
	return environment;
  }

  public void setEnvironment(Environment environment) {
	this.environment = environment;
  }

}
