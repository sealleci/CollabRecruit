package com.rai.dao;

import java.util.List;

import com.rai.domain.po.EnterAccount;
import com.rai.domain.vo.Page;

public interface EnterAccountDao extends GenericDao<EnterAccount, Integer>{
	EnterAccount findByEmail(String email);
	List<EnterAccount> fuzzyFind(String keyword);
	int getTotalRows(String sql);
	Page findByPage(int currentPage);
	Page fuzzyFindByPage(int currentPage,String keyword);
}
