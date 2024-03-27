<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>登录</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
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
        <div class="col-11 col-md-8 col-lg-6 card">
            <div class="card-body">
                <h3>登录</h3>
                <form id="loginForm" class="needs-validation" novalidate>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" for="inputEmail">
                            邮箱
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputEmail" name="email" required>
                            <div class="invalid-feedback">
                                请输入用户名
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" for="inputPassword">
                            密码
                        </label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword" name="pwd" required>
                            <div class="invalid-feedback">
                                请输入密码
                            </div>
                        </div>
                    </div>
                    <div class="alert alert-danger collapse" id="error-login" role="alert">
                        用户名或密码错误！
                    </div>

                    <div class="form-group row">
                        <div class="custom-control custom-radio custom-control-inline ml-auto">
                            <input type="radio" id="userType1" name="userType" class="custom-control-input"
                                   value="user" required>
                            <label class="custom-control-label" for="userType1">用户</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" id="userType2" name="userType" class="custom-control-input"
                                   value="enter" required>
                            <label class="custom-control-label" for="userType2">企业</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline mr-auto">
                            <input type="radio" id="userType3" name="userType" class="custom-control-input"
                                   value="admin" required>
                            <label class="custom-control-label" for="userType3">管理员</label>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" for="inputCaptcha">
                            验证码
                        </label>
                        <div class="col">
                            <input type="text" class="form-control" id="inputCaptcha" name="captcha"
                                   placeholder="不区分大小写" required>
                            <div class="invalid-feedback">
                                请输入验证码
                            </div>
                        </div>
                        <div class="col-auto">
                            <img alt="验证码" src="${pageContext.request.contextPath}/kaptcha.jpg" id="kaptcha"
                                 style="height: calc(1.5em + .75rem + 2px)" onclick="changeCaptcha()">
                        </div>
                    </div>
                    <div class="alert alert-danger collapse" id="error-captcha" role="alert">
                        验证码错误！
                    </div>
                </form>
                <button type="button" class="btn btn-primary w-100" id="submit-btn">登录</button>
            </div>
        </div>
        <div class="col-12 text-center mt-3">
            还没有账号？<a href="${pageContext.request.contextPath}/register.jsp" class="ml-3">立即注册</a>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>

<script>
    function changeCaptcha() {
        $('#kaptcha').attr('src', '${pageContext.request.contextPath}/kaptcha.jpg?' + Math.floor(Math.random() * 100000)).fadeIn();
    }

    $('#submit-btn').click(function () {
        const form = $('.needs-validation')[0];
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            const data = $('#loginForm').serializeJSON();
            const $ec = $('#error-captcha');
            const $lf = $('#error-login');

            data.action = 'login';
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/loginServlet',
                data: data,
                async: true,
                error: function (request) {
                    alert('连接错误，请刷新重试');
                },
                success: function (data) {
                    const res = JSON.parse(data);
                    switch (res.code) {
                        case 0:
                            $ec.collapse('hide');
                            location.href = '${pageContext.request.contextPath}/index.jsp';
                            break;
                        case 1:
                            $ec.collapse('show');
                            $lf.collapse('hide');
                            break;
                        case 2:
                            $ec.collapse('hide');
                            $lf.collapse('show');
                            break;
                    }
                }
            });

            changeCaptcha();
        }
    });
</script>
</body>

</html>