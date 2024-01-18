package com.github.ska.traffic.viz.model.vizceral;

import java.util.HashSet;
import java.util.Set;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@AutoProperty
public class Connection {

  private NodeId source;
  private NodeId target;
  private Metadata metadata;
  private Metrics metrics = new Metrics();
  private Set<Notice> notices = new HashSet<>();

  public Connection(NodeId nodeU, NodeId nodeV) {
    this.source = nodeU;
    this.target = nodeV;
  }

  public NodeId getSource() {
    return source;
  }

  public void setSource(NodeId source) {
    this.source = source;
  }

  public NodeId getTarget() {
    return target;
  }

  public void setTarget(NodeId target) {
    this.target = target;
  }

  public Metrics getMetrics() {
    return metrics;
  }

  public void setMetrics(Metrics metrics) {
    this.metrics = metrics;
  }

  public Set<Notice> getNotices() {
    return notices;
  }

  public void setNotices(Set<Notice> notices) {
    this.notices = notices;
  }
  
  public void addNotice(Notice notice) {
    this.notices.add(notice);
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public Connection withMetrics(Metrics metrics) {
    this.setMetrics(metrics);
    return this;
  }
  
  public Connection withNotices(Set<Notice> notices) {
    this.setNotices(notices);
    return this;
  }
  
  public Connection withNotice(Notice notice) {
    this.getNotices().add(notice);
    return this;
  }

  public static Connection newStreamingConnection(NodeId nodeIdU, NodeId nodeIdV) {
    Connection connection = new Connection(nodeIdU, nodeIdV);
/*    Metadata metadata = new Metadata();
    metadata.setStreaming(1);
    connection.setMetadata(metadata);
*/    return connection;
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
