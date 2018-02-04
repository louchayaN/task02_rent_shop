package com.corporation.task02.entity;

import java.util.ArrayList;
import java.util.List;

public class RentUnit {

	private List<SportEquipment> rentEquipments = new ArrayList<>();

	public List<SportEquipment> getRentEquipments() {
		return rentEquipments;
	}

	public void addRentEquipment(SportEquipment sportEquipment) {
		rentEquipments.add(sportEquipment);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rentEquipments == null) ? 0 : rentEquipments.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentUnit other = (RentUnit) obj;
		if (rentEquipments == null) {
			if (other.rentEquipments != null)
				return false;
		} else if (!rentEquipments.equals(other.rentEquipments))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RentUnit [rentUnits=" + rentEquipments + "]";
	}


}
