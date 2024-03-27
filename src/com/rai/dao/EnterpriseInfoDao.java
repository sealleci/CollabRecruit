package com.rai.dao;

import com.rai.domain.po.EnterpriseInfo;
import com.rai.domain.vo.Page;

public interface EnterpriseInfoDao extends GenericDao<EnterpriseInfo, Integer>{
	int insertByEid(EnterpriseInfo entity,Integer id);
	int getTotalRows(String sql);
	Page findByPage(int currentPage);
}