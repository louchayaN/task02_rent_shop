package com.corporation.task02.entity;

import java.util.ArrayList;
import java.util.List;

public class RentUnit {

	private List<SportEquipment> rentUnits = new ArrayList<>();

	public List<SportEquipment> getRentUnits() {
		return rentUnits;
	}

	public void addRentUnit(SportEquipment sportEquipment) {
		rentUnits.add(sportEquipment);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	
}
