package com.corporation.task02.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
    
	private static final String regexInt = "[0-9]+";
	private static final String regexDouble = "[0-9]+\\.[0-9]+";
	
	private NumberUtil() {
		throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static boolean isNumber(String str) {
       Pattern pattern = Pattern.compile(regexInt + "|" +regexDouble);
       Matcher matcher = pattern.matcher(str);
       return matcher.matches();
    }   
   
    public static boolean isNotNumber(String str) {
        return !isNumber(str);
    }
  
}