package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Arrays;
import java.util.List;
import org.cytoscape.cyrestjsonutilsample.internal.ViewWriterFactoryManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

public class RootNetworkTask extends AbstractTask implements ObservableTask {
	
	private final CyNetworkManager networkManager;
	private final CyRootNetworkManager cyRootNetworkManager;
	private final ViewWriterFactoryManager viewWriterFactoryManager;
		
	public RootNetworkTask(CyNetworkManager networkManager, CyRootNetworkManager cyRootNetworkManager, ViewWriterFactoryManager viewWriterFactoryManager) {
		this.networkManager = networkManager;
		this.cyRootNetworkManager = cyRootNetworkManager;
		this.viewWriterFactoryManager = viewWriterFactoryManager;
	}

	@Tunable
	public Long rootNetworkSUID;
	
	CyRootNetwork rootNetwork;
	
	@ProvidesTitle
	public String getTitle() { return "JSONUtil Get Root Networks"; }

	
	@Override
	public void run(TaskMonitor arg0) throws Exception {
	
		for (CyNetwork network : networkManager.getNetworkSet()) {
			CyRootNetwork currentRootNetwork = cyRootNetworkManager.getRootNetwork(network);
			if (currentRootNetwork != null && currentRootNetwork.getSUID() == rootNetworkSUID) {
				rootNetwork = currentRootNetwork;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R> R getResults(Class<? extends R> type) {
		if (type.equals(CyRootNetwork.class)) {
			return (R) rootNetwork;
		} 
		/* This is where we return JSON from this Task. 
		 */
		else if (type.equals(CyRootNetworkJSONResult.class)) {
			return (R) new CyRootNetworkJSONResult(rootNetwork, viewWriterFactoryManager);
		} else if (type.equals(String.class)) {
			return (R) (new CyRootNetworkJSONResult(rootNetwork, viewWriterFactoryManager).getJSON());
		}
		else {
			return null;
		}
	}

	@Override 
	public List<Class<?>> getResultClasses() {
		return Arrays.asList(String.class, CyRootNetworkJSONResult.class);
	}
}
