package com.github.ska.traffic.viz.opentsdb;

import static com.github.ska.traffic.viz.web.declarative.opentsdb.Tag.ASSET_TYPE;

import com.github.ska.traffic.viz.model.vizceral.NodeId;
import com.github.ska.traffic.viz.model.vizceral.id.NodeIdGenerator;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Result;

public class AssetTypeNodeIdGenerator implements NodeIdGenerator<Result> {

  private static final String NON_ALPHA = "[^a-zA-Z0-9-]";

  @Override
  public NodeId generateNodeId(Result dataNode) {
	return new NodeId(dataNode.getTags()
	                          .get(ASSET_TYPE)
	                          .replaceAll(NON_ALPHA, " ")
	                          .trim());
  }

}
