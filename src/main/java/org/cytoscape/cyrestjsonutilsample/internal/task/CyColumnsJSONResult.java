package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Collection;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyColumnsJSONResult extends CyJSONUtilResult implements JSONResult{

	private final Collection<CyColumn> result;
	
	public CyColumnsJSONResult(CyJSONUtil jsonUtil, Collection<CyColumn> result) {
		super(jsonUtil);
		this.result = result;
	}
	
	@Override
	@ExampleJSONString(value="[\"SUID\", \"name\"]") 
	public String getJSON() {
		return jsonUtil.cyColumnsToJson(result);
	}
}