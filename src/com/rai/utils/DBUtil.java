package com.rai.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBUtil {
	private static Properties prop = new Properties();
	
	static {
		try {
			InputStream is=PageUtil.class.getClassLoader().getResourceAsStream("dbConfig.properties");
			if(is!=null) {
				prop.load(is);
			}else {
				System.out.println("cannot find file dbConfig.properties");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String driver = prop.getProperty("driver","com.mysql.cj.jdbc.Driver");
	public static String url = prop.getProperty("url","jdbc:mysql:///mydb?serverTimezone=UTC&useSSL=false");
	public static String usn = prop.getProperty("usn","root");
	public static String pwd = prop.getProperty("pwd","root");
}