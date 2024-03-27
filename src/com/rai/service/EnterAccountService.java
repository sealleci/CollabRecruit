package com.rai.service;

import com.rai.domain.po.EnterAccount;
import com.rai.domain.vo.Page;

import java.util.List;

public interface EnterAccountService {
	boolean isEmailExit(String email);
	boolean login(String email,String password);
	boolean register(EnterAccount enterAccount);
	boolean remove(EnterAccount enterAccount);
	boolean modify(EnterAccount enterAccount);
	EnterAccount findById(Integer id);
	List<EnterAccount> findAll();
	Page selectReinByPage(int currentPage);

    EnterAccount findByEmail(String email);
	List<EnterAccount> fuzzyFind(String keyword);
	Page fuzzySelectByPage(int currentPage,String keyword);
}
