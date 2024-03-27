package com.rai.dao;

import java.util.List;

import com.rai.domain.po.Resume;
import com.rai.domain.vo.Page;

public interface ResumeDao extends GenericDao<Resume, Integer>{
	List<Resume> fuzzyFind(String keyword);
	int getTotalRows(String sql);
	Resume findByUserId(Integer id); 
	Page findByPage(int currentPage, int eid);
	Page fuzzyFindByPage(int currentPage,String keyword);
}
