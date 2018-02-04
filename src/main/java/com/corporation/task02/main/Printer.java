package com.corporation.task02.main;

import java.util.Collection;
import java.util.List;

import com.corporation.task02.dto.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;

public class Printer {
	
	public static void printAllEquipments() {
		
		Shop shop = Shop.getInstance();		
		Collection<SportEquipment> equipments = shop.getGoods().values();
		
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
		
		System.out.println("RentEquipment:");
		for(User user: users) {
			List<SportEquipment> rentEquipments = user.getRentUnits().getRentEquipments();
			StringBuffer userRents= new StringBuffer("");
			for (SportEquipment rentEquipment : rentEquipments) {
				userRents.append(rentEquipment.getCategory()).append(" ").append(rentEquipment.getTitle()).append("\n");		
			}				
			System.out.printf(
					"name: %s, passport: %s, rented units:%n%s", 
					user.getName(), user.getPassport(), userRents);						
		}		
	}
	
	
}
