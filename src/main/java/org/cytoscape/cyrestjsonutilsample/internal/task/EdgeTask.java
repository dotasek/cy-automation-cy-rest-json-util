package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Arrays;
import java.util.List;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

public class EdgeTask extends CyJSONUtilTask implements ObservableTask {
	
	@Tunable
	public CyNetwork network;
	
	@Tunable
	public Long edgeSUID;
	
	private CyEdge edge;
	
	public EdgeTask(CyJSONUtil jsonUtil) {
		super(jsonUtil);
	}

	
	
	@ProvidesTitle
	public String getTitle() { return "JSONUtil Get Node"; }

	
	@Override
	public void run(TaskMonitor arg0) throws Exception {
		edge = network.getEdge(edgeSUID);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R> R getResults(Class<? extends R> type) {
		
		if (type.equals(CyNode.class)) {
			return (R) edge;
		} 
		/* This is where we return JSON from this Task. 
		 */
		else if (type.equals(CyNodeJSONResult.class)) {
			return (R) new CyEdgeJSONResult(jsonUtil, network, edge);
		} else if (type.equals(String.class)) {
			return (R) (new CyEdgeJSONResult(jsonUtil, network, edge).getJSON());
		}
		else {
			return null;
		}
	}

	@Override 
	public List<Class<?>> getResultClasses() {
		return Arrays.asList(String.class, CyNodeJSONResult.class);
	}
}
