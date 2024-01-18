package com.github.ska.traffic.viz.serving.gateway.template;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.ska.traffic.viz.serving.rest.OpenTsdbClient;
import com.github.ska.traffic.viz.web.declarative.opentsdb.SuggestRequest;

@Component
public class DefaultSuggestTemplate {

  @Resource
  private OpenTsdbClient openTsdbClient;

  public List<String> lookUpSuggestions(SuggestRequest suggestRequest) {
	return openTsdbClient.findSuggestions(suggestRequest);
  }

}
