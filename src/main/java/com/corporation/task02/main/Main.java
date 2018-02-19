package com.corporation.task02.main;

import java.util.List;
import java.util.Map;

import com.corporation.task02.dto.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.entity.criteria.SearchCriteria;
import com.corporation.task02.service.RentUnitService;
import com.corporation.task02.service.ServiceFactory;
import com.corporation.task02.service.exception.ResourceUpdateServiceException;
import com.corporation.task02.service.exception.ShopInitializeServiceException;

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
			if(! equipments.isEmpty()) {
				rentUnitService.rent(user, equipments.get(0));
			}
		
			rentUnitService.returnRentUnit(user, equipments.get(0));
				
			Printer.printAllEquipments();
			Printer.printRentEquipments();
				
		} catch (ShopInitializeServiceException e) {
			e.printStackTrace();
		} catch (ResourceUpdateServiceException e) {
			e.printStackTrace();
		}

	}

	
}
