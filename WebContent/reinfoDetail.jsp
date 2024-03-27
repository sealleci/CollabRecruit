<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">
    <title>岗位详情</title>

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
            <h3>${requestScope.reinfo.rePositionName}<small class="text-muted"> ${requestScope.reinfo.e.ename}</small>
            </h3>
            <hr>
            <dl class="row">
                <dt class="col-sm-3">年薪</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reSalary}</dd>
                <dt class="col-sm-3">工作时间</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reWorkYears}</dd>
                <dt class="col-sm-3">岗位特色</dt>
                <dd class="col-sm-9"><p>${requestScope.reinfo.reLightPoint}</p></dd>
                <dt class="col-sm-3">工作需求</dt>
                <dd class="col-sm-9"><p>${requestScope.reinfo.reJobDesc}</p></dd>
                <dt class="col-sm-3">所属部门</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reDepartment}</dd>
                <dt class="col-sm-3">下属职员数量</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reSubordinates}</dd>
                <dt class="col-sm-3">所需学历</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reEducation}</dd>
                <dt class="col-sm-3">所需专业</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reProfessional}</dd>
                <dt class="col-sm-3">需求人数</dt>
                <dd class="col-sm-9">${requestScope.reinfo.rePersonNum}</dd>
                <dt class="col-sm-3">城市</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reCity}</dd>
                <dt class="col-sm-3">地区</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reArea}</dd>
                <dt class="col-sm-3">面试官</dt>
                <dd class="col-sm-9">${requestScope.reinfo.reReportPerson}</dd>
            </dl>
            <hr>
            <dl class="row">
                <dt class="col-sm-3">企业领域</dt>
                <dd class="col-sm-9">${requestScope.reinfo.e.intustry}</dd>
                <dt class="col-sm-3">企业类型</dt>
                <dd class="col-sm-9">${requestScope.reinfo.e.entertype}</dd>
                <dt class="col-sm-3">企业法人</dt>
                <dd class="col-sm-9">${requestScope.reinfo.e.artiperson}</dd>
                <dt class="col-sm-3">注册基金</dt>
                <dd class="col-sm-9">${requestScope.reinfo.e.regmoney}</dd>
                <dt class="col-sm-3">企业简介</dt>
                <dd class="col-sm-9"><p>${requestScope.reinfo.e.eintro}</p></dd>
            </dl>
            <hr>
            <c:choose>
                <c:when test='${requestScope.delivery.state == 0}'>
                    已投递
                </c:when>
                <c:when test='${requestScope.delivery.state == 1}'>
                    已录用
                </c:when>
                <c:when test='${requestScope.delivery.state == 2}'>
                    已驳回
                </c:when>
                <c:otherwise>
                    <a class="btn btn-outline-primary"
                       href="${pageContext.request.contextPath}/userServlet?action=deliverResume&reinfoid=${requestScope.reinfo.reid}">投递简历</a>
                </c:otherwise>
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
