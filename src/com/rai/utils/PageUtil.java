package com.rai.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PageUtil {
	private static Properties prop = new Properties();
	
	static {
		try {
			InputStream is=PageUtil.class.getClassLoader().getResourceAsStream("pageInfo.properties");
			if(is!=null) {
				prop.load(is);
			}else {
				System.out.println("cannot find file pageInfo.properties");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int pageSize = Integer.parseInt(prop.getProperty("pageSize", "20"));
}