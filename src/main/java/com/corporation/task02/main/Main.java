package com.corporation.task02.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.entity.criteria.SearchCriteria;
import com.corporation.task02.service.RentUnitService;
import com.corporation.task02.service.ServiceFactory;

public class Main {

	public static void main(String[] args) {

		ServiceFactory factory = ServiceFactory.getInstance();
		RentUnitService rentUnitService = factory.getRentUnitService();
		
		try {
			rentUnitService.initializeShop();
		
			Shop shop = Shop.getInstance();
			Map<Integer, User> users = shop.getUsers();
			
			User user = users.get(1);
			Criteria searchCriterias = new Criteria();
			searchCriterias.add(SearchCriteria.CATEGORY, "SKI");
			searchCriterias.add(SearchCriteria.PRICE, 15.0);
			
			List<SportEquipment> equipments = rentUnitService.find(searchCriterias);
			if(equipments.isEmpty()) {
				System.out.println("Such goods are not found");
			} else {
				rentUnitService.rent(user, equipments.get(0));
			}
			
			Printer.print();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		
		
		// 
		// RentUnit rentUnits = userService.findRentUnits(user1);
		// PrintRentUnit.print(rentUnits);

	}

}
