package com.rai.service.impl;

import com.rai.dao.AdminDao;
import com.rai.dao.impl.AdminDaoImpl;
import com.rai.domain.po.Admin;
import com.rai.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao=new AdminDaoImpl();
	
	@Override
	public boolean isEmailExit(String email) {
		boolean result = false;
		Admin admin=adminDao.findByEmail(email);
		if(admin!=null) {
			result=true;
		}
		return result;
	}

	@Override
	public boolean login(String email, String password) {
		boolean result = false;
		Admin admin=adminDao.findByEmail(email);
		if(admin!=null) {
			if(admin.getPassword().equals(password)) {
				result=true;
			}
		}
		return result;
	}

	@Override
	public boolean register(Admin admin) {
		boolean result = false;
		int res = adminDao.insert(admin);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean remove(Admin admin) {
		boolean result = false;
		int res = adminDao.delete(admin);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean modify(Admin admin) {
		boolean result = false;
		int res = adminDao.update(admin);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public Admin findByEmail(String email) {
		return adminDao.findByEmail(email);
	}

	@Override
	public Admin findById(Integer id) {
		return adminDao.findById(id);
	}
}