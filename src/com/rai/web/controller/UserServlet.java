package com.rai.web.controller;

import com.rai.domain.po.Delivery;
import com.rai.domain.po.Reinformation;
import com.rai.domain.po.Resume;
import com.rai.domain.po.UserInfo;
import com.rai.domain.vo.Page;
import com.rai.domain.vo.ViewResume;
import com.rai.service.DeliveryService;
import com.rai.service.ReinformationService;
import com.rai.service.ResumeService;
import com.rai.service.UserInfoService;
import com.rai.service.impl.DeliveryServiceImpl;
import com.rai.service.impl.ReinformationServiceImpl;
import com.rai.service.impl.ResumeServiceImpl;
import com.rai.service.impl.UserInfoServiceImpl;
import com.rai.utils.ConvertUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserInfoService userInfoService = new UserInfoServiceImpl();
    private ResumeService resumeService = new ResumeServiceImpl();
    private DeliveryService deliveryService = new DeliveryServiceImpl();
    private ReinformationService reinformationService = new ReinformationServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //参数action
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectPath = "user/userInfo.jsp";
        String action = request.getParameter("action");

        int userId = loginState(request, response);
        if (userId == -1) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        switch (action) {
            case "modifyUserInfo": {
                modifyUserInfo(request, response, userId);
                break;
            }
            case "modifyResume": {
                modifyResume(request, response, userId);
                break;
            }
            case "addResume": {
                addResume(request, response);
                break;
            }
            case "generViewResume": {
                generViewResume(request, response, userId);
                break;
            }
            case "deleteResume": {
                deleteResume(request, response);
                break;
            }
            case "deliverResume": {
                deliverResume(request, response, userId);
                break;
            }
            case "checkDelivery": {
                checkDelivery(request, response);
                break;
            }
            default: {
                response.sendRedirect(redirectPath);
                break;
            }
        }
    }

    protected int loginState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("info");
        if (userInfo != null) {
            return userInfo.getUserId();
        }
        return -1;
    }

    //邮箱不能改，属性名和数据库字段一致
    private void modifyUserInfo(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
        UserInfo user = userInfoService.findById(userId);

        if (user != null) {
            String username = request.getParameter("username");
            Date borndate = ConvertUtil.convertStringToDate(request.getParameter("borndate"));
            String gender = request.getParameter("gender");
            String city = request.getParameter("city");
            String telephone = request.getParameter("telephone");
            String address = request.getParameter("address");
            user.setUsername(username);
            user.setBorndate(borndate);
            user.setGender(gender);
            user.setCity(city);
            user.setTelephone(telephone);
            user.setAddress(address);
            userInfoService.modify(user);

            request.getSession().setAttribute("info", user);
        } else {
            request.getSession().setAttribute("error", "no_account");
        }
        System.out.println("modify userinfo...");
        // response.sendRedirect(redirectPath);
    }

    private void modifyResume(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
    	System.out.println("modify resume...");
        Resume resume = resumeService.findByUserId(userId);
        if (resume != null) {
            String rcompany = request.getParameter("rcompany");
            String position = request.getParameter("position");
            Date workStartTime = ConvertUtil.convertStringToDate(request.getParameter("workStartTime"));
            Date workEndTime = ConvertUtil.convertStringToDate(request.getParameter("workEndTime"));
            BigDecimal salary = ConvertUtil.convertStringToBigDecimal(request.getParameter("salary"));
            String duty = request.getParameter("duty");
            String schoolName = request.getParameter("schoolName");
            String education = request.getParameter("education");
            String professional = request.getParameter("professional");
            Date entranceTime = ConvertUtil.convertStringToDate(request.getParameter("entranceTime"));
            Date graduateTime = ConvertUtil.convertStringToDate(request.getParameter("graduateTime"));
            resume.setRcompany(rcompany);
            resume.setPosition(position);
            resume.setWorkStartTime(workStartTime);
            resume.setWorkEndTime(workEndTime);
            resume.setSalary(salary);
            resume.setDuty(duty);
            resume.setSchoolName(schoolName);
            resume.setEducation(education);
            resume.setProfessional(professional);
            resume.setEntranceTime(entranceTime);
            resume.setGraduateTime(graduateTime);
            resumeService.modify(resume);

        } else {
            //request.getSession().setAttribute("error", "no_resume");
        	System.out.println("to add resume...");
        	addResume(request, response);
        }

        
    }

    private void addResume(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "user/userResume.jsp";
        String redirectPath = "user/userResume.jsp";
        int id = -1;
        String paraId = request.getParameter("userid");
        if (paraId != null && paraId != "") {
            id = Integer.parseInt(paraId);
        }

        UserInfo user = null;
        if (id != -1) {
            user = userInfoService.findById(id);
        }

        if (user != null) {
            if (resumeService.findByUserId(id) == null) {
                String rcompany = request.getParameter("rcompany");
                String position = request.getParameter("position");
                Date workStartTime = ConvertUtil.convertStringToDate(request.getParameter("workStartTime"));
                Date workEndTime = ConvertUtil.convertStringToDate(request.getParameter("workEndTime"));
                BigDecimal salary = ConvertUtil.convertStringToBigDecimal(request.getParameter("salary"));
                String duty = request.getParameter("duty");
                String schoolName = request.getParameter("schoolName");
                String education = request.getParameter("education");
                String professional = request.getParameter("professional");
                Date entranceTime = ConvertUtil.convertStringToDate(request.getParameter("entranceTime"));
                Date graduateTime = ConvertUtil.convertStringToDate(request.getParameter("workEndTime"));
                Resume resume = new Resume(rcompany, position, workStartTime, workEndTime, salary, duty, schoolName, education, professional, entranceTime, graduateTime, id);
                resumeService.create(resume);
                request.getRequestDispatcher(dispatcherPath).forward(request, response);
                return;
            } else {
                request.getSession().setAttribute("error", "have_resume");
            }
        } else {
            request.getSession().setAttribute("error", "no_account");
        }
        System.out.println("add resume...");
        response.sendRedirect(redirectPath);
    }

    private void generViewResume(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
        UserInfo user = userInfoService.findById(userId);

        if (user != null) {
            Resume resume = resumeService.findByUserId(userId);
            if (resume != null) {
                ViewResume viewResume = resumeService.getViewResumeByUserId(userId);
                request.getSession().setAttribute("viewResume", viewResume);
                request.getSession().setAttribute("resume", resume);
                request.getRequestDispatcher("user/userResume.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("error", "no_resume");
            }
        } else {
            request.getSession().setAttribute("error", "no_account");
        }
        System.out.println("gener resueme...");
    }

    private void deleteResume(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "user/userResume.jsp";
        String redirectPath = "user/userResume.jsp";
        int id = -1;
        String paraId = request.getParameter("userid");
        if (paraId != null && paraId != "") {
            id = Integer.parseInt(paraId);
        }

        UserInfo user = null;
        if (id != -1) {
            user = userInfoService.findById(id);
        }

        if (user != null) {
            Resume resume = resumeService.findByUserId(id);
            if (resume != null) {
                resumeService.remove(resume);
                request.getRequestDispatcher(dispatcherPath).forward(request, response);
                return;
            } else {
                request.getSession().setAttribute("error", "no_resume");
            }
        } else {
            request.getSession().setAttribute("error", "no_account");
        }
        System.out.println("delete resume...");
        response.sendRedirect(redirectPath);
    }

    //delivery中state=0为投递出去但企业没有审核，state=1为企业要你,state=2为企业驳回
    private void deliverResume(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
        String dispatcherPath = "user/userResume.jsp";
        String redirectPath = "user/userResume.jsp";
        int reinfoid = -1;
        String paraReinfoId = request.getParameter("reinfoid");

        if (paraReinfoId != null && paraReinfoId != "") {
            reinfoid = Integer.parseInt(paraReinfoId);
        }

        UserInfo user = null;
        Reinformation reinformation = null;
        if (userId != -1) {
            user = userInfoService.findById(userId);
        }
        if (reinfoid != -1) {
            reinformation = reinformationService.findById(reinfoid);
        }

        if (user != null) {
            if (reinformation != null) {
                Resume resume = resumeService.findByUserId(userId);
                if (resume != null) {
                    Delivery delivery = new Delivery(reinfoid, userId, 0);
                    deliveryService.create(delivery);
                    response.sendRedirect("showServlet?action=reinfo&reid=" + reinfoid);
                    // request.getRequestDispatcher(dispatcherPath).forward(request, response);
                    return;
                } else {
                    request.getSession().setAttribute("error", "no_resume");
                }
            } else {
                request.getSession().setAttribute("error", "no_reinfo");
            }
        } else {
            request.getSession().setAttribute("error", "no_account");
        }
        System.out.println("deliver resume...");
        response.sendRedirect("showServlet?action=reinfo&reid=" + reinfoid);
    }

    @SuppressWarnings("unchecked")
    private void checkDelivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherPath = "user/userResume.jsp";
        String redirectPath = "user/userResume.jsp";
        int id = -1;
        String paraId = request.getParameter("userid");
        if (paraId != null && paraId != "") {
            id = Integer.parseInt(paraId);
        }

        UserInfo user = null;
        if (id != -1) {
            user = userInfoService.findById(id);
        }

        if (user != null) {
            int currentPage = 1;
            String paraCurPage = request.getParameter("currentPage");
            if (paraCurPage != null && paraCurPage != "") {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }
            Page deliveryPage = deliveryService.selectReinByPageByUserId(currentPage, id);
            List<Reinformation> reinfoList = new ArrayList<Reinformation>();
            for (Delivery d : (List<Delivery>) deliveryPage.getData()) {
                reinfoList.add(reinformationService.findById(d.getReid()));
            }
            request.getSession().setAttribute("deliveryPage", deliveryPage);
            request.getSession().setAttribute("reinfoList", reinfoList);
            request.getRequestDispatcher(dispatcherPath).forward(request, response);
            return;
        } else {
            request.getSession().setAttribute("error", "no_account");
        }
        System.out.println("check delivery...");
        response.sendRedirect(redirectPath);
    }
}