package com.github.ska.traffic.viz.model.vizceral.render;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class NodeType {

  private String key;
  private String value;

  public NodeType() {
	//Framework support for reflection
  }

  public NodeType(String key, String value) {
	this.key = key;
	this.value = value;
  }

  public String getKey() {
	return key;
  }

  public void setKey(String key) {
	this.key = key;
  }

  public String getValue() {
	return value;
  }

  public void setValue(String value) {
	this.value = value;
  }

  @Override
  public boolean equals(Object other) {
	return Pojomatic.equals(this, other);
  }

  @Override
  public String toString() {
	return Pojomatic.toString(this);
  }

  @Override
  public int hashCode() {
	return Pojomatic.hashCode(this);
  }

}
