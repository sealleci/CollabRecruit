package com.rai.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rai.dao.EnterpriseInfoDao;
import com.rai.domain.po.EnterpriseInfo;
import com.rai.domain.vo.Page;
import com.rai.utils.PageUtil;

public class EnterpriseInfoDaoImpl extends GenericBaseDao implements EnterpriseInfoDao {

	@Override
	public EnterpriseInfo findById(Integer id) {
		EnterpriseInfo enterpriseInfo=null;
		try {
			this.getConnection();
			String sql = "select * from enterpriseinfo where eid = ? ;";
			this.executeQuery(sql, id);
			if (rs != null && rs.next()) {
				enterpriseInfo = new EnterpriseInfo(
						rs.getInt("eid"), rs.getString("ename")
						, rs.getString("eintro"), rs.getString("industry")
						,rs.getString("artiperson"),rs.getInt("regmoney"),rs.getString("entertype"));
				enterpriseInfo.setExamineState(rs.getInt("examineState"));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return enterpriseInfo;
	}

	@Override
	public List<EnterpriseInfo> findAll() {
		List<EnterpriseInfo> entrEnterpriseInfos = null;
		try {
			this.getConnection();
			String sql = "select * from enterpriseinfo ;";
			this.executeQuery(sql);
			if (rs!=null) {
				entrEnterpriseInfos = new ArrayList<EnterpriseInfo>();
				while(rs.next()){
					EnterpriseInfo enterpriseInfo = new EnterpriseInfo(
							rs.getInt("eid"), rs.getString("ename")
							, rs.getString("eintro"), rs.getString("industry")
							,rs.getString("artiperson"),rs.getInt("regmoney"),rs.getString("entertype"));
					enterpriseInfo.setExamineState(rs.getInt("examineState"));
					entrEnterpriseInfos.add(enterpriseInfo);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return entrEnterpriseInfos;
	}

	@Override
	public List<EnterpriseInfo> findBySQL(String sql, Object... params) {
		List<EnterpriseInfo> entrEnterpriseInfos = null;
		try {
			this.getConnection();
			this.executeQuery(sql, params);
			if (rs!=null) {
				entrEnterpriseInfos = new ArrayList<EnterpriseInfo>();
				while(rs.next()){
					EnterpriseInfo enterpriseInfo = new EnterpriseInfo(
							rs.getInt("eid"), rs.getString("ename")
							, rs.getString("eintro"), rs.getString("industry")
							,rs.getString("artiperson"),rs.getInt("regmoney"),rs.getString("entertype"));
					enterpriseInfo.setExamineState(rs.getInt("examineState"));
					entrEnterpriseInfos.add(enterpriseInfo);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return entrEnterpriseInfos;
	}
	
	@Override
	public int insertByEid(EnterpriseInfo entity, Integer id) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "insert into enterpriseinfo values(?, ?, ?, ?, ?, ?, ?, ?) ;";
			this.executeUpdate(sql,id,entity.getEname()
					,entity.getEintro(),entity.getIntustry()
					,entity.getArtiperson(),entity.getRegmoney()
					,entity.getEntertype(),entity.getExamineState());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int insert(EnterpriseInfo entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "insert into enterpriseinfo values(?, ?, ?, ?, ?, ?, ?, ?) ;";
			this.executeUpdate(sql, entity.getEid(),entity.getEname()
					,entity.getEintro(),entity.getIntustry()
					,entity.getArtiperson(),entity.getRegmoney()
					,entity.getEntertype(), entity.getExamineState());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int delete(EnterpriseInfo entity) {
		return this.delete(entity.getEid());
	}

	@Override
	public int delete(Integer id) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "delete from enterpriseinfo where eid = ? ;";
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
	public int update(EnterpriseInfo entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "update enterpriseinfo "
					+ " set ename = ?, eintro = ?, industry = ?, artiperson=?, regmoney=?, entertype=?, examineState=? "
					+ " where eid = ? ;";
			this.executeUpdate(sql, entity.getEname(),entity.getEintro()
					,entity.getIntustry(),entity.getArtiperson(),entity.getRegmoney()
					,entity.getEntertype(),entity.getExamineState(),entity.getEid());
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
		String sql = "select count(*) from enterpriseinfo ;";
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

}