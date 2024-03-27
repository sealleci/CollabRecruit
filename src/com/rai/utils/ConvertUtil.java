package com.rai.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class ConvertUtil {
	public enum CommonFormat{
		YMD("yyyy-MM-dd",0);

		private String name;
		private int index;
		
		CommonFormat(String name, int index) {
			this.name=name;
			this.index=index;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		
		 public static String getName(int index) {  
	        for (CommonFormat c : CommonFormat.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	}
	
	public static Date convertStringToDate(String dateString) {
		return Date.valueOf(LocalDate.parse(dateString));
	}
	
	public static BigDecimal convertStringToBigDecimal(String decimalString) {
		BigDecimal resultDecimal=null;
		try {
			resultDecimal=new BigDecimal(Double.parseDouble(decimalString));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		return resultDecimal;
	}
}
