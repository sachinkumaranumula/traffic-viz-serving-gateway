package com.github.ska.traffic.viz.serving.gateway.template;

import java.io.IOException;

import javax.annotation.Resource;

import com.github.ska.traffic.viz.serving.gateway.web.dto.MetricsBoundary;
import com.github.ska.traffic.viz.serving.gateway.web.dto.MetricsRequest;
import com.github.ska.traffic.viz.serving.gateway.web.dto.ServiceRequest;
import com.github.ska.traffic.viz.serving.gateway.web.dto.ViewRequest;
import com.github.ska.traffic.viz.serving.rest.CannedRequestStore;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ska.traffic.viz.Environment;
import com.github.ska.traffic.viz.web.declarative.opentsdb.Tag;

@Component
public class DefaultRequestMetaMorph {

  @Resource
  private ObjectMapper oMapper;

  @Resource
  private CannedRequestStore store;

  public MetricsRequest morph(ViewRequest viewRequest) {
	MetricsRequest requestForTier = requestFromStore(viewRequest.getTier(), viewRequest.getEnvironment());
	defineRequestScope(viewRequest, requestForTier);
	return requestForTier;
  }

  public MetricsRequest morph(ServiceRequest serviceRequest) {
	MetricsRequest requestForServices = requestFromStore(CannedRequestStore.JsonRequestFileFactory.SERVICES.name(), serviceRequest.getEnvironment());
	defineRequestScope(serviceRequest, requestForServices);
	requestForServices.getQuerySpec()
	                  .getTags()
	                  .put(Tag.ASSET_NAME, serviceRequest.getServices());
	return requestForServices;
  }

  private MetricsRequest requestFromStore(String tier, Environment env) {
	try {
	  return oMapper.readValue(store.getRequestFor(tier, env), MetricsRequest.class);
	} catch (IOException e) {
	  throw new IllegalStateException("Cannot read from json request store to build opentsdb query", e);
	}
  }

  private void defineRequestScope(MetricsBoundary boundary, MetricsRequest requestForTier) {
	requestForTier.getQuerySpec()
	              .setStartDate(boundary.getFrom());
	requestForTier.getQuerySpec()
	              .setEndDate(boundary.getTo());
	requestForTier.getQuerySpec()
	              .setEnvironment(boundary.getEnvironment());
  }

}
