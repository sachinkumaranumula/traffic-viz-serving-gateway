package com.github.ska.traffic.viz.web.declarative.opentsdb;

import static org.fest.assertions.Assertions.assertThat;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.Test;

import com.github.ska.traffic.viz.web.declarative.opentsdb.Result;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public final class ResultTest {

  @Test
  public void test_diffAggreagation_whenCounterIsResetInTheSeriesThenMaxAndMinAreDiffed() {
	Result result = new Result();
	result.setDps(ImmutableMap.<String, BigInteger>builder()
	                          .put("1543590484", BigInteger.valueOf(21089))
	                          .put("1543591682", BigInteger.valueOf(234888))
	                          .put("1543591741", BigInteger.valueOf(3841))
	                          .put("1543592703", BigInteger.valueOf(252750))
	                          .put("1543592760", BigInteger.valueOf(5349))
	                          .put("1543592824", BigInteger.valueOf(26258))
	                          .put("1543592884", BigInteger.valueOf(10058))
	                          .put("1543592945", BigInteger.valueOf(8887))
	                          .put("1543593002", BigInteger.valueOf(4892))
	                          .put("1543593064", BigInteger.valueOf(681))
	                          .put("1543593123", BigInteger.valueOf(19232))
	                          .put("1543593182", BigInteger.valueOf(30635))
	                          .put("1543593242", BigInteger.valueOf(7345))
	                          .put("1543593302", BigInteger.valueOf(12742))
	                          .put("1543593361", BigInteger.valueOf(11485))
	                          .put("1543593424", BigInteger.valueOf(3643))
	                          .put("1543593481", BigInteger.valueOf(14272))
	                          .put("1543593542", BigInteger.valueOf(24523))
	                          .put("1543593600", BigInteger.valueOf(606))
	                          .put("1543593661", BigInteger.valueOf(17951))
	                          .put("1543593721", BigInteger.valueOf(13274))
	                          .put("1543593785", BigInteger.valueOf(3549))
	                          .put("1543593843", BigInteger.valueOf(354))
	                          .put("1543593902", BigInteger.valueOf(1581))
	                          .put("1543593962", BigInteger.valueOf(2))
	                          .put("1543594022", BigInteger.valueOf(734))
	                          .build());
	Optional<BigInteger> diffAggregation = result.diffAggregation();
	assertThat(diffAggregation.isPresent()).isTrue();
	assertThat(diffAggregation.get()
	                          .signum()).isEqualTo(1);
	assertThat(diffAggregation.get()
	                          .longValue()).isEqualTo(252748);
  }

  @Test
  public void test_diffAggreagation_whenDpsIsNull_diffIsAbsent() {
	Result result = new Result();
	assertThat(result.getDps()).isNull();
	Optional<BigInteger> diffAggregation = result.diffAggregation();
	assertThat(diffAggregation.isPresent()).isFalse();
  }

  @Test
  public void test_diffAggreagation_whenDpsIsEmpty_diffIsAbsent() {
	Result result = new Result();
	result.setDps(Maps.newIdentityHashMap());
	assertThat(result.getDps()).isEmpty();
	Optional<BigInteger> diffAggregation = result.diffAggregation();
	assertThat(diffAggregation.isPresent()).isTrue();
	assertThat(diffAggregation.get()
	                          .longValue()).isEqualTo(0);

  }

  @Test
  public void test_diffAggreagation_whenCounterIsCorrectInTheSeriesThenLastAndFirstAreDiffed() {
	Result result = new Result();
	result.setDps(ImmutableMap.<String, BigInteger>builder()
	                          .put("1543590482", BigInteger.valueOf(423333559))
	                          .put("1543590543", BigInteger.valueOf(423369794))
	                          .put("1543590601", BigInteger.valueOf(423405076))
	                          .put("1543590664", BigInteger.valueOf(423440679))
	                          .put("1543590720", BigInteger.valueOf(423467962))
	                          .put("1543590784", BigInteger.valueOf(423514125))
	                          .build());
	Optional<BigInteger> diffAggregation = result.diffAggregation();
	assertThat(diffAggregation.isPresent()).isTrue();
	assertThat(diffAggregation.get()
	                          .signum()).isEqualTo(1);
	assertThat(diffAggregation.get()
	                          .longValue()).isEqualTo(180566);
  }

  @Test
  public void test_diffAggreagation_whenCounterHasNegativeNumbersInTheSeriesThenItIsFilteredAndThenLastAndFirstAreDiffed() {
	Result result = new Result();
	result.setDps(ImmutableMap.<String, BigInteger>builder()
	                          .put("1543590482", BigInteger.valueOf(423333559))
	                          .put("1543590543", BigInteger.valueOf(423369794))
	                          .put("1543590601", BigInteger.valueOf(423405076))
	                          .put("1543590664", BigInteger.valueOf(423440679))
	                          .put("1543590720", BigInteger.valueOf(423467962))
	                          .put("1543590784", BigInteger.valueOf(-423514125))
	                          .build());
	Optional<BigInteger> diffAggregation = result.diffAggregation();
	assertThat(diffAggregation.isPresent()).isTrue();
	assertThat(diffAggregation.get()
	                          .signum()).isEqualTo(1);
	assertThat(diffAggregation.get()
	                          .longValue()).isEqualTo(134403);
  }

}
