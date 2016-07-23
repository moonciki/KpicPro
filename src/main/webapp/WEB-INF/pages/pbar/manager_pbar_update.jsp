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
          <input type="text" class="form-control" id="name" name="name" disabled="disabled" value="${pbar.name}" placeholder="请输入圈子名称">
          </li>
        <li class="list-group-item">圈子签名：
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="text" class="form-control" id="msg" name="msg" value="${pbar.msg}" placeholder="请输入圈子签名">
          </li>

        </ul>

      <span class="glyphicon glyphicon-tag" style="color:#FF95CA"></span>
      &nbsp;圈子皮肤<br/><br/>
      <ul class="list-group" style="color:#9D9D9D">
        <li class="list-group-item">圈子logo:
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span id="logo_img"><img src="${pbar.logo}?imageView2/1/w/89/h/89/interlace/0/q/95"/></span>
        </li>
        <input type="hidden" name="logo" id="logo" value="${pbar.logo}"/>

        <li class="list-group-item">修改圈子logo:
          <center>
            <button type="button" id="sclogo" class="btn btn-info">上传新的logo图片</button>
            <br/>
            <br/>
            <span class="badge">为了保证效果美观，请上传 <span style="color:#FFB5B5">宽：100px&nbsp;&nbsp;&nbsp;&nbsp;高：100px</span> 且大小<span style="color:#FFB5B5">小于30k</span>的图片</span>

          </center>
        </li>

        <li class="list-group-item">背景图片：
          &nbsp;&nbsp;&nbsp;
          <span id="logo_bimg"><img src="${pbar.backgroundLogo}?imageView2/1/w/670/h/144/q/95"/></span>
          <input type="hidden" id="background_logo" name="background_logo" value="${pbar.backgroundLogo}"/>
        </li>
        <li class="list-group-item">修改背景图片：
          <center>
            <button type="button" id="scblogo" class="btn btn-info">上传新的背景图片</button>
            <br/>
            <br/>
            <span class="badge">为了保证效果美观，请上传 <span style="color:#FFB5B5">宽：1100px&nbsp;&nbsp;&nbsp;&nbsp;高：244px</span> 且大小<span style="color:#FFB5B5">小于300k</span>的图片</span>
          </center>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qiniu.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pbar/manager_pbar_update.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
