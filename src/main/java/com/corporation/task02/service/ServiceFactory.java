package com.corporation.task02.service;

import com.corporation.task02.service.impl.RentUnitServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	private final RentUnitService rentUnitService = new RentUnitServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public RentUnitService getRentUnitService() {
		return rentUnitService;
	}

}
