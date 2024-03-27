package com.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.rai.domain.po.Delivery;
import com.rai.domain.po.Reinformation;
import com.rai.domain.vo.Page;
import com.rai.service.DeliveryService;
import com.rai.service.ReinformationService;
import com.rai.service.ResumeService;
import com.rai.service.UserInfoService;
import com.rai.service.impl.DeliveryServiceImpl;
import com.rai.service.impl.ReinformationServiceImpl;
import com.rai.service.impl.ResumeServiceImpl;
import com.rai.service.impl.UserInfoServiceImpl;
 
public class TestServiceImpl {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		UserInfoService userInfoService=new UserInfoServiceImpl();
		ResumeService resumeService=new ResumeServiceImpl();
		DeliveryService deliveryService=new DeliveryServiceImpl();
		ReinformationService reinformationService=new ReinformationServiceImpl();
		String email="zhangsan@163.com";
		String email2="114514";
		//String password="123456";
		/*
		 * System.out.println(userInfoService.isEmailExit(email));
		 * System.out.println(userInfoService.isEmailExit(email2));
		 * System.out.println(resumeService.getViewResumeByUserId(1));
		 */
		//DeliveryService deliveryService=new DeliveryServiceImpl();
		//Delivery delivery=deliveryService.findById(1);
		//System.out.println(delivery);
		//delivery.setState(1);
		//deliveryService.modify(delivery);
		
		Page deliveryPage=deliveryService.selectReinByPageByUserId(1, 1);
		List<Reinformation> reinfoList=new ArrayList<Reinformation>();
		for(Delivery d:(List<Delivery>)deliveryPage.getData()) {
			reinfoList.add(reinformationService.findById(d.getReid()));
			System.out.println(reinformationService.findById(d.getReid()));
		}
	}
}