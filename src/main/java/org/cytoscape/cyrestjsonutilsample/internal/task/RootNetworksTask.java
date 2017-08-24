package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;

public class RootNetworksTask extends CyJSONUtilTask implements ObservableTask {
	
	private final CyNetworkManager networkManager;
	private final CyRootNetworkManager cyRootNetworkManager;
		
	public RootNetworksTask(CyJSONUtil jsonUtil, CyNetworkManager networkManager, CyRootNetworkManager cyRootNetworkManager) {
		super(jsonUtil);
		this.networkManager = networkManager;
		this.cyRootNetworkManager = cyRootNetworkManager;
	}

	Set<CyNetwork> rootNetworks;
	
	@ProvidesTitle
	public String getTitle() { return "JSONUtil Get Root Networks"; }

	
	@Override
	public void run(TaskMonitor arg0) throws Exception {
		rootNetworks = networkManager.getNetworkSet().stream().map(net -> cyRootNetworkManager.getRootNetwork(net))
				.collect(Collectors.toSet());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R> R getResults(Class<? extends R> type) {
		if (type.equals(Set.class)) {
			return (R) rootNetworks;
		} 
		/* This is where we return JSON from this Task. 
		 */
		else if (type.equals(CyIdentifiablesJSONResult.class)) {
			return (R) new CyIdentifiablesJSONResult(jsonUtil, (Collection<? extends CyIdentifiable>)rootNetworks);
		} else if (type.equals(String.class)) {
			return (R) (new CyIdentifiablesJSONResult(jsonUtil, (Collection<? extends CyIdentifiable>)rootNetworks).getJSON());
		}
		else {
			return null;
		}
	}

	@Override 
	public List<Class<?>> getResultClasses() {
		return Arrays.asList(String.class, CyIdentifiablesJSONResult.class);
	}
}
