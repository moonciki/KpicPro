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
        <span style="color:#FF95CA" class="glyphicon glyphicon-user"></span>
        基本信息操作
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        个人信息
      </span>
    </div>
    <div class="main_02_content">
      <span class="glyphicon glyphicon-folder-open" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">个人资料</span>
      <div class="panel panel-default">
        <ul class="list-group">
          <li class="list-group-item">用户昵称：
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${user.name}</li>
          <li class="list-group-item">用户头像：
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <c:choose>
              <c:when test="${user.avater != null}">
                <img src="${user.avater}?imageView2/1/w/30/h/30/q/95"/>
              </c:when>
              <c:otherwise>
                <img src="${user.userPic}?size=30"/>
              </c:otherwise>
            </c:choose>


          </li>
          <li class="list-group-item">用户年龄：
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${user.age} 岁</li>
          <li class="list-group-item">用户级别：
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${level.level} 级</li>
          <li class="list-group-item">当前经验：
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${level.score}</li>
          <li class="list-group-item">粉丝数量：
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${fansNum}</li>
          <li class="list-group-item">累计发帖：
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${user.postNum}</li>
          <li class="list-group-item">现居坐标：
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${user.address}</li>
        </ul>

      </div>

      <span class="glyphicon glyphicon-pencil" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">级别信息</span>

      <div class="panel panel-default">
        <div class="panel-body">
          距离下次升级进度：<span style="font-weight: bold;font-size: 20px"><span style="color: #FF60AF">${level.score}</span>
          /
          <span style="color: #009393">${allscore}</span></span>
          &nbsp;&nbsp;当前级别：<span style="color:#FF9797;font-size: 24px;font-weight: bold;">${level.level}</span> 级
          <div class="progress progress-striped active">
            <div class="progress-bar progress-bar-success" role="progressbar"
                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                 style="width: ${(level.score/allscore)*100}%;">
              <span class="sr-only">${(level.score/allscore)*100}% 完成</span>
            </div>
          </div>

        </div>
      </div>

      <span class="glyphicon glyphicon-pencil" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">个人签名</span>
      <div class="panel panel-default">
        <div class="panel-body">
          <c:choose>
            <c:when test="${user.tag != null}">
              ${user.tag}
            </c:when>
            <c:otherwise>
              你还没有个人签名哦~
            </c:otherwise>
          </c:choose>
        </div>

      </div>



    </div>
  </div>
</div>

</body>
</html>
