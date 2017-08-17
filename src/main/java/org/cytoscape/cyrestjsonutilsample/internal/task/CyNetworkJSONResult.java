package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyNetworkJSONResult extends CyJSONUtilResult implements JSONResult{

	private final CyNetwork result;
	
	public CyNetworkJSONResult(CyJSONUtil jsonUtil, CyNetwork result) {
		super(jsonUtil);
		this.result = result;
	}
	
	@Override
	@ExampleJSONString(value="{\"SUID\":\"101\"}") 
	public String getJSON() {
		return jsonUtil.toJson(result);
	}
}