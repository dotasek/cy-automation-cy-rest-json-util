package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Arrays;
import java.util.List;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

public class ColumnTask extends CyJSONUtilTask implements ObservableTask {
	
	@Tunable
	public CyTable cyTable;
	
	@Tunable
	public String columnName;
	
	private CyColumn column;
	
	@Tunable
	public boolean includeDefinition;
	
	@Tunable 
	public boolean includeValues;
	
	public ColumnTask(CyJSONUtil jsonUtil) {
		super(jsonUtil);
	}

	
	
	@ProvidesTitle
	public String getTitle() { return "JSONUtil Get Node"; }

	
	@Override
	public void run(TaskMonitor arg0) throws Exception {
		column = cyTable.getColumn(columnName);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R> R getResults(Class<? extends R> type) {
		
		if (type.equals(CyColumn.class)) {
			return (R) column;
		} 
		/* This is where we return JSON from this Task. 
		 */
		else if (type.equals(CyColumnJSONResult.class)) {
			return (R) new CyColumnJSONResult(jsonUtil, column, includeDefinition, includeValues);
		} else if (type.equals(String.class)) {
			return (R) (new CyColumnJSONResult(jsonUtil, column, includeDefinition, includeValues).getJSON());
		}
		else {
			return null;
		}
	}

	@Override 
	public List<Class<?>> getResultClasses() {
		return Arrays.asList(String.class, CyColumnJSONResult.class);
	}
}
