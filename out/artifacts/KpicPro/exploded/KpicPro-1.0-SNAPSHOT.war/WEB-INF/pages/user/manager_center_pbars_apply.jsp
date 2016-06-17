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
  <%@include file="../common/manage_center_left.jsp"%>
  <div class="main_02">
    <div class="main_02_title">
      <span id="content_title">
        <span style="color:#FF79BC" class="glyphicon glyphicon-cog"></span>
        用户管理中心
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#FF95CA" class="glyphicon glyphicon-leaf"></span>
        我的圈子
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        圈子申请
      </span>
    </div>

    <input type="hidden" id="pager" value="0"/>
    <input type="hidden" id="userId" value="${user.id}"/>

    <div class="main_02_content">
        <c:choose>
          <c:when test="${jc <= 28}">
            <br/><br/><br/>
            <center>由于您的节操值低于<span style="color:#CD3333;font-weight: bold">28</span>，当前处于<span style="color:#CD3333;font-weight: bold">禁言</span>状态，无法申请圈子</center>
          </c:when>
          <c:otherwise>
            <div class="loading3" style="display:none"></div><br/>
            <div id="loading4" style="color:#FF79BC;display: none">申请递交中...</div>
          <span id="pbar_add_success" style="display: none;">
            <center>
              <button type="button" class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/user/all/user/subject'">
                恭喜您，您所申请的圈子已成功递交至kpic管理员，我们会在两个工作日内审核完毕，点击查看&nbsp;<span class="glyphicon glyphicon-chevron-right"></span>
              </button>
            </center>
          </span>
            <div id="zt">
              <span class="glyphicon glyphicon-align-center" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">基本信息</span>
              <ul class="list-group">
                <li class="list-group-item">
                  圈子名称：<br/><br/>
                  <input type="text" style="width: 500px" id="name" class="form-control" placeholder="请输入圈子名称"/>
                </li>
                <li class="list-group-item">
                  圈子主题色：<br/><br/>
                  <input type="radio" name="color" class="color" value="#FF9797" checked="checked"/>
                  <span class="label" style="background-color: #FF9797">#FF9797</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#00CACA"/>
                  <span class="label" style="background-color: #00CACA">#00CACA</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#6A6AFF"/>
                  <span class="label" style="background-color: #6A6AFF">#6A6AFF</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#82D900"/>
                  <span class="label" style="background-color: #82D900">#82D900</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#A6A600"/>
                  <span class="label" style="background-color: #A6A600">#A6A600</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#FF8000"/>
                  <span class="label" style="background-color: #FF8000">#FF8000</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#B87070"/>
                  <span class="label" style="background-color: #B87070">#B87070</span>
                  <br/><br/>

                  <input type="radio" name="color" class="color" value="#A5A552"/>
                  <span class="label" style="background-color: #A5A552">#A5A552</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#FF77FF"/>
                  <span class="label" style="background-color: #FF77FF">#FF77FF</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#BE77FF"/>
                  <span class="label" style="background-color: #BE77FF">#BE77FF</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#02C874"/>
                  <span class="label" style="background-color: #02C874">#02C874</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#FFD306"/>
                  <span class="label" style="background-color: #FFD306">#FFD306</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#B766AD"/>
                  <span class="label" style="background-color: #B766AD">#B766AD</span>
                  &nbsp;&nbsp;&nbsp;

                  <input type="radio" name="color" class="color" value="#6FB7B7"/>
                  <span class="label" style="background-color: #6FB7B7">#6FB7B7</span>
                  &nbsp;&nbsp;&nbsp;

                </li>
                <li class="list-group-item">
                  分类：<br/><br/>
                  <select class="form-control" id="type" style="width: 300px">
                    <c:forEach items="${allType}" var="item">
                      <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                  </select>

                </li>
                <li class="list-group-item">
                  请简单描述一下本圈子：<br/><br/>
                  <textarea class="form-control" id="msg" rows="3"></textarea>
                </li>

              </ul>

              <span class="glyphicon glyphicon-camera" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">logo上传</span>
              <span style="color:#BEBEBE;font-size: 12px"><b> *上传的图片宽度为100px × 100px 最佳，大小小于25K</b></span>
              <div class="panel panel-default">
                <div class="panel-body">
                  <div id="logo_show" style="border: solid 1px #66B3FF; height: 102px;width: 102px;">
                    <span id="scz" style="color:#ffaad5; line-height: 5.5; display: none"><center>上传中..</center></span>
                  </div>
                  <br/>
                  &nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="logo_upload" class="btn btn-info"><span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;上传</button>
                  <input type="hidden" id="logo" value=""/>
                </div>
              </div>


              <span class="glyphicon glyphicon-tags" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">请为该圈子加一些标签</span>
              <span style="color:#BEBEBE;font-size: 12px"><b> * 每个圈子最多能加4个标签</b></span>
              <div class="panel panel-default">

                <input type="hidden" value="" id="tag_value"/>

                <div class="panel-body">
                  <span id="tags_show"></span><span class="label" id="tag_close" style="display:none; background-color: #ffaad5; cursor: pointer">重新设置</span><br/>
                  <br/>
                  <input type="text" class="form-control" id="tags" placeholder="请输入标签名" style="width: 300px; float: left;">
                  &nbsp;&nbsp;
                  <button type="button" id="add_tags" class="btn btn-success">添加</button>
                  <br/>
                  <br/>

                  <input type="radio" name="tag_color" class="tag_color" checked="checked" value="#ff7575"/>
                  <span class="label" style="background-color: #ff7575">#ff7575</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;

                  <input type="radio" name="tag_color" class="tag_color" value="#FF8000"/>
                  <span class="label" style="background-color: #FF8000">#FF8000</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;

                  <input type="radio" name="tag_color" class="tag_color" value="#0066cc"/>
                  <span class="label" style="background-color: #0066cc">#0066cc</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;

                  <input type="radio" name="tag_color" class="tag_color" value="#A6A600"/>
                  <span class="label" style="background-color: #A6A600">#A6A600</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;

                  <input type="radio" name="tag_color" class="tag_color" value="#00AEAE"/>
                  <span class="label" style="background-color: #00AEAE">#00AEAE</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;

                  <input type="radio" name="tag_color" class="tag_color" value="#7274A7"/>
                  <span class="label" style="background-color: #7274A7">#7274A7</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;

                  <input type="radio" name="tag_color" class="tag_color" value="#FF60AF"/>
                  <span class="label" style="background-color: #FF60AF">#FF60AF</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;


                </div>
              </div>

              <button type="button" id="save_pbar" class="btn btn-success">递交申请</button>
            </div>
          </c:otherwise>
        </c:choose>
    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qiniu.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/manager_center_pbars_apply.js"></script>
</body>
</html>
