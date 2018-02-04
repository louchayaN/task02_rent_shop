package com.corporation.task02.dao.rw;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceNameReader {

	private static final ResourceNameReader instance = new ResourceNameReader();

	private ResourceNameReader() {
	}

	public String getResourceName(String propertyKey) throws IOException {
		
		String propertyFileName = "resources.properties";

		InputStream stream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		Properties resources = new Properties();
		
		try {
			resources.load(stream);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return resources.getProperty(propertyKey);
	}

	public static ResourceNameReader getInstance() {
		return instance;
	}

}
