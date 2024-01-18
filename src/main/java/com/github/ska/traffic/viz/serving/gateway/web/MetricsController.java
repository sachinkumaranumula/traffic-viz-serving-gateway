package com.github.ska.traffic.viz.serving.gateway.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.github.ska.traffic.viz.serving.gateway.web.dto.MetricsRequest;
import com.github.ska.traffic.viz.serving.gateway.web.dto.ServiceRequest;
import com.github.ska.traffic.viz.serving.gateway.web.dto.ViewRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ska.traffic.viz.model.vizceral.Node;
import com.github.ska.traffic.viz.serving.gateway.template.DefaultMetricsTemplate;
import com.github.ska.traffic.viz.serving.gateway.template.DefaultRequestMetaMorph;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

  @Resource
  private DefaultMetricsTemplate template;

  @Resource
  private DefaultRequestMetaMorph metaMorph;

  @PostMapping("/query")
  public Node visualizeMetricsQuery(@Valid @RequestBody MetricsRequest metricsRequest) {
	return template.visualizeMetrics(metricsRequest);
  }

  @PostMapping("/view")
  public Node visualizeView(@Valid @RequestBody ViewRequest viewRequest) {
	return template.visualizeMetrics(metaMorph.morph(viewRequest));
  }
  

  @PostMapping("/services")
  public Node visualizeServices(@Valid @RequestBody ServiceRequest serviceRequest) {
	return template.visualizeMetrics(metaMorph.morph(serviceRequest));
  }

}
