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
        <span style="color:#FF95CA" class="glyphicon glyphicon-picture"></span>
        云图集管理
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        上传表情包图
      </span>
    </div>
    <div class="main_02_content">

      <span id="emotion_list">



      </span>

      <input type="hidden" id="page" value="0"/>

      <center>
        <button type="button" id="jz" class="btn btn-info">加载更多</button>
        <br/>
        <span id="load" style="display: none">加载中...</span>
        <span id="no_data" style="display: none">没有更多了</span>
      </center>

      <br/><br/>
      <div class="well">

        <input type="text" class="form-control" id="title" placeholder="请输入表情名称（选填）">
        <input type="hidden" value="" id="url"/>
        <br/>
        <button type="button" id="emotion_upload" class="btn btn-primary">
          <span class="glyphicon glyphicon-cloud-upload"></span>
          上传表情包图片
        </button>
        <br/>
        <span id="upload_loading" style="display:none;padding-left: 100px">
            <img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg" style="width: 30px"/>
            &nbsp;上传中...
          </span>
        <span id="img_url">

        </span>
        <br/>
        <span id="an"></span>

      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qiniu.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/make_emotion.js"></script>

</body>
</html>
