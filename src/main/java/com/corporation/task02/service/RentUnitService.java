package com.corporation.task02.service;

import java.util.List;

import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.service.exception.ResourceUpdateServiceException;
import com.corporation.task02.service.exception.ShopInitializeServiceException;

public interface RentUnitService {

	void initializeShop() throws ShopInitializeServiceException;

	List<SportEquipment> find(Criteria searchCriterias);

	void rent(User user, SportEquipment sportEquipment) throws ResourceUpdateServiceException;

	void returnRentUnit(User user, SportEquipment sportEquipment) throws ResourceUpdateServiceException;

}
