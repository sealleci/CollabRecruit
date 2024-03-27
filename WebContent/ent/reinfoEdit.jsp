<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

	<title>岗位编辑</title>

	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/common.css">
</head>

<body class="bg-light">
	<nav class="navbar navbar-expand navbar-light bg-light border-bottom">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">才聘网</a>
        <div class="ml-auto">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">${sessionScope.username}</a>
                </li>
                <li class="nav-item ml-2">
                    <form action="${pageContext.request.contextPath}/user/LoginServlet" method="post">
                        <input type="hidden" name="action" value="logout">
                        <input type="submit" class="btn btn-outline-warning" value="登出">
                    </form>
                </li>

                <li class="nav-item" id="login">
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/user/login.jsp">登录</a>
                </li>
                <li class="nav-item ml-2" id="register">
                    <a class="btn btn-outline-primary"
                        href="${pageContext.request.contextPath}/user/register.jsp">注册</a>
                </li>
            </ul>
        </div>
	</nav>
	
    <div class="container-fluid my-5">
        <div class="row justify-content-center">
            <div class="col-md-8 border rounded-lg bg-white p-4">
                岗位编辑
                
			</div>
		</div>
	</div>
	
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/font-awesome.all.min.js"></script>
    <script src="../js/jquery.nicescroll.min.js"></script>

    <script>
        $(function () {
            $("body").niceScroll();
        });
    </script>
</body>

</html>