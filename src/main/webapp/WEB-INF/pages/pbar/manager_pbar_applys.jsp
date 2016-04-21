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
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../common/top.jsp" %>

<div class="main">

  <%@include file="../common/manage_pbar_left.jsp" %>
  <div class="main_02">
    <input type="hidden" id="pbarId" value="${pbar.id}"/>
    <input type="hidden" id="page" value="0"/>
    <div class="main_02_title">
      <span class="glyphicon glyphicon-tags" style="color:#FF60AF"></span>
      &nbsp;<b>${pbar.name}小管理猿申请信息管理</b>
    </div>
    <div class="main_02_content">

      <span id="content">

      </span>
      <center><button type="button" disabled="true" onclick="jz()" class="btn btn-info" id="btn_jz"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
        <br/><span id="jzz" style="display: none">加载中..</span><span id="my" style="display: none">没有更多了</span>
      </center>
    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pbar/manager_pbar_applys.js"></script>
</body>
</html>
