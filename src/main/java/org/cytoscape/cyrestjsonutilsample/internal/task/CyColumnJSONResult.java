package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyColumnJSONResult extends CyJSONUtilResult implements JSONResult{

	private final CyColumn result;
	
	boolean includeDefinition;
	boolean includeValues;
	
	public CyColumnJSONResult(CyJSONUtil jsonUtil, CyColumn result, boolean includeDefinition, boolean includeValues) {
		super(jsonUtil);
		this.result = result;
		this.includeDefinition = includeDefinition;
		this.includeValues = includeValues;
	}
	
	@Override
	@ExampleJSONString(value="{}") 
	public String getJSON() {
		return jsonUtil.toJson(result, includeDefinition, includeValues);
	}
}