package com.github.ska.traffic.viz.serving.gateway.web;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.github.ska.traffic.viz.serving.gateway.TrafficVizTestExecutionListener;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:unit-test.properties")
@TestExecutionListeners(mergeMode = MergeMode.MERGE_WITH_DEFAULTS,
                        listeners = { TrafficVizTestExecutionListener.class })
public class ApplicationTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getHello() throws Exception {
	mvc.perform(MockMvcRequestBuilders.get("/suggest/domains")
	                                  .accept(MediaType.APPLICATION_JSON))
	   .andExpect(status().isOk())
	   .andExpect(content().string(equalTo("[ \"domain-1\", \"domain-2\" ]")));
  }
}
