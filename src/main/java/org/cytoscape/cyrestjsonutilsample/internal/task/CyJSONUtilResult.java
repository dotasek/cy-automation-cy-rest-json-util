package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.util.json.CyJSONUtil;
import org.cytoscape.work.json.JSONResult;

public abstract class CyJSONUtilResult implements JSONResult {
	protected CyJSONUtil jsonUtil;
	
	public CyJSONUtilResult(CyJSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}
}
