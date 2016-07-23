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
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/user/manage_center.css" rel="stylesheet">
</head>
<body>

<div class="ckxq01" id="tck_well">
  <br/><br/><br/><br/><br/><br/>
  <div class="tck" style="display: block">
    <div class="panel panel-default" style="border: solid #ff809e 1px;">
      <div class="panel-body">
        <span id="tck_c"></span>
      </div>
    </div>
    <button type="button" class="btn btn-success btn-sm" onclick="tck_close()">
      关闭
    </button>
  </div>
</div>

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
        <span style="color:#FF95CA" class="glyphicon glyphicon-home"></span>
        申请上首页
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的申请
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
        <center><img src="http://7xwibn.com1.z0.glb.clouddn.com/static/page_loading.gif"/></center>
      </span>
      <span id="pageloading2" style="display:none"><center>没有更多了</center></span>
    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/home_request_list.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
