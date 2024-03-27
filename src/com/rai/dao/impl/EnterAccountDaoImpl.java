package com.rai.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rai.dao.EnterAccountDao;
import com.rai.domain.po.EnterAccount;
import com.rai.domain.vo.Page;
import com.rai.utils.PageUtil;

public class EnterAccountDaoImpl extends GenericBaseDao implements EnterAccountDao {

	@Override
	public EnterAccount findById(Integer id) {
		EnterAccount enterAccount = null;
		try {
			this.getConnection();
			String sql = "select * from enteraccount where eid=? ;";
			this.executeQuery(sql, id);
			if (rs != null && rs.next()) {
				enterAccount = new EnterAccount(
						rs.getInt("eid"), rs.getString("eaname"), rs.getString("password")
						, rs.getString("telephone"), rs.getString("email"),rs.getString("address"));
				enterAccount.setState(rs.getInt("state"));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return enterAccount;
	}
	
	@Override
	public EnterAccount findByEmail(String email) {
		EnterAccount enterAccount = null;
		try {
			this.getConnection();
			String sql = "select * from enteraccount where email=? ;";
			this.executeQuery(sql, email);
			if (rs != null && rs.next()) {
				enterAccount = new EnterAccount(
						rs.getInt("eid"), rs.getString("eaname"), rs.getString("password")
						, rs.getString("telephone"), rs.getString("email"),rs.getString("address"));
				enterAccount.setState(rs.getInt("state"));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return enterAccount;
	}

	@Override
	public List<EnterAccount> findAll() {
		List<EnterAccount> enterAccounts = null;
		try {
			this.getConnection();
			String sql = "select * from enteraccount ;";
			this.executeQuery(sql);
			if (rs!=null) {
				enterAccounts = new ArrayList<EnterAccount>();
				while(rs.next()){
					EnterAccount enterAccount = new EnterAccount(
							rs.getInt("eid"), rs.getString("eaname"), rs.getString("password")
							, rs.getString("telephone"), rs.getString("email"),rs.getString("address"));
					enterAccount.setState(rs.getInt("state"));
					enterAccounts.add(enterAccount);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return enterAccounts;
	}

	@Override
	public List<EnterAccount> findBySQL(String sql, Object... params) {
		List<EnterAccount> enterAccounts = null;
		try {
			this.getConnection();
			this.executeQuery(sql, params);
			if (rs!=null) {
				enterAccounts = new ArrayList<EnterAccount>();
				while(rs.next()){
					EnterAccount enterAccount = new EnterAccount(
							rs.getInt("eid"), rs.getString("eaname"), rs.getString("password")
							, rs.getString("telephone"), rs.getString("email"),rs.getString("address"));
					enterAccount.setState(rs.getInt("state"));
					enterAccounts.add(enterAccount);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return enterAccounts;
	}

	
	@Override
	public List<EnterAccount> fuzzyFind(String keyword) {
		String sql="select * from enteraccount "
				+ " where concat(`eaname`,`telephone`,`email`,`address`) "
				+ " like '%"+keyword+"%' ;";
		return findBySQL(sql);
	}

	@Override
	public int insert(EnterAccount entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "insert into enteraccount values(null, ?, ?, ?, ?, ?, ?) ;";
			this.executeUpdate(sql, entity.getEaname(), entity.getPassword()
					,entity.getTelephone(), entity.getEmail(), entity.getAddress(), entity.getState());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int delete(EnterAccount entity) {
		return this.delete(entity.getEid());
	}

	@Override
	public int delete(Integer id) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "delete from enteraccount where eid = ? ;";
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
	public int update(EnterAccount entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "update enteraccount set eaname = ?, `password` = ? "
					+ ",telephone = ?, email = ?, address = ?, state = ? "
					+ "where eid = ? ;";
			this.executeUpdate(sql, entity.getEaname(), entity.getPassword(),entity.getTelephone()
					, entity.getEmail(), entity.getAddress(),entity.getState(), entity.getEid());
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
		String sql = "select count(*) from enteraccount ;";
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
		String sql2 = "select * from enteraccount limit ?,? ;";
		try {
			page.setData(this.findBySQL(sql2,beginPos,page.getPageSize()));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}

	@Override
	public Page fuzzyFindByPage(int currentPage, String keyword) {
		Page page = new Page();
		String sql = "select count(*) from enteraccount "
				+ " where concat(`eaname`,`telephone`,`email`,`address`) "
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
		String sql2 = "select * from enteraccount "
				+ " where concat(`eaname`,`telephone`,`email`,`address`) "
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