package com.github.ska.traffic.viz.transform.exception;

public class NodeNotPresentException extends RuntimeException {

  private static final long serialVersionUID = -5801842259117771819L;

  public NodeNotPresentException(String message) {
    super(message);
  }

  public NodeNotPresentException(String message, Throwable cause) {
    super(message, cause);
  }

}
