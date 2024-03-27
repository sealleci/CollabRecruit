package com.rai.dao.impl;

import com.rai.dao.UserInfoDao;
import com.rai.domain.po.UserInfo;
import com.rai.domain.vo.Page;
import com.rai.utils.PageUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl extends GenericBaseDao implements UserInfoDao{
	
	@Override
	public UserInfo findById(Integer id) {
		UserInfo userInfo = null;
		try {
			this.getConnection();
			String sql = "select * from userinfo where userid = ? ;";
			this.executeQuery(sql, id);
			if (rs != null && rs.next()) {
				userInfo = new UserInfo(
						rs.getInt("userid"), rs.getString("username"), rs.getString("password")
						, rs.getDate("borndate"), rs.getString("gender"), rs.getString("city")
						, rs.getString("telephone"), rs.getString("email"), rs.getString("address"));
				// userInfo.setState(rs.getInt("state"));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return userInfo;
	}
	
	@Override
	public UserInfo findByEmail(String email) {
		UserInfo userInfo = null;
		try {
			this.getConnection();
			String sql = "select * from userinfo where email = ? ;";
			this.executeQuery(sql, email);
			if (rs != null && rs.next()) {
				userInfo = new UserInfo(
						rs.getInt("userid"), rs.getString("username"), rs.getString("password")
						, rs.getDate("borndate"), rs.getString("gender"), rs.getString("city")
						, rs.getString("telephone"), rs.getString("email"), rs.getString("address"));
				userInfo.setState(rs.getInt("state"));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return userInfo;
	}
	
	@Override
	public List<UserInfo> findAll() {
		List<UserInfo> userInfos = null;
		try {
			this.getConnection();
			String sql = "select * from userinfo ;";
			this.executeQuery(sql);
			if (rs!=null) {
				userInfos = new ArrayList<UserInfo>();
				while(rs.next()){
					UserInfo userInfo = new UserInfo(
						rs.getInt("userid"), rs.getString("username"), rs.getString("password")
						, rs.getDate("borndate"), rs.getString("gender"), rs.getString("city")
						, rs.getString("telephone"), rs.getString("email"), rs.getString("address"));
					userInfo.setState(rs.getInt("state"));
					userInfos.add(userInfo);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return userInfos;
	}
	
	@Override
	public List<UserInfo> findBySQL(String sql, Object... params) {
		List<UserInfo> userInfos = null;
		try {
			this.getConnection();
			this.executeQuery(sql, params);
			if (rs!=null) {
				userInfos = new ArrayList<UserInfo>();
				while(rs.next()){
					UserInfo userInfo = new UserInfo(
						rs.getInt("userid"), rs.getString("username"), rs.getString("password")
						, rs.getDate("borndate"), rs.getString("gender"), rs.getString("city")
						, rs.getString("telephone"), rs.getString("email"), rs.getString("address"));
					userInfo.setState(rs.getInt("state"));
					userInfos.add(userInfo);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return userInfos;
	}
	
	
	@Override
	public List<UserInfo> fuzzyFind(String keyword) {
		String sql="select * from userinfo "
				+ " where concat(`username`,`city`,`telephone`,`email`,`address`) "
				+ " like '%"+keyword+"%' ;";
		return findBySQL(sql);
	}

	@Override
	public int insert(UserInfo entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "insert into userinfo values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			this.executeUpdate(sql, entity.getUsername(), entity.getPassword(), entity.getBorndate()
					, entity.getGender(), entity.getCity(), entity.getTelephone(), entity.getEmail()
					, entity.getAddress(),entity.getState());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int delete(UserInfo entity) {
		return this.delete(entity.getUserId());
	}

	@Override
	public int delete(Integer id) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "delete from userinfo where userid = ? ;";
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
	public int update(UserInfo entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "update userinfo set username = ?, password = ?, borndate = ?, gender = ?"
					+ ", city = ?, telephone = ?, email = ?, address = ?,state = ? "
					+ " where userid = ? ;";
			this.executeUpdate(sql, entity.getUsername(), entity.getPassword(), entity.getBorndate()
					, entity.getGender(), entity.getCity(), entity.getTelephone(), entity.getEmail()
					, entity.getAddress(),entity.getState(), entity.getUserId());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int getTotalRows(String sql) {
		try {
			this.getConnection();
			this.executeQuery(sql);
			if (rs != null && rs.next())
				return rs.getInt(1);
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
		return -1;
	}

	@Override
	public Page findByPage(int currentPage) {
		Page page = new Page();
		String sql = "select count(*) from userinfo ;";
		int totalRows = this.getTotalRows(sql);
		page.setTotalRows(totalRows);
		if(totalRows==-1) {
			totalRows=0;
		}

		page.setPageSize(PageUtil.pageSize);
		int totalPages = (int) Math.ceil(page.getTotalRows() * 1.0 / page.getPageSize());
		page.setTotalPages(totalPages);
		page.setCurrentPage(currentPage);

		int beginPos = (page.getCurrentPage()-1)*page.getPageSize();
		String sql2 = "select * from userinfo limit ?,? ;";
		try {
			page.setData(this.findBySQL(sql2,beginPos,page.getPageSize()));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}

	@Override
	public Page fuzzyFindByPage(int currentPage,String keyword) {
		Page page = new Page();
		String sql = "select count(*) from userinfo "
				+ " where concat(`username`,`city`,`telephone`,`email`,`address`) "
				+ " like '%"+keyword+"%' ;";
		int totalRows = this.getTotalRows(sql);
		page.setTotalRows(totalRows);
		if(totalRows==-1) {
			totalRows=0;
		}

		page.setPageSize(PageUtil.pageSize);
		int totalPages = (int) Math.ceil(page.getTotalRows() * 1.0 / page.getPageSize());
		page.setTotalPages(totalPages);
		page.setCurrentPage(currentPage);

		int beginPos = (page.getCurrentPage()-1)*page.getPageSize();
		String sql2 = "select * from userinfo "
				+ " where concat(`username`,`city`,`telephone`,`email`,`address`) "
				+ " like '%"+keyword+"%' "
				+ " limit ?,? ;";
		try {
			page.setData(this.findBySQL(sql2,beginPos,page.getPageSize()));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}
	
}