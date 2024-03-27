package com.test.utils;

import com.rai.domain.po.UserInfo;
import com.rai.service.UserInfoService;
import com.rai.service.impl.UserInfoServiceImpl;
import com.rai.utils.ConvertUtil;

public class TestUtil {
	public static void main(String[] args) {
		//System.out.println(PageUtil.pageSize);
		//System.out.println(DBUtil.driver);
		//String email1="a-1@126.cn";
		//String email2="";
//		System.out.println(VerifyUtil.isEmailCorrect(email1));
//		System.out.println(VerifyUtil.isEmailCorrect(email2));
		java.util.Date date=ConvertUtil.convertStringToDate("1145-08-11");
		System.out.println(date);
		UserInfoService userInfoService=new UserInfoServiceImpl();
		UserInfo user=userInfoService.findById(1);
		user.setBorndate(date);
		userInfoService.modify(user);
		System.out.println(ConvertUtil.convertStringToBigDecimal("1145.23"));
	}
}