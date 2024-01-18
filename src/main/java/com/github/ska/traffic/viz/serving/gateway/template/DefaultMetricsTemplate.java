package com.github.ska.traffic.viz.serving.gateway.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.annotation.Resource;

import com.github.ska.traffic.viz.render.TrafficGraphRenderer;
import com.github.ska.traffic.viz.serving.gateway.web.dto.MetricsRequest;
import com.github.ska.traffic.viz.serving.rest.OpenTsdbClient;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.google.common.graph.MutableNetwork;
import com.github.ska.traffic.viz.model.vizceral.Connection;
import com.github.ska.traffic.viz.model.vizceral.Node;
import com.github.ska.traffic.viz.model.vizceral.NodeDepth;
import com.github.ska.traffic.viz.model.vizceral.NodeId;
import com.github.ska.traffic.viz.model.vizceral.render.Level;
import com.github.ska.traffic.viz.opentsdb.OpenTsdbMetricsConverter;
import com.github.ska.traffic.viz.transform.opentsdb.OpenTsdbMetricsNetworkTransformer;
import com.github.ska.traffic.viz.transform.opentsdb.OpenTsdbTransformationSpec;
import com.github.ska.traffic.viz.web.declarative.opentsdb.QueryResponse;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Result;

@Component
public class DefaultMetricsTemplate {

  @Resource
  private OpenTsdbClient openTsdbClient;

  @Resource
  private OpenTsdbMetricsConverter converter;

  @Resource
  private OpenTsdbMetricsNetworkTransformer transformer;

  @Resource
  private TrafficGraphRenderer renderer;

  public Node visualizeMetrics(MetricsRequest metricsRequest) {
	List<Result> results = openTsdbClient.getMetrics(converter.convertRequest(metricsRequest.getQuerySpec()));
	if (results.isEmpty()) {
	  return noDataFound();
	}
	QueryResponse response = converter.convertResponse(results);
	Map<Integer, Node> levels = buildLevels(metricsRequest, response);
	return linkLevels(levels, metricsRequest);
  }

  private Node linkLevels(Map<Integer, Node> levels, MetricsRequest metricsRequest) {
	if (metricsRequest.getRenderSpec()
	                  .getConnectLevels() != null) {
	  for (Entry<Integer, Integer> connectPair : metricsRequest.getRenderSpec()
	                                                           .getConnectLevels()
	                                                           .entrySet()) {
		Node global = levels.get(connectPair.getKey());
		Node region = levels.get(connectPair.getValue());
		Optional<Node> childNodeById = global.findChildNodeById(region.getId());
		if (childNodeById.isPresent()) {
		  global.replaceChildNode(childNodeById.get(), region);
		}
	  }
	}
	return levels.get(0);
  }

  private Map<Integer, Node> buildLevels(MetricsRequest metricsRequest, QueryResponse response) {
	Map<Integer, Node> levels = new HashMap<>();
	for (Entry<Integer, Level> level : metricsRequest.getRenderSpec()
	                                                 .getLevels()
	                                                 .entrySet()) {
	  MutableNetwork<Node, Connection> network = transformer.transform(new OpenTsdbTransformationSpec(level.getValue(), metricsRequest.getRenderSpec()),
	                                                                   response);
	  levels.put(level.getKey(), renderer.renderGraph(level.getValue(), network));
	}
	return levels;
  }
  
  private Node noDataFound() {
	Node node = new Node(new NodeId("No Data"));
	node.setNodes(Sets.newHashSet(new Node(new NodeId("No data found"))));
	node.setNodeDepth(NodeDepth.REGION);
	return node;
  }
}
