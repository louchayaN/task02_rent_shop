package com.corporation.task02.dao.rw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.corporation.task02.dto.Shop;
import com.corporation.task02.entity.Category;
import com.corporation.task02.entity.RentUnit;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;

public class ShopInitializer {

	private static final String GOODS_REG_EX = "(id=)(.+)(, CATEGORY=)(.+)(, TITLE=)(.*)(, PRICE=)(.*)(, AVAILABLE_QUANTITY=)(.+)";
	private static final String USERS_REG_EX = "(id=)(.+)(, name=)(.*)(, passport=)(.*)(, rented_units_id=\\[)(.*)(\\])";
	
	public static void initializeGoods(String resourceLine) {
					
		Pattern pattern = Pattern.compile(GOODS_REG_EX);
		Matcher matcher = pattern.matcher(resourceLine);
		matcher.find();
		
		SportEquipment sportEquipment = new SportEquipment();
		sportEquipment.setId(Integer.parseInt(matcher.group(2)));
		switch(matcher.group(4)) {
			case "SKI":
				sportEquipment.setCategory(Category.SKI);
				break;
			case "SNOWBOARD":
				sportEquipment.setCategory(Category.SNOWBOARD);
				break;
		}
		sportEquipment.setTitle(matcher.group(6));
		sportEquipment.setPrice(Float.parseFloat(matcher.group(8)));
		sportEquipment.setAvailableQuantity(Integer.parseInt(matcher.group(10)));
		
		Shop shop = Shop.getInstance();
		shop.addGoods(Integer.valueOf(matcher.group(2)), sportEquipment);			
	}
	
	public static void initializeUser(String resourceLine) {
				
		Pattern pattern = Pattern.compile(USERS_REG_EX);
		Matcher matcher = pattern.matcher(resourceLine);
		matcher.find();
		
		User user = new User();
		user.setId(Integer.parseInt(matcher.group(2)));
		user.setName(matcher.group(4));
		user.setPassport(matcher.group(6));
		
		Shop shop = Shop.getInstance();
		RentUnit rentUnits = new RentUnit();
		String[] rentedUnitsId = matcher.group(8).trim().split(" ");
		if(rentedUnitsId.length != 0) {
			for(String id: rentedUnitsId) {
				SportEquipment equipment = shop.getGoods().get(Integer.valueOf(id));
				rentUnits.addRentEquipment(equipment);
			}
		}
		user.setRentUnits(rentUnits);
		
		shop.addUsers(Integer.valueOf(matcher.group(2)), user);			
	}

}
