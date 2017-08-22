package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

public class ColumnsTask extends CyJSONUtilTask implements ObservableTask {
	
	@Tunable
	public CyTable table;
	
	private Collection<CyColumn> cyColumns;
	
	public ColumnsTask(CyJSONUtil jsonUtil) {
		super(jsonUtil);
	}

	
	
	@ProvidesTitle
	public String getTitle() { return "JSONUtil Get Node"; }

	
	@Override
	public void run(TaskMonitor arg0) throws Exception {
		cyColumns = table.getColumns();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R> R getResults(Class<? extends R> type) {
		
		if (type.equals(CyTable.class)) {
			return (R) table;
		} 
		/* This is where we return JSON from this Task. 
		 */
		else if (type.equals(CyColumnsJSONResult.class)) {
			return (R) new CyColumnsJSONResult(jsonUtil, cyColumns);
		} else if (type.equals(String.class)) {
			return (R) (new CyColumnsJSONResult(jsonUtil, cyColumns).getJSON());
		}
		else {
			return null;
		}
	}

	@Override 
	public List<Class<?>> getResultClasses() {
		return Arrays.asList(String.class, CyColumnsJSONResult.class);
	}
}
