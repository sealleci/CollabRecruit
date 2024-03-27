<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>简历列表</title>

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
        <div class="col-md-10 border rounded-lg bg-white p-4">
            <div class="row">
                <div class="col">
                    <table class="table table-hover table-striped table-responsive-md">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>学历</th>
                            <th>岗位名</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${requestScope.page.data.size() > 0 }" >
	                        <c:forEach begin="0" end="${requestScope.page.data.size() - 1}" var="i">
	                            <tr onclick="info(${requestScope.page.data[i].did})">
	                                <th>${requestScope.userInfoList[i].username}</th>
	                                <th>${requestScope.resumeList[i].education}</th>
	                                <th>${requestScope.reinfoList[i].rePositionName}</th>
	                            </tr>
	                        </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-auto">
                    <nav>
                        <ul class="pagination">
                            <li class="page-item<c:if test="${requestScope.page.currentPage <= 1}"> disabled</c:if>">
                                <button class="page-link" data-index="${requestScope.page.currentPage - 1}">
                                    <span aria-hidden="true">&laquo;</span>
                                </button>
                            </li>

                            <c:forEach var="i" begin="1" end="${requestScope.page.totalPages}">
                                <li class="page-item<c:if test="${requestScope.page.currentPage == i}"> active</c:if>">
                                    <button class="page-link" data-index="${i}">${i}</button>
                                </li>
                            </c:forEach>

                            <li class="page-item <c:if test="${requestScope.page.currentPage >= requestScope.page.totalPages}"> disabled</c:if>">
                                <button class="page-link" data-index="${requestScope.page.currentPage + 1}"
                                        aria-label="下一页">
                                    <span aria-hidden="true">&raquo;</span>
                                </button>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
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

    function info(did){
        window.open('${pageContext.request.contextPath}/showServlet?action=resume&did='+did,'_blank')
    };

    $(".page-item").click(function () {
        if ($(this).hasClass('disabled') || $(this).hasClass('active')) {
            return;
        }
        const $btn = $(this).children("button");
        const pageIndex = $btn.data('index');
        location.search = '?action=listResumePage&currentPage=' + pageIndex;
    });
</script>
</body>

</html>