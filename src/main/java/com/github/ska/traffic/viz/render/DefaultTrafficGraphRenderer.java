package com.github.ska.traffic.viz.render;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.google.common.graph.MutableNetwork;
import com.github.ska.traffic.viz.model.vizceral.Connection;
import com.github.ska.traffic.viz.model.vizceral.Node;
import com.github.ska.traffic.viz.model.vizceral.NodeDepth;
import com.github.ska.traffic.viz.model.vizceral.NodeId;
import com.github.ska.traffic.viz.model.vizceral.render.Level;

@Component
public class DefaultTrafficGraphRenderer implements TrafficGraphRenderer {

  @Override
  public Node renderGraph(Level level, MutableNetwork<Node, Connection> mutableNetwork) {
	Node saga = new Node(new NodeId(level.getName()));
	saga.setDepth(NodeDepth.valueOf(level.getRenderer()));
	saga.setLayout(level.getLayoutAlgorithm());
	saga.setNodes(new HashSet<>(mutableNetwork.nodes()));
	saga.setConnections(mutableNetwork.edges());
	decorateBasedOnDepth(saga);
	return saga;
  }

  private void decorateBasedOnDepth(Node saga) {
	if (saga.getNodeDepth().equals(NodeDepth.GLOBAL)) {
	  saga.getNodes().forEach(n -> n.addNode(new Node()));
	}
  }

}
