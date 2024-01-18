package com.github.ska.traffic.viz.serving.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.github.ska.traffic.viz.serving.rest.CannedRequestStore;
import com.github.ska.traffic.viz.serving.rest.OpenTsdbClient;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Configuration
public class RestContext {

  @Bean
  public OpenTsdbClient opentsdbClient(@Value("${traffic.viz.feign.opentsdb.url}") String url, ObjectMapper oMapper) {
	return Feign.builder()
	            .logger(new Slf4jLogger())
	            .logLevel(feign.Logger.Level.FULL)
	            .contract(new JAXRSContract())
	            .client(new OkHttpClient())
	            .decoder(new JacksonDecoder(oMapper))
	            .encoder(new JacksonEncoder(oMapper))
	            .target(OpenTsdbClient.class, url);
  }

  @Bean
  public ObjectMapper oMapper() {
	return new ObjectMapper().registerModule(new Jdk8Module().configureAbsentsAsNulls(true))
	                         .setInjectableValues(new InjectableValues.Std())
	                         .setSerializationInclusion(JsonInclude.Include.NON_NULL)
	                         .configure(SerializationFeature.INDENT_OUTPUT, true)
	                         .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
	                         .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
	                         .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
  }

  @Bean
  public CannedRequestStore cannedRequestStore() {
	return new CannedRequestStore();
  }

}
