package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.CyRow;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyRowJSONResult extends CyJSONUtilResult implements JSONResult{

	private final CyRow result;
	
	public CyRowJSONResult(CyJSONUtil jsonUtil, CyRow result) {
		super(jsonUtil);
		this.result = result;
	}
	
	@Override
	@ExampleJSONString(value="{\"SUID\":\"101\"}") 
	public String getJSON() {
		return jsonUtil.toJson(result);
	}
}