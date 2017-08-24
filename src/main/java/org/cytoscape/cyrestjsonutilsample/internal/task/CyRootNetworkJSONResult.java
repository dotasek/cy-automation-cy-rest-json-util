package org.cytoscape.cyrestjsonutilsample.internal.task;

import java.io.ByteArrayOutputStream;

import org.cytoscape.cyrestjsonutilsample.internal.ViewWriterFactoryManager;
import org.cytoscape.io.write.CyNetworkViewWriterFactory;
import org.cytoscape.io.write.CyWriter;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.work.json.ExampleJSONString;
import org.cytoscape.work.json.JSONResult;

public class CyRootNetworkJSONResult  implements JSONResult{

	private final CyRootNetwork result;
	private final ViewWriterFactoryManager viewWriterFactoryManager;
	
	public CyRootNetworkJSONResult(CyRootNetwork result, ViewWriterFactoryManager viewWriterFactoryManager) {
		this.result = result;
		this.viewWriterFactoryManager = viewWriterFactoryManager;
	}
	
	@Override
	@ExampleJSONString(value="{}") 
	public String getJSON() {
		final CyNetworkViewWriterFactory cxWriterFactory = viewWriterFactoryManager.getCxFactory();
		
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		CyWriter writer = cxWriterFactory.createWriter(stream, result.getSubNetworkList().get(0));
		String jsonString = null;
		try {
			writer.run(null);
			jsonString = stream.toString("UTF-8");
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}