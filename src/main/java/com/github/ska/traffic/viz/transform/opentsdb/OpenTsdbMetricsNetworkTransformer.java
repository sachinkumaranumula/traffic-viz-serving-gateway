package com.github.ska.traffic.viz.transform.opentsdb;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ska.traffic.viz.model.vizceral.Connection;
import com.github.ska.traffic.viz.model.vizceral.Metrics;
import com.github.ska.traffic.viz.model.vizceral.Node;
import com.github.ska.traffic.viz.model.vizceral.NodeId;
import com.github.ska.traffic.viz.model.vizceral.id.NodeIdGenerator;
import com.github.ska.traffic.viz.model.vizceral.render.Level;
import com.github.ska.traffic.viz.model.vizceral.render.NodeLayout;
import com.github.ska.traffic.viz.model.vizceral.render.Severity;
import com.github.ska.traffic.viz.opentsdb.OpenTsdbNodeIdFactory;
import com.github.ska.traffic.viz.transform.NetworkTransformer;
import com.github.ska.traffic.viz.web.declarative.opentsdb.QueryResponse;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Result;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Tag;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;

public class OpenTsdbMetricsNetworkTransformer implements NetworkTransformer<OpenTsdbTransformationSpec, QueryResponse> {

  private static final Logger LOGGER = LoggerFactory.getLogger(OpenTsdbMetricsNetworkTransformer.class);

  @Override
  public MutableNetwork<Node, Connection> transform(OpenTsdbTransformationSpec transformSpec, QueryResponse reply) {
	MutableNetwork<Node, Connection> network = NetworkBuilder.directed()
	                                                         .allowsSelfLoops(true)
	                                                         .build();
	Level currentLevel = transformSpec.getCurrentLevel();
	NodeIdGenerator<Result> rootId = generatorForId(currentLevel.getRootIdGenerator());
	NodeIdGenerator<Result> leafId = generatorForId(currentLevel.getLeafIdGenerator());
	for (Result result : reply.getMetrics()) {
	  try {
		NodeId nodeIdU = rootId.generateNodeId(result).overrideIfNecessary(transformSpec.getRenderSpec().getIdOverrides());
		Node tierNode = new Node(nodeIdU);
		network.addNode(tierNode);
		NodeId nodeIdV = leafId.generateNodeId(result).overrideIfNecessary(transformSpec.getRenderSpec().getIdOverrides());
		Node dataNode = new Node(nodeIdV);
		network.addNode(dataNode);
		meterConnection(transformSpec, result, determineEdge(transformSpec, result, network, tierNode, dataNode));
	  } catch (Exception e) {
		LOGGER.error("Dropping result " + result + " from network analysis", e);
	  }
	}
	return network;
  }

  private void meterConnection(OpenTsdbTransformationSpec transformSpec, Result result, Connection connection) {
	Optional<BigInteger> aggregatedDataPoint = getAggregatedDps(transformSpec, result);
	if (aggregatedDataPoint.isPresent()) {
	  Metrics metrics = connection.getMetrics();
	  double aggregatedValue = aggregatedDataPoint.get()
	                                              .doubleValue();
	  Severity severityForMetric = transformSpec.getRenderSpec()
	                                            .getSeverityForMetric(result.getMetric());
	  switch (severityForMetric) {
		case NORMAL:
		  metrics.addNormal(aggregatedValue);
		  break;
		case DANGER:
		  metrics.addDanger(aggregatedValue);
		  break;
		case WARNING:
		  metrics.addWarning(aggregatedValue);
		  break;
		default:
		  LOGGER.error("Unknown severity {} received for aggregation", severityForMetric);
	  }
	}
  }

  private Optional<BigInteger> getAggregatedDps(OpenTsdbTransformationSpec transformSpec, Result result) {
	switch (transformSpec.getRenderSpec()
	                     .getPostAggregator()) {
	  case DIFF:
		return result.diffAggregation();
	  default:
		return result.getDps()
		             .values()
		             .stream()
		             .findFirst();
	}
  }

  private Connection determineEdge(OpenTsdbTransformationSpec transformSpec,
                                   Result result,
                                   MutableNetwork<Node, Connection> network,
                                   Node rootNode,
                                   Node leafNode) {
	Connection connection = null;
	NodeLayout layout = transformSpec.getRenderSpec()
	                                 .getLayoutForNode(determineLayoutBy(result, transformSpec.getCurrentLevel()
	                                                                                          .getLayoutBy()));
	switch (layout) {
	  case LTR:
		connection = connectIfDoesNotExist(network, leafNode, rootNode, transformSpec.getRenderSpec().getLayoutOverrides());
		break;
	  case RTL:
		connection = connectIfDoesNotExist(network, rootNode, leafNode, transformSpec.getRenderSpec().getLayoutOverrides());
		break;
	  default:
		LOGGER.error("Unknown layout {} received for connection", layout);
	}
	return connection;
  }

  private String determineLayoutBy(Result result, String layoutBy) {
	Tag layoutTag = Tag.forValue(layoutBy);
	String tagValue = result.getTags()
	                        .get(layoutTag);
	if (tagValue == null && Tag.METRIC == layoutTag) {
	  tagValue = result.getMetric();
	}
	return tagValue;
  }

  private Connection connectIfDoesNotExist(MutableNetwork<Node, Connection> network, Node nodeU, Node nodeV, Map<String, String> layoutOverrides) {
	Optional<Connection> connectingEdge = network.edgeConnecting(nodeU, nodeV);
	if (connectingEdge.isPresent()) {
	  return connectingEdge.get();
	}
	Connection connection = connectWithOverrides(nodeU, nodeV, layoutOverrides);
	network.addEdge(nodeU, nodeV, connection);
	return connection;
  }

  private Connection connectWithOverrides(Node nodeU, Node nodeV, Map<String, String> layoutOverrides) {
	if (Optional.ofNullable(layoutOverrides).isPresent() && layoutOverrides.containsKey(nodeV.getId().getId()) && layoutOverrides.containsValue(nodeU.getId().getId())) {
	  return Connection.newStreamingConnection(nodeV.getId(), nodeU.getId());
	}
	return Connection.newStreamingConnection(nodeU.getId(), nodeV.getId());
  }

  private NodeIdGenerator<Result> generatorForId(String nodeIdGen) {
	String idGen = nodeIdGen;
	Tag value = Tag.forValue(nodeIdGen);
	if (value != Tag.UNKNOWN) {
	  idGen = value.name();
	}
	return OpenTsdbNodeIdFactory.getIdGenerator(idGen);
  }

}
