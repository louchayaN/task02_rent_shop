package com.corporation.task02.dao.rw;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ResourceReaderWriter {

	private static final StringBuilder goodsResourceName = new StringBuilder("");
	private static final StringBuilder usersResourceName = new StringBuilder("");;
	
	static {
		ResourceNameReader resourceNameReader = ResourceNameReader.getInstance();
		try {
			goodsResourceName.append(resourceNameReader.getResourceName("goods.file.name"));
			usersResourceName.append(resourceNameReader.getResourceName("users.file.name"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
	public static List<String> readGoodsResource() throws IOException{
		
		List<String> resourceLines = Files.readAllLines(Paths.get(goodsResourceName.toString()), StandardCharsets.UTF_8);	
		return resourceLines;		
	}
	
	public static List<String> readUsersResource() throws IOException{
		
		List<String> resourceLines = Files.readAllLines(Paths.get(usersResourceName.toString()), StandardCharsets.UTF_8);	
		return resourceLines;		
	}
	
	public static void rewriteGoodsResource() throws IOException {
		
		ShopResourceWriter shopResourceWriter = ShopResourceWriter.getInstance();
		shopResourceWriter.updateGoodsResouce(goodsResourceName.toString());		
	}
	
	public static void rewriteUsersResource() throws IOException {
			
		ShopResourceWriter shopResourceWriter = ShopResourceWriter.getInstance();
		shopResourceWriter.updateUsersResouce(usersResourceName.toString());
	}
	
}
