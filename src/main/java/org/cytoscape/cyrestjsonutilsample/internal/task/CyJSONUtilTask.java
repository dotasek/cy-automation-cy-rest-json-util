package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.AbstractTask;

public abstract class CyJSONUtilTask extends AbstractTask {
	protected CyJSONUtil jsonUtil;
	
	public CyJSONUtilTask(CyJSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}
}
