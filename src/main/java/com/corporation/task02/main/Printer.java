package com.corporation.task02.main;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;

public class Printer {
	
	public static void printEquipments() {
		
		Shop shop = Shop.getInstance();		
		Map<Integer, SportEquipment> goods = shop.getGoods();
		Collection<SportEquipment> equipments = goods.values();
		
		System.out.println("SportEquipments:");
		for(SportEquipment equipment: equipments) {
			System.out.printf(
					"Category=%s, Title=%s, Price=%s, Availble quantity=%s%n", 
					equipment.getCategory(), equipment.getTitle(), equipment.getPrice(), equipment.getAvailableQuantity());			
		}
		
	}
	
	public static void printRentEquipments() {
		
		Shop shop = Shop.getInstance();		
		Collection<User> users = shop.getUsers().values();
		
		System.out.println("Users:");
		for(User user: users) {
			List<SportEquipment> rentEquipments = user.getRentUnits().getRentUnits();
			StringBuffer buffer = new StringBuffer("");
			for (SportEquipment rentEquipment : rentEquipments) {
				buffer.append(rentEquipment.getCategory()).append(" ").append(rentEquipment.getTitle()).append("\n");		
			}				
			System.out.printf(
					"Name: %s, passport: %s. Rented units:%n%s%n", 
					user.getName(), user.getPassport(), buffer);						
		}		
	}
	
	
}
