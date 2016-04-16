<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/3/31
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${user.name}的管理中心</title>
  <link href="${pageContext.request.contextPath}/static/css/user/manage_center.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="main">
  <input type="hidden" value="0" id = "page"/>
  <%@include file="../common/manage_center_left.jsp"%>
  <div class="main_02">
    <div class="main_02_title">
      <span id="content_title">
        <span style="color:#FF79BC" class="glyphicon glyphicon-cog"></span>
        用户管理中心
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#FF95CA" class="glyphicon glyphicon-leaf"></span>
        我的话题
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        话题管理
      </span>
    </div>

    <input type="hidden" id="pager" value="0"/>
    <input type="hidden" id="userId" value="${user.id}"/>

    <div class="main_02_content">

      <div class="panel panel-default">

        <div class="panel-body">

          <div class="loading3" id="load"></div>
          <div class="no_data" id="nodata" style="display: none"></div>
          <span id="topic_post_area">

          </span>



        </div>
      </div>

      <center><button type="button" class="btn btn-info" id="load_more" onclick="more()"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button></center>
      <br/>
      <span style="display: none" id="jz_load">
        <center><img src="${pageContext.request.contextPath}/static/images/page_loading.gif"/></center>
      </span>
      <span id="pageloading2" style="display:none"><center>没有更多了</center></span>
    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/manager_center_pbars.js"></script>
</body>
</html>
