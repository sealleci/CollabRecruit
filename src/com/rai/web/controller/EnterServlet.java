package com.rai.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rai.domain.po.Delivery;
import com.rai.domain.po.EnterAccount;
import com.rai.domain.po.EnterpriseInfo;
import com.rai.domain.po.Reinformation;
import com.rai.service.DeliveryService;
import com.rai.service.EnterAccountService;
import com.rai.service.EnterpriseInfoService;
import com.rai.service.ReinformationService;
import com.rai.service.impl.DeliveryServiceImpl;
import com.rai.service.impl.EnterAccountServiceImpl;
import com.rai.service.impl.EnterpriseInfoServiceImpl;
import com.rai.service.impl.ReinformationServiceImpl;

public class EnterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EnterAccountService enterAccountService = new EnterAccountServiceImpl();
    //private ResumeService resumeService=new ResumeServiceImpl();
    private EnterpriseInfoService enterpriseInfoService = new EnterpriseInfoServiceImpl();
    private ReinformationService reinformationService = new ReinformationServiceImpl();
    private DeliveryService deliveryService = new DeliveryServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectPath = "user/userInfo.jsp";
        String action = request.getParameter("action");

        int eId = loginState(request, response);
        if (eId == -1) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        switch (action) {
            case "addEnterInfo": {
                addEnterInfo(request, response, eId);
                break;
            }
            case "modifyAcEnterInfo": {
                modifyAcEnterInfo(request, response, eId);
                break;
            }
            case "modifyEnterInfo": {
                modifyEnterInfo(request, response, eId);
                break;
            }
            case "modifyReinfo": {
                modifyReinfo(request, response, eId);
                break;
            }
            case "deleteReinfo": {
                deleteReinfo(request, response, eId);
                break;
            }
            case "publishReinfo": {
                publishReinfo(request, response, eId);
                break;
            }
            case "passDelivery": {
                passDelivery(request, response, eId);
                break;
            }
            case "denyDelivery": {
                denyDelivery(request, response, eId);
                break;
            }
            default: {
                response.sendRedirect(redirectPath);
                break;
            }
        }
    }

    protected int loginState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EnterAccount enterAccount = (EnterAccount) request.getSession().getAttribute("info");
        if (enterAccount == null) {
            return -1;
        } else {
            return enterAccount.getEid();
        }
    }

    private void addEnterInfo(HttpServletRequest request, HttpServletResponse response, int eId) throws ServletException, IOException {
        //String dispatcherPath = "ent/enterInfo.jsp";
        // String redirectPath = "ent/enterInfo.jsp";
        System.out.println("add enter...");
        //System.out.println(eId);
        //System.out.println(enterpriseInfoService.findById(eId));
        if (enterpriseInfoService.findById(eId) == null) {
            String ename = request.getParameter("ename");
            String eintro = request.getParameter("eintro");
            String industry = request.getParameter("industry");
            String artiperson = request.getParameter("artiperson");
            Integer regmoney = Integer.parseInt(request.getParameter("regmoney"));
            String entertype = request.getParameter("entertype");
            EnterpriseInfo enterpriseInfo = new EnterpriseInfo(eId, ename, eintro, industry, artiperson, regmoney, entertype);
            enterpriseInfoService.create(enterpriseInfo);
        } else {
            request.getSession().setAttribute("error", "have_enterinfo");
        }
    }

    private void modifyAcEnterInfo(HttpServletRequest request, HttpServletResponse response, int eId) throws ServletException, IOException {
        EnterAccount enterAccount = enterAccountService.findById(eId);
        if (enterAccount != null) {
            String name = request.getParameter("name");
            String tel = request.getParameter("tel");
            String address = request.getParameter("address");
            enterAccount.setEaname(name);
            enterAccount.setTelephone(tel);
            enterAccount.setAddress(address);
            enterAccountService.modify(enterAccount);
            request.getSession().setAttribute("info", enterAccount);
        } else {
            request.getSession().setAttribute("error", "no_enterinfo");
        }
    }

    private void modifyEnterInfo(HttpServletRequest request, HttpServletResponse response, int eId) throws ServletException, IOException {
        //String dispatcherPath = "ent/enterInfo.jsp";
        // String redirectPath = "ent/enterInfo.jsp";
        System.out.println("modify enter...");

        EnterpriseInfo enterpriseInfo = enterpriseInfoService.findById(eId);
        if (enterpriseInfo != null) {
        	System.out.println(enterpriseInfo);
            String ename = request.getParameter("ename");
            String eintro = request.getParameter("eintro");
            String industry = request.getParameter("industry");
            String artiperson = request.getParameter("artiperson");
            Integer regmoney = Integer.parseInt(request.getParameter("regmoney"));
            String entertype = request.getParameter("entertype");
            enterpriseInfo.setEname(ename);
            enterpriseInfo.setEintro(eintro);
            enterpriseInfo.setIntustry(industry);
            enterpriseInfo.setArtiperson(artiperson);
            enterpriseInfo.setRegmoney(regmoney);
            enterpriseInfo.setEntertype(entertype);
            enterpriseInfoService.modify(enterpriseInfo);
        } else {
            //request.getSession().setAttribute("error", "no_enterinfo");
        	System.out.println("to add enter...");
        	addEnterInfo(request, response, eId);
        }
    }

    private void modifyReinfo(HttpServletRequest request, HttpServletResponse response, int eId) throws ServletException, IOException {
        //String dispatcherPath = "ent/reinfoEdit.jsp";
        //String redirectPath = "ent/reinfoEdit.jsp";
        System.out.println("modify reinfo...");

        Integer id = Integer.parseInt(request.getParameter("rId"));
        Reinformation reinformation = reinformationService.findById(id);
        if (reinformation != null) {
            String rePositionName = request.getParameter("rePositionName");
            String reSalary = request.getParameter("reSalary");
            String reCity = request.getParameter("reCity");
            String reArea = request.getParameter("reArea");
            String reWorkYears = request.getParameter("reWorkYears");
            String reEducation = request.getParameter("reEducation");
            Integer rePersonNum = Integer.parseInt(request.getParameter("rePersonNum"));
            String reLightPoint = request.getParameter("reLightPoint");
            String reJobDesc = request.getParameter("reJobDesc");
            String reDepartment = request.getParameter("reDepartment");
            String reProfessional = request.getParameter("reProfessional");
            String reReportPerson = request.getParameter("reReportPerson");
            Integer reSubordinates = Integer.parseInt(request.getParameter("reSubordinates"));
            reinformation.setRePositionName(rePositionName);
            reinformation.setReSalary(reSalary);
            reinformation.setReCity(reCity);
            reinformation.setReArea(reArea);
            reinformation.setReWorkYears(reWorkYears);
            reinformation.setReEducation(reEducation);
            reinformation.setRePersonNum(rePersonNum);
            reinformation.setReLightPoint(reLightPoint);
            reinformation.setReJobDesc(reJobDesc);
            reinformation.setReDepartment(reDepartment);
            reinformation.setReProfessional(reProfessional);
            reinformation.setReReportPerson(reReportPerson);
            reinformation.setReSubordinates(reSubordinates);
            reinformationService.modify(reinformation);
        } else {
            request.getSession().setAttribute("error", "no_reinfo");
        }
    }

    private void deleteReinfo(HttpServletRequest request, HttpServletResponse response, int eId) throws ServletException, IOException {
        //String dispatcherPath = "ent/enterInfo.jsp";
        //String redirectPath = "ent/enterInfo.jsp";
        System.out.println("delete reinfo...");

        Integer id = (Integer) request.getSession().getAttribute("rId");
        if (id != null) {
            Reinformation reinformation = reinformationService.findById(id);
            if (reinformation != null) {
                reinformationService.remove(reinformation);
            }
        }
    }

    private void publishReinfo(HttpServletRequest request, HttpServletResponse response, int eId) throws ServletException, IOException {
        //String dispatcherPath = "ent/reinfoEdit.jsp";
        //String redirectPath = "ent/reinfoEdit.jsp";
        System.out.println("modify enter...");

        String rePositionName = request.getParameter("rePositionName");
        String reSalary = request.getParameter("reSalary");
        String reCity = request.getParameter("reCity");
        String reArea = request.getParameter("reArea");
        String reWorkYears = request.getParameter("reWorkYears");
        String reEducation = request.getParameter("reEducation");
        Integer rePersonNum = Integer.parseInt(request.getParameter("rePersonNum"));
        String reLightPoint = request.getParameter("reLightPoint");
        String reJobDesc = request.getParameter("reJobDesc");
        String reDepartment = request.getParameter("reDepartment");
        String reProfessional = request.getParameter("reProfessional");
        String reReportPerson = request.getParameter("reReportPerson");
        Integer reSubordinates = Integer.parseInt(request.getParameter("reSubordinates"));
        Reinformation reinformation = new Reinformation(rePositionName, reSalary, reCity, reArea
                , reWorkYears, reEducation, rePersonNum, reLightPoint, reJobDesc, reDepartment
                , reProfessional, reReportPerson, reSubordinates, enterpriseInfoService.findById(eId));
        reinformationService.create(reinformation);

    }

    private void passDelivery(HttpServletRequest request, HttpServletResponse response, int eId) throws ServletException, IOException {
        //String dispatcherPath = "ent/enterInfo.jsp";
        //String redirectPath = "ent/enterInfo.jsp";
        System.out.println("pass delivery...");

        Integer id = Integer.parseInt(request.getParameter("dId"));
        if (id != null) {
            Delivery delivery = deliveryService.findById(id);
            if (delivery != null) {
                if (delivery.getState() != 1) {
                    delivery.setState(1);
                    deliveryService.modify(delivery);
                    response.sendRedirect(request.getContextPath() + "/showServlet?action=resume&did=" + id);
                }
            } else {
                request.getSession().setAttribute("error", "no_delivery");
            }
        } else {
            request.getSession().setAttribute("error", "no_delivery");
        }
    }

    private void denyDelivery(HttpServletRequest request, HttpServletResponse response, int eId) throws ServletException, IOException {
        //String dispatcherPath = "ent/enterInfo.jsp";
        //String redirectPath = "ent/enterInfo.jsp";
        System.out.println("deny delivery...");

        Integer id = Integer.parseInt(request.getParameter("dId"));
        if (id != null) {
            Delivery delivery = deliveryService.findById(id);
            if (delivery != null) {
                if (delivery.getState() != 2) {
                    delivery.setState(2);
                    deliveryService.modify(delivery);
                    response.sendRedirect(request.getContextPath() + "/showServlet?action=resume&did=" + id);
                }
            } else {
                request.getSession().setAttribute("error", "no_delivery");
            }
        } else {
            request.getSession().setAttribute("error", "no_delivery");
        }
    }
}