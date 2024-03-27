package com.rai.dao;

import java.util.List;

import com.rai.domain.po.Reinformation;
import com.rai.domain.vo.Page;

public interface ReinformationDao extends GenericDao<Reinformation, Integer>{
	List<Reinformation> findByEnterId(Integer id);
	List<Reinformation> fuzzyFind(String keyword);
	int getTotalRows(String sql);
	Page findByPage(int currentPage);
	Page fuzzyFindByPage(int currentPage,String keyword);
}