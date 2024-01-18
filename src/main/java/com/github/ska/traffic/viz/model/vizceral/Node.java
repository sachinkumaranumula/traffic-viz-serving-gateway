package com.github.ska.traffic.viz.model.vizceral;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.Property;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class Node {

  @Property
  private NodeId id;

  private String displayName;

  private LocalDateTime recordedTime;

  private Set<Node> nodes = new HashSet<>();

  private Set<Connection> connections = new HashSet<>();

  private NodeStatus nodeStatus;

  private Set<Notice> notices;

  private NodeDepth nodeDepth;

  private String layout;

  public Node() {
	// Empty for frameworks reflection
  }

  public Node(NodeId nodeId) {
	this.id = nodeId;
  }

  @JsonProperty("name")
  public NodeId getId() {
	return id;
  }

  public void setId(NodeId id) {
	this.id = id;
  }

  public String getDisplayName() {
	return displayName;
  }

  public void setDisplayName(String displayName) {
	this.displayName = displayName;
  }

  @JsonProperty("updated")
  public LocalDateTime getRecordedTime() {
	return recordedTime;
  }

  public void setRecordedTime(LocalDateTime recordedTime) {
	this.recordedTime = recordedTime;
  }

  public void addNode(Node node) {
	this.nodes.add(node);
  }

  public void replaceChildNode(Node existing, Node newNode) {
	nodes.remove(existing);
	nodes.add(newNode);
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

  @JsonProperty("class")
  public NodeStatus getNodeStatus() {
	return nodeStatus;
  }

  public void setNodeStatus(NodeStatus nodeStatus) {
	this.nodeStatus = nodeStatus;
  }

  public Set<Notice> getNotices() {
	return notices;
  }

  public void setNotices(Set<Notice> notices) {
	this.notices = notices;
  }

  public void setDepth(NodeDepth nodeDepth) {
	this.nodeDepth = nodeDepth;
  }

  @JsonProperty("renderer")
  public NodeDepth getNodeDepth() {
	return nodeDepth;
  }

  public Optional<Node> findChildNodeById(NodeId nodeId) {
	return nodes.stream()
	            .filter(n -> n.getId()
	                          .equals(nodeId))
	            .findFirst();
  }

  @JsonProperty("maxVolume")
  public OptionalDouble getMaxVolume() {
	return connections.stream()
	                  .map(Connection::getMetrics)
	                  .mapToDouble(Metrics::getMaxVolume)
	                  .average();
  }

  public void setNodeDepth(NodeDepth nodeDepth) {
	this.nodeDepth = nodeDepth;
  }

  public String getLayout() {
	return layout;
  }

  public void setLayout(String layout) {
	this.layout = layout;
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
