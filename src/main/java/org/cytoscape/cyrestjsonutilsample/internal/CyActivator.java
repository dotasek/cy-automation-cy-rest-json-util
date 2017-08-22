package org.cytoscape.cyrestjsonutilsample.internal;


import org.osgi.framework.BundleContext;

import static org.cytoscape.work.ServiceProperties.COMMAND;
import static org.cytoscape.work.ServiceProperties.COMMAND_DESCRIPTION;
import static org.cytoscape.work.ServiceProperties.COMMAND_LONG_DESCRIPTION;
import static org.cytoscape.work.ServiceProperties.COMMAND_NAMESPACE;
import java.util.Properties;

import org.cytoscape.ci.CIErrorFactory;
import org.cytoscape.ci.CIExceptionFactory;
import org.cytoscape.cyrestjsonutilsample.internal.resource.CXResource;
import org.cytoscape.cyrestjsonutilsample.internal.resource.CXResourceImpl;
import org.cytoscape.cyrestjsonutilsample.internal.resource.JSONUtilResourceImpl;
import org.cytoscape.cyrestjsonutilsample.internal.task.NetworkTaskFactory;
import org.cytoscape.cyrestjsonutilsample.internal.task.NetworksTaskFactory;
import org.cytoscape.cyrestjsonutilsample.internal.task.NodeTaskFactory;
import org.cytoscape.cyrestjsonutilsample.internal.task.NodesTaskFactory;
import org.cytoscape.io.write.CyNetworkViewWriterFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.work.TaskFactory;

public class CyActivator extends AbstractCyActivator {
	
	public CyActivator() 
	{
		super();
	}
	
	public void start(BundleContext bc) 
	{
		CyNetworkManager networkManager = getService(bc, CyNetworkManager.class);
		CIExceptionFactory ciExceptionFactory = this.getService(bc, CIExceptionFactory.class);
		CIErrorFactory ciErrorFactory = this.getService(bc, CIErrorFactory.class);
		CyJSONUtil cyJSONUtil = this.getService(bc, CyJSONUtil.class);
		
		final ViewWriterFactoryManager viewWriterManager = new ViewWriterFactoryManager();
		registerServiceListener(bc, viewWriterManager::addFactory, viewWriterManager::removeFactory,
				CyNetworkViewWriterFactory.class);
		
		CyRootNetworkManager cyRootNetworkManager = this.getService(bc, CyRootNetworkManager.class);
		
		
		// CyREST Functions
		try {
			registerService(bc, new JSONUtilResourceImpl(ciExceptionFactory, ciErrorFactory, cyJSONUtil , networkManager), JSONUtilResourceImpl.class, new Properties());
			registerService(bc, new CXResourceImpl(ciExceptionFactory, ciErrorFactory, networkManager, cyRootNetworkManager, viewWriterManager, cyJSONUtil), CXResource.class, new Properties());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//Commands
		Properties networksTaskFactoryProperties = new Properties();
		networksTaskFactoryProperties.setProperty(COMMAND_NAMESPACE, "jsonutil");
		networksTaskFactoryProperties.setProperty(COMMAND, "networks");
		networksTaskFactoryProperties.setProperty(COMMAND_DESCRIPTION,  "return all networks");
		networksTaskFactoryProperties.setProperty(COMMAND_LONG_DESCRIPTION, "return all networks");

		TaskFactory networksTaskFactory = new NetworksTaskFactory(cyJSONUtil, networkManager);
		registerAllServices(bc, networksTaskFactory, networksTaskFactoryProperties);
		
		Properties networkTaskFactoryProperties = new Properties();
		networkTaskFactoryProperties.setProperty(COMMAND_NAMESPACE, "jsonutil");
		networkTaskFactoryProperties.setProperty(COMMAND, "network");
		networkTaskFactoryProperties.setProperty(COMMAND_DESCRIPTION,  "return a network");
		networkTaskFactoryProperties.setProperty(COMMAND_LONG_DESCRIPTION, "return a network");

		TaskFactory networkTaskFactory = new NetworkTaskFactory(cyJSONUtil);
		registerAllServices(bc, networkTaskFactory, networkTaskFactoryProperties);
		
		Properties nodesTaskFactoryProperties = new Properties();
		nodesTaskFactoryProperties.setProperty(COMMAND_NAMESPACE, "jsonutil");
		nodesTaskFactoryProperties.setProperty(COMMAND, "nodes");
		nodesTaskFactoryProperties.setProperty(COMMAND_DESCRIPTION,  "return all the nodes in a network");
		nodesTaskFactoryProperties.setProperty(COMMAND_LONG_DESCRIPTION, "return all the nodes in a network");

		TaskFactory nodesTaskFactory = new NodesTaskFactory(cyJSONUtil);
		registerAllServices(bc, nodesTaskFactory, nodesTaskFactoryProperties);
		
		Properties nodeTaskFactoryProperties = new Properties();
		nodeTaskFactoryProperties.setProperty(COMMAND_NAMESPACE, "jsonutil");
		nodeTaskFactoryProperties.setProperty(COMMAND, "node");
		nodeTaskFactoryProperties.setProperty(COMMAND_DESCRIPTION,  "return a node");
		nodeTaskFactoryProperties.setProperty(COMMAND_LONG_DESCRIPTION, "return a node");

		TaskFactory nodeTaskFactory = new NodeTaskFactory(cyJSONUtil);
		registerAllServices(bc, nodeTaskFactory, nodeTaskFactoryProperties);
	}
}

