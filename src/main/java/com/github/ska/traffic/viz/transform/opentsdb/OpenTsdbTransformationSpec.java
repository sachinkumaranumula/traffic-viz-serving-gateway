package com.github.ska.traffic.viz.transform.opentsdb;

import com.github.ska.traffic.viz.model.vizceral.render.Level;
import com.github.ska.traffic.viz.model.vizceral.render.RenderSpec;

public class OpenTsdbTransformationSpec {

  private Level currentLevel;
  private RenderSpec renderSpec;

  public OpenTsdbTransformationSpec(Level level, RenderSpec renderSpec) {
	this.currentLevel = level;
	this.renderSpec = renderSpec;
  }

  public Level getCurrentLevel() {
	return currentLevel;
  }

  public void setCurrentLevel(Level currentLevel) {
	this.currentLevel = currentLevel;
  }

  public RenderSpec getRenderSpec() {
	return renderSpec;
  }

  public void setRenderSpec(RenderSpec renderSpec) {
	this.renderSpec = renderSpec;
  }

}
