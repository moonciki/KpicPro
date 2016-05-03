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
  <input type="hidden" value="ttp.updateTime DESC" id="orderBy"/>
  <%@include file="../common/manage_center_left.jsp"%>
  <div class="main_02">
    <div class="main_02_title">
      <span id="content_title">
        <span style="color:#FF79BC" class="glyphicon glyphicon-cog"></span>
        用户管理中心
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#FF95CA" class="glyphicon glyphicon-th-list"></span>
        历史帖子浏览
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的回复帖
      </span>
    </div>
    <div class="main_02_content">

      <div class="panel panel-default" style="border: 1px solid #E0E0E0">
        <div class="panel-body">
          <span class="glyphicon glyphicon-sort" style="color: #64A600"></span>&nbsp;&nbsp;按条件排序：
          <span style="font-size: 18px">
          &nbsp;&nbsp;&nbsp;&nbsp;<span class="badge xz"  id="sjd" style="background-color: #FF60AF">按发布时间倒序</span>
          &nbsp;&nbsp;&nbsp;&nbsp;<span class="badge wxz" id="sjz">按发布时间正序</span>
          &nbsp;&nbsp;&nbsp;&nbsp;<span class="badge wxz" id="hfl">按回复量</span>
          </span>
        </div>
      </div>


        <div class="panel panel-default">
          <div class="panel-body">
            <div class="loading3"></div>

            <span id="topic_post_area"></span>

          </div>
        </div>

      <center><button type="button" class="btn btn-info" id="jz"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button></center>
      <br/>
      <span style="display: none" id="jz_load">
        <center><img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/page_loading.gif"/></center>
      </span>
      <span id="pageloading2" style="display:none"><center>没有更多了</center></span>
    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/manage_center_reply.js"></script>
</body>
</html>
