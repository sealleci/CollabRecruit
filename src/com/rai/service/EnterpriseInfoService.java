package com.rai.service;

import java.util.List;

import com.rai.domain.po.EnterpriseInfo;
import com.rai.domain.vo.Page;

public interface EnterpriseInfoService {
	boolean create(EnterpriseInfo enterpriseInfo);
	boolean remove(EnterpriseInfo enterpriseInfo);
	boolean modify(EnterpriseInfo enterpriseInfo);
	EnterpriseInfo findById(Integer id);
	List<EnterpriseInfo> findAll();
	Page selectReinByPage(int currentPage);
}
