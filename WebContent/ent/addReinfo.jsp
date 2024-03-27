<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>新增岗位</title>

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
            <div class="row" id="EditRow">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>岗位详情</h3>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-outline-success" id="Save">
                                <i class="far fa-save" style="width: 1.125em;"></i>
                                保存
                            </button>
                        </div>
                    </div>
                    <hr>
                    <form id="reinfoForm" novalidate>
                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="name">
                                    岗位名
                                </label>
                                <input type="text" class="form-control" id="name" name="rePositionName" required>
                            </div>
                            <div class="form-group col-md">
                                <label for="reSlary">
                                    年薪
                                </label>
                                <input type="text" class="form-control" id="reSlary" name="reSalary" required>
                            </div>
                            <div class="form-group col-md">
                                <label for="reWorkYears">
                                    工作时间
                                </label>
                                <input type="text" class="form-control" id="reWorkYears" name="reWorkYears"
                                       required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="reDepartment">
                                    所属部门
                                </label>
                                <input type="text" class="form-control" id="reDepartment" name="reDepartment"
                                       required>
                            </div>
                            <div class="form-group col-md">
                                <label for="reSubordinates">
                                    下属职员数量
                                </label>
                                <input type="text" class="form-control" id="reSubordinates" name="reSubordinates"
                                       required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="reLightPoint">
                                    岗位特色
                                </label>
                                <textarea type="text" class="form-control" id="reLightPoint" name="reLightPoint"
                                          rows="3" required></textarea>
                            </div>

                            <div class="form-group col-md">
                                <label for="reJobDesc">
                                    工作需求
                                </label>
                                <textarea type="text" class="form-control" id="reJobDesc" name="reJobDesc" rows="3"
                                          required></textarea>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="reEdu">
                                    所需学历
                                </label>
                                <input type="text" class="form-control" id="reEdu" name="reEducation" required>
                            </div>
                            <div class="form-group col-md">
                                <label for="rePro">
                                    所需专业
                                </label>
                                <input type="text" class="form-control" id="rePro" name="reProfessional" required>
                            </div>
                            <div class="form-group col-md">
                                <label for="rePersonNum">
                                    需求人数
                                </label>
                                <input type="text" class="form-control" id="rePersonNum" name="rePersonNum"
                                       required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="reCity">
                                    城市
                                </label>
                                <input type="text" class="form-control" id="reCity" name="reCity" required>
                            </div>
                            <div class="form-group col-md">
                                <label for="reArea">
                                    地区
                                </label>
                                <input type="text" class="form-control" id="reArea" name="reArea" required>
                            </div>
                            <div class="form-group col-md">
                                <label for="reReportPerson">
                                    面试官
                                </label>
                                <input type="text" class="form-control" id="reReportPerson" name="reReportPerson"
                                       required>
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
<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>


<script>
    $(function () {
        $("body").niceScroll();
    });

    $('#Edit').click(function () {
        $('#InfoRow').hide();
        $('#EditRow').show();
    });

    $('#Save').click(function () {
        const form = $('#reinfoForm')[0];
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            $('#enInfoRow').show();
            $('#enEditRow').hide();

            const data = $('#reinfoForm').serializeJSON();
            data.action = 'publishReinfo';
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/enterServlet',
                data: data,
                async: true,
                error: function (request) {
                    alert('连接错误，请刷新重试');
                },
                success: function () {
                    if (confirm("保存成功，即将关闭")) {
                        window.close();
                    }
                }
            });
        }
    });
</script>
</body>

</html>