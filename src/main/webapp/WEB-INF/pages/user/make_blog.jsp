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

  <link href="${pageContext.request.contextPath}/static/css/user/manage_center.css" rel="stylesheet"/>

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config2.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>

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
        <span style="color:#FF95CA" class="glyphicon glyphicon-list-alt"></span>
        文章管理
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        写文章
      </span>
    </div>
    <div class="main_02_content">
      <span id="html" style="display: none">
        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/user/list/blog'" class="btn btn-danger btn-lg btn-block">
          <span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;恭喜您，文章已提交完成，请点击查看
        </button>
      </span>
      <span id="html2">
        <div class="well">
          <span style="color:#00AEAE;font-size:14px">
          <span class="glyphicon glyphicon-info-sign" style="color:#FF60AF"></span>
          说明：可以在此写个人文章，写好的文章自己可以按需要发布到已关注的话题下
          </span>
        </div>

        <input type="text" class="form-control" id="title" placeholder="请输入博文标题">
        <br/>
        <script type="text/plain" id="myEditor" name="content" style="width:806px;height:340px;"></script>

        <br/>

        <button type="button" id="tj" class="btn btn-primary">
          <span class="glyphicon glyphicon-send"></span>
          提交
        </button>

      </span>
    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/make_blog.js"></script>

</body>
</html>