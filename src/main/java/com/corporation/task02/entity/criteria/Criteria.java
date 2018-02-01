package com.corporation.task02.entity.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria {

	private Map<SearchCriteria, Object> criteria = new HashMap<>();

	public Map<SearchCriteria, Object> getCriteria() {
		return criteria;
	}

	public void add(SearchCriteria searchCriteria, Object value) {
		criteria.put(searchCriteria, value);
	}

}
