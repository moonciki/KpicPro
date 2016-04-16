
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/pbar.css" rel="stylesheet">
</head>
<body>
<ol class="breadcrumb" style="padding-top:10px; width: 100%; font-size: 16px; font-family: 微软雅黑; border-bottom: 1px solid #F0F0F0; box-shadow:0px 0px 8px #FFD9EC; height: 50px; background-color: #FFF">
  <li><a href="#">Home</a></li>
  <li><a href="#">2013</a></li>
  <c:choose>
    <c:when test="${user != null}">
      <li class="active">${user.name}
        &nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/user/${user.id}/info.html">个人中心</a>
        &nbsp;&nbsp;
        <a href="#">消息盒子 <span class="badge" style="background-color: #FF79BC" id="user_news"></span></a>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/top.js"></script>
      </li>
    </c:when>
    <c:otherwise>
      <li class="active">登录</li>
    </c:otherwise>
  </c:choose>

</ol>

</body>
</html>
