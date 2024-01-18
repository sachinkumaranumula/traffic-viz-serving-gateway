package com.github.ska.traffic.viz.model.vizceral.id;

import com.github.ska.traffic.viz.model.vizceral.NodeId;

public class IdentityGenerator<T> implements NodeIdGenerator<T> {

  private final String id;

  public IdentityGenerator(String id) {
	this.id = id;
  }

  @Override
  public NodeId generateNodeId(T dataNode) {
	return new NodeId(id);
  }

}
