package org.cytoscape.cyrestjsonutilsample.internal;


import org.osgi.framework.BundleContext;
import java.util.Properties;

import org.cytoscape.ci.CIErrorFactory;
import org.cytoscape.ci.CIExceptionFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.json.CyJSONUtil;
import org.cytoscape.service.util.AbstractCyActivator;

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
		
		System.out.println("CyREST JSONUtil Sample start cyJSONUtil found: " + (cyJSONUtil != null));
		try {
			registerService(bc, new JSONUtilResourceImpl(ciExceptionFactory, ciErrorFactory, cyJSONUtil , networkManager), JSONUtilResourceImpl.class, new Properties());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("CyREST JSONUtil Sample registerService complete");
	}
}

