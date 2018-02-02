package com.corporation.task02.service;

import java.io.IOException;
import java.util.List;

import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;
import com.corporation.task02.entity.criteria.Criteria;

public interface RentUnitService {

	void initializeShop() throws IOException;

	List<SportEquipment> find(Criteria searchCriterias);

	void rent(User user, SportEquipment sportEquipment);

	void returnRentUnit(User user, SportEquipment sportEquipment);

}
