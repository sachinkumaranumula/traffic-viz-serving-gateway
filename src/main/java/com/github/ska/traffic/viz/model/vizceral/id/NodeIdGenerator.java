package com.github.ska.traffic.viz.model.vizceral.id;

import com.github.ska.traffic.viz.model.vizceral.NodeId;

@FunctionalInterface
public interface NodeIdGenerator<T> {

  NodeId generateNodeId(T dataNode);
  
}
