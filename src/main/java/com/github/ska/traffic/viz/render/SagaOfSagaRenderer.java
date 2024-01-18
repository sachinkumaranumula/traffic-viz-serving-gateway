package com.github.ska.traffic.viz.render;

import com.google.common.graph.MutableNetwork;
import com.google.common.graph.Network;
import com.github.ska.traffic.viz.model.vizceral.Connection;
import com.github.ska.traffic.viz.model.vizceral.Node;
import com.github.ska.traffic.viz.model.vizceral.Saga;
import com.github.ska.traffic.viz.model.vizceral.SagaOfSaga;

public interface SagaOfSagaRenderer {

  SagaOfSaga renderGlobal(String title, Network<Saga, Connection> sagaNetwork);

  Saga renderRegion(String title, MutableNetwork<Node, Connection> mutableNetwork);

}