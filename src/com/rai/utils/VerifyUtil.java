package com.rai.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {
	private static final String regexOfEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	public static boolean isEmailCorrect(String email) {
		if(email==null||email.equals("")) {
			return false;
		}else {
			Pattern pattern=Pattern.compile(regexOfEmail);
			Matcher matcher=pattern.matcher(email);
			if(matcher.matches()) {
				return true;
			}else {
				return false;
			}
		}
	}
}
