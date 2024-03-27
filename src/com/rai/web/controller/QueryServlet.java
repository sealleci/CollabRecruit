package com.rai.web.controller;

import com.rai.domain.po.EnterAccount;
import com.rai.domain.po.EnterpriseInfo;
import com.rai.domain.po.Resume;
import com.rai.domain.po.UserInfo;
import com.rai.domain.vo.Page;
import com.rai.service.*;
import com.rai.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/queryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReinformationService reinformationService = new ReinformationServiceImpl();
	private UserInfoService userInfoService = new UserInfoServiceImpl();
	private EnterAccountService enterAccountService = new EnterAccountServiceImpl();
	private ResumeService resumeService = new ResumeServiceImpl();
	private EnterpriseInfoService enterpriseInfoService = new EnterpriseInfoServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectPath="reingoList.jsp";
		String action=request.getParameter("action");
		String keyword=request.getParameter("keyword");
		if(keyword==null||keyword.equals("")) {
			response.sendRedirect(redirectPath);
            return;
		}
		
		switch(action) {
		case "queryResumeServlet":{
			queryResumeServlet(request, response,keyword);
			break;}
		case "queryUserServlet":{
			queryUserServlet(request, response,keyword);
			break;}
		case "queryEnterServlet":{
			queryEnterServlet(request, response,keyword);
			break;}
		case "queryReinfoServlet":{
			queryReinfoServlet(request, response,keyword);
			break;}
		default:{
			response.sendRedirect(redirectPath);
			break;}
		}
	}
	
	private void queryResumeServlet(HttpServletRequest request, HttpServletResponse response,String keyword) throws ServletException, IOException{
		String dispatcherPath="resumePage.jsp";
		System.out.println("ListResumPageServlet ...");
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		Page page = resumeService.fuzzySelectByPage(currentPage, keyword);
		request.setAttribute("page", page);
		request.getRequestDispatcher(dispatcherPath).forward(request, response);	
	}
	
	private void queryUserServlet(HttpServletRequest request, HttpServletResponse response,String keyword) throws ServletException, IOException{
		String dispatcherPath="userInfo.jsp";
		System.out.println("ListUserPageServlet ...");
		
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		Page page = userInfoService.fuzzySelectByPage(currentPage, keyword);
		List<Resume> resumeList = new ArrayList<Resume>();
		for (Object o : page.getData()) {
			UserInfo u = (UserInfo) o;
			resumeList.add(resumeService.findByUserId(u.getUserId()));
		}
		request.setAttribute("page", page);
		request.setAttribute("resumeList", resumeList);
		request.getRequestDispatcher("admin/userPage.jsp").forward(request, response);
	}
	
	private void queryEnterServlet(HttpServletRequest request, HttpServletResponse response,String keyword) throws ServletException, IOException{
		String dispatcherPath="enterInfo.jsp";
		System.out.println("ListEnterPageServlet ...");
		
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		Page page = enterAccountService.fuzzySelectByPage(currentPage, keyword);
		List<EnterpriseInfo> eninfoList = new ArrayList<EnterpriseInfo>();
		for (Object o : page.getData()) {
			EnterAccount u = (EnterAccount) o;
			eninfoList.add(enterpriseInfoService.findById(u.getEid()));
		}
		request.setAttribute("page", page);
		request.setAttribute("eninfoList", eninfoList);
		request.getRequestDispatcher("admin/enterPage.jsp").forward(request, response);
	}
	
	private void queryReinfoServlet(HttpServletRequest request, HttpServletResponse response,String keyword) throws ServletException, IOException{
		String dispatcherPath="reinfoPage.jsp";
		System.out.println("ListReinfoPagesServlet ... ");

		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		Page page = reinformationService.fuzzySelectByPage(currentPage, keyword);
		request.setAttribute("page", page);
		request.getRequestDispatcher("user/reinfoList.jsp").forward(request, response);
	}
}