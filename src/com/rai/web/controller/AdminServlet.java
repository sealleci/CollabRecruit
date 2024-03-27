package com.rai.web.controller;

import com.rai.domain.po.*;
import com.rai.service.*;
import com.rai.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService = new AdminServiceImpl();
    private ResumeService resumeService = new ResumeServiceImpl();
    private EnterpriseInfoService enterpriseInfoService = new EnterpriseInfoServiceImpl();
    private EnterAccountService enterAccountService = new EnterAccountServiceImpl();
    private UserInfoService userInfoService = new UserInfoServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectPath = request.getContextPath() + "/login.jsp";
        String redirectPath2 = "user/userInfo.jsp";
        String action = request.getParameter("action");

        if (!loginState(request, response)) {
            response.sendRedirect(redirectPath);
            return;
        }

        switch (action) {
            case "lockAccount": {
                lockAccount(request, response);
                break;
            }
            case "unlockAccount": {
                unlockAccount(request, response);
                break;
            }
            case "passResume": {
                passResume(request, response);
                break;
            }
            case "passEnterInfo": {
                passEnterInfo(request, response);
                break;
            }
            case "denyResume": {
                denyResume(request, response);
                break;
            }
            case "denyEnterInfo": {
                denyEnterInfo(request, response);
                break;
            }
            case "deleteInfo": {
                deleteInfo(request, response);
                break;
            }
            case "modifyInfo": {
                modifyInfo(request, response);
                break;
            }
            default: {
                response.sendRedirect(redirectPath2);
                break;
            }
        }
    }

    protected boolean loginState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = (Admin) request.getSession().getAttribute("info");
        return admin != null;
    }

    //state=0为正常,state=1为冻结
    private void lockAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "index.jsp";
        String redirectPath = "index.jsp";
        System.out.println("lock account...");

        String paraType = request.getParameter("type");
        String paraId = null;
        int id = -1;

        if (paraType != null && !paraType.equals("")) {
            switch (paraType) {
                case "user": {
                    paraId = request.getParameter("id");
                    if (paraId != null && !paraId.equals("")) {
                        id = Integer.parseInt(paraId);
                    }

                    if (id != -1) {
                        UserInfo user = userInfoService.findById(id);
                        if (user != null) {
                            user.setState(1);
                            userInfoService.modify(user);
                            response.sendRedirect(request.getContextPath() + "/listPagesServlet?action=listUserPage");
                        }
                    }

                    break;
                }

                case "enter": {
                    paraId = request.getParameter("id");
                    if (paraId != null && !paraId.equals("")) {
                        id = Integer.parseInt(paraId);
                    }

                    if (id != -1) {
                        EnterAccount enter = enterAccountService.findById(id);
                        if (enter != null) {
                            enter.setState(1);
                            enterAccountService.modify(enter);
                            response.sendRedirect(request.getContextPath() + "/listPagesServlet?action=listEnterPage");
                        }
                    }

                    break;
                }

                default:
                    break;
            }
        }
    }

    private void unlockAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "index.jsp";
        String redirectPath = "index.jsp";
        System.out.println("unlock account...");

        String paraType = request.getParameter("type");
        String paraId = null;
        int id = -1;

        if (paraType != null && !paraType.equals("")) {
            switch (paraType) {
                case "user": {
                    paraId = request.getParameter("id");
                    if (paraId != null && !paraId.equals("")) {
                        id = Integer.parseInt(paraId);
                    }

                    if (id != -1) {
                        UserInfo user = userInfoService.findById(id);
                        if (user != null) {
                            user.setState(0);
                            userInfoService.modify(user);
                            response.sendRedirect(request.getContextPath() + "/listPagesServlet?action=listUserPage");
                        }
                    }

                    break;
                }

                case "enter": {
                    paraId = request.getParameter("id");
                    if (paraId != null && !paraId.equals("")) {
                        id = Integer.parseInt(paraId);
                    }

                    if (id != -1) {
                        EnterAccount enter = enterAccountService.findById(id);
                        if (enter != null) {
                            enter.setState(0);
                            enterAccountService.modify(enter);
                            response.sendRedirect(request.getContextPath() + "/listPagesServlet?action=listEnterPage");
                        }
                    }

                    break;
                }

                default:
                    break;
            }
        }
    }

    //exaimeState=0为未审核，=1为审核成功，=2为审核未通过
    private void passResume(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "ent/resumePage.jsp";
        String redirectPath = "ent/resumePage.jsp";
        System.out.println("pass resume...");

        int id = -1;
        String paraId = request.getParameter("id");
        if (paraId != null && !paraId.equals("")) {
            id = Integer.parseInt(paraId);
        }

        if (id != -1) {
            Resume resume = resumeService.findById(id);
            System.out.println(resume);
            if (resume != null) {
                resume.setExamineState(1);
                resumeService.modify(resume);
                response.sendRedirect(request.getContextPath() + "/listPagesServlet?action=listUserPage");
            }
        }
    }

    private void passEnterInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "ent/enterInfo.jsp";
        String redirectPath = "ent/enterInfo.jsp";
        System.out.println("pass enterinfo...");

        int id = -1;
        String paraId = request.getParameter("id");
        if (paraId != null && !paraId.equals("")) {
            id = Integer.parseInt(paraId);
        }

        if (id != -1) {
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.findById(id);
            System.out.println(enterpriseInfo);
            if (enterpriseInfo != null) {
                enterpriseInfo.setExamineState(1);
                enterpriseInfoService.modify(enterpriseInfo);
                response.sendRedirect(request.getContextPath() + "/listPagesServlet?action=listEnterPage");
            }
        }
    }

    private void denyResume(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "ent/resumePage.jsp";
        String redirectPath = "ent/resumePage.jsp";
        System.out.println("deny resume...");

        int id = -1;
        String paraId = request.getParameter("id");
        if (paraId != null && !paraId.equals("")) {
            id = Integer.parseInt(paraId);
        }

        if (id != -1) {
            Resume resume = resumeService.findById(id);
            System.out.println(resume);
            if (resume != null) {
                resume.setExamineState(2);
                resumeService.modify(resume);
                response.sendRedirect(request.getContextPath() + "/listPagesServlet?action=listUserPage");
            }
        }
    }

    private void denyEnterInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "ent/enterInfo.jsp";
        String redirectPath = "ent/enterInfo.jsp";
        System.out.println("deny enterinfo...");

        int id = -1;
        String paraId = request.getParameter("id");
        if (paraId != null && !paraId.equals("")) {
            id = Integer.parseInt(paraId);
        }

        if (id != -1) {
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.findById(id);
            System.out.println(enterpriseInfo);
            if (enterpriseInfo != null) {
                enterpriseInfo.setExamineState(2);
                enterpriseInfoService.modify(enterpriseInfo);
                response.sendRedirect(request.getContextPath() + "/listPagesServlet?action=listEnterPage");
            }
        }
    }

    private void deleteInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String dispatcherPath="index.jsp";
        String redirectPath = "index.jsp";
        System.out.println("delete info...");
        response.sendRedirect(redirectPath);
    }

    private void modifyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String dispatcherPath="index.jsp";
        String redirectPath = "index.jsp";
        System.out.println("modify info...");
        response.sendRedirect(redirectPath);
    }
}