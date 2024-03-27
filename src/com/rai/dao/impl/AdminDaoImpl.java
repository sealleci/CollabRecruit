package com.rai.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rai.dao.AdminDao;
import com.rai.domain.po.Admin;

public class AdminDaoImpl extends GenericBaseDao implements AdminDao {

	@Override
	public Admin findById(Integer id) {
		Admin admin = null;
		try {
			this.getConnection();
			String sql = "select * from admin where adid = ? ;";
			this.executeQuery(sql, id);
			if (rs != null && rs.next()) {
				admin = new Admin(
						rs.getInt("adid"), rs.getString("adname")
						, rs.getString("password"), rs.getString("email"));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return admin;
	}

	@Override
	public Admin findByEmail(String email) {
		Admin admin = null;
		try {
			this.getConnection();
			String sql = "select * from admin where email = ? ;";
			this.executeQuery(sql, email);
			if (rs != null && rs.next()) {
				admin = new Admin(
						rs.getInt("adid"), rs.getString("adname")
						, rs.getString("password"), rs.getString("email"));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return admin;
	}
	
	@Override
	public List<Admin> findAll() {
		List<Admin> admins = null;
		try {
			this.getConnection();
			String sql = "select * from admin ;";
			this.executeQuery(sql);
			if (rs!=null) {
				admins = new ArrayList<Admin>();
				while(rs.next()){
					Admin admin = new Admin(
							rs.getInt("adid"), rs.getString("adname")
							, rs.getString("password"), rs.getString("email"));
					admins.add(admin);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return admins;
	}

	@Override
	public List<Admin> findBySQL(String sql, Object... params) {
		List<Admin> admins = null;
		try {
			this.getConnection();
			this.executeQuery(sql, params);
			if (rs!=null) {
				admins = new ArrayList<Admin>();
				while(rs.next()){
					Admin admin = new Admin(
							rs.getInt("adid"), rs.getString("adname")
							, rs.getString("password"), rs.getString("email"));
					admins.add(admin);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return admins;
	}

	@Override
	public int insert(Admin entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "insert into admin values(null, ?, ?, ?) ;";
			this.executeUpdate(sql, entity.getAdname()
					, entity.getPassword(), entity.getEmail());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int delete(Admin entity) {
		return this.delete(entity.getAdid());
	}

	@Override
	public int delete(Integer id) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "delete from admin where adid = ? ;";
			this.executeUpdate(sql, id);
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int update(Admin entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "update admin set adname = ?, `password` = ?, email = ? "
					+ "where adid = ? ;";
			this.executeUpdate(sql, entity.getAdname(), entity.getPassword()
					, entity.getEmail(), entity.getAdid());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}
}