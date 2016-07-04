<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/27 0027
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${user.name}的管理中心</title>

  <link href="${pageContext.request.contextPath}/static/css/user/manage_center.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/user/album_list.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="main">
  <%@include file="../common/manage_center_left.jsp"%>
  <div class="pic_big">
    <input type="hidden" value="${user.id}" id="userId"/>
    <input type="hidden" value="${jc}" id="jc"/>
    <div class="pl_content">
      <span class="badge pull-right" id="closeh" title="关闭" style="background-color: #FF5151; cursor: pointer;"><span class="glyphicon glyphicon-remove"></span></span>
      <input type="hidden" value="0" id="gzht_page"/>
      <input type="hidden" value="" id="album_id"/>
      <div class="well"><span class="glyphicon glyphicon-send" style="color:#FF95CA"></span>&nbsp;&nbsp;将<b>“<span id="point_title"></span>”</b>发布到：</div>

        <span id="self_pbar">

      </span>

      <center><button type="button" onclick="jz(true)" class="btn btn-info" id="btn_jz1"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
        <br/><span id="jzz1" style="display: none">加载中..</span><span id="my1" style="display: none">没有更多了</span>
      </center>

    </div>
  </div>
  <div class="main_02">
    <div class="main_02_title">
      <span id="content_title">
        <span style="color:#FF79BC" class="glyphicon glyphicon-cog"></span>
        用户管理中心
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#FF95CA" class="glyphicon glyphicon-list-alt"></span>
        文章管理
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的文章
      </span>
    </div>
    <input id="page" value="0" type="hidden"/>
    <div class="main_02_content">


      <span id="album_list"></span>

      <center>
        <button type="button" id="jz" class="btn btn-info">加载更多</button>
        <br/>
        <span id="load" style="display: none">加载中...</span>
        <span id="no_data" style="display: none">没有更多了</span>
      </center>

    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/blog_list.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
