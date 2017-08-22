package org.cytoscape.cyrestjsonutilsample.internal.task;

import org.cytoscape.model.CyTable;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyTableJSONResult extends CyJSONUtilResult implements JSONResult{

	private final CyTable result;
	
	boolean includeDefinition;
	boolean includeRows;
	
	public CyTableJSONResult(CyJSONUtil jsonUtil, CyTable result, boolean includeDefinition, boolean includeRows) {
		super(jsonUtil);
		this.result = result;
		this.includeDefinition = includeDefinition;
		this.includeRows = includeRows;
	}
	
	@Override
	@ExampleJSONString(value="{}") 
	public String getJSON() {
		return jsonUtil.toJson(result, includeDefinition, includeRows);
	}
}