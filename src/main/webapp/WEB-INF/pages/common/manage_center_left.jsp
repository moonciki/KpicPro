<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/3/29
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/user/cd.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/user/cd.js"></script>
</head>
<body>
<div class="main_01">
  <div class="cd_head">
    <span style="color:#FF79BC" class="glyphicon glyphicon-cog"></span>
    <b>用户管理中心</b>
  </div>
  <div class="cd_title">
    <span style="color:#FF95CA" class="glyphicon glyphicon-user"></span>
    基本信息操作
  </div>

  <c:choose>
    <c:when test="${flag == 1}">
      <div class="cd_unit_now">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        个人信息
      </div>
    </c:when>
    <c:otherwise>
      <div class="cd_unit cd_unit" onclick="window.location.href='${pageContext.request.contextPath}/user/management/center'">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        个人信息
      </div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 2}">
      <div class="cd_unit_now">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        修改个人信息
      </div>
    </c:when>
    <c:otherwise>
      <div class="cd_unit" onclick="window.location.href='${pageContext.request.contextPath}/user/management/center/edit'">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        修改个人信息
      </div>
    </c:otherwise>
  </c:choose>


  <div class="cd_title2">
    <span style="color:#FF95CA" class="glyphicon glyphicon-th-list"></span>
    历史帖子浏览
  </div>

  <c:choose>
    <c:when test="${flag == 3}">
      <div class="cd_unit_now">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的主题帖
      </div>
    </c:when>
    <c:otherwise>
      <div class="cd_unit" onclick="window.location.href='${pageContext.request.contextPath}/user/management/center/topics'">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的主题帖
      </div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 4}">
      <div class="cd_unit_now">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的回复帖
      </div>
    </c:when>
    <c:otherwise>
      <div class="cd_unit" onclick="window.location.href='${pageContext.request.contextPath}/user/management/center/replys'">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的回复帖
      </div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 5}">
      <div class="cd_unit_now">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的短评
      </div>
    </c:when>
    <c:otherwise>
      <div class="cd_unit" onclick="window.location.href='${pageContext.request.contextPath}/user/management/center/shortreplys'">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的短评
      </div>
    </c:otherwise>
  </c:choose>
  <div class="cd_title2">
    <span style="color:#FF95CA" class="glyphicon glyphicon-bell"></span>
    消息系统
  </div>
  <c:choose>
    <c:when test="${flag == 6}">
      <div class="cd_unit_now">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        查看回复消息
      </div>
    </c:when>
    <c:otherwise>
      <div class="cd_unit" onclick="window.location.href='${pageContext.request.contextPath}/user/all/user/news'">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        查看回复消息
      </div>
    </c:otherwise>
  </c:choose>
  <div id="p7" class="cd_unit">
    <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
    系统公共消息
  </div>
  <div class="cd_title2">
    <span style="color:#FF95CA" class="glyphicon glyphicon-envelope"></span>
    私信系统
  </div>
  <div id="p8" class="cd_unit">
    <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
    查看私信
  </div>
  <div class="cd_title2">
    <span style="color:#FF95CA" class="glyphicon glyphicon-picture"></span>
    云图管理
  </div>
  <div id="p9" class="cd_unit">
    <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
    我的云相册
  </div>
  <div id="p10" class="cd_unit">
    <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
    我的云图集
  </div>
  <div class="cd_title2">
    <span style="color:#FF95CA" class="glyphicon glyphicon-leaf"></span>
    我的话题
  </div>
  <c:choose>
    <c:when test="${flag == 11}">
      <div class="cd_unit_now">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        话题管理
      </div>
    </c:when>
    <c:otherwise>
      <div class="cd_unit" onclick="window.location.href='${pageContext.request.contextPath}/user/all/user/subject'">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        话题管理
      </div>
    </c:otherwise>
  </c:choose>
  <c:choose>
    <c:when test="${flag == 12}">
      <div class="cd_unit_now">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        话题申请
      </div>
    </c:when>
    <c:otherwise>
      <div class="cd_unit" onclick="window.location.href='${pageContext.request.contextPath}/user/subject/apply'">
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        话题申请
      </div>
    </c:otherwise>
  </c:choose>

  <div class="cd_unit_blank"></div>

</div>

<div class="loading">
  <div class="loading_jz"><span style="font-size:18px;color:#FF79BC"><b>载入中...</b></span></div>
</div>
</body>
</html>
