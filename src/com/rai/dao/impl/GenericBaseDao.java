package com.rai.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rai.utils.DBUtil;

public class GenericBaseDao {
	
	private static String driver;
	private static String url;
	private static String usn;
	private static String pwd;
	
	static {
		driver = DBUtil.driver;
		url = DBUtil.url;
		usn = DBUtil.usn;
		pwd = DBUtil.pwd;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected int result = -1;
	
	public void getConnection() throws SQLException {
		conn = DriverManager.getConnection(url, usn, pwd);
	}
	
	public void closeAll() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
		if (result != -1) {
			result = -1;
		}
	}
	
	public void executeQuery(final String sql, final Object... params) throws SQLException {
		pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}
		}
		rs = pstmt.executeQuery();
	}
	
	public void executeUpdate(final String sql, final Object... params) throws SQLException {
		pstmt = conn.prepareStatement(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}
		}
		result = pstmt.executeUpdate();
	}
}