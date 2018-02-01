package com.corporation.task02.entity;

import java.util.HashMap;
import java.util.Map;

public class Shop {
	
	private static final Shop instance = new Shop();
	
	private Map<Integer, SportEquipment> goods = new HashMap<>();
	private Map<Integer, User> users = new HashMap<>();

	public void addGoods(Integer equipment_id, SportEquipment equipment) {
		goods.put(equipment_id, equipment);
	}

	public Map<Integer, SportEquipment> getGoods() {
		return goods;
	}

	public void addUsers(Integer user_id, User user) {
		users.put(user_id, user);
	}

	public Map<Integer, User> getUsers() {
		return users;
	}
	
	public static Shop getInstance() {
		return instance;
	}


}
