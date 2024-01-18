package com.github.ska.traffic.viz.serving.gateway.web;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.ska.traffic.viz.serving.gateway.TrafficVizTestExecutionListener;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:unit-test.properties")
@TestExecutionListeners(mergeMode = MergeMode.MERGE_WITH_DEFAULTS,
                        listeners = { TrafficVizTestExecutionListener.class })
public class ApplicationIT {

  @LocalServerPort
  private int port;

  private URL base;

  @Autowired
  private TestRestTemplate template;

  @Before
  public void setUp() throws Exception {
	this.base = new URL("http://localhost:" + port + "/suggest/domains");
  }

  @Test
  public void getHello() throws Exception {
	ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
	assertThat(response.getBody(), equalTo("[ \"domain-1\", \"domain-2\" ]"));
  }
}