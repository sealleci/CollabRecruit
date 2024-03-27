package com.rai.web.controller;

import com.rai.domain.po.EnterAccount;
import com.rai.domain.po.UserInfo;
import com.rai.service.impl.EnterAccountServiceImpl;
import com.rai.service.impl.UserInfoServiceImpl;
import com.rai.utils.ConvertUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

//@WebServlet("/regServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
    private EnterAccountServiceImpl enterAccountService = new EnterAccountServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String captcha = request.getParameter("captcha");
        String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        System.out.println(kaptchaExpected);
        if (!(captcha.toLowerCase().equals(kaptchaExpected.toLowerCase()))) {
            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 1, "验证码错误"));
            return;
        }

        String type = request.getParameter("type");
        switch (type) {
            case "user":
                registerUser(request, response);
                break;
            case "ent":
                registerEnt(request, response);
                break;
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String password = request.getParameter("pwd");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        Date borndate = ConvertUtil.convertStringToDate(request.getParameter("date"));
        String telephone = request.getParameter("tel");
        String city = request.getParameter("city");
        String address = request.getParameter("address");

        UserInfo user = new UserInfo(username, password, borndate, gender, city, telephone, email, address);
        boolean result = userInfoService.register(user);

        PrintWriter out = response.getWriter();
        if (result) {
            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 0, "注册成功"));
        } else {
            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 2, "注册失败"));
        }
    }

    private void registerEnt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("pwd");
        String email = request.getParameter("email");
        String telephone = request.getParameter("tel");
        String address = request.getParameter("address");

        EnterAccount enterAccount = new EnterAccount(account, password, telephone, email, address);
        boolean result = enterAccountService.register(enterAccount);

        PrintWriter out = response.getWriter();
        if (result) {
            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 0, "注册成功"));
        } else {
            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 2, "注册失败"));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}