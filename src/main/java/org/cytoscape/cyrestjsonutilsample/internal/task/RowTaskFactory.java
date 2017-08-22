package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.TaskIterator;

public class RowTaskFactory extends CyJSONUtilTaskFactory{

	public RowTaskFactory(CyJSONUtil cyJSONUtil) {
		super(cyJSONUtil);
	}
	
	public boolean isReady() {
		return true;
	}
	
	public TaskIterator createTaskIterator() {
		return new TaskIterator(new RowTask(jsonUtil));
	}
}
