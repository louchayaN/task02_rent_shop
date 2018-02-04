package com.corporation.task02.dao;

import com.corporation.task02.dao.impl.RentUnitDAOImpl;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();
	private final RentUnitDAO rentUnitsDao = new RentUnitDAOImpl();

	public static DAOFactory getInstance() {
		return instance;
	}

	public RentUnitDAO getRentUnitDao() {
		return rentUnitsDao;
	}

}
