<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <title>${user.name}的过往发帖</title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/user/user_info.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/user/level.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="main">

  <%@include file="../common/user_self_info.jsp"%>

  <div class="content">
    <input type="hidden" id="userId" value="${user2.id}"/>
    <input type="hidden" value="0" id = "page"/>
    <input type="hidden" value="ttp.updateTime DESC" id="orderBy"/>
    <div class="panel panel-default" style="border: 1px solid #E0E0E0">
      <div class="panel-body">
        <span class="glyphicon glyphicon-sort" style="color: #64A600"></span>&nbsp;&nbsp;按条件排序：
          <span style="font-size: 18px">
          &nbsp;&nbsp;&nbsp;&nbsp;<span class="badge xz"  id="rd" style="background-color: #FF60AF">按热度排序</span>
          &nbsp;&nbsp;&nbsp;&nbsp;<span class="badge wxz" id="sjd">按发布时间倒序</span>
          &nbsp;&nbsp;&nbsp;&nbsp;<span class="badge wxz" id="sjz">按发布时间正序</span>
          &nbsp;&nbsp;&nbsp;&nbsp;<span class="badge wxz" id="hfl">按回复量</span>
          </span>
      </div>
    </div>
    <div class="loading3"></div>
      <span id="topic_post_area">

      </span>
    <center><button type="button" class="btn btn-info" id="jz"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button></center>
    <br/>
      <span style="display: none" id="jz_load">
        <center><img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/page_loading.gif"/></center>
      </span>
    <span id="pageloading2" style="display:none"><center>没有更多了</center></span>

  </div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/manage_center_topic.js"></script>

</body>
</html>
