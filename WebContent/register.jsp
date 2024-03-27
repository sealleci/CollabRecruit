<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>注册</title>

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
    <div class="row justify-content-center c1 c2 collapse show">
        <div class="col-auto">
            <div class="card" style="width: 18rem;">
                <div class="card-body text-center my-5">
                    <h5 class="card-title"><i class="far fa-building"></i> 企业用户</h5>
                    <button class="btn btn-outline-primary stretched-link" data-toggle="collapse"
                            data-target=".c1">企业注册
                    </button>
                </div>
            </div>
        </div>
        <div class="col-auto">
            <div class="card" style="width: 18rem;">
                <div class="card-body text-center my-5">
                    <h5 class="card-title"><i class="far fa-user"></i> 个人用户</h5>
                    <button class="btn btn-outline-primary stretched-link" data-toggle="collapse"
                            data-target=".c2">用户注册
                    </button>
                </div>
            </div>
        </div>
        <div class="col-12 text-center mt-3">
            已有账号？<a href="${pageContext.request.contextPath}/login.jsp" class="ml-3">立即登录</a>
        </div>
    </div>
    <div class="row justify-content-center c1 collapse">
        <div class="col-6 border rounded bg-white p-3">
            <div class="row">
                <div class="col d-flex">
                    <h3>企业注册</h3>
                    <button class="btn btn-outline-primary stretched-link ml-auto" data-toggle="collapse"
                            data-target=".c1"><i class="fas fa-arrow-left"></i> 返回
                    </button>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col px-4">
                    <form id="entForm">
                        <input type="hidden" name="type" value="ent">
                        <div class="form-group">
                            <label for="entAccount">
                                账户名
                            </label>
                            <input type="text" class="form-control" id="entAccount" name="account" required>
                        </div>

                        <div class="form-group">
                            <label for="entPassword">
                                密码
                            </label>
                            <input type="password" class="form-control" id="entPassword" name="pwd" required>
                            <small class="form-text text-muted">
                                5~20 位，允许特殊字符
                            </small>
                        </div>
                        <div class="alert alert-danger collapse" id="ent-error-pwd" role="alert">
                            无效密码！
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="entEmail">
                                    Email
                                </label>
                                <input type="email" class="form-control" id="entEmail"
                                       placeholder="name@example.com" name="email" required>
                            </div>

                            <div class="form-group col-md">
                                <label for="entTel">
                                    电话
                                </label>
                                <input type="tel" class="form-control" id="entTel" name="tel" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="entAddr">
                                地址
                            </label>
                            <input type="text" class="form-control" id="entAddr" name="address" required>
                        </div>


                        <div class="form-row">
                            <div class="form-group col">
                                <label for="entCaptcha">
                                    验证码
                                </label>
                                <input type="text" class="form-control" id="entCaptcha" name="captcha" required>
                                <small class="form-text text-muted">
                                    不区分大小写
                                </small>
                            </div>
                            <div class="col-auto">
                                <label style="height: 18px"></label>
                                <img alt="验证码" src="${pageContext.request.contextPath}/kaptcha.jpg" class="kaptcha"
                                     style="height: calc(1.5em + .75rem + 2px); display: block"
                                     onclick="changeCaptcha()">
                            </div>
                        </div>
                        <div class="alert alert-danger collapse" id="ent-error-captcha" role="alert">
                            验证码错误！
                        </div>
                    </form>
                    <button type="button" class="btn btn-primary w-100" id="entSubmit">注册</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row justify-content-center c2 collapse">
        <div class="col-6 border rounded bg-white p-3">
            <div class="row">
                <div class="col d-flex">
                    <h3>用户注册</h3>
                    <button class="btn btn-outline-primary stretched-link ml-auto" data-toggle="collapse"
                            data-target=".c2"><i class="fas fa-arrow-left"></i> 返回
                    </button>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col px-4">
                    <form id="userForm">
                        <input type="hidden" name="type" value="user">
                        <div class="form-group">
                            <label for="userName">
                                姓名
                            </label>
                            <input type="text" class="form-control" id="userName" name="name" required>
                        </div>

                        <div class="form-group">
                            <label for="userPassword">
                                密码
                            </label>
                            <input type="password" class="form-control" id="userPassword" name="pwd" required>
                            <small class="form-text text-muted">
                                5~20 位，允许特殊字符
                            </small>
                        </div>
                        <div class="alert alert-danger collapse" id="error-pwd" role="alert">
                            无效密码！
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label for="userEmail">
                                    Email
                                </label>
                                <input type="email" class="form-control" id="userEmail"
                                       placeholder="name@example.com" name="email" required>
                            </div>

                            <div class="form-group col-md-4">
                                <label>性别</label>

                                <div class="d-flex justify-content-around"
                                     style="height: calc(1.5em + .75rem + 2px);">
                                    <div class="custom-control custom-radio custom-control-inline my-auto">
                                        <input type="radio" id="gender1" name="gender" class="custom-control-input"
                                               value="男">
                                        <label class="custom-control-label" for="gender1">男</label>
                                    </div>
                                    <div class="custom-control custom-radio custom-control-inline my-auto">
                                        <input type="radio" id="gender2" name="gender" class="custom-control-input"
                                               value="女" required>
                                        <label class="custom-control-label" for="gender2">女</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="userDate">
                                    出生日期
                                </label>
                                <input type="date" class="form-control" id="userDate" name="date" required>
                            </div>
                            <div class="form-group col-md">
                                <label for="userTel">
                                    电话
                                </label>
                                <input type="tel" class="form-control" id="userTel" name="tel" required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="userCity">
                                    城市
                                </label>
                                <input type="text" class="form-control" id="userCity" name="city" required>
                            </div>
                            <div class="form-group col-md">
                                <label for="userAddr">
                                    地址
                                </label>
                                <input type="text" class="form-control" id="userAddr" name="address" required>
                            </div>
                        </div>


                        <div class="form-row">
                            <div class="form-group col">
                                <label for="userCaptcha">
                                    验证码
                                </label>
                                <input type="text" class="form-control" id="userCaptcha" name="captcha" required>
                                <small class="form-text text-muted">
                                    不区分大小写
                                </small>
                            </div>
                            <div class="col-auto">
                                <label style="height: 18px"></label>
                                <img alt="验证码" src="${pageContext.request.contextPath}/kaptcha.jpg" class="kaptcha"
                                     style="height: calc(1.5em + .75rem + 2px); display: block"
                                     onclick="changeCaptcha()">
                            </div>
                        </div>
                        <div class="alert alert-danger collapse" id="error-captcha" role="alert">
                            验证码错误！
                        </div>
                    </form>
                    <button type="button" class="btn btn-primary w-100" id="userSubmit">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/font-awesome.all.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>

<script>
    function changeCaptcha() {
        $('.kaptcha').attr('src', '${pageContext.request.contextPath}/kaptcha.jpg?' + Math.floor(Math.random() * 100000)).fadeIn();
    }

    $(function () {
        $("body").niceScroll();
    });

    $('.collapse').on('shown.bs.collapse', function () {
        $("body").getNiceScroll().resize();
    });

    $('#entSubmit').click(function () {
        const form = $('#entForm')[0];
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            const data = $('#entForm').serializeJSON();
            let fail = false;

            if (!/^[0-9A-Za-z!@#$%^&*-_]{5,32}$/.test(data.pwd)) {
                $("#ent-error-pwd").collapse('show');
                fail = true;
            } else {
                $("#ent-error-pwd").collapse('hide');
            }

            if (!fail) {
                const $ec = $('#ent-error-captcha');

                $.ajax({
                        type: 'POST',
                        url: '${pageContext.request.contextPath}/registerServlet',
                        data: data,
                        async: true,
                        error: function (request) {
                            $ec.collapse('hide');
                            alert('连接错误，请刷新重试');
                        },
                        success: function (data) {
                            console.log(data);
                            const res = JSON.parse(data);
                            switch (res.code) {
                                case 0:
                                    $ec.collapse('hide');
                                    alert("注册成功");
                                    location.href = 'login.jsp';
                                    break;
                                case 1:
                                    $ec.collapse('show');
                                    break;
                            }
                        }
                    }
                );
            }

            changeCaptcha();
        }
    });

    $('#userSubmit').click(function () {
        const form = $('#userForm')[0];
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            const data = $('#userForm').serializeJSON();
            let fail = false;

            if (!/^[0-9A-Za-z!@#$%^&*-_]{5,32}$/.test(data.pwd)) {
                $("#error-pwd").collapse('show');
                fail = true;
            } else {
                $("#error-pwd").collapse('hide');
            }

            if (!fail) {
                const $ec = $('#error-captcha');

                $.ajax({
                        type: 'POST',
                        url: '${pageContext.request.contextPath}/registerServlet',
                        data: data,
                        async: true,
                        error: function (request) {
                            $ec.collapse('hide');
                            alert('连接错误，请刷新重试');
                        },
                        success: function (data) {
                            console.log(data);
                            const res = JSON.parse(data);
                            switch (res.code) {
                                case 0:
                                    $ec.collapse('hide');
                                    alert("注册成功");
                                    location.href = 'login.jsp';
                                    break;
                                case 1:
                                    $ec.collapse('show');
                                    break;
                            }
                        }
                    }
                );
            }


            changeCaptcha();
        }
    });
</script>
</body>

</html>