package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Arrays;
import java.util.List;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

public class NetworkTask extends CyJSONUtilTask implements ObservableTask {
	
	@Tunable
	public CyNetwork network;
	
	public NetworkTask(CyJSONUtil jsonUtil) {
		super(jsonUtil);
		
	}
	
	@ProvidesTitle
	public String getTitle() { return "JSONUtil Get Networks"; }

	
	@Override
	public void run(TaskMonitor arg0) throws Exception {

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R> R getResults(Class<? extends R> type) {
		if (type.equals(CyNetwork.class)) {
			return (R) network;
		} 
		/* This is where we return JSON from this Task. 
		 */
		else if (type.equals(CyNetworkJSONResult.class)) {
			return (R) new CyNetworkJSONResult(jsonUtil, network);
		} else if (type.equals(String.class)) {
			return (R) (new CyNetworkJSONResult(jsonUtil, network)).getJSON();
		}
		else {
			return null;
		}
	}

	@Override 
	public List<Class<?>> getResultClasses() {
		return Arrays.asList(String.class, CyNetworkJSONResult.class);
	}
}
