package com.github.ska.traffic.viz.model.vizceral;

import java.util.HashSet;
import java.util.Set;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

@AutoProperty
public class Saga {

  private NodeDepth depth;
  private NodeId name;
  private Set<Node> nodes = new HashSet<>();
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

  public Set<Node> getNodes() {
	return nodes;
  }

  public void setNodes(Set<Node> nodes) {
	this.nodes = nodes;
  }

  public Set<Connection> getConnections() {
	return connections;
  }

  public void setConnections(Set<Connection> connections) {
	this.connections = connections;
  }

  public static Saga centerOfWorldSaga(NodeId cowNodeId) {
	Saga saga = new Saga();
	saga.setName(cowNodeId);
	saga.setDepth(NodeDepth.REGION);
	return saga;
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
