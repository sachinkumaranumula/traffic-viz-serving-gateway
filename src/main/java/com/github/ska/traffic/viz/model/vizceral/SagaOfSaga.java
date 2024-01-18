package com.github.ska.traffic.viz.model.vizceral;

import java.util.HashSet;
import java.util.Set;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

@AutoProperty
public class SagaOfSaga {

  private NodeDepth depth;
  private NodeId name;
  private Set<Saga> sagas = new HashSet<>();
  private Set<Connection> connections = new HashSet<>();

  @JsonProperty("renderer")
  public NodeDepth getDepth() {
    return depth;
  }

  public void setDepth(NodeDepth depth) {
    this.depth = depth;
  }

  public NodeId getName() {
    return name;
  }

  public void setName(NodeId name) {
    this.name = name;
  }

  @JsonProperty("nodes")
  public Set<Saga> getSagas() {
    return sagas;
  }

  public void setSagas(Set<Saga> sagas) {
    this.sagas = sagas;
  }

  public Set<Connection> getConnections() {
    return connections;
  }

  public void setConnections(Set<Connection> connections) {
    this.connections = connections;
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
