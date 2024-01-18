package com.github.ska.traffic.viz;

/**
 * Default is introduced as a means to indicate any new environment that might
 * be introduced into the enterprise will be treated the default way.
 */
public enum Environment {

  DEV("development"),
  TEST("test"),
  PROD("production"),
  ANY("default");

  private final String fullName;

  Environment(String fullName) {
	this.fullName = fullName;
  }

  public String getFullName() {
	return fullName;
  }

}
