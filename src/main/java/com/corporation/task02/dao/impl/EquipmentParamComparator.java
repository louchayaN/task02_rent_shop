package com.corporation.task02.dao.impl;

import java.util.Map;

import com.corporation.task02.entity.SportEquipment;
import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.entity.criteria.SearchCriteria;

public class EquipmentParamComparator {

	public static boolean match(SportEquipment equipment, Criteria searchCriterias) {
		
		Map<SearchCriteria, Object> criterias = searchCriterias.getCriteria();
		
		if(criterias.containsKey(SearchCriteria.CATEGORY)) {
			if(! equipment.getCategory().toString().equals(criterias.get(SearchCriteria.CATEGORY).toString())){
				return false;
			}
		}
		
		if(criterias.containsKey(SearchCriteria.TITLE)) {
			if(! equipment.getTitle().equals(criterias.get(SearchCriteria.TITLE).toString())){
				return false;
			}
		}

		if(criterias.containsKey(SearchCriteria.PRICE)) {
			if(! (criterias.get(SearchCriteria.PRICE).toString()).equals(String.valueOf(equipment.getPrice()))){
				return false;
			}
		}
		
		return true;
		
	}
	
}
