package com.github.ska.traffic.viz.serving.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ska.traffic.viz.opentsdb.OpenTsdbMetricsConverter;

@Configuration
public class RestServicesContext {

  @Bean
  public OpenTsdbMetricsConverter openTsdbMetricsConverter() {
	return new OpenTsdbMetricsConverter();
  }

}
