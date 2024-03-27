<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>用户信息</title>

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
            <div class="row" id="infoRow">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>用户信息</h3>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-outline-primary ml-auto" id="edit">
                                <i class="far fa-edit"></i>
                                编辑
                            </button>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label>
                            姓名
                        </label>
                        <span class="inputHolder">${info.username}</span>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-8">
                            <label>
                                Email
                            </label>
                            <span class="inputHolder">${info.email}</span>
                        </div>
                        <div class="form-group col-md-4">
                            <label>性别</label>
                            <span class="inputHolder">${info.gender}</span>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md">
                            <label>
                                出生日期
                            </label>
                            <span class="inputHolder">${info.borndate}</span>
                        </div>
                        <div class="form-group col-md">
                            <label>
                                电话
                            </label>
                            <span class="inputHolder">${info.telephone}</span>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label>
                                城市
                            </label>
                            <span class="inputHolder">${info.city}</span>
                        </div>
                        <div class="form-group col-md">
                            <label>
                                地址
                            </label>
                            <span class="inputHolder">${info.address}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" id="editRow" style="display: none;">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>用户信息</h3>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-outline-success" id="save">
                                <i class="far fa-save" style="width: 1.125em;"></i>
                                保存
                            </button>
                        </div>
                    </div>
                    <hr>
                    <form id="userForm">
                        <div class="form-group">
                            <label for="userName">
                                姓名
                            </label>
                            <input type="text" class="form-control" id="userName" name="username" required
                                   value="${info.username}">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label for="userEmail">
                                    Email
                                </label>
                                <input type="email" class="form-control" id="userEmail"
                                       readonly value="${info.email}">
                            </div>

                            <div class="form-group col-md-4">
                                <label>性别</label>

                                <div class="d-flex justify-content-around"
                                     style="height: calc(1.5em + .75rem + 2px);">
                                    <div class="custom-control custom-radio custom-control-inline my-auto">
                                        <input type="radio" id="gender1" name="gender"
                                               class="custom-control-input" value="男"
                                               <c:if test='${info.gender == "男"}'>checked</c:if>>
                                        <label class="custom-control-label" for="gender1">男</label>
                                    </div>
                                    <div class="custom-control custom-radio custom-control-inline my-auto">
                                        <input type="radio" id="gender2" name="gender"
                                               class="custom-control-input" value="女"
                                               required <c:if test='${info.gender == "女"}'>checked</c:if>>
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
                                <input type="date" class="form-control" id="userDate" name="borndate" required
                                       value="${info.borndate}">
                            </div>
                            <div class="form-group col-md">
                                <label for="userTel">
                                    电话
                                </label>
                                <input type="tel" class="form-control" id="userTel" name="telephone" required
                                       value="${info.telephone}">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="userCity">
                                    城市
                                </label>
                                <input type="text" class="form-control" id="userCity" name="city" required
                                       value="${info.city}">
                            </div>
                            <div class="form-group col-md">
                                <label for="userAddr">
                                    地址
                                </label>
                                <input type="text" class="form-control" id="userAddr" name="address" required
                                       value="${info.address}">
                            </div>
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
<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>

<script>
    $('#edit').click(function () {
        $('#infoRow').hide();
        $('#editRow').show();
    });

    $('#save').click(function () {
        const form = $('#userForm')[0];
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            // $('#infoRow').show();
            // $('#editRow').hide();

            const data = $('#userForm').serializeJSON();
            data.action = 'modifyUserInfo';
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/userServlet',
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

    const logState = ${!empty sessionScope.logged};
    $(function () {
        if (!logState) {
            alert("请先登录！");
            location.href = '${pageContext.request.contextPath}/index.jsp';
        }
    });
</script>
</body>

</html>