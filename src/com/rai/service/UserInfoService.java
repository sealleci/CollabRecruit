package com.rai.service;

import com.rai.domain.po.UserInfo;
import com.rai.domain.vo.Page;

import java.util.List;

public interface UserInfoService {
	boolean isEmailExit(String email);
	boolean login(String email,String password);
	boolean register(UserInfo user);
	boolean remove(UserInfo user);
	boolean modify(UserInfo user);
	UserInfo findById(Integer id);
    UserInfo findByEmail(String email);
    List<UserInfo> findAll();
	Page selectReinByPage(int currentPage);
	List<UserInfo> fuzzyFind(String keyword);
	Page fuzzySelectByPage(int currentPage,String keyword);
}