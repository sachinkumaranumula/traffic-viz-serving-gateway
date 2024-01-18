package com.github.ska.traffic.viz.serving.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ska.traffic.viz.render.DefaultSagaOfSagaRenderer;
import com.github.ska.traffic.viz.render.DefaultTrafficGraphRenderer;
import com.github.ska.traffic.viz.render.SagaOfSagaRenderer;
import com.github.ska.traffic.viz.render.TrafficGraphRenderer;

@Configuration
public class VizceralContext {

  @Bean
  public SagaOfSagaRenderer vizceralRenderer() {
	return new DefaultSagaOfSagaRenderer();
  }

  @Bean
  public TrafficGraphRenderer trafficGraphRenderer() {
	return new DefaultTrafficGraphRenderer();
  }

}
