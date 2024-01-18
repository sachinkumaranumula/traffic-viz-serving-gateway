package com.github.ska.traffic.viz.serving.rest;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.google.common.io.Resources;
import com.github.ska.traffic.viz.Environment;

public class CannedRequestStore {

  private static final Logger LOGGER = LoggerFactory.getLogger(CannedRequestStore.class);

  private static final String CANNED_REQUESTS = "/cannedRequests/";

  public enum JsonRequestFileFactory {
	HAPROXY("haproxy",
	    "opentsdb_ha.json",
	    Environment.ANY),
	ESB1("esb1",
	    "opentsdb_esb1.json",
	    Environment.ANY),
	ESB2("esb2",
	    "opentsdb_esb2.json",
	    Environment.ANY),
	SERVICES("services",
	    "opentsdb_services.json",
	    Environment.ANY),
	JMS("jms",
	    "opentsdb_jms.json",
	    Environment.ANY),
	GEOTRACKR("geotrackr",
	    "opentsdb_geo.json",
	    Environment.ANY);

	private final String tierName;
	private final String jsonFileName;
	private final Environment env;

	private JsonRequestFileFactory(String tierName, String jsonFileName, Environment env) {
	  this.tierName = tierName;
	  this.jsonFileName = jsonFileName;
	  this.env = env;
	}

	public String jsonFileName() {
	  return jsonFileName;
	}

	public Environment env() {
	  return env;
	}

	public static JsonRequestFileFactory requestFor(String tier, Environment env) {
	  for (JsonRequestFileFactory file : values()) {
		if (file.tierName.equalsIgnoreCase(tier) && file.env == env) {
		  return file;
		}
	  }
	  throw new IllegalArgumentException("Nothing in request filestore for tier: " + tier);
	}

  }

  @Cacheable("jsonFileStore")
  public String getRequestFor(String tier, Environment env) throws IOException {
	return Resources.toString(lookUpRequestFromStore(tier, env), StandardCharsets.UTF_8);
  }

  private URL lookUpRequestFromStore(String tier, Environment env) {
	try {
	  return CannedRequestStore.class.getResource(CANNED_REQUESTS + JsonRequestFileFactory.requestFor(tier, env)
	                                                                                      .jsonFileName());
	} catch (Exception e) {
	  LOGGER.debug("Fetching default request for view");
	  return CannedRequestStore.class.getResource(CANNED_REQUESTS + JsonRequestFileFactory.requestFor(tier, Environment.ANY)
	                                                                                      .jsonFileName());
	}
  }

}
