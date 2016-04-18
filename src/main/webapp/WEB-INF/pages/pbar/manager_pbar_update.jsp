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
  <link href="${pageContext.request.contextPath}/static/css/pbar/pbar_manager.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>

<div class="main">

  <%@include file="../common/manage_pbar_left.jsp" %>
  <div class="main_02">
    <div class="main_02_title">
      <span class="glyphicon glyphicon-tags" style="color:#FF60AF"></span>
      &nbsp;<b>${pbar.name}话题信息管理</b>
    </div>
    <div class="main_02_content">

      <span class="glyphicon glyphicon-tag" style="color:#FF95CA"></span>
      &nbsp;话题基本资料<br/><br/>
      <ul class="list-group" style="color:#9D9D9D">
        <li class="list-group-item">话题名称：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="text" class="form-control" id="name" name="name" value="${pbar.name}" placeholder="请输入话题名称">
          </li>
        <li class="list-group-item">话题签名：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="text" class="form-control" id="msg" name="msg" value="${pbar.msg}" placeholder="请输入话题签名">
          </li>

        </ul>

      <span class="glyphicon glyphicon-tag" style="color:#FF95CA"></span>
      &nbsp;话题皮肤<br/><br/>
      <ul class="list-group" style="color:#9D9D9D">
        <li class="list-group-item">话题logo:
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <img src="${pbar.logo}?imageView2/1/w/89/h/89/q/95"/>
        </li>
        <input type="hidden" name="logo" id="logo" value="${pbar.logo}"/>
        <li class="list-group-item">背景图片：
          &nbsp;&nbsp;&nbsp;
          <img src="${pbar.backgroundLogo}?imageView2/1/w/680/q/95"/>
          <input type="hidden" id="background_logo" name="background_logo" value="${pbar.backgroundLogo}"/>
        </li>
      </ul>
      <input type="hidden" value="${pbar.id}" id="pbarId"/>
      <button type="button" id="submit" class="btn btn-info">
        <span class="glyphicon glyphicon-send"></span>
        &nbsp;提交
      </button>

    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pbar/manager_pbar_update.js"></script>
</body>
</html>
