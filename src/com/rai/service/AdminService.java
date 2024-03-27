package com.rai.service;

import com.rai.domain.po.Admin;

public interface AdminService {
	boolean isEmailExit(String email);
	boolean login(String email,String password);
	boolean register(Admin admin);
	boolean remove(Admin admin);
	boolean modify(Admin admin);

    Admin findByEmail(String email);
	Admin findById(Integer id);
}