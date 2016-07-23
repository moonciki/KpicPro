<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/26 0026
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>咔哔圈子</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/pbar/type_result.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="main">
  <c:choose>
    <c:when test="${typeId ==null}">
      <span class="curr label label-default" onclick="new_result(null)" id = "all">全部</span>
    </c:when>
    <c:otherwise>
      <span class="pt label label-default" onclick="new_result(null)" id = "all">全部</span>
    </c:otherwise>
  </c:choose>
  <c:forEach items="${types}" var="type">
    <c:choose>
      <c:when test="${type.id == typeId}">
        <span class="curr label label-default" onclick="new_result(${type.id})" id = "tp${type.id}">${type.name}</span>
      </c:when>
      <c:otherwise>
        <span class="pt label label-default" onclick="new_result(${type.id})" id = "tp${type.id}">${type.name}</span>
      </c:otherwise>
    </c:choose>
  </c:forEach>


  <div style="margin-top: 30px">
    <span id="pbars"></span>
    <div style="width: 1100px;margin-top: 30px; float: left;">
      <center>
        <button type="button" id="jz" onclick="jz(false)" class="btn btn-default" style="color:#ff68ae; border: 1px solid #ff68ae;"><span class="glyphicon glyphicon-plus"></span> 加载更多</button>
        <br/>
        <span id="no_data" style="display:none;color:#ff81b1;font-size: 14px">没有更多了</span>
      </center>
    </div>
  </div>
</div>
<input type="hidden" id="page" value="0"/>
<input type="hidden" id="typeId" value="${typeId}"/>
<script src="${pageContext.request.contextPath}/static/js/pbar/type_result.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
