<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/3/4
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <title>${user.name}的个人首页</title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/user/user_info.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/user/level.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="main">

  <%@include file="../common/user_self_info.jsp"%>

  <div class="content">

    <div class="user_jdt">
      <div class="user_jdt_unit_01">
        <span class="glyphicon glyphicon-leaf" style="color:#82D900"></span>
        &nbsp;&nbsp;距离下次升级进度（<span style="color: #FF60AF"><b>${level.score}</b></span>
        /
        <span style="color: #009393"><b>${allscore}</b></span>）
      </div>
      <div class="user_jdt_unit_02">
        <div class="progressbar" data-perc="${(level.score/allscore)*100}">
          <div class="bar color3"><span></span></div>
          <div class="label"><span></span></div>
        </div>
      </div>
    </div>


    <div class="user_info">
      <div class="user_info_content">
        <div class="user_info_box">
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-user"></span>
            &nbsp;&nbsp;用户昵称：${user2.name}
          </div>
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-stats"></span>
            &nbsp;&nbsp;用户级别：<span style="color:#FF5151"><b>${level.level}</b></span> 级
          </div>
        </div>

        <div class="user_info_box">
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-list-alt"></span>
            &nbsp;&nbsp;累计发帖：<span style="color:#FF60AF"><b>${user2.postNum}</b></span>
          </div>
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-record"></span>
            &nbsp;&nbsp;当前经验：<span style="color:#9F35FF"><b>${level.score}</b></span>
          </div>
        </div>

        <div class="user_info_box">
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-fire"></span>
            &nbsp;&nbsp;总粉丝量：<span style="color:#46A3FF"><b>${fansNum}</b></span>
          </div>
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-map-marker"></span>
            &nbsp;&nbsp;现居坐标：${user2.address}
          </div>
        </div>
      </div>

      <c:if test="${is_login == true}">
        <button type="button" class="btn btn-info" onclick="window.open('${pageContext.request.contextPath}/user/management/center/edit')"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;编辑个人信息</button>
      </c:if>


    </div>


  </div>

</div>

<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/user/user_info.js"></script>
</body>
</html>
