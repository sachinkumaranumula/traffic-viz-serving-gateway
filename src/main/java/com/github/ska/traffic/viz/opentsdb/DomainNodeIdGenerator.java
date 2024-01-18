package com.github.ska.traffic.viz.opentsdb;

import static com.github.ska.traffic.viz.web.declarative.opentsdb.Tag.ASSET_NAME;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.ska.traffic.viz.model.vizceral.NodeId;
import com.github.ska.traffic.viz.model.vizceral.id.NodeIdGenerator;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Result;

public class DomainNodeIdGenerator implements NodeIdGenerator<Result> {

  private static final Pattern SOE_PATTERN = Pattern.compile("(([a-zA-Z-]{3,})\\/([a-zA-Z0-9-_\\/]*)\\/([\\d.]*))");

  @Override
  public NodeId generateNodeId(Result dataNode) {
	return new NodeId(extractDomainFromService(dataNode.getTags()
	                                                   .get(ASSET_NAME)));
  }

  private String extractDomainFromService(String serviceName) {
	Matcher matcher = SOE_PATTERN.matcher(serviceName);
	if (matcher.matches()) {
	  return matcher.group(2);
	}
	throw new RuntimeException(String.format("Cannot identify domain from service string [%s]", serviceName));
  }

}
