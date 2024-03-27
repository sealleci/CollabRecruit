package com.rai.service.impl;

import com.rai.dao.EnterAccountDao;
import com.rai.dao.impl.EnterAccountDaoImpl;
import com.rai.domain.po.EnterAccount;
import com.rai.domain.vo.Page;
import com.rai.service.EnterAccountService;

import java.util.List;

public class EnterAccountServiceImpl implements EnterAccountService {
	private EnterAccountDao enterAccountDao=new EnterAccountDaoImpl();
	
	
	@Override
	public boolean isEmailExit(String email) {
		boolean result = false;
		EnterAccount enterAccount=enterAccountDao.findByEmail(email);
		if(enterAccount!=null) {
			result=true;
		}
		return result;
	}

	@Override
	public boolean login(String email, String password) {
		boolean result = false;
		EnterAccount enterAccount=enterAccountDao.findByEmail(email);
		if(enterAccount!=null) {
			if(enterAccount.getPassword().equals(password)) {
				result=true;
			}
		}
		return result;
	}

	@Override
	public boolean register(EnterAccount enterAccount) {
		boolean result = false;
		int res = enterAccountDao.insert(enterAccount);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean remove(EnterAccount enterAccount) {
		boolean result = false;
		int res = enterAccountDao.delete(enterAccount);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean modify(EnterAccount enterAccount) {
		boolean result = false;
		int res = enterAccountDao.update(enterAccount);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public EnterAccount findById(Integer id) {
		return enterAccountDao.findById(id);
	}

	@Override
	public EnterAccount findByEmail(String email) {
		return enterAccountDao.findByEmail(email);
	}

	@Override
	public List<EnterAccount> findAll() {
		return enterAccountDao.findAll();
	}

	@Override
	public Page selectReinByPage(int currentPage) {
		return enterAccountDao.findByPage(currentPage);
	}

	@Override
	public List<EnterAccount> fuzzyFind(String keyword) {
		return enterAccountDao.fuzzyFind(keyword);
	}

	@Override
	public Page fuzzySelectByPage(int currentPage,String keyword) {
		return enterAccountDao.fuzzyFindByPage(currentPage, keyword);
	}
}