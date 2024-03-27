package com.rai.dao;

import java.util.List;

import com.rai.domain.po.UserInfo;
import com.rai.domain.vo.Page;

public interface UserInfoDao extends GenericDao<UserInfo, Integer>{
	UserInfo findByEmail(String email);
	List<UserInfo> fuzzyFind(String keyword);
	int getTotalRows(String sql);
	Page findByPage(int currentPage);
	Page fuzzyFindByPage(int currentPage,String keyword);
}