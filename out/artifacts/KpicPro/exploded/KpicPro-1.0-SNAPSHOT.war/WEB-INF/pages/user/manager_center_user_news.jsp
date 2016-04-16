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

<input type="hidden" value="0" id="ckxq_page"/>
<input type="hidden" value="" id="ckxq_replyId"/>
<input type="hidden" value="${num}" id = "news_num"/>

<div class="ckxq01">
  <br/><br/><br/><br/>
  <div class="ckxq02">
    <div class="cw" onclick="closeInfo()" title="关闭"></div>
    <div class="well" style="border-top: 8px solid #FF359A">

      <span class="label label-warning" style="background-color: #FF79BC">对应主题帖</span>&nbsp;&nbsp;
      <span style="font-size: 14px">
      <span id="ckxq_topic_user">

      </span>
      <br/><br/>
      <span id="ckxq_title"></span></span>
      </div>
    <div class="panel panel-default" style="margin-bottom: 0px; border-top: 5px solid #FF8F59">
      <div class="panel-body">
        <span class="label label-warning">对应回复帖</span>&nbsp;&nbsp;

          <span style="font-size: 14px" id="ckxq_reply_user">

          </span>

        <div class="ckxq_reply">

          <br/>
          <span id="ckxq_reply_content"></span>
        </div>
      </div>
    </div>

    <div class="yb2"></div>
    <div class="panel panel-default" style="margin-top:20px;border:1px #FFF4C1 solid;background-color: #FFF4C1">
      <div class="panel-body">
        <span style="font-size: 18px; font-weight: bold; color:#FF8000">
          短评&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-up"></span></span>
        <br/>
        <span style="color:#FF95CA">---------------------------------------------------------------------------------------------------------------------</span>

        <span id="ckxq_short_list">

        </span>

        <br/><br/>
        <center>
          <button type="button" class="btn btn-info" onclick="ckxq_jz()" id="btn_ckxq"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
          <br/>
          <span id="ckxq_nodata" style="display: none">没有更多了</span>
          <span id="ckxq_load" style="display: none">加载中..</span>
        </center>

      </div>
    </div>

  </div>
</div>

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
        <span style="color:#FF95CA" class="glyphicon glyphicon-bell"></span>
        消息系统
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        查看回复消息
      </span>
    </div>
    <div class="main_02_content">
      <div class="panel panel-default">
        <div class="panel-body">

          <div class="panel panel-default">
            <div class="panel-body">
              图例：<span class="label" style="color:#6C6C6C; border: 1px #FFB5B5 solid; background-color: #FFECEC">表示最新消息（红框淡红底）</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="label" style="color:#6C6C6C; border: 1px #d0d0d0 solid; background-color: #FFF">表示旧消息（灰框白底）</span>
            </div>
          </div>

          <div class="loading3"></div>

          <span id="topic_post_area" style="color:#9D9D9D"></span>

        </div>
      </div>

      <center><button type="button" class="btn btn-info" onclick="more()"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button></center>
      <br/>
      <span style="display: none" id="jz_load">
        <center><img src="${pageContext.request.contextPath}/static/images/page_loading.gif"/></center>
      </span>
      <span id="pageloading2" style="display:none"><center>没有更多了</center></span>
    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/manager_center_user_news.js"></script>
</body>
</html>
