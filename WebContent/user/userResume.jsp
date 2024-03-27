<%--@elvariable id="viewResume" type="com.rai.domain.vo.ViewResume"--%>
<%--@elvariable id="resume" type="com.rai.domain.po.Resume"--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>个人简历</title>

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
            <div class="row">
                <div class="col">
                    <c:choose>
                        <c:when test="${resume.examineState==0}">
                            <div class="alert alert-warning text-center" role="alert">
                                <i class="fas fa-tasks"></i> 简历待审核
                            </div>
                        </c:when>
                        <c:when test="${resume.examineState==1}">

                        </c:when>
                        <c:when test="${resume.examineState==2}">
                            <div class="alert alert-danger text-center" role="alert">
                                <i class="fas fa-exclamation-triangle"></i> 简历审核不通过！
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="row" id="infoRow">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>用户简历</h3>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-outline-primary ml-auto" id="edit">
                                <i class="far fa-edit"></i>
                                编辑
                            </button>
                        </div>
                    </div>
                    <hr>
                    <h4>工作经历</h4>
                    <div class="form-row">
                        <div class="form-group col-md">
                            <label>
                                曾任公司
                            </label>
                            <span class="inputHolder">${viewResume.company}</span>
                        </div>
                        <div class="form-group col-md">
                            <label>
                                职务
                            </label>
                            <span class="inputHolder">${viewResume.position}</span>
                        </div>
                        <div class="form-group col-md-3">
                            <label>
                                年薪
                            </label>
                            <span class="inputHolder">${viewResume.salary}</span>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md">
                            <label>
                                始于
                            </label>
                            <span class="inputHolder">${viewResume.workStartTime}</span>
                        </div>
                        <div class="form-group col-md">
                            <label>
                                终于
                            </label>
                            <span class="inputHolder">${viewResume.workEndTime}</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>
                            主要工作
                        </label>
                        <textarea type="text" class="form-control" rows="3" readonly>${viewResume.duty}</textarea>
                    </div>

                    <h4>教育经历</h4>
                    <div class="form-row">
                        <div class="form-group col-md">
                            <label for="schoolName">
                                毕业于
                            </label>
                            <span class="inputHolder">${viewResume.schoolName}</span>
                        </div>
                        <div class="form-group col-md">
                            <label for="education">
                                学历
                            </label>
                            <span class="inputHolder">${viewResume.education}</span>
                        </div>
                        <div class="form-group col-md">
                            <label for="professional">
                                专业
                            </label>
                            <span class="inputHolder">${viewResume.professional}</span>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md">
                            <label for="entranceTime">
                                入学于
                            </label>
                            <span class="inputHolder">${viewResume.entranceTime}</span>
                        </div>
                        <div class="form-group col-md">
                            <label for="graduateTime">
                                毕业于
                            </label>
                            <span class="inputHolder">${viewResume.graduateTime}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" id="editRow" style="display: none;">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h3>用户简历</h3>
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
                        <h4>工作经历</h4>
                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="rcompany">
                                    曾任公司
                                </label>
                                <input type="text" class="form-control" id="rcompany" name="rcompany" required
                                       value="${viewResume.company}">
                            </div>
                            <div class="form-group col-md">
                                <label for="position">
                                    职务
                                </label>
                                <input type="text" class="form-control" id="position" name="position" required
                                       value="${viewResume.position}">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="salary">
                                    年薪
                                </label>
                                <input type="number" class="form-control" id="salary" name="salary" required
                                       value="${viewResume.salary}">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="workStartTime">
                                    始于
                                </label>
                                <input type="date" class="form-control" id="workStartTime" name="workStartTime"
                                       required value="${viewResume.workStartTime}">
                            </div>
                            <div class="form-group col-md">
                                <label for="workEndTime">
                                    终于
                                </label>
                                <input type="date" class="form-control" id="workEndTime" name="workEndTime"
                                       required value="${viewResume.workEndTime}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="duty">
                                主要工作
                            </label>
                            <textarea type="text" class="form-control" id="duty" name="duty" rows="3"
                                      required>${viewResume.duty}</textarea>
                        </div>

                        <h4>教育经历</h4>
                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="schoolName">
                                    毕业于
                                </label>
                                <input type="text" class="form-control" id="schoolName" name="schoolName" required
                                       value="${viewResume.schoolName}">
                            </div>
                            <div class="form-group col-md">
                                <label for="education">
                                    学历
                                </label>
                                <input type="text" class="form-control" id="education" name="education" required
                                       value="${viewResume.education}">
                            </div>
                            <div class="form-group col-md">
                                <label for="professional">
                                    专业
                                </label>
                                <input type="text" class="form-control" id="professional" name="professional"
                                       required value="${viewResume.professional}">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md">
                                <label for="entranceTime">
                                    入学于
                                </label>
                                <input type="date" class="form-control" id="entranceTime" name="entranceTime"
                                       required value="${viewResume.entranceTime}">
                            </div>
                            <div class="form-group col-md">
                                <label for="graduateTime">
                                    毕业于
                                </label>
                                <input type="date" class="form-control" id="graduateTime" name="graduateTime"
                                       required value="${viewResume.graduateTime}">
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

    $('#edit').click(function () {
        $('#infoRow').hide();
        $('#editRow').show();
    });

    $('#save').click(function () {
        const form = $('#userForm')[0];
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            const data = $('#userForm').serializeJSON();
            data.action = 'modifyResume';
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
</script>
</body>

</html>