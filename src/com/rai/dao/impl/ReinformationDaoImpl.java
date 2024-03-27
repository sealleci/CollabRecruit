package com.rai.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rai.dao.ReinformationDao;
import com.rai.domain.po.EnterpriseInfo;
import com.rai.domain.po.Reinformation;
import com.rai.domain.vo.Page;
import com.rai.utils.PageUtil;

public class ReinformationDaoImpl extends GenericBaseDao implements ReinformationDao {

	@Override
	public Reinformation findById(Integer id) {
		Reinformation reinformation = null;
		try {
			this.getConnection();
			String sql = "select r.*, e.* " + 
					" from reinformation r, enterpriseinfo e " + 
					" where r.eid = e.eid and r.reid = ? ;";
			this.executeQuery(sql, id);
			if (rs != null & rs.next()) {
				reinformation = new Reinformation();
				reinformation.setReid(rs.getInt(1));
				reinformation.setRePositionName(rs.getString(2));
				reinformation.setReSalary(rs.getString(3));
				reinformation.setReCity(rs.getString(4));
				reinformation.setReArea(rs.getString(5));;
				reinformation.setReWorkYears(rs.getString(6));
				reinformation.setReEducation(rs.getString(7));
				reinformation.setRePersonNum(rs.getInt(8));
				reinformation.setReLightPoint(rs.getString(9));
				reinformation.setReJobDesc(rs.getString(10));;
				reinformation.setReDepartment(rs.getString(11));
				reinformation.setReProfessional(rs.getString(12));
				reinformation.setReReportPerson(rs.getString(13));
				reinformation.setReSubordinates(rs.getInt(14));
				
				EnterpriseInfo e = new EnterpriseInfo();
				e.setEid(rs.getInt(15));
				e.setEname(rs.getString(17));
				e.setEintro(rs.getString(18));
				e.setIntustry(rs.getString(19));
				e.setArtiperson(rs.getString(20));
				e.setRegmoney(rs.getInt(21));
				e.setEntertype(rs.getString(22));
				e.setExamineState(rs.getInt(23));
				reinformation.setE(e);
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return reinformation;
	}

	
	@Override
	public List<Reinformation> findByEnterId(Integer id) {
		List<Reinformation> reinformations = null;
		try {
			this.getConnection();
			String sql = "select r.*, e.* " + 
					" from reinformation r, enterpriseinfo e " + 
					" where e.eid=? and r.eid = e.eid ;";
			this.executeQuery(sql,id);
			if (rs!=null) {
				reinformations = new ArrayList<Reinformation>();
				while(rs.next()){
					Reinformation reinformation = new Reinformation();
					reinformation.setReid(rs.getInt(1));
					reinformation.setRePositionName(rs.getString(2));
					reinformation.setReSalary(rs.getString(3));
					reinformation.setReCity(rs.getString(4));
					reinformation.setReArea(rs.getString(5));;
					reinformation.setReWorkYears(rs.getString(6));
					reinformation.setReEducation(rs.getString(7));
					reinformation.setRePersonNum(rs.getInt(8));
					reinformation.setReLightPoint(rs.getString(9));
					reinformation.setReJobDesc(rs.getString(10));;
					reinformation.setReDepartment(rs.getString(11));
					reinformation.setReProfessional(rs.getString(12));
					reinformation.setReReportPerson(rs.getString(13));
					reinformation.setReSubordinates(rs.getInt(14));
					
					EnterpriseInfo e = new EnterpriseInfo();
					e.setEid(rs.getInt(15));
					e.setEname(rs.getString(17));
					e.setEintro(rs.getString(18));
					e.setIntustry(rs.getString(19));
					e.setArtiperson(rs.getString(20));
					e.setRegmoney(rs.getInt(21));
					e.setEntertype(rs.getString(22));
					e.setExamineState(rs.getInt(23));
					reinformation.setE(e);
					reinformations.add(reinformation);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return reinformations;
	}


	@Override
	public List<Reinformation> findAll() {
		List<Reinformation> reinformations = null;
		try {
			this.getConnection();
			String sql = "select r.*, e.* " + 
					" from reinformation r, enterpriseinfo e " + 
					" where r.eid = e.eid ;";
			this.executeQuery(sql);
			if (rs!=null) {
				reinformations = new ArrayList<Reinformation>();
				while(rs.next()){
					Reinformation reinformation = new Reinformation();
					reinformation.setReid(rs.getInt(1));
					reinformation.setRePositionName(rs.getString(2));
					reinformation.setReSalary(rs.getString(3));
					reinformation.setReCity(rs.getString(4));
					reinformation.setReArea(rs.getString(5));;
					reinformation.setReWorkYears(rs.getString(6));
					reinformation.setReEducation(rs.getString(7));
					reinformation.setRePersonNum(rs.getInt(8));
					reinformation.setReLightPoint(rs.getString(9));
					reinformation.setReJobDesc(rs.getString(10));;
					reinformation.setReDepartment(rs.getString(11));
					reinformation.setReProfessional(rs.getString(12));
					reinformation.setReReportPerson(rs.getString(13));
					reinformation.setReSubordinates(rs.getInt(14));
					
					EnterpriseInfo e = new EnterpriseInfo();
					e.setEid(rs.getInt(15));
					e.setEname(rs.getString(17));
					e.setEintro(rs.getString(18));
					e.setIntustry(rs.getString(19));
					e.setArtiperson(rs.getString(20));
					e.setRegmoney(rs.getInt(21));
					e.setEntertype(rs.getString(22));
					e.setExamineState(rs.getInt(23));
					reinformation.setE(e);
					reinformations.add(reinformation);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return reinformations;
	}

	@Override
	public List<Reinformation> findBySQL(String sql, Object... params) {
		List<Reinformation> reinformations = null;
		try {
			this.getConnection();
			this.executeQuery(sql, params);
			if (rs!=null) {
				reinformations = new ArrayList<Reinformation>();
				while(rs.next()){
					Reinformation reinformation = new Reinformation();
					reinformation.setReid(rs.getInt(1));
					reinformation.setRePositionName(rs.getString(2));
					reinformation.setReSalary(rs.getString(3));
					reinformation.setReCity(rs.getString(4));
					reinformation.setReArea(rs.getString(5));;
					reinformation.setReWorkYears(rs.getString(6));
					reinformation.setReEducation(rs.getString(7));
					reinformation.setRePersonNum(rs.getInt(8));
					reinformation.setReLightPoint(rs.getString(9));
					reinformation.setReJobDesc(rs.getString(10));;
					reinformation.setReDepartment(rs.getString(11));
					reinformation.setReProfessional(rs.getString(12));
					reinformation.setReReportPerson(rs.getString(13));
					reinformation.setReSubordinates(rs.getInt(14));
					
					EnterpriseInfo e = new EnterpriseInfo();
					e.setEid(rs.getInt(15));
					e.setEname(rs.getString(17));
					e.setEintro(rs.getString(18));
					e.setIntustry(rs.getString(19));
					e.setArtiperson(rs.getString(20));
					e.setRegmoney(rs.getInt(21));
					e.setEntertype(rs.getString(22));
					e.setExamineState(rs.getInt(23));
					reinformation.setE(e);
					reinformations.add(reinformation);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return reinformations;
	}
	
	@Override
	public List<Reinformation> fuzzyFind(String keyword) {
		String sql="select * from reinformation r,enterpriseinfo e "
				+ " where r.eid = e.eid and concat(r.`rePositionName`,r.`reSalary`,r.`reCity`,r.`reArea`"
				+ ",r.`reWorkYears`,r.`reEducation`,r.`reLightPoint`,r.`reJobDesc`,r.`reProfessional`"
				+ ",e.`ename`,e.`eintro`,e.`industry`,e.`artiperson`,e.`regmoney`,e.`entertype`) "
				+ " like '%"+keyword+"%' ;";
		return findBySQL(sql);
	}

	@Override
	public int insert(Reinformation entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "insert into reinformation values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
			this.executeUpdate(sql,entity.getRePositionName(),entity.getReSalary()
					,entity.getReCity(),entity.getReArea(),entity.getReWorkYears()
					,entity.getReEducation(),entity.getRePersonNum(),entity.getReLightPoint()
					,entity.getReJobDesc(),entity.getReDepartment(),entity.getReProfessional()
					,entity.getReReportPerson(),entity.getReSubordinates(),entity.getE().getEid());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int delete(Reinformation entity) {
		return this.delete(entity.getReid());
	}

	@Override
	public int delete(Integer id) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "delete from reinformation where reid = ?";
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
	public int update(Reinformation entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "update reinformation "
					+ "set rePositionName = ?, reSalary = ?, reCity = ?, reArea = ?"
					+ ", reWorkYears = ?, reEducation = ?, rePersonNum = ?, reLightPoint = ?, reJobDesc = ?"
					+ ", reDepartment = ?, reProfessional = ?, reReportPerson = ?, reSubordinates = ?, eid = ? "
					+ "where reid = ? ;";
			this.executeUpdate(sql, entity.getRePositionName(),entity.getReSalary()
					,entity.getReCity(),entity.getReArea(),entity.getReWorkYears()
					,entity.getReEducation(),entity.getRePersonNum(),entity.getReLightPoint()
					,entity.getReJobDesc(),entity.getReDepartment(),entity.getReProfessional()
					,entity.getReReportPerson(),entity.getReSubordinates(),entity.getE().getEid(),entity.getReid());
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
		String sql = "select count(*) from reinformation ;";
		int totalRows = this.getTotalRows(sql);
		if(totalRows==-1) {
			totalRows=0;
		}
		
		page.setTotalRows(totalRows);

		page.setPageSize(PageUtil.pageSize);
		int totalPages = (int) Math.ceil(page.getTotalRows() * 1.0 / page.getPageSize());
		page.setTotalPages(totalPages);
		page.setCurrentPage(currentPage);

		int beginPos = (page.getCurrentPage()-1)*page.getPageSize();
		//System.out.println(totalRows+","+beginPos+","+page.getPageSize());
		
		String sql2 = "select * from reinformation r,enterpriseinfo e where r.eid=e.eid limit ?,? ;";
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
		String sql = "select count(*) from reinformation r,enterpriseinfo e "
				+ " where r.eid = e.eid and concat(r.`rePositionName`,r.`reSalary`,r.`reCity`,r.`reArea`"
				+ ",r.`reWorkYears`,r.`reEducation`,r.`reLightPoint`,r.`reJobDesc`,r.`reProfessional`"
				+ ",e.`ename`,e.`eintro`,e.`industry`,e.`artiperson`,e.`regmoney`,e.`entertype`) "
				+ " like '%"+keyword+"%' ;";
		int totalRows = this.getTotalRows(sql);
		if(totalRows==-1) {
			totalRows=0;
		}
		
		page.setTotalRows(totalRows);

		page.setPageSize(PageUtil.pageSize);
		int totalPages = (int) Math.ceil(page.getTotalRows() * 1.0 / page.getPageSize());
		page.setTotalPages(totalPages);
		page.setCurrentPage(currentPage);

		int beginPos = (page.getCurrentPage()-1)*page.getPageSize();
		//System.out.println(totalRows+","+beginPos+","+page.getPageSize());
		
		String sql2 = "select * from reinformation r,enterpriseinfo e "
				+ " where r.eid = e.eid and concat(r.`rePositionName`,r.`reSalary`,r.`reCity`,r.`reArea`"
				+ ",r.`reWorkYears`,r.`reEducation`,r.`reLightPoint`,r.`reJobDesc`,r.`reProfessional`"
				+ ",e.`ename`,e.`eintro`,e.`industry`,e.`artiperson`,e.`regmoney`,e.`entertype`) "
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