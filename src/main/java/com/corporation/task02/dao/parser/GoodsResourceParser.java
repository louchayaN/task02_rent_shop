package com.corporation.task02.dao.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.corporation.task02.entity.Category;
import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;

public class GoodsResourceParser {

	private static final String REG_EX = "(id=)(.+)(, CATEGORY=)(.+)(, TITLE=)(.*)(, PRICE=)(.*)(, AVAILABLE_QUANTITY=)(.+)";
	
	public static void parseLine(String resourceLine) {
		
		Shop shop = Shop.getInstance();
		SportEquipment sportEquipment = new SportEquipment();
		
		Pattern pattern = Pattern.compile(REG_EX);
		Matcher matcher = pattern.matcher(resourceLine);
		matcher.find();
		sportEquipment.setId(Integer.parseInt(matcher.group(2)));
		switch(matcher.group(4)) {
			case "SKI":
				sportEquipment.setCategory(Category.SKI);
				break;
			case "DUMBELLS":
				sportEquipment.setCategory(Category.DUMBELLS);
				break;
		}
		sportEquipment.setTitle(matcher.group(6));
		sportEquipment.setPrice(Float.parseFloat(matcher.group(8)));
		sportEquipment.setAvailableQuantity(Integer.parseInt(matcher.group(10)));
		
		shop.addGoods(Integer.valueOf(matcher.group(2)), sportEquipment);			
	}

}
