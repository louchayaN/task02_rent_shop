package com.corporation.task02.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {

	private MapUtil() {
		throw new AssertionError("Class contains static methods only. You should not instantiate it!");
	}

	public static <T, V> Map<String, String> transformMapEntryToString(Map<T, V> mapOfAnyType) {

		Map<String, String> MapEntryToString = new HashMap<String, String>();
		for (Entry<T, V> entry : mapOfAnyType.entrySet()) {
			MapEntryToString.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return MapEntryToString;
	}
}
