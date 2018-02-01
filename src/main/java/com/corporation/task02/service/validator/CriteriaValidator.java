package com.corporation.task02.service.validator;

import java.util.HashMap;
import java.util.Map;

import com.corporation.task02.entity.criteria.Criteria;
import com.corporation.task02.entity.criteria.SearchCriteria;
import com.corporation.task02.util.NumberUtil;

public class CriteriaValidator {
	
	private static final String IS_NOT_NUMBER = "should be number"; 
	
	public static Map<String, String> validate(Criteria searchCriterias) {
		
		Map<SearchCriteria, Object> criterias = searchCriterias.getCriteria();
		
		Map<String, String> validationErrors = new HashMap<>();
		if(criterias.containsKey(SearchCriteria.PRICE)) {
			if(NumberUtil.isNotNumber(criterias.get(SearchCriteria.PRICE).toString())) {
				validationErrors.put(SearchCriteria.PRICE.toString(), IS_NOT_NUMBER);
			}
		}
		
		return validationErrors;
		
		
	}
}
