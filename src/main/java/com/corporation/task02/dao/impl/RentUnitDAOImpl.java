package com.corporation.task02.dao.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.corporation.task02.dao.RentUnitDAO;
import com.corporation.task02.dao.reader.GoodsResourceParser;
import com.corporation.task02.dao.reader.ResourceNameReader;
import com.corporation.task02.dao.reader.UsersResouceParser;
import com.corporation.task02.dao.writer.GoodsResourceWriter;
import com.corporation.task02.dao.writer.UserResourceWriter;
import com.corporation.task02.entity.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.entity.criteria.SearchCriteria;

public class RentUnitDAOImpl implements RentUnitDAO {

	private static final StringBuilder goodsResourceName = new StringBuilder("");
	private static final StringBuilder usersResourceName = new StringBuilder("");;
	
	static {
		ResourceNameReader resourceNameReader = ResourceNameReader.getInstance();
		try {
			goodsResourceName.append(resourceNameReader.getResourceName("goods.file.name"));
			usersResourceName.append(resourceNameReader.getResourceName("users.file.name"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
	@Override
	public void initializeShop() throws IOException {
				
		List<String> resourceLines = Files.readAllLines(Paths.get(goodsResourceName.toString()), StandardCharsets.UTF_8);
		for(String resourceLine : resourceLines) {
			GoodsResourceParser.parseLine(resourceLine);
		}
			
		List<String> resourceLines2 = Files.readAllLines(Paths.get(usersResourceName.toString()), StandardCharsets.UTF_8);
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
	public void rent(User user, SportEquipment sportEquipment) {
	
		try {
			List<SportEquipment> rentUnits = user.getRentUnits().getRentUnits();
			rentUnits.add(sportEquipment);
			sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() - 1);
			
			updateResources();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void returnRentUnit(User user1, SportEquipment sportEquipment) {

		try {
			List<SportEquipment> rentUnits = user1.getRentUnits().getRentUnits();
			rentUnits.remove(sportEquipment);
			sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() + 1);
			
			updateResources();			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
	}
	
	
	private static void updateResources() throws IOException {
		
		GoodsResourceWriter goodsResourceWriter = GoodsResourceWriter.getInstance();
		goodsResourceWriter.updateGoodsResouce(goodsResourceName.toString());
		
		UserResourceWriter usersResourceWriter = UserResourceWriter.getInstance();
		usersResourceWriter.updateUsersResouce(usersResourceName.toString());
	}
	
}
