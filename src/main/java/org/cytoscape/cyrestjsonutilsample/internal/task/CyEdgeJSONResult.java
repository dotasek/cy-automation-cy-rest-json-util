package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyEdgeJSONResult extends CyJSONUtilResult implements JSONResult{

	private final CyNetwork network;
	
	private final CyEdge result;
	
	public CyEdgeJSONResult(CyJSONUtil jsonUtil, CyNetwork network, CyEdge result) {
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