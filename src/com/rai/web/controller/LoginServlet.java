package com.rai.web.controller;

import com.rai.domain.po.Admin;
import com.rai.domain.po.EnterAccount;
import com.rai.domain.po.UserInfo;
import com.rai.service.impl.AdminServiceImpl;
import com.rai.service.impl.EnterAccountServiceImpl;
import com.rai.service.impl.UserInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
    private EnterAccountServiceImpl enterAccountService = new EnterAccountServiceImpl();
    private AdminServiceImpl adminService = new AdminServiceImpl();


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();

        if (action.equals("logout")) {
            request.getSession().setAttribute("username", "");
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else if (action.equals("login")) {
            String captcha = request.getParameter("captcha");
            String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

            if (!(captcha.toLowerCase().equals(kaptchaExpected.toLowerCase()))) {
                out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 1, "验证码错误"));
            } else {
                String email = request.getParameter("email");
                String pwd = request.getParameter("pwd");
                String type = request.getParameter("userType");
                switch (type) {
                    case "user":
                        if (userInfoService.login(email, pwd)) {
                            UserInfo userInfo = userInfoService.findByEmail(email);
                            request.getSession().setAttribute("logged", true);
                            request.getSession().setAttribute("info", userInfo);
                            request.getSession().setAttribute("type", "user");
                            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 0, "登录成功"));
                        } else {
                            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 2, "登录失败"));
                        }
                        break;
                    case "enter":
                        if (enterAccountService.login(email, pwd)) {
                            EnterAccount enterAccount = enterAccountService.findByEmail(email);
                            request.getSession().setAttribute("logged", true);
                            request.getSession().setAttribute("info", enterAccount);
                            request.getSession().setAttribute("type", "enter");
                            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 0, "登录成功"));
                        } else {
                            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 2, "登录失败"));
                        }
                        break;
                    case "admin":
                        if (adminService.login(email, pwd)) {
                            Admin admin = adminService.findByEmail(email);
                            request.getSession().setAttribute("logged", true);
                            request.getSession().setAttribute("info", admin);
                            request.getSession().setAttribute("type", "admin");
                            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 0, "登录成功"));
                        } else {
                            out.write(String.format("{\"code\":%d, \"message\":\"%s\"}", 2, "登录失败"));
                        }
                        break;
                }
            }
        }

        // response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
