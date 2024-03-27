<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="el-common" prefix="el" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <title>企业列表</title>

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
    <div class="row justify-content-center mb-2">
        <div class="col-md-10 pr-0">
            <form class="form-inline" action="queryServlet" type="get">
                <div class="form-row ml-auto">
                    <input type="hidden" name="action" value="queryEnterServlet">
                    <div class="col">
                        <input class="form-control" type="search" id="search" name="keyword" placeholder="搜索"
                               required>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-outline-success" type="submit" id="submit">
                            <i class="fas fa-search"></i>
                            搜索
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-10 border rounded-lg bg-white p-4">
            <div class="row">
                <div class="col">
                    <table class="table table-hover table-striped table-responsive-md">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>邮箱</th>
                            <th>电话</th>
                            <th>账户状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${requestScope.page.data.size() > 0}">
                            <c:forEach begin="0" end="${requestScope.page.data.size() - 1}" var="i">
                                <tr data-toggle="modal" data-target="#enterModal" data-rowid="${i}">
                                    <th>${requestScope.page.data[i].eid}</th>
                                    <th>${requestScope.page.data[i].eaname}</th>
                                    <th>${requestScope.page.data[i].email}</th>
                                    <th>${requestScope.page.data[i].telephone}</th>
                                    <th>${requestScope.page.data[i].state == 0 ? "正常" : "封禁"}</th>
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

<div class="modal fade" id="enterModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-12">
                        <dl class="row" id="modal-content">

                        </dl>
                    </div>
                    <div class="w-100">
                        <hr>
                    </div>
                    <div class="col-12">
                        <dl class="row">
                            <dt class="col-sm-3">状态</dt>
                            <dd class="col-sm-9" id="state"></dd>
                            <dt class="col-sm-3">操作</dt>
                            <dd class="col-sm-9">
                                <button class="btn btn-outline-danger" onclick="oper('ban')">封禁</button>
                                <button class="btn btn-outline-primary" onclick="oper('unban')">解禁</button>
                                <button class="btn btn-outline-success" onclick="oper('pass')">通过审核</button>
                                <button class="btn btn-outline-warning" onclick="oper('deny')">驳回审核</button>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
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
        $('body').niceScroll();
    });

    $(".page-item").click(function () {
        if ($(this).hasClass('disabled') || $(this).hasClass('active')) {
            return;
        }
        const $btn = $(this).children("button");
        const pageIndex = $btn.data('index');
        location.search = '?action=listReinfoPages&currentPage=' + pageIndex;
    });

    const enterList = ${el:toJsonString(requestScope.page.data)};
    const enterInfoIndex = ['eid', 'eaname', 'email', 'telephone', 'address'];
    const enterInfoName = ['eid', '账户名', '邮箱', '电话', '地址'];

    const eninfoList = ${el:toJsonString(requestScope.eninfoList)};
    const eninfoIndex = ['ename', 'entertype', 'intustry', 'eintro', 'artiperson', 'regmoney'];
    const eninfoName = ['公司名', '公司类型', '领域', '公司简介', '法人', '注册基金'];

    let cuurrentID = -1;

    $('#enterModal').on('show.bs.modal', function (event) {
        const $button = $(event.relatedTarget)
        const rowid = $button.data('rowid')
        cuurrentID = rowid;
        const $modal = $(this)
        $modal.find('.modal-title').text(enterList[rowid].entername)
        $modal.find('#modal-content').html(enterInfo(rowid));
        $modal.find('#state').html((enterList[rowid].state ? `账号封禁，` : `账号正常，`) +
            (eninfoList[rowid].examineState === 0 ? `待审核` : (eninfoList[rowid].examineState === 1 ? `审核通过` : `审核未通过`)));
    });

    function enterInfo(rowid) {
        let temp = '';
        enterInfoIndex.forEach((key, index) => {
            temp += `<dt class="col-sm-3">${"${enterInfoName[index]}"}</dt>
                     <dd class="col-sm-9">${"${enterList[rowid][key]}"}</dd>\n`;
        });
        temp += `<div class="w-100"><hr></div>`;
        eninfoIndex.forEach((key, index) => {
            temp += `<dt class="col-sm-3">${"${eninfoName[index]}"}</dt>
                     <dd class="col-sm-9">${"${eninfoList[rowid][key]}"}</dd>\n`;
        });
        return temp;
    }

    function oper(type) {
        switch (type) {
            case 'ban':
                location.href = "${pageContext.request.contextPath}/adminServlet?action=lockAccount&type=enter&id=" + enterList[cuurrentID].eid;
                break;
            case 'unban':
                location.href = "${pageContext.request.contextPath}/adminServlet?action=unlockAccount&type=enter&id=" + enterList[cuurrentID].eid;
                break;
            case 'pass':
                location.href = "${pageContext.request.contextPath}/adminServlet?action=passEnterInfo&id=" + enterList[cuurrentID].eid;
                break;
            case 'deny':
                location.href = "${pageContext.request.contextPath}/adminServlet?action=denyEnterInfo&id=" + enterList[cuurrentID].eid;
                break;
        }
    }
</script>
</body>

</html>