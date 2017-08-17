package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.TaskIterator;

public class NodesTaskFactory extends CyJSONUtilTaskFactory{

	public NodesTaskFactory(CyJSONUtil cyJSONUtil) {
		super(cyJSONUtil);
	}
	
	public boolean isReady() {
		return true;
	}
	
	public TaskIterator createTaskIterator() {
		return new TaskIterator(new NodesTask(jsonUtil));
	}
}
