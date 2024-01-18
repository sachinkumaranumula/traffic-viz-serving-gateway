package com.github.ska.traffic.viz.serving.rest;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.github.ska.traffic.viz.web.declarative.opentsdb.QueryRequest;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Result;
import com.github.ska.traffic.viz.web.declarative.opentsdb.SuggestRequest;

public interface OpenTsdbClient {

  @POST
  @Path("/api/query")
  List<Result> getMetrics(QueryRequest queryRequest);

  @POST
  @Path("/api/suggest")
  List<String> findSuggestions(SuggestRequest suggestRequest);

}