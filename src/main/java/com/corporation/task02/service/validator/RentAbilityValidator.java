package com.corporation.task02.service.validator;

import java.util.List;

import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.User;

public class RentAbilityValidator {

	public static void validate(User user, SportEquipment sportEquipment) {
		
		List<SportEquipment> rentUnits = user.getRentUnits().getRentEquipments();
		if(rentUnits.size() == 3) {
			throw new RuntimeException("You can't rent more than three options!");
		}
		
		if(sportEquipment.getAvailableQuantity() == 0) {
			throw new RuntimeException("The equipment is not in the shop now!");
		}
		
	}

}
