package com.github.ska.traffic.viz.opentsdb;

import java.util.Map;
import java.util.function.Supplier;

import com.github.ska.traffic.viz.model.vizceral.id.IdentityGenerator;
import com.github.ska.traffic.viz.model.vizceral.id.NodeIdGenerator;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Result;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Tag;
import com.google.common.collect.ImmutableMap;

public class OpenTsdbNodeIdFactory {

  private static final Map<String, Supplier<NodeIdGenerator<Result>>> MAP = ImmutableMap.<String, Supplier<NodeIdGenerator<Result>>>builder()
                                                                                        .put(Tag.ASSET_NAME.name(), AssetNameNodeIdGenerator::new)
                                                                                        .put(Tag.HOST_NAME.name(), HostNameNodeIdGenerator::new)
                                                                                        .put(Tag.ASSET_TYPE.name(), AssetTypeNodeIdGenerator::new)
                                                                                        .put(Tag.DOMAIN_NAME.name(), DomainNodeIdGenerator::new)
                                                                                        .put(Tag.METRIC.name(), MetricNodeIdGenerator::new)
                                                                                        .build();

  private OpenTsdbNodeIdFactory() {
	throw new AssertionError();
  }

  public static NodeIdGenerator<Result> getIdGenerator(String factoryId) {
	Supplier<NodeIdGenerator<Result>> idGen = MAP.get(factoryId);
	if (idGen != null) {
	  return idGen.get();
	}
	return new IdentityGenerator<>(factoryId);
  }

}
