package com.github.ska.traffic.viz.render;

import org.springframework.stereotype.Component;

import com.google.common.graph.MutableNetwork;
import com.google.common.graph.Network;
import com.github.ska.traffic.viz.model.vizceral.Connection;
import com.github.ska.traffic.viz.model.vizceral.Node;
import com.github.ska.traffic.viz.model.vizceral.NodeDepth;
import com.github.ska.traffic.viz.model.vizceral.NodeId;
import com.github.ska.traffic.viz.model.vizceral.Saga;
import com.github.ska.traffic.viz.model.vizceral.SagaOfSaga;

@Component
public class DefaultSagaOfSagaRenderer implements SagaOfSagaRenderer {

  @Override
  public SagaOfSaga renderGlobal(String title, Network<Saga, Connection> sagaNetwork) {
	SagaOfSaga sos = new SagaOfSaga();
	sos.setName(new NodeId(title));
	sos.setDepth(NodeDepth.GLOBAL);
	sos.setSagas(sagaNetwork.nodes());
	sos.setConnections(sagaNetwork.edges());
	return sos;
  }

  @Override
  public Saga renderRegion(String title, MutableNetwork<Node, Connection> mutableNetwork) {
	Saga saga = new Saga();
	saga.setDepth(NodeDepth.REGION);
	saga.setName(new NodeId(title));
	saga.setNodes(mutableNetwork.nodes());
	saga.setConnections(mutableNetwork.edges());
	return saga;
  }

}
