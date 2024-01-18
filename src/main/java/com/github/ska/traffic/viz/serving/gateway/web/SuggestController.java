package com.github.ska.traffic.viz.serving.gateway.web;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.github.ska.traffic.viz.serving.gateway.template.DefaultSuggestTemplate;
import com.github.ska.traffic.viz.serving.gateway.web.dto.Suggestions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ska.traffic.viz.web.declarative.opentsdb.SuggestRequest;

@RestController
@RequestMapping("/suggest")
public class SuggestController {

  @Resource
  private DefaultSuggestTemplate suggestTemplate;

  @PostMapping("/tags")
  public List<String> lookupServices(@Valid @RequestBody SuggestRequest suggestRequest) {
	return suggestTemplate.lookUpSuggestions(suggestRequest);
  }

  @GetMapping("/domains")
  public List<String> lookupDomains() {
	return Suggestions.INSTANCE.getAllDomains();
  }
}
