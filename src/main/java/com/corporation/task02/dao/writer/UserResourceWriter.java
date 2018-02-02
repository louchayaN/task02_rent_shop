package com.corporation.task02.dao.writer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;

public class UserResourceWriter {

	private static final UserResourceWriter instance = new UserResourceWriter();
	
	public void updateUsersResouce(String usersResourceName) {
		
		Shop shop = Shop.getInstance();
		Map<Integer, User> users = shop.getUsers();
		Collection<User> values = users.values();
		List<String> usersDB = new ArrayList<>();
		StringBuffer rentUnitstoString = new StringBuffer("");
		
		for(User user: values) {
			List<SportEquipment> units = user.getRentUnits().getRentUnits();
			rentUnitstoString.replace(0, rentUnitstoString.length(), "");
			for (SportEquipment equipment : units) {
				equipment.getId();
				rentUnitstoString.append(equipment.getId()).append(" ");		
			}		
			
			String usersDBLine = String.format(
					"id=%s, name=%s, passport=%s, rented_units_id=[%s]", 
					user.getId(), user.getName(), user.getPassport(), rentUnitstoString);			
			usersDB.add(usersDBLine);
		}
			
		Path path = Paths.get(usersResourceName.toString());
		try {
			Files.write(path, usersDB, Charset.forName("UTF-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static UserResourceWriter getInstance() {
		return instance;
	}
	
}
