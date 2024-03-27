package com.rai.dao.impl;

import com.rai.dao.ResumeDao;
import com.rai.domain.po.Resume;
import com.rai.domain.vo.Page;
import com.rai.utils.PageUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResumeDaoImpl extends GenericBaseDao implements ResumeDao {

	@Override
	public Resume findById(Integer id) {
		Resume resume = null;
		try {
			this.getConnection();
			String sql = "select * from resume where rid = ? ;";
			this.executeQuery(sql, id);
			if (rs != null && rs.next()) {
				resume = new Resume(
						rs.getInt(1), rs.getString(2), rs.getString(3)
						, rs.getDate(4), rs.getDate(5), rs.getBigDecimal(6)
						, rs.getString(7), rs.getString(8), rs.getString(9)
						, rs.getString(10), rs.getDate(11), rs.getDate(12)
						,rs.getInt(13));
				resume.setExamineState(rs.getInt(14));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return resume;
	}

	@Override
	public Resume findByUserId(Integer id) {
		Resume resume = null;
		try {
			this.getConnection();
			String sql = "select r.* from resume r,userinfo u where u.userid = ?  and r.userid = u.userid ;";
			this.executeQuery(sql, id);
			if (rs != null && rs.next()) {
				resume = new Resume(
						rs.getInt(1), rs.getString(2), rs.getString(3)
						, rs.getDate(4), rs.getDate(5), rs.getBigDecimal(6)
						, rs.getString(7), rs.getString(8), rs.getString(9)
						, rs.getString(10), rs.getDate(11), rs.getDate(12)
						,rs.getInt(13));
				resume.setExamineState(rs.getInt(14));
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return resume;
	}


	@Override
	public List<Resume> findAll() {
		List<Resume> resumes = null;
		try {
			this.getConnection();
			String sql = "select * from resume ;";
			this.executeQuery(sql);
			if (rs!=null) {
				resumes = new ArrayList<Resume>();
				while(rs.next()){
					Resume resume = new Resume(
							rs.getInt(1), rs.getString(2), rs.getString(3)
							, rs.getDate(4), rs.getDate(5), rs.getBigDecimal(6)
							, rs.getString(7), rs.getString(8), rs.getString(9)
							, rs.getString(10), rs.getDate(11), rs.getDate(12)
							,  rs.getInt(13));
					resume.setExamineState(rs.getInt(14));
					resumes.add(resume);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return resumes;
	}

	@Override
	public List<Resume> findBySQL(String sql, Object... params) {
		List<Resume> resumes = null;
		try {
			this.getConnection();
			this.executeQuery(sql, params);
			if (rs!=null) {
				resumes = new ArrayList<Resume>();
				while(rs.next()){
					Resume resume = new Resume(
							rs.getInt(1), rs.getString(2), rs.getString(3)
							, rs.getDate(4), rs.getDate(5), rs.getBigDecimal(6)
							, rs.getString(7), rs.getString(8), rs.getString(9)
							, rs.getString(10), rs.getDate(11), rs.getDate(12)
							, rs.getInt(13));
					resume.setExamineState(rs.getInt(14));
					resumes.add(resume);
				}
			}
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return resumes;
	}

	@Override
	public List<Resume> fuzzyFind(String keyword) {
		String sql="select * from resume "
				+ " where concat(`rcompany`,`position`,`salary`,`duty`,`schoolName`,`education`,`professional`,`entranceTime`,`graduateTime`) "
				+ " like '%"+keyword+"%' ;";
		return findBySQL(sql);
	}

	@Override
	public int insert(Resume entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "insert into resume values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
			this.executeUpdate(sql, entity.getRcompany(),entity.getPosition()
					,entity.getWorkStartTime(),entity.getWorkEndTime(),entity.getSalary()
					,entity.getDuty(),entity.getSchoolName(),entity.getEducation()
					,entity.getProfessional(),entity.getEntranceTime(),entity.getGraduateTime()
					,entity.getUserid(),entity.getExamineState());
			res = result;
			this.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return res;
		}
		
		return res;
	}

	@Override
	public int delete(Resume entity) {
		return this.delete(entity.getRid());
	}

	@Override
	public int delete(Integer id) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "delete from resume where rid = ? ;";
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
	public int update(Resume entity) {
		int res = -1;
		try {
			this.getConnection();
			String sql = "update resume set rcompany = ?, position = ?, workStartTime = ?, workEndTime = ?"
					+ ", salary = ?, duty = ?, schoolName = ?, education = ?, professional=?, entranceTime=?"
					+ ", graduateTime=?, userid=?, examineState=? "
					+ " where rid = ? ;";
			this.executeUpdate(sql,  entity.getRcompany(),entity.getPosition()
					,entity.getWorkStartTime(),entity.getWorkEndTime(),entity.getSalary()
					,entity.getDuty(),entity.getSchoolName(),entity.getEducation()
					,entity.getProfessional(),entity.getEntranceTime(),entity.getGraduateTime()
					,entity.getUserid(),entity.getExamineState(),entity.getRid());
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
	public Page findByPage(int currentPage, int eid) {
		Page page = new Page();
		String sql = "select count(*) from resume ;";
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
		String sql2 = "select * from resume limit ?,? ;";
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
		String sql = "select count(*) from resume "
				+ " where concat(`rcompany`,`position`,`salary`,`duty`,`schoolName`,`education`,`professional`,`entranceTime`,`graduateTime`) "
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
		String sql2 = "select * from resume "
				+ " where concat(`rcompany`,`position`,`salary`,`duty`,`schoolName`,`education`,`professional`,`entranceTime`,`graduateTime`) "
				+ " like '%"+keyword+"%' ;"
				+ " limit ?,? ;";
		try {
			page.setData(this.findBySQL(sql2,beginPos,page.getPageSize()));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}
}