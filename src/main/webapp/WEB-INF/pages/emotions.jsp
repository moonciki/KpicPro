<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/15 0015
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>咔哔-表情池</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/emotions.css" rel="stylesheet">
</head>
<body>
<%@include file="./common/top.jsp" %>

<div class="main">
  <center><span style="color:#ff81b1;font-size: 25px;font-weight: bold">咔哔表情池</span></center>
  <br/>
  <br/>
  <span id="bqs"></span>

    <div style="width:100%;height: 50px;float: left">
        <center>
            <button type="button" style="border: 1px solid #ff7276;color:#ff7276" class="btn btn-default" id="jz" onclick="jz()"><span class="glyphicon glyphicon-plus"></span> 加载更多表情</button>
            <br/>
            <span style="color:#ff7276;display: none" id="jzz">加载中...</span>
            <span style="color:#ff7276;display: none" id="mgd">没有更多了</span>
        </center>
    </div>

</div>
<input type="hidden" id="cur" value="0"/>
<input type="hidden" id="dsa" value="${user.id}"/>
<script src="${pageContext.request.contextPath}/static/js/emotions.js"></script>
<%@include file="./common/foot.jsp" %>
</body>
</html>
