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
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../common/top.jsp" %>

<div class="main">

  <%@include file="../common/manage_pbar_left.jsp" %>
  <div class="main_02">
    <div class="main_02_title">
      <span class="glyphicon glyphicon-tags" style="color:#FF60AF"></span>
      &nbsp;<b>${pbar.name}举报信息管理</b>
    </div>
    <div class="main_02_content">

      <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#home" data-toggle="tab">
          <span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;主题帖的举报信息</a>
        </li>
        <li><a href="#replyTips" data-toggle="tab"><span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;回复帖的举报信息</a></li>

        <li><a href="#shortTips" data-toggle="tab"><span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;短评的举报信息</a></li>

      </ul>
      <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="home">
          <input type="hidden" value="0" id="01_page"/>
          <input type="hidden" value="0" id="02_page"/>
          <input type="hidden" value="0" id="03_page"/>
          <input type="hidden" value="${pbar.id}" id="pbarId"/>
          <div class="panel panel-default" style="border-top: 1px #FFF solid">
            <div class="panel-body">

            <span id="topicTips">

            </span>

              <center><button type="button" disabled="true" onclick="jz()" class="btn btn-info" id="btn_jz1"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
                <br/><span id="jzz1" style="display: none">加载中..</span><span id="my1" style="display: none">没有更多了</span>
              </center>

            </div>
          </div>
        </div>
        <div class="tab-pane fade" id="replyTips">
          <div class="panel panel-default" style="border-top: 1px #FFF solid">
            <div class="panel-body">

              <span id="con2">

              </span>

              <center><button type="button" disabled="true" onclick="jz2()" class="btn btn-info" id="btn_jz2"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
                <br/><span id="jzz2" style="display: none">加载中..</span><span id="my2" style="display: none">没有更多了</span>
              </center>

            </div>
          </div>
        </div>

        <div class="tab-pane fade" id="shortTips">
          <div class="panel panel-default" style="border-top: 1px #FFF solid">
            <div class="panel-body">

              <span id="con3">

              </span>

              <center><button type="button" disabled="true" onclick="jz3()" class="btn btn-info" id="btn_jz3"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
                <br/><span id="jzz3" style="display: none">加载中..</span><span id="my3" style="display: none">没有更多了</span>
              </center>

            </div>
          </div>
        </div>
      </div>

      <script>
        $(function () {
          $('#myTab li:eq(0) a').tab('show');
        });
      </script>

    </div>
  </div>
</div>

<%@include file="../common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pbar/manager_pbar_tips.js"></script>
</body>
</html>
