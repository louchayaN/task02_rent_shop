package com.corporation.task02.main;

import java.util.Collection;
import java.util.Map;

import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;

public class Printer {
	
	public static void print() {
		
		Shop shop = Shop.getInstance();		
		Map<Integer, SportEquipment> goods = shop.getGoods();
		Collection<SportEquipment> equipments = goods.values();
			
		for(SportEquipment equipment: equipments) {
			System.out.printf(
					"id=%s, CATEGORY=%s, TITLE=%s, PRICE=%s, AVAILABLE_QUANTITY=%s%n", 
					equipment.getId(), equipment.getCategory(), equipment.getTitle(), equipment.getPrice(), equipment.getAvailableQuantity());			
		}
		
	}
}
