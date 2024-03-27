package com.rai.web.controller;

import com.rai.domain.po.*;
import com.rai.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ShowServlet
 */
//@WebServlet("/showServlet")
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReinformationServiceImpl reinformationService = new ReinformationServiceImpl();
    private EnterpriseInfoServiceImpl enterpriseInfoService = new EnterpriseInfoServiceImpl();
    private DeliveryServiceImpl deliveryService = new DeliveryServiceImpl();
    private UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
    private ResumeServiceImpl resumeService = new ResumeServiceImpl();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "enter":
                enter(request, response);
                break;
            case "enterReinfo":
                enterReinfo(request, response);
                break;
            case "enterReinfoEdit":
                enterReinfoEdit(request, response);
                break;
            case "resume":
                resume(request, response);
                break;
            case "reinfo":
                reinfo(request, response);
                break;
        }
    }

    protected int userLoginState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("info");
        if (userInfo != null) {
            return userInfo.getUserId();
        }
        return -1;
    }

    protected int enterLoginState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EnterAccount enterAccount = (EnterAccount) request.getSession().getAttribute("info");
        if (enterAccount != null) {
            return enterAccount.getEid();
        }
        return -1;
    }

    private void enter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reid = enterLoginState(request, response);
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.findById(reid);
        request.setAttribute("einfo", enterpriseInfo);
        request.getRequestDispatcher("ent/enterInfo.jsp").forward(request, response);
    }

    private void enterReinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reid = enterLoginState(request, response);
        List<Reinformation> reinformationList = reinformationService.findByEnterId(reid);
        request.setAttribute("reinfoList", reinformationList);
        request.getRequestDispatcher("ent/reinfoPage.jsp").forward(request, response);
    }

    private void enterReinfoEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reid = Integer.parseInt(request.getParameter("reid"));
        Reinformation reinformation = reinformationService.findById(reid);
        request.setAttribute("reinfo", reinformation);
        request.getRequestDispatcher("ent/reinfoInfo.jsp").forward(request, response);
    }


    private void resume(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int did = Integer.parseInt(request.getParameter("did"));
        Delivery delivery = deliveryService.findById(did);
        UserInfo userInfo = userInfoService.findById(delivery.getUserid());
        Resume resume = resumeService.findByUserId(delivery.getUserid());
        Reinformation reinfo = reinformationService.findById(delivery.getReid());

        request.setAttribute("delivery", delivery);
        request.setAttribute("userInfo", userInfo);
        request.setAttribute("resume", resume);
        request.setAttribute("reinfo", reinfo);
        request.getRequestDispatcher("resumeDetail.jsp").forward(request, response);
    }

    private void reinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reid = Integer.parseInt(request.getParameter("reid"));
        int userid = userLoginState(request, response);

        Reinformation reinformation = reinformationService.findById(reid);
        Delivery delivery = deliveryService.findByReIdAndUserId(reid, userid);
        request.setAttribute("reinfo", reinformation);
        request.setAttribute("delivery", delivery);
        request.getRequestDispatcher("reinfoDetail.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
