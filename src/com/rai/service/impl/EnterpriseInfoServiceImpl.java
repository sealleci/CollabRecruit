package com.rai.service.impl;

import java.util.List;

import com.rai.dao.EnterpriseInfoDao;
import com.rai.dao.impl.EnterpriseInfoDaoImpl;
import com.rai.domain.po.EnterpriseInfo;
import com.rai.domain.vo.Page;
import com.rai.service.EnterpriseInfoService;

public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {
	private EnterpriseInfoDao enterpriseInfoDao=new EnterpriseInfoDaoImpl();
	

	@Override
	public boolean create(EnterpriseInfo enterpriseInfo) {
		boolean result = false;
		int res = enterpriseInfoDao.insert(enterpriseInfo);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean remove(EnterpriseInfo enterpriseInfo) {
		boolean result = false;
		int res = enterpriseInfoDao.delete(enterpriseInfo);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean modify(EnterpriseInfo enterpriseInfo) {
		boolean result = false;
		int res = enterpriseInfoDao.update(enterpriseInfo);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public EnterpriseInfo findById(Integer id) {
		return enterpriseInfoDao.findById(id);
	}

	@Override
	public List<EnterpriseInfo> findAll() {
		return enterpriseInfoDao.findAll();
	}

	@Override
	public Page selectReinByPage(int currentPage) {
		return enterpriseInfoDao.findByPage(currentPage);
	}
	
}
