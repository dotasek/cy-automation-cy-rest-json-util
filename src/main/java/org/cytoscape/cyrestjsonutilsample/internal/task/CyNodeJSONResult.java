package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyNodeJSONResult extends CyJSONUtilResult implements JSONResult{

	private final CyNetwork network;
	
	private final CyNode result;
	
	public CyNodeJSONResult(CyJSONUtil jsonUtil, CyNetwork network, CyNode result) {
		super(jsonUtil);
		this.network = network;
		this.result = result;
	}
	
	@Override
	@ExampleJSONString(value="{\"SUID\":\"101\"}") 
	public String getJSON() {
		return jsonUtil.toJson(network, result);
	}
}