package com.corporation.task02.dao.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.corporation.task02.entity.RentUnit;
import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;

public class UsersResouceParser {
	
	private static final String REG_EX = "(id=)(.+)(, name=)(.*)(, passport=)(.*)(, rented_units_id=\\[)(.*)(\\])";
	
	public static void parseLine(String resourceLine) {
		
		Shop shop = Shop.getInstance();
		User user = new User();

		Pattern pattern = Pattern.compile(REG_EX);
		Matcher matcher = pattern.matcher(resourceLine);
		matcher.find();
		user.setId(Integer.parseInt(matcher.group(2)));
		user.setName(matcher.group(4));
		user.setPassport(matcher.group(6));
		
		RentUnit rentUnits = new RentUnit();
		String[] rentedUnitsId = matcher.group(8).trim().split(" ");
		if(rentedUnitsId.length != 0) {
			for(String id: rentedUnitsId) {
				SportEquipment unit = shop.getGoods().get(Integer.valueOf(id));
				rentUnits.addRentUnit(unit);
			}
		}
		user.setRentUnits(rentUnits);
		
		shop.addUsers(Integer.valueOf(matcher.group(2)), user);			
	}

}
