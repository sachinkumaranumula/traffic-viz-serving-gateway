package com.github.ska.traffic.viz.model.vizceral.render;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class RenderSpec {

  private String tier;
  private Map<Severity, List<String>> severityMappings;
  private Map<NodeLayout, List<String>> layoutMappings;
  private PostAggregator postAggregator = PostAggregator.NONE;
  private Map<Integer, Level> levels;
  private Map<Integer, Integer> connectLevels;
  private Map<String, String> idOverrides;
  private Map<String, String> layoutOverrides;

  public String getTier() {
	return tier;
  }

  public void setTier(String tier) {
	this.tier = tier;
  }

  public Map<Severity, List<String>> getSeverityMappings() {
	return severityMappings;
  }

  public void setSeverityMappings(Map<Severity, List<String>> severityMappings) {
	this.severityMappings = severityMappings;
  }

  public Map<NodeLayout, List<String>> getLayoutMappings() {
	return layoutMappings;
  }

  public void setLayoutMappings(Map<NodeLayout, List<String>> layoutMappings) {
	this.layoutMappings = layoutMappings;
  }

  public Severity getSeverityForMetric(String metricName) {
	Optional<Entry<Severity, List<String>>> firstFound = severityMappings.entrySet()
	                                                                     .stream()
	                                                                     .filter(e -> e.getValue()
	                                                                                   .contains(metricName))
	                                                                     .findFirst();
	if (firstFound.isPresent()) {
	  return firstFound.get()
	                   .getKey();
	}
	return Severity.NORMAL;
  }

  public Map<Integer, Level> getLevels() {
	return levels;
  }

  public void setLevels(Map<Integer, Level> levels) {
	this.levels = levels;
  }

  public NodeLayout getLayoutForNode(String nodeType) {
	Optional<Entry<NodeLayout, List<String>>> firstFound = layoutMappings.entrySet()
	                                                                     .stream()
	                                                                     .filter(e -> e.getValue()
	                                                                                   .contains(nodeType))
	                                                                     .findFirst();
	if (firstFound.isPresent()) {
	  return firstFound.get()
	                   .getKey();
	}
	return NodeLayout.LTR;
  }

  public Map<Integer, Integer> getConnectLevels() {
	return connectLevels;
  }

  public void setConnectLevels(Map<Integer, Integer> connectLevels) {
	this.connectLevels = connectLevels;
  }

  public PostAggregator getPostAggregator() {
	return postAggregator;
  }

  public void setPostAggregator(PostAggregator postAggregator) {
	this.postAggregator = postAggregator;
  }

  public Map<String, String> getIdOverrides() {
	return idOverrides;
  }

  public void setIdOverrides(Map<String, String> idOverrides) {
	this.idOverrides = idOverrides;
  }

  public Map<String, String> getLayoutOverrides() {
	return layoutOverrides;
  }

  public void setLayoutOverrides(Map<String, String> layoutOverrides) {
	this.layoutOverrides = layoutOverrides;
  }

}
