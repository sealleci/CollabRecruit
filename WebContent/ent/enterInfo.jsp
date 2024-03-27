<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>企业信息</title>

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
            <div class="row" id="acInfoRow">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>账户信息</h3>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-outline-primary ml-auto" id="acEdit">
                                <i class="far fa-edit"></i>
                                编辑
                            </button>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label>账户名</label>
                        <span class="inputHolder">${sessionScope.info.eaname}</span>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md">
                            <label>Email</label>
                            <span class="inputHolder">${sessionScope.info.email}</span>
                        </div>
                        <div class="form-group col-md">
                            <label>电话</label>
                            <span class="inputHolder">${sessionScope.info.telephone}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>地址</label>
                        <span class="inputHolder">${sessionScope.info.address}</span>
                    </div>
                </div>
            </div>

            <div class="row" id="acEditRow" style="display: none;">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>账户信息</h3>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-outline-success" id="acSave">
                                <i class="far fa-save" style="width: 1.125em;"></i>
                                保存
                            </button>
                        </div>
                    </div>
                    <hr>
                    <form id="acForm">
                        <div class="form-group">
                            <label for="name">
                                账户名
                            </label>
                            <input type="text" class="form-control" id="name" name="name" required
                                   value="${sessionScope.info.eaname}">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="email">
                                    Email
                                </label>
                                <input type="email" class="form-control" id="email" placeholder="name@example.com"
                                       name="email" readonly value="${sessionScope.info.email}">
                            </div>
                            <div class="form-group col-md">
                                <label for="tel">
                                    电话
                                </label>
                                <input type="tel" class="form-control" id="tel" name="tel" required
                                       value="${sessionScope.info.telephone}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address">
                                地址
                            </label>
                            <input type="text" class="form-control" id="address" name="address" required
                                   value="${sessionScope.info.address}">
                        </div>
                    </form>
                </div>
            </div>

            <div class="row" id="enInfoRow">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>企业信息</h3>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-outline-primary ml-auto" id="enEdit">
                                <i class="far fa-edit"></i>
                                编辑
                            </button>
                        </div>
                    </div>
                    <hr>
                    <div class="form-row">
                        <div class="form-group col-md">
                            <label>
                                企业名
                            </label>
                            <span class="inputHolder">${requestScope.einfo.ename}</span>
                        </div>
                        <div class="form-group col-md">
                            <label>
                                企业领域
                            </label>
                            <span class="inputHolder">${requestScope.einfo.intustry}</span>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md">
                            <label>
                                企业类型
                            </label>
                            <span class="inputHolder">${requestScope.einfo.entertype}</span>
                        </div>
                        <div class="form-group col-md">
                            <label>
                                企业法人
                            </label>
                            <span class="inputHolder">${requestScope.einfo.artiperson}</span>
                        </div>
                        <div class="form-group col-md">
                            <label>
                                注册基金
                            </label>
                            <span class="inputHolder">${requestScope.einfo.regmoney}</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>
                            企业简介
                        </label>
                        <textarea type="text" class="form-control" rows="3"
                                  readonly>${requestScope.einfo.eintro}</textarea>
                    </div>
                </div>
            </div>

            <div class="row" id="enEditRow" style="display: none;">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>企业信息</h3>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-outline-success" id="enSave">
                                <i class="far fa-save" style="width: 1.125em;"></i>
                                保存
                            </button>
                        </div>
                    </div>
                    <hr>
                    <form id="enForm">
                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="ename">
                                    企业名
                                </label>
                                <input type="text" class="form-control" id="ename" name="ename" required
                                       value="${requestScope.einfo.ename}">
                            </div>
                            <div class="form-group col-md">
                                <label for="industry">
                                    企业领域
                                </label>
                                <input type="text" class="form-control" id="industry" name="industry" required
                                       value="${requestScope.einfo.intustry}">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="entertype">
                                    企业类型
                                </label>
                                <input type="text" class="form-control" id="entertype" name="entertype" required
                                       value="${requestScope.einfo.entertype}">
                            </div>
                            <div class="form-group col-md">
                                <label for="artiperson">
                                    企业法人
                                </label>
                                <input type="text" class="form-control" id="artiperson" name="artiperson" required
                                       value="${requestScope.einfo.artiperson}">
                            </div>
                            <div class="form-group col-md">
                                <label for="regmoney">
                                    注册基金
                                </label>
                                <input type="number" class="form-control" id="regmoney" name="regmoney" required
                                ${requestScope.einfo.regmoney}>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="eintro">
                                企业简介
                            </label>
                            <textarea type="text" class="form-control" id="eintro" name="eintro" rows="3"
                                      required>${requestScope.einfo.eintro}</textarea>
                        </div>
                    </form>
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
    $(function () {
        $("body").niceScroll();
    });

    $('#acEdit').click(function () {
        $('#acInfoRow').hide();
        $('#acEditRow').show();
    });

    $('#acSave').click(function () {
        const form = $('#acForm')[0];
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            const data = $('#acForm').serializeJSON();
            data.action = 'modifyAcEnterInfo';
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/enterServlet',
                data: data,
                async: true,
                error: function (request) {
                    alert('连接错误，请刷新重试');
                },
                success: function () {
                    location.reload();
                }
            });
        }
    });

    $('#enEdit').click(function () {
        $('#enInfoRow').hide();
        $('#enEditRow').show();
    });

    $('#enSave').click(function () {
        const form = $('#enForm')[0];
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            const data = $('#enForm').serializeJSON();
            data.action = 'modifyEnterInfo';
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/enterServlet',
                data: data,
                async: true,
                error: function (request) {
                    alert('连接错误，请刷新重试');
                },
                success: function () {
                    location.reload();
                }
            });
        }
    });
</script>
</body>

</html>