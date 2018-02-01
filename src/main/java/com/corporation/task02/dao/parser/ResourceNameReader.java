package com.corporation.task02.dao.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceNameReader {

	private static final ResourceNameReader instance = new ResourceNameReader();

	private ResourceNameReader() {
	}

	public String getResourceName(String propertyfilekey) throws IOException {
		String propertyFile = "resources.properties";
		String resourceName;

		InputStream stream = ResourceNameReader.class.getClassLoader().getResourceAsStream(propertyFile);
		Properties resourcesInPropertyFile = new Properties();

		try {
			resourcesInPropertyFile.load(stream);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		resourceName = resourcesInPropertyFile.getProperty(propertyfilekey);
		return resourceName;
	}

	public static ResourceNameReader getInstance() {
		return instance;
	}

}
