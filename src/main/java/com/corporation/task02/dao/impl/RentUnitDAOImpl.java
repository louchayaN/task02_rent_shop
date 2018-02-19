 package com.corporation.task02.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.corporation.task02.dao.RentUnitDAO;
import com.corporation.task02.dao.exception.ResourceUpdateDaoException;
import com.corporation.task02.dao.exception.ShopInitializeDaoException;
import com.corporation.task02.dao.rw.ResourceReaderWriter;
import com.corporation.task02.dao.rw.ShopInitializer;
import com.corporation.task02.dto.Shop;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;

public class RentUnitDAOImpl implements RentUnitDAO {

	@Override
	public void initializeShop() throws ShopInitializeDaoException   {
			
		try {
			for(String resourceLine : ResourceReaderWriter.readGoodsResource()) {
				ShopInitializer.initializeGoods(resourceLine);
			}
				
			for(String resourceLine : ResourceReaderWriter.readUsersResource()) {
				ShopInitializer.initializeUser(resourceLine);
			}	
		} catch(IOException e) {
			throw new ShopInitializeDaoException("Resources for shop initialization haven't been read", e);
		}
			
	}

	@Override
	public List<SportEquipment> find(Criteria searchCriterias) {
		
		List<SportEquipment> foundEquipments= new ArrayList<>();
		
		Collection<SportEquipment> equipments = Shop.getInstance().getGoods().values();
		for(SportEquipment equipment: equipments) {		
			if (EquipmentParamComparator.match(equipment, searchCriterias)) {
				foundEquipments.add(equipment);
			}	
		}
		
		return foundEquipments;
	}

	@Override
	public void rent(User user, SportEquipment sportEquipment) throws ResourceUpdateDaoException {	
		try {				
			List<SportEquipment> rentEquipments = user.getRentUnits().getRentEquipments();
			rentEquipments.add(sportEquipment);
			sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() - 1);
			
			ResourceReaderWriter.rewriteGoodsResource();
			ResourceReaderWriter.rewriteUsersResource();
			
		} catch (Exception e) {
			List<SportEquipment> rentEquipments = user.getRentUnits().getRentEquipments();
			rentEquipments.remove(sportEquipment);
			sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() + 1);
			throw new ResourceUpdateDaoException("Resources haven't been updated correctly", e);		
		} 
	}

	@Override
	public void returnRentUnit(User user, SportEquipment sportEquipment) throws ResourceUpdateDaoException {

		try {
			List<SportEquipment> rentUnits = user.getRentUnits().getRentEquipments();
			rentUnits.remove(sportEquipment);
			sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() + 1);			
			
			ResourceReaderWriter.rewriteGoodsResource();
			ResourceReaderWriter.rewriteUsersResource();
			
		} catch (Exception e) {
			List<SportEquipment> rentEquipments = user.getRentUnits().getRentEquipments();
			rentEquipments.add(sportEquipment);
			sportEquipment.setAvailableQuantity(sportEquipment.getAvailableQuantity() - 1);
			throw new ResourceUpdateDaoException("Resources haven't been updated correctly", e);
		}
	}
	
	
	
}
