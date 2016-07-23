<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/16
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pbar.name}-管理中心</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/pbar/pbar_manager.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>

<div class="main">

  <%@include file="../common/manage_pbar_left.jsp" %>
  <div class="main_02">
    <div class="main_02_title">
      <span class="glyphicon glyphicon-tags" style="color:#FF60AF"></span>
      &nbsp;<b>${pbar.name}圈子信息管理</b>
    </div>
    <div class="main_02_content">
      <span class="glyphicon glyphicon-tag" style="color:#FF95CA"></span>
      &nbsp;圈子基本资料<br/><br/>
      <ul class="list-group" style="color:#9D9D9D">
        <li class="list-group-item">圈子名称：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          ${pbar.name}</li>
        <li class="list-group-item">圈子签名：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        ${pbar.msg}</li>
        <li class="list-group-item">所属分类：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        ${pbar.type}</li>
        <li class="list-group-item">圈子标签：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <c:forEach items="${pbar.tags}" var="tag">
            <span class="glyphicon glyphicon-tag" style="color: ${tag.color}"><span style="font-family: 微软雅黑">${tag.name}</span></span>
            &nbsp;
          </c:forEach>
        </li>
        <li class="list-group-item">总帖子数：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        ${pbar.topicNum}</li>
        <li class="list-group-item">总关注数：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        ${pbar.focusNum}</li>
        <li class="list-group-item">主题颜色：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span class="label" style="background-color: ${pbar.color}">${pbar.color}</span></li>
      </ul>

      <span class="glyphicon glyphicon-tag" style="color:#FF95CA"></span>
      &nbsp;圈子皮肤<br/><br/>
      <ul class="list-group" style="color:#9D9D9D">
        <li class="list-group-item">圈子logo:
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <img src="${pbar.logo}?imageView2/1/w/89/h/89/interlace/0/q/95"/>
        </li>
        <li class="list-group-item">背景图片：
          &nbsp;&nbsp;&nbsp;
          <img src="${pbar.backgroundLogo}?imageView2/1/w/670/h/144/q/95"/>
        </li>
      </ul>
    </div>
  </div>
</div>
<%@include file="../common/foot.jsp" %>
</body>
</html>
