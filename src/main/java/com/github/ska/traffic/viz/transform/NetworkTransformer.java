package com.github.ska.traffic.viz.transform;

import com.github.ska.traffic.viz.model.vizceral.Connection;
import com.github.ska.traffic.viz.model.vizceral.Node;
import com.google.common.graph.MutableNetwork;

@FunctionalInterface
public interface NetworkTransformer<S, T> {

  MutableNetwork<Node, Connection> transform(S transformationSpec, T reply);

}