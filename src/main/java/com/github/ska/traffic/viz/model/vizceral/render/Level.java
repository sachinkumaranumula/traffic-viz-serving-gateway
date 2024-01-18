package com.github.ska.traffic.viz.model.vizceral.render;

public class Level {

  private String name;
  private String leafIdGenerator;
  private String rootIdGenerator;
  private String layoutBy;
  private String renderer;
  private String layoutAlgorithm;

  public String getLeafIdGenerator() {
	return leafIdGenerator;
  }

  public void setLeafIdGenerator(String leafIdGenerator) {
	this.leafIdGenerator = leafIdGenerator;
  }

  public String getRootIdGenerator() {
	return rootIdGenerator;
  }

  public void setRootIdGenerator(String rootIdGenerator) {
	this.rootIdGenerator = rootIdGenerator;
  }

  public String getRenderer() {
	return renderer;
  }

  public void setRenderer(String renderer) {
	this.renderer = renderer;
  }

  public String getLayoutBy() {
	return layoutBy;
  }

  public void setLayoutBy(String layoutBy) {
	this.layoutBy = layoutBy;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public String getLayoutAlgorithm() {
	return layoutAlgorithm;
  }

  public void setLayoutAlgorithm(String layoutAlgorithm) {
	this.layoutAlgorithm = layoutAlgorithm;
  }

}
