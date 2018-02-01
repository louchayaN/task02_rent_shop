package com.corporation.task02.util;

public class StringUtil {
    
    public static final String EMPTY_STR = "";

    private StringUtil() {
    	throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static boolean isEmpty(String str) {
        if (str != null) {
            return EMPTY_STR.equals(str);
        }

        return true;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        if (isNotEmpty(str)) {
            return EMPTY_STR.equals(str.trim());
        }

        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
