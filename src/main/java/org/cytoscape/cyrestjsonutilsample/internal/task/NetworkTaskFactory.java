package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class NetworkTaskFactory extends CyJSONUtilTaskFactory{

	public NetworkTaskFactory(CyJSONUtil cyJSONUtil) {
		super(cyJSONUtil);
		
	}
	
	public boolean isReady() {
		return true;
	}
	
	public TaskIterator createTaskIterator() {
		return new TaskIterator(new NetworkTask(jsonUtil));
	}
}
