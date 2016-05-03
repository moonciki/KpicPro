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
        上传音乐
      </span>
    </div>
    <div class="main_02_content">
      <span id="html" style="display: none">
        <button type="button" class="btn btn-danger btn-lg btn-block">
          <span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;恭喜您，音乐已经上传成功，请点击查看
        </button>
      </span>
      <span id="html2">
        <div class="well">
          <span style="color:#00AEAE;font-size:14px">
          <span class="glyphicon glyphicon-info-sign" style="color:#FF60AF"></span>
          说明：乐库音乐用来上传一些在音乐搜索中找不到而您又很想要分享的音乐，音频文件不要超过10M，由于本站不支持转码，请您上传标准的Mp3文件
            谢谢您的合作 O(∩_∩)O哈哈~
          </span>
        </div>


      <ul class="list-group">
        <input type="hidden" style="width: 500px" id="id" value="${user.id}"/>
        <li class="list-group-item">音乐名称
          <input type="text" style="width: 500px" id="name" class="form-control"/>
        </li>
        <li class="list-group-item">音乐作者：
          <input type="text" style="width: 500px" id="songer" class="form-control"/>
        </li>
        <li class="list-group-item">上传音乐：

          <button type="button" id="music_upload" class="btn btn-info">
            <span class="glyphicon glyphicon-cloud-upload"></span>
            请上传MP3文件
          </button>
          <br/>
          <span id="upload_loading" style="display:none;padding-left: 100px">
            <img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg" style="width: 30px"/>
            &nbsp;上传中...
          </span>
          <br/>
          <input type="text" value="" style="width: 500px" id="musicUrl" disabled="disabled" class="form-control"/>
        </li>
        <li class="list-group-item">
          <button type="button" id="tj" class="btn btn-primary">
            <span class="glyphicon glyphicon-send"></span>
            提交
          </button>
        </li>
      </ul>
      </span>
    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qiniu.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/make_music.js"></script>

</body>
</html>