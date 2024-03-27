package com.rai.web.controller;

import com.rai.domain.po.*;
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

//@WebServlet("/listPagesServlet")
public class ListPagesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ReinformationService reinformationService = new ReinformationServiceImpl();
    private UserInfoService userInfoService = new UserInfoServiceImpl();
    private EnterAccountService enterAccountService = new EnterAccountServiceImpl();
    private EnterpriseInfoService enterpriseInfoService = new EnterpriseInfoServiceImpl();
    private ResumeService resumeService = new ResumeServiceImpl();
    private DeliveryService deliveryService = new DeliveryServiceImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectPath = "reingoList.jsp";
        String action = request.getParameter("action");
        switch (action) {
            case "listReinfoPages": {
                listReinfoPages(request, response);
                break;
            }
            case "listEnterPage": {
                listEnterPage(request, response);
                break;
            }
            case "listUserPage": {
                listUserPage(request, response);
                break;
            }
            case "listResumePage": {
                listResumePage(request, response);
                break;
            }
            default: {
                response.sendRedirect(redirectPath);
                break;
            }
        }
    }

    protected int enterLoginState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EnterAccount enterAccount = (EnterAccount) request.getSession().getAttribute("info");
        if (enterAccount != null) {
            return enterAccount.getEid();
        }
        return -1;
    }

    private void listReinfoPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "reinfoPage.jsp";
        System.out.println("ListReinfoPagesServlet ... ");

        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        Page page = reinformationService.selectReinByPage(currentPage);
        request.setAttribute("page", page);
        request.getRequestDispatcher("user/reinfoList.jsp").forward(request, response);
    }

    private void listEnterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ListEnterPageServlet ...");
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        Page page = enterAccountService.selectReinByPage(currentPage);
        List<EnterpriseInfo> eninfoList = new ArrayList<EnterpriseInfo>();
        for (Object o : page.getData()) {
            EnterAccount u = (EnterAccount) o;
            eninfoList.add(enterpriseInfoService.findById(u.getEid()));
        }
        request.setAttribute("page", page);
        request.setAttribute("eninfoList", eninfoList);
        request.getRequestDispatcher("admin/enterPage.jsp").forward(request, response);
    }

    private void listUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ListUserPageServlet ...");

        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        Page page = userInfoService.selectReinByPage(currentPage);
        List<Resume> resumeList = new ArrayList<Resume>();
        for (Object o : page.getData()) {
            UserInfo u = (UserInfo) o;
            resumeList.add(resumeService.findByUserId(u.getUserId()));
        }
        request.setAttribute("page", page);
        request.setAttribute("resumeList", resumeList);
        request.getRequestDispatcher("admin/userPage.jsp").forward(request, response);
    }

    private void listResumePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "resumePage.jsp";
        System.out.println("ListResumPageServlet ...");
        int eid = enterLoginState(request, response);
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        Page page = deliveryService.selectReinByPageByEnterId(currentPage, eid);
        request.setAttribute("page", page);
        List<Resume> resumeList = new ArrayList<Resume>();
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        List<Reinformation> reinfoList = new ArrayList<Reinformation>();

        for (Object o : page.getData()) {
            Delivery d = (Delivery) o;
            resumeList.add(resumeService.findByUserId(d.getUserid()));
            userInfoList.add(userInfoService.findById(d.getUserid()));
            reinfoList.add(reinformationService.findById(d.getReid()));
        }
        request.setAttribute("resumeList", resumeList);
        request.setAttribute("userInfoList", userInfoList);
        request.setAttribute("reinfoList", reinfoList);
        request.getRequestDispatcher("ent/resumePage.jsp").forward(request, response);
    }

}
