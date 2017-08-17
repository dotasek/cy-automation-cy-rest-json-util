package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.AbstractTaskFactory;

public abstract class CyJSONUtilTaskFactory extends AbstractTaskFactory{
	protected CyJSONUtil jsonUtil;
	
	public CyJSONUtilTaskFactory(CyJSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}
}
