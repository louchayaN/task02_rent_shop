package com.corporation.task02.dao.writer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;

public class GoodsResourceWriter {
	
	private static final GoodsResourceWriter instance = new GoodsResourceWriter();

	public void updateGoodsResouce(String goodsResourceName) throws IOException {	
	
		Shop shop = Shop.getInstance();
		Collection<SportEquipment> equipments = shop.getGoods().values();
		List<String> goodsDB = new ArrayList<>();
			
		for(SportEquipment equipment: equipments) {
			String goodsDBLine = String.format(
					"id=%s, CATEGORY=%s, TITLE=%s, PRICE=%s, AVAILABLE_QUANTITY=%s", 
					equipment.getId(), equipment.getCategory(), equipment.getTitle(), equipment.getPrice(), equipment.getAvailableQuantity());			
			goodsDB.add(goodsDBLine);
		}
		
		Path path = Paths.get(goodsResourceName);
		Files.write(path, goodsDB, Charset.forName("UTF-8"));
	}
	
	public static GoodsResourceWriter getInstance() {
		return instance;
	}
	
}
