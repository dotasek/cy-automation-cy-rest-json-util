package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Collection;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyIdentifiablesJSONResult extends CyJSONUtilResult implements JSONResult{

	private final Collection<? extends CyIdentifiable> result;
	
	public CyIdentifiablesJSONResult(CyJSONUtil jsonUtil, Collection<? extends CyIdentifiable> cyIdentifiables) {
		super(jsonUtil);
		this.result = cyIdentifiables;
	}
	
	@Override
	@ExampleJSONString(value="[101,102,103]") 
	public String getJSON() {
		return jsonUtil.cyIdentifiablesToJson(result);
	}
}