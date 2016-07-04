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
        制作云图集
      </span>
    </div>
    <div class="main_02_content">
      <span id="html2" style="display: none">
        <button type="button" class="btn btn-danger btn-lg btn-block" onclick="window.location.href='/user/list/album'">
        <span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;恭喜您，您的图集已生成，请点击查看
        </button>
      </span>
      <span id="html">
        <div class="well">
      <span style="color:#00AEAE;font-size:16px"><span class="glyphicon glyphicon-info-sign" style="color:#FF60AF"></span>
        建议：上传图片前尽量用专业工具美化并处理一下图片，并保证单张图片大小不超过1M
      </span></div>
      <c:choose>
        <c:when test="${jc <= 28}">
          <center>目前您的节操值低于<span style="font-weight: bold;color:red">28</span>，处于禁言状态，无法制作图集</center>
        </c:when>
        <c:otherwise>
          <ul class="list-group">
            <input type="hidden" id="id" value="${user.id}"/>
            <li class="list-group-item">图集名称
              <input type="text" id="name" placeholder="请输入图集标题，不能为空，最少5个字符，最多36个字符" class="form-control"/>
            </li>
            <li class="list-group-item">图集描述
              <textarea id="msg" class="form-control" placeholder="请输入图集描述，不能为空，最少5个字符，最多200个字符" rows="3"></textarea>
            </li>
            <li class="list-group-item">封面：
              <input type="hidden" id="imageUrl" value=""/>
              <button type="button" id="fm_upload" class="btn btn-primary">
                <span class="glyphicon glyphicon-cloud-upload"></span>
                上传封面
              </button>
              &nbsp;&nbsp;&nbsp;
          <span style="color:#ff7575">
            <span class="glyphicon glyphicon-info-sign"></span>
            如果不上传则默认图集第一张图片为封面
          </span><br/>
              <span id="fm_url"></span>

          <span id="upload_loading1" style="display: none">
          <center>
            <img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg">
            <br/>
            <span style="font-weight: bold;color:#FF60AF">上传中...</span>
          </center></span>

            </li>
          </ul>



          当前第
          <span id="already_num" style="font-size: 20px;font-weight: bold;color:#ff7575;font-style: italic">1</span>
          张图片
          <div class="panel panel-default">
            <div class="panel-body">
              请输入图片描述：
              <textarea class="form-control" id="picContent_1" rows="3"></textarea>
              <br/>
              <center>
            <span id="picBtn_1">
              <button id="picBtn1" type="button" class="btn btn-info">
                <span class="glyphicon glyphicon-cloud-upload"></span>
                上传图片
              </button>
            </span>
            <span id="picUrl_1">
              <input id="picUrl_1_content" value="" type="hidden"/>
            </span>
              </center>
            </div>
          </div>

      <span id="pic_xl">

        <%@include file="../common/album_pic_list.jsp"%>

      </span>

          <center>
        <span style="font-size: 15px;">
        还可以添加
        <span id="sy_num" style="font-size: 20px;font-weight: bold;color:#ff7575;font-style: italic">29</span>
        张图片
        </span>
          </center>
          <br/>
          <button type="button" id="jxtj" class="btn btn-success btn-lg btn-block">
            <span class="glyphicon glyphicon-camera"></span>
            继续添加图片
          </button>

          <button type="button" id="done" class="btn btn-primary btn-lg btn-block">
            <span class="glyphicon glyphicon-send"></span>
            完成
          </button>



          <input type="hidden" value="1" id="already_numt"/>
          <input type="hidden" value="29" id="sy_numt"/>
        </c:otherwise>
      </c:choose>
      </span>
    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qiniu.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/make_album.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
