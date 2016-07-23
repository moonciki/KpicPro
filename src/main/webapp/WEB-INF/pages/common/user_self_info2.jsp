<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/9
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<div class="head" style="background-image: url('http://7xwibn.com1.z0.glb.clouddn.com/static/spring.png')">
  <c:choose>
    <c:when test="${user2.avater != null}">
      <div class="user_pic" style="background-image: url('${user2.avater}?imageView2/1/w/89/h/89/interlace/0/q/95')"></div>
    </c:when>
    <c:otherwise>
      <div class="user_pic" style="background-image: url('${user2.userPic}?size=89')"></div>
    </c:otherwise>
  </c:choose>

</div>

<div class="cd">
  <c:choose>
    <c:when test="${flag == 1}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-info-sign"></span> TA的基本情况</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/user/u6514${user2.id}/index.html'"><span class="glyphicon glyphicon-info-sign"></span> TA的基本情况</div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 2}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-heart-empty"></span> TA的关注</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/u6514${user2.id}/focus/subjects.html'"><span class="glyphicon glyphicon-heart-empty"></span> 关注相关</div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 4}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-indent-left"></span> TA的发帖</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/u6514${user2.id}/personality/dynamic.html'"><span class="glyphicon glyphicon-indent-left"></span> TA的发帖</div>
    </c:otherwise>
  </c:choose>

</div>

<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/common/user_self_info.js"></script>

<div class="loading">
  <br/><br/><br/><br/><br/><br/><br/><br/><br/>
  <center><span style="color:#FF60AF;font-size: 35px">加载中..</span></center>
</div>

</body>
</html>
