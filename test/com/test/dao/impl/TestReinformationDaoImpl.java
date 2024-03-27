package com.test.dao.impl;

import org.junit.Assert;
import org.junit.Test;

import com.rai.dao.ReinformationDao;
import com.rai.dao.impl.ReinformationDaoImpl;
import com.rai.domain.po.Reinformation;

public class TestReinformationDaoImpl {
	private ReinformationDao reinformationDao = new ReinformationDaoImpl();

	@Test
	public void testFindById() {
		Reinformation reinformation =  reinformationDao.findById(1);
		Assert.assertTrue(reinformation.getRePersonNum()==2);
	}
}
