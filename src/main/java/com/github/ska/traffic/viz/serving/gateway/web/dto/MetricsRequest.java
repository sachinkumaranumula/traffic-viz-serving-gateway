package com.github.ska.traffic.viz.serving.gateway.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.ska.traffic.viz.model.vizceral.render.RenderSpec;
import com.github.ska.traffic.viz.opentsdb.QuerySpec;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricsRequest {

  private QuerySpec querySpec;
  private RenderSpec renderSpec;

  public QuerySpec getQuerySpec() {
	return querySpec;
  }

  public void setQuerySpec(QuerySpec querySpec) {
	this.querySpec = querySpec;
  }

  public RenderSpec getRenderSpec() {
	return renderSpec;
  }

  public void setRenderSpec(RenderSpec renderSpec) {
	this.renderSpec = renderSpec;
  }

}
