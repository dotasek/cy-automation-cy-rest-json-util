package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Arrays;
import java.util.List;

import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

public class RowTask extends CyJSONUtilTask implements ObservableTask {
	
	@Tunable
	public CyTable table;
	
	public CyRow row;
	
	@Tunable
	public Long primaryKey;
	
	public RowTask(CyJSONUtil jsonUtil) {
		super(jsonUtil);
	}

	
	
	@ProvidesTitle
	public String getTitle() { return "JSONUtil Get Node"; }

	
	@Override
	public void run(TaskMonitor arg0) throws Exception {
		row = table.getRow(primaryKey);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R> R getResults(Class<? extends R> type) {
		
		if (type.equals(CyRow.class)) {
			return (R) table;
		} 
		/* This is where we return JSON from this Task. 
		 */
		else if (type.equals(CyRowJSONResult.class)) {
			return (R) new CyRowJSONResult(jsonUtil, row);
		} else if (type.equals(String.class)) {
			return (R) (new CyRowJSONResult(jsonUtil, row).getJSON());
		}
		else {
			return null;
		}
	}

	@Override 
	public List<Class<?>> getResultClasses() {
		return Arrays.asList(String.class, CyRowJSONResult.class);
	}
}
