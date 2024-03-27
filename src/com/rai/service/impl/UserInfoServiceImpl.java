package com.rai.service.impl;

import com.rai.dao.ResumeDao;
import com.rai.dao.UserInfoDao;
import com.rai.dao.impl.ResumeDaoImpl;
import com.rai.dao.impl.UserInfoDaoImpl;
import com.rai.domain.po.Resume;
import com.rai.domain.po.UserInfo;
import com.rai.domain.vo.Page;
import com.rai.service.UserInfoService;

import java.util.List;

public class UserInfoServiceImpl implements UserInfoService{
	private UserInfoDao userInfoDao = new UserInfoDaoImpl();
	private ResumeDao resumeDao = new ResumeDaoImpl();


	@Override
	public boolean isEmailExit(String email) {
		boolean result = false;
		UserInfo user=userInfoDao.findByEmail(email);
		if(user!=null) {
			result=true;
		}
		return result;
	}

	@Override
	public boolean login(String email,String password) {
		boolean result = false;
		UserInfo user=userInfoDao.findByEmail(email);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				result=true;
			}
		}
		return result;
	}
	
	@Override
	public boolean register(UserInfo user) {
		boolean result = false;
		int res = userInfoDao.insert(user);
		if (res == 1) {
			UserInfo temp = findByEmail(user.getEmail());
			Resume resume = new Resume();
			resume.setUserid(temp.getUserId());
			resume.setExamineState(3);
			int r2 = resumeDao.insert(resume);
			if (r2 ==1) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean remove(UserInfo user) {
		boolean result = false;
		int res = userInfoDao.delete(user);
		if (res == 1) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean modify(UserInfo user) {
		boolean result = false;
		int res = userInfoDao.update(user);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public UserInfo findById(Integer id) {
		return userInfoDao.findById(id);
	}

	@Override
	public UserInfo findByEmail(String email){
		return userInfoDao.findByEmail(email);
	}

	@Override
	public List<UserInfo> findAll() {
		return userInfoDao.findAll();
	}

	@Override
	public Page selectReinByPage(int currentPage) {
		return userInfoDao.findByPage(currentPage);
	}

	@Override
	public List<UserInfo> fuzzyFind(String keyword) {
		return userInfoDao.fuzzyFind(keyword);
	}

	@Override
	public Page fuzzySelectByPage(int currentPage,String keyword) {
		return userInfoDao.fuzzyFindByPage(currentPage, keyword);
	}
}