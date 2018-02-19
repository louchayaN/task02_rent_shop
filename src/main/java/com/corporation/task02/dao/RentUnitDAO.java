package com.corporation.task02.dao;

import java.util.List;

import com.corporation.task02.dao.exception.ResourceUpdateDaoException;
import com.corporation.task02.dao.exception.ShopInitializeDaoException;
import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;

public interface RentUnitDAO {

	void initializeShop() throws ShopInitializeDaoException;

	List<SportEquipment> find(Criteria searchCriterias);

	void rent(User user, SportEquipment sportEquipment) throws ResourceUpdateDaoException;

	void returnRentUnit(User user, SportEquipment sportEquipment) throws ResourceUpdateDaoException;

}
