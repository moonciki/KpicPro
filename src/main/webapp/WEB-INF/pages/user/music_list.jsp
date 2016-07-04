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
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="main">
  <%@include file="../common/manage_center_left.jsp"%>
  <div class="main_02">
    <div class="main_02_title">
      <span id="content_title">
       <span style="color:#FF79BC" class="glyphicon glyphicon-cog"></span>
        用户管理中心
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#FF95CA" class="glyphicon glyphicon-music"></span>
        乐库管理
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的乐库
      </span>
    </div>
    <input id="page" value="0" type="hidden"/>
    <div class="main_02_content">

        <div class="panel panel-default">
            <div class="panel-body" style="color:#ff7575">
                <button type="button" class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/user/make/music'"><span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;上传</button>&nbsp;如果在音乐搜索中找不到你想要的音乐，就可以选择自己上传，然后将外链复制并粘贴到指定位置即可

            </div>
        </div>

      <span id="album_list">

      </span>

      <center>
        <button type="button" id="jz" class="btn btn-info">加载更多</button>
        <br/>
        <span id="load" style="display: none">加载中...</span>
        <span id="no_data" style="display: none">没有更多了</span>
      </center>

    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/music_list.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
