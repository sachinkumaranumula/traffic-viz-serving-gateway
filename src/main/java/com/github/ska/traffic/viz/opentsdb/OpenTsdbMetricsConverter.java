package com.github.ska.traffic.viz.opentsdb;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.ska.traffic.viz.web.declarative.opentsdb.Query;
import com.github.ska.traffic.viz.web.declarative.opentsdb.QueryRequest;
import com.github.ska.traffic.viz.web.declarative.opentsdb.QueryResponse;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Result;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Tag;
import com.google.common.base.Joiner;

public class OpenTsdbMetricsConverter {

  private static final Joiner TAG_JOINER = Joiner.on("|")
                                                 .skipNulls();

  public QueryRequest convertRequest(QuerySpec querySpec) {
	QueryRequest queryRequest = new QueryRequest();
	queryRequest.setStart(querySpec.getStartDate());
	queryRequest.setEnd(querySpec.getEndDate());
	queryRequest.setQueries(querySpec.getMetrics()
	                                 .stream()
	                                 .map(m -> toQuery(m, querySpec))
	                                 .collect(Collectors.toList()));
	return queryRequest;
  }

  public QueryResponse convertResponse(List<Result> results) {
	QueryResponse metricsResponse = new QueryResponse();
	metricsResponse.setMetrics(results);
	return metricsResponse;
  }

  private Query toQuery(MetricSpec metric, QuerySpec querySpec) {
	Query query = new Query();
	query.setAggregator(metric.getAggregator());
	query.setMetric(metric.getName());
	query.setDownsample(metric.getDownsample());
	query.setTags(buildTags(querySpec));
	return query;
  }

  private Map<String, String> buildTags(QuerySpec querySpec) {
	Map<String, String> metricTags = querySpec.getTags()
	                                          .entrySet()
	                                          .stream()
	                                          .collect(Collectors.toMap(e -> e.getKey()
	                                                                          .value(),
	                                                                    e -> TAG_JOINER.join(e.getValue())));
	metricTags.put(Tag.ENVIRONMENT.value(), querySpec.getEnvironment().getFullName());
	return metricTags;
  }

}
