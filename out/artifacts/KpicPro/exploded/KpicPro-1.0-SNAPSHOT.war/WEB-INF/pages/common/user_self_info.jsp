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
<div class="head" style="background-image: url('http://7xs5lv.com1.z0.glb.clouddn.com/static/spring.png')">
  <c:choose>
    <c:when test="${user.avater != null}">
      <div class="user_pic" style="background-image: url('${user.avater}?imageView2/1/w/89/h/89/q/95')"></div>
    </c:when>
    <c:otherwise>
      <div class="user_pic" style="background-image: url('${user.userPic}?size=89')"></div>
    </c:otherwise>
  </c:choose>

</div>

<div class="cd">
  <c:choose>
    <c:when test="${flag == 1}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-info-sign"></span> 我的基本情况</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/user/info.html'"><span class="glyphicon glyphicon-info-sign"></span> 我的基本情况</div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 2}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-heart-empty"></span> 关注相关</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/user/focus/subjects.html'"><span class="glyphicon glyphicon-heart-empty"></span> 关注相关</div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 4}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-indent-left"></span> 个性·动态</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/user/personality/dynamic.html'"><span class="glyphicon glyphicon-indent-left"></span> 个性·动态</div>
    </c:otherwise>
  </c:choose>

  <div id = "msg_center" class="cd_dy"><span class="glyphicon glyphicon-cog"></span> 进入管理中心>></div>
</div>

<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/common/user_self_info.js"></script>

<div class="loading">
  <br/><br/><br/><br/><br/><br/><br/><br/><br/>
  <center><span style="color:#FF60AF;font-size: 35px">加载中..</span></center>
</div>

</body>
</html>
