package com.github.ska.traffic.viz.render;

import com.google.common.graph.MutableNetwork;
import com.github.ska.traffic.viz.model.vizceral.Connection;
import com.github.ska.traffic.viz.model.vizceral.Node;
import com.github.ska.traffic.viz.model.vizceral.render.Level;

public interface TrafficGraphRenderer {

  Node renderGraph(Level level, MutableNetwork<Node, Connection> mutableNetwork);

}