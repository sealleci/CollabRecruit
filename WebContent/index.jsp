<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>才聘网</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>

<body>
<nav class="navbar navbar-expand navbar-light bg-light border-bottom">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">才聘网</a>
    <div>
        <ul class="navbar-nav">
            <c:choose>
                <c:when test='${sessionScope.type=="user"}'>
                    <li>
                        <a class="nav-link" href="${pageContext.request.contextPath}/user/userInfo.jsp">个人信息</a>
                    </li>
                    <li>
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/userServlet?action=generViewResume">个人简历</a>
                    </li>
                    <li>
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/listPagesServlet?action=listReinfoPages">岗位检索</a>
                    </li>
                </c:when>
                <c:when test='${sessionScope.type=="enter"}'>
                    <li>
                        <a class="nav-link" href="${pageContext.request.contextPath}/showServlet?action=enter">企业信息</a>
                    </li>
                    <li>
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/showServlet?action=enterReinfo">岗位列表</a>
                    </li>
                    <li>
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/listPagesServlet?action=listResumePage">简历列表</a>
                    </li>
                </c:when>
                <c:when test='${sessionScope.type=="admin"}'>
                    <li>
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/listPagesServlet?action=listUserPage">用户列表</a>
                    </li>
                    <li>
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/listPagesServlet?action=listEnterPage">企业列表</a>
                    </li>
                </c:when>
            </c:choose>
        </ul>
    </div>
    <div class="ml-auto">
        <ul class="navbar-nav">
            <c:choose>
                <c:when test="${!empty sessionScope.logged}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <c:choose>
                                <c:when test='${sessionScope.type == "user"}'>
                                    ${sessionScope.info.username}
                                </c:when>
                                <c:when test='${sessionScope.type == "enter"}'>
                                    ${sessionScope.info.eaname}
                                </c:when>
                                <c:when test='${sessionScope.type == "admin"}'>
                                    ${sessionScope.info.adname}
                                </c:when>
                            </c:choose>
                        </a>
                    </li>
                    <li class="nav-item ml-2">
                        <form action="${pageContext.request.contextPath}/loginServlet" method="post">
                            <input type="hidden" name="action" value="logout">
                            <input type="submit" class="btn btn-outline-warning" value="登出">
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item" id="login">
                        <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/login.jsp">登录</a>
                    </li>
                    <li class="nav-item ml-2" id="register">
                        <a class="btn btn-outline-primary"
                           href="${pageContext.request.contextPath}/register.jsp">注册</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<div class="container-fluid mt-5">
    <div class="row justify-content-center">
        <div class="col-11 col-md-10 col-lg-8">
            <div class="jumbotron">
                <h1 class="display-4">才聘网</h1>
                <hr class="my-5">
                <div class="row justify-content-around">
                    <div class="col-auto">
                        <c:choose>
                            <c:when test='${sessionScope.type=="user"}'>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/user/userInfo.jsp">个人信息</a>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/userServlet?action=generViewResume">个人简历</a>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/listPagesServlet?action=listReinfoPages">岗位检索</a>
                            </c:when>
                            <c:when test='${sessionScope.type=="enter"}'>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/showServlet?action=enter">企业信息</a>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/showServlet?action=enterReinfo">岗位列表</a>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/listPagesServlet?action=listResumePage">简历列表</a>
                            </c:when>
                            <c:when test='${sessionScope.type=="admin"}'>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/listPagesServlet?action=listUserPage">用户列表</a>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/listPagesServlet?action=listEnterPage">企业列表</a>
                            </c:when>
                            <c:otherwise>
                                <p>游客您好，请先
                                    <a class="btn btn-outline-success"
                                       href="${pageContext.request.contextPath}/login.jsp">登录</a>
                                    或
                                    <a class="btn btn-outline-primary"
                                       href="${pageContext.request.contextPath}/register.jsp">注册</a>
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>

</html>