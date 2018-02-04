package com.corporation.task02.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.corporation.task02.dao.RentUnitDAO;
import com.corporation.task02.dao.rw.ResourceReaderWriter;
import com.corporation.task02.dao.rw.ShopInitializer;
import com.corporation.task02.dto.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;

public class RentUnitDAOImpl implements RentUnitDAO {

	@Override
	public void initializeShop() throws IOException {
				
		for(String resourceLine : ResourceReaderWriter.readGoodsResource()) {
			ShopInitializer.initializeGoods(resourceLine);
		}
			
		for(String resourceLine : ResourceReaderWriter.readUsersResource()) {
			ShopInitializer.initializeUser(resourceLine);
		}		
	}

	@Override
	public List<SportEquipment> find(Criteria searchCriterias) {
				
		Collection<SportEquipment> goods = Shop.getInstance().getGoods().values();
		List<SportEquipment> foundEquipments= new ArrayList<>();
		
		for(SportEquipment equipment: goods) {		
			if (EquipmentParamComparator.match(equipment, searchCriterias)) {
				foundEquipments.add(equipment);
			}	
		}
		
		return foundEquipments;
	}

	@Override
	public void rent(User user, SportEquipment sportEquipment) {
	
		try {
			List<SportEquipment> rentEquipments = user.getRentUnits().getRentEquipments();
			rentEquipments.add(sportEquipment);
			sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() - 1);
			ResourceReaderWriter.rewriteGoodsResource();	
			ResourceReaderWriter.rewriteUsersResource();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void returnRentUnit(User user1, SportEquipment sportEquipment) {

		try {
			List<SportEquipment> rentUnits = user1.getRentUnits().getRentEquipments();
			rentUnits.remove(sportEquipment);
			sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() + 1);			
			ResourceReaderWriter.rewriteGoodsResource();	
			ResourceReaderWriter.rewriteUsersResource();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
	}
	
	
}
