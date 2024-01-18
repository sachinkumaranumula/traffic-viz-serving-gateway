package com.github.ska.traffic.viz.model.vizceral;

import java.util.Map;
import java.util.Optional;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonValue;

@AutoProperty
public class NodeId {

  private final String id;

  public NodeId(String id) {
    this.id = id;
  }

  @JsonValue
  public String getId() {
    return id;
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
  
  public NodeId overrideIfNecessary(Map<String, String> overrides) {
	if (Optional.ofNullable(overrides).isPresent() && overrides.containsKey(this.id)) {
	  return new NodeId(overrides.get(this.id));
	}
	return this;
  }

}
