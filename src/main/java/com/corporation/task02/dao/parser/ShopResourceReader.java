package com.corporation.task02.dao.parser;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShopResourceReader implements Closeable {

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	
	public String readLine() throws IOException {
		ResourceNameReader resourceNameReader = ResourceNameReader.getInstance();
		String resourceName = resourceNameReader.getResourceName("goods.file.name");	
		InputStream stream = ShopResourceReader.class.getClassLoader().getResourceAsStream(resourceName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		return reader.readLine();
	}
	
//	private String resourceName;
//	
//	private static final BufferedReader reader;
//
//	static {		
//		InputStream stream = ShopResourceReader.class.getClassLoader().getResourceAsStream(resourceName);
//		reader = new BufferedReader(new InputStreamReader(stream));
//	}
//
//	public ShopResourceReader(String resourceName) {
//		this.resourceName=resourceName;
//	}
//
//	public String readLine() throws IOException {
//		return reader.readLine();
//	}
//
//	@Override
//	public void close() {
//		try {
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
