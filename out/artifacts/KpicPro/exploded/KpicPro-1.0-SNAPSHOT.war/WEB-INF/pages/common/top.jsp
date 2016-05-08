
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/pbar.css" rel="stylesheet">
</head>
<body>
<ol class="breadcrumb" style="padding-top:10px; width: 100%; font-size: 16px; font-family: 微软雅黑; border-bottom: 1px solid #F0F0F0; box-shadow:0px 0px 8px #FFD9EC; height: 70px; background-color: #FFF">
  <div class="top_main">

    <div class="logo" onclick="window.location.href='/'" title="咔哔社区"></div>

    <div class="top_content">
      <a href="/">主站</a>
      <a href="#">2013</a>
      <c:choose>
        <c:when test="${user != null}">
          ${user.name}
          &nbsp;&nbsp;
          <a href="${pageContext.request.contextPath}/user/u6514${user.id}/index.html" target="_blank">个人中心</a>
          &nbsp;&nbsp;
          <a href="#">消息盒子 <span id="user_news"></span></a>
          <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/top.js"></script>

        </c:when>
        <c:otherwise>
          登录
        </c:otherwise>
      </c:choose>
    </div>

  </div>


</body>
</html>
