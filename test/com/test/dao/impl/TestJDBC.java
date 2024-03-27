package com.test.dao.impl;

import java.util.List;

import com.rai.dao.impl.ReinformationDaoImpl;
import com.rai.domain.po.EnterAccount;
import com.rai.domain.po.Reinformation;
import com.rai.service.EnterAccountService;
import com.rai.service.EnterpriseInfoService;
import com.rai.service.impl.EnterAccountServiceImpl;
import com.rai.service.impl.EnterpriseInfoServiceImpl;

public class TestJDBC {

	public static void main(String[] args) {
//		EnterAccountDao enterAccountDao=new EnterAccountDaoImpl();
//		List<EnterAccount> list=enterAccountDao.findAll();
//		for(EnterAccount e:list) {
//			System.out.println(e);
//		}
//		ResumeDao resumeDao=new ResumeDaoImpl();
//		List<Resume> list=resumeDao.findAll();
//		for(Resume e:list) {
//			System.out.println(e);
//		}
//		List<Reinformation> list=new ReinformationDaoImpl().fuzzyFind("45454545");
//		for(Reinformation u:list) {
//			System.out.println(u);
//		}
		EnterpriseInfoService enterpriseInfoService=new EnterpriseInfoServiceImpl();
		EnterAccountService enterAccountService=new EnterAccountServiceImpl();
		EnterAccount enterAccount = enterAccountService.findById(5);
		System.out.println(enterpriseInfoService.findById(5));
	}
}