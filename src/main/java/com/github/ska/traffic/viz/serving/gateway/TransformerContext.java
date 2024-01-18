package com.github.ska.traffic.viz.serving.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.ska.traffic.viz.transform.opentsdb.OpenTsdbMetricsNetworkTransformer;

@Configuration
public class TransformerContext {

    @Bean
    public OpenTsdbMetricsNetworkTransformer openTsdbMetricsNetworkTransformer() {
        return new OpenTsdbMetricsNetworkTransformer();
    }

}
