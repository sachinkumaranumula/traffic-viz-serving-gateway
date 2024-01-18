package com.github.ska.traffic.viz.web.declarative.opentsdb;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QueryRequest {

  @JsonFormat(shape = JsonFormat.Shape.STRING,
              pattern = "yyyy/MM/dd HH:mm:ss",
              timezone = "CST")
  private Date start;
  @JsonFormat(shape = JsonFormat.Shape.STRING,
              pattern = "yyyy/MM/dd HH:mm:ss",
              timezone = "CST")
  private Date end;
  private List<Query> queries;

  public Date getStart() {
	return start;
  }

  public void setStart(Date start) {
	this.start = start;
  }

  public Date getEnd() {
	return end;
  }

  public void setEnd(Date end) {
	this.end = end;
  }

  public List<Query> getQueries() {
	return queries;
  }

  public void setQueries(List<Query> queries) {
	this.queries = queries;
  }

}
