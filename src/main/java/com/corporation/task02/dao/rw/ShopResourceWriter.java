package com.corporation.task02.dao.rw;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.corporation.task02.dto.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;

public class ShopResourceWriter {
	
	private static final ShopResourceWriter instance = new ShopResourceWriter();

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
	
	public void updateUsersResouce(String usersResourceName) throws IOException {
		
		Collection<User> users = Shop.getInstance().getUsers().values();
		List<String> usersDB = new ArrayList<>();
		
		StringBuffer rentUnitsId = new StringBuffer();
		for(User user: users) {		
			List<SportEquipment> units = user.getRentUnits().getRentEquipments();
			rentUnitsId.delete(0, rentUnitsId.length());
			for (SportEquipment equipment : units) {
				equipment.getId();
				rentUnitsId.append(equipment.getId()).append(" ");		
			}	
			
			String usersDBLine = String.format(
					"id=%s, name=%s, passport=%s, rented_units_id=[%s]", 
					user.getId(), user.getName(), user.getPassport(), rentUnitsId);
			
			usersDB.add(usersDBLine);
		}
			
		Path path = Paths.get(usersResourceName.toString());
		Files.write(path, usersDB, Charset.forName("UTF-8"));
		
	}
	
	public static ShopResourceWriter getInstance() {
		return instance;
	}
	
}
