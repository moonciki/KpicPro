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
    <input type="hidden" id="pbarId" value="${pbar.id}"/>
    <input type="hidden" id="pbarName" value="${pbar.name}"/>
    <input type="hidden" id="page" value="0"/>
    <div class="main_02_title">
      <span class="glyphicon glyphicon-tags" style="color:#FF60AF"></span>
      &nbsp;<b>${pbar.name}小管理猿信息管理</b>
    </div>
    <div class="main_02_content">

      <table class="table table-bordered">
        <thead>
        <tr>
          <th>头像</th>
          <th>昵称</th>
          <th>等级</th>
          <th>经验</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
      
      <c:forEach items="${managers}" var="item">
        <span id="bg${item.thisId}">
          <tr>
            <td>
              <c:choose>
                <c:when test="${item.avater == null || item.avater == ''}">
                  <img src="${item.userPic}?size=40" style="border-radius: 6px;"/>
                </c:when>
                <c:otherwise>
                  <img src="${item.avater}?imageView2/1/w/40/h/40/q/95" style="border-radius: 6px;"/>
                </c:otherwise>
              </c:choose>
            </td>
            <td><a href="${pageContext.request.contextPath}/user/u6514${item.id}/index.html" target="_blank">${item.name}</a></td>
            <td><span style="font-weight: bold; color:#2975ff">${item.level}</span></td>
            <td><span style="font-weight: bold; color:#ff45b4">${item.score}</span></td>
            <td><button id="bt_${item.id}" onclick="cxzw(${item.thisId}, ${item.id})" type="button" class="btn btn-default btn-sm" style="border: 1px #ff5dbc solid;color:#ff5dbc"><span class="glyphicon glyphicon-remove-circle"></span> 撤销职位</button></td>
          </tr>
        </span>
      </c:forEach>
        </tbody>
      </table>

    </div>
  </div>
</div>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript" src = ${pageContext.request.contextPath}/static/js/pbar/manager_pbar_smalls.js></script>
</body>
</html>
