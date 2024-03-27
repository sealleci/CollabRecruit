<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">
    <title>简历详情</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body class="bg-light">
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

<div class="container-fluid my-5">
    <div class="row justify-content-center">
        <div class="col-md-8 border rounded-lg bg-white p-4">
            <h3>${requestScope.userInfo.username}<small class="text-muted">
                应职：${requestScope.reinfo.rePositionName}</small></h3>
            <hr>
            <dl class="row">
                <dt class="col-sm-3">性别</dt>
                <dd class="col-sm-9">${requestScope.userInfo.gender}</dd>
                <dt class="col-sm-3">出生日期</dt>
                <dd class="col-sm-9">${requestScope.userInfo.borndate}</dd>
                <dt class="col-sm-3">电话</dt>
                <dd class="col-sm-9">${requestScope.userInfo.telephone}</dd>
                <dt class="col-sm-3">邮箱</dt>
                <dd class="col-sm-9">${requestScope.userInfo.email}</dd>
                <dt class="col-sm-3">城市</dt>
                <dd class="col-sm-9">${requestScope.userInfo.city}</dd>
                <dt class="col-sm-3">地址</dt>
                <dd class="col-sm-9">${requestScope.userInfo.address}</dd>
            </dl>
            <hr>
            <dl class="row">
                <dt class="col-sm-3">曾任公司</dt>
                <dd class="col-sm-9">${requestScope.resume.rcompany}</dd>
                <dt class="col-sm-3">职务</dt>
                <dd class="col-sm-9">${requestScope.resume.position}</dd>
                <dt class="col-sm-3">年薪</dt>
                <dd class="col-sm-9">${requestScope.resume.salary}</dd>
                <dt class="col-sm-3">始于</dt>
                <dd class="col-sm-9">${requestScope.resume.workStartTime}</dd>
                <dt class="col-sm-3">终于</dt>
                <dd class="col-sm-9">${requestScope.resume.workEndTime}</dd>
                <dt class="col-sm-3">主要工作</dt>
                <dd class="col-sm-9">${requestScope.resume.duty}</dd>
            </dl>
            <hr>
            <dl class="row">
                <dt class="col-sm-3">毕业学校</dt>
                <dd class="col-sm-9">${requestScope.resume.schoolName}</dd>
                <dt class="col-sm-3">学历</dt>
                <dd class="col-sm-9">${requestScope.resume.education}</dd>
                <dt class="col-sm-3">专业</dt>
                <dd class="col-sm-9">${requestScope.resume.professional}</dd>
                <dt class="col-sm-3">入学于</dt>
                <dd class="col-sm-9">${requestScope.resume.entranceTime}</dd>
                <dt class="col-sm-3">毕业于</dt>
                <dd class="col-sm-9">${requestScope.resume.graduateTime}</dd>
            </dl>
            <hr>
            <c:choose>
                <c:when test='${requestScope.delivery.state == 0}'>
                    <a class="btn btn-outline-primary"
                       href="enterServlet?action=passDelivery&dId=${requestScope.delivery.did}">接受</a>
                    <a class="btn btn-outline-warning"
                       href="enterServlet?action=denyDelivery&dId=${requestScope.delivery.did}">拒绝</a>
                </c:when>
                <c:when test='${requestScope.delivery.state == 1}'>
                    已录用
                </c:when>
                <c:when test='${requestScope.delivery.state == 2}'>
                    已驳回
                </c:when>
            </c:choose>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/font-awesome.all.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.min.js"></script>

<script>
    $(function () {
        $("body").niceScroll();
    });
</script>
</body>
</html>
