package com.corporation.task02.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.corporation.task02.dao.DAOFactory;
import com.corporation.task02.dao.RentUnitDAO;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.service.RentUnitService;
import com.corporation.task02.service.validator.CriteriaValidator;
import com.corporation.task02.service.validator.RentAbilityValidator;

public class RentUnitServiceImpl implements RentUnitService {

	private static final RentUnitDAO rentUnitDao = DAOFactory.getInstance().getRentUnitDao();
	
	@Override
	public void initializeShop() throws IOException {

		rentUnitDao.initializeShop();
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
	public void rent(User user, SportEquipment sportEquipment) {
		
		RentAbilityValidator.validate(user, sportEquipment);
		rentUnitDao.rent(user,sportEquipment);	
	}

	@Override
	public void returnRentUnit(User user, SportEquipment sportEquipment) {
		
		rentUnitDao.returnRentUnit(user,sportEquipment);	
	}
	
}
