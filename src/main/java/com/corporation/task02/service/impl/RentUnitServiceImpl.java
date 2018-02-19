package com.corporation.task02.service.impl;

import java.util.List;
import java.util.Map;

import com.corporation.task02.dao.DAOFactory;
import com.corporation.task02.dao.RentUnitDAO;
import com.corporation.task02.dao.exception.ResourceUpdateDaoException;
import com.corporation.task02.dao.exception.ShopInitializeDaoException;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.service.RentUnitService;
import com.corporation.task02.service.exception.ResourceUpdateServiceException;
import com.corporation.task02.service.exception.ShopInitializeServiceException;
import com.corporation.task02.service.validator.CriteriaValidator;
import com.corporation.task02.service.validator.RentAbilityValidator;

public class RentUnitServiceImpl implements RentUnitService {

	private static final RentUnitDAO rentUnitDao = DAOFactory.getInstance().getRentUnitDao();
	
	@Override
	public void initializeShop() throws ShopInitializeServiceException{
		
		try {
			rentUnitDao.initializeShop();
		} catch (ShopInitializeDaoException e) {
			new ShopInitializeServiceException("Shop haven't been initialized", e);
		}
	}

	@Override
	public List<SportEquipment> find(Criteria searchCriterias) {
		
		Map<String, String> validationErrors = CriteriaValidator.validate(searchCriterias);
		
		if (!validationErrors.isEmpty()) {
			throw new IllegalArgumentException(validationErrors.toString());
		}		
		return rentUnitDao.find(searchCriterias);
	}

	@Override
	public void rent(User user, SportEquipment sportEquipment) throws ResourceUpdateServiceException {
		
		RentAbilityValidator.validate(user, sportEquipment);
		
		try {
			rentUnitDao.rent(user,sportEquipment);
		} catch (ResourceUpdateDaoException e) {
			throw new ResourceUpdateServiceException("Resources haven't been updated correctly", e);
		}	
	}

	@Override
	public void returnRentUnit(User user, SportEquipment sportEquipment) throws ResourceUpdateServiceException {
		
		try {
			rentUnitDao.returnRentUnit(user,sportEquipment);
		} catch (ResourceUpdateDaoException e) {
			throw new ResourceUpdateServiceException("Resources haven't been updated correctly", e);
		}	
	}
	
}
