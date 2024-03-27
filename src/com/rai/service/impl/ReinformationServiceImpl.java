package com.rai.service.impl;

import com.rai.dao.ReinformationDao;
import com.rai.dao.impl.ReinformationDaoImpl;
import com.rai.domain.po.Reinformation;
import com.rai.domain.vo.Page;
import com.rai.service.ReinformationService;

import java.util.List;

public class ReinformationServiceImpl implements ReinformationService {
	private ReinformationDao reinformationDao = new ReinformationDaoImpl();

	@Override
	public boolean create(Reinformation reinformation) {
		boolean result = false;
		int res = reinformationDao.insert(reinformation);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean remove(Reinformation reinformation) {
		boolean result = false;
		int res = reinformationDao.delete(reinformation);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean modify(Reinformation reinformation) {
		boolean result = false;
		int res = reinformationDao.update(reinformation);
		if (res == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public Reinformation findById(Integer id) {
		return reinformationDao.findById(id);
	}

	@Override
	public List<Reinformation> findByEnterId(Integer id) {
		return reinformationDao.findByEnterId(id);
	}

	@Override
	public List<Reinformation> findAll() {
		return reinformationDao.findAll();
	}

	@Override
	public Page selectReinByPage(int currentPage) {
		return reinformationDao.findByPage(currentPage);
	}

	@Override
	public List<Reinformation> fuzzyFind(String keyword) {
		return reinformationDao.fuzzyFind(keyword);
	}

	@Override
	public Page fuzzySelectByPage(int currentPage,String keyword) {
		return reinformationDao.fuzzyFindByPage(currentPage, keyword);
	}
}