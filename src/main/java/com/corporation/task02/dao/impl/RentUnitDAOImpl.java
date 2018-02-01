package com.corporation.task02.dao.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.corporation.task02.dao.RentUnitDAO;
import com.corporation.task02.dao.parser.GoodsResourceParser;
import com.corporation.task02.dao.parser.ResourceNameReader;
import com.corporation.task02.dao.parser.UsersResouceParser;
import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.entity.criteria.SearchCriteria;

public class RentUnitDAOImpl implements RentUnitDAO {

	@Override
	public void initializeShop() throws IOException {
				
		ResourceNameReader resourceNameReader = ResourceNameReader.getInstance();
		String resourceName = resourceNameReader.getResourceName("goods.file.name");
		
		List<String> resourceLines = Files.readAllLines(Paths.get(resourceName), StandardCharsets.UTF_8);
		for(String resourceLine : resourceLines) {
			GoodsResourceParser.parseLine(resourceLine);
		}
		
		String resourceName2 = resourceNameReader.getResourceName("users.file.name");	
		List<String> resourceLines2 = Files.readAllLines(Paths.get(resourceName2), StandardCharsets.UTF_8);
		for(String resourceLine : resourceLines2) {
			UsersResouceParser.parseLine(resourceLine);
		}		
	}

	@Override
	public List<SportEquipment> find(Criteria searchCriterias) {
		
		List<SportEquipment> findEquipment= new ArrayList<>();
		Map<SearchCriteria, Object> criterias = searchCriterias.getCriteria();
		
		Collection<SportEquipment> goods = Shop.getInstance().getGoods().values();
		
		for(SportEquipment equipment: goods) {
			if(criterias.containsKey(SearchCriteria.CATEGORY)) {
				if(! equipment.getCategory().toString().equals(criterias.get(SearchCriteria.CATEGORY).toString())){
					continue;
				}
			}
			
			if(criterias.containsKey(SearchCriteria.TITLE)) {
				if(! equipment.getTitle().equals(criterias.get(SearchCriteria.TITLE).toString())){
					continue;
				}
			}

			if(criterias.containsKey(SearchCriteria.PRICE)) {
				if(! (criterias.get(SearchCriteria.PRICE).toString()).equals(String.valueOf(equipment.getPrice()))){
					continue;
				}
			}
			findEquipment.add(equipment);
		}

		return findEquipment;
	}

	@Override
	public void rent(User user1, SportEquipment sportEquipment) {
			
		List<SportEquipment> rentUnits = user1.getRentUnits().getRentUnits();
		rentUnits.add(sportEquipment);
		sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() - 1);
		
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
		
		ResourceNameReader resourceNameReader = ResourceNameReader.getInstance();
		String resourceName;
		try {
			resourceName = resourceNameReader.getResourceName("users.file.name");
			Path path = Paths.get(resourceName);
			Files.write(path, usersDB, Charset.forName("UTF-8"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Map<Integer, SportEquipment> goods = shop.getGoods();
		Collection<SportEquipment> equipments = goods.values();
		List<String> goodsDB = new ArrayList<>();
			
		for(SportEquipment equipment: equipments) {
			String goodsDBLine = String.format(
					"id=%s, CATEGORY=%s, TITLE=%s, PRICE=%s, AVAILABLE_QUANTITY=%s", 
					equipment.getId(), equipment.getCategory(), equipment.getTitle(), equipment.getPrice(), equipment.getAvailableQuantity());			
			goodsDB.add(goodsDBLine);
		}
		
		String resourceName2;
		try {
			resourceName2 = resourceNameReader.getResourceName("goods.file.name");
			Path path = Paths.get(resourceName2);
			Files.write(path, goodsDB, Charset.forName("UTF-8"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
