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
  <link href="${pageContext.request.contextPath}/static/css/user_self_info.css" rel="stylesheet">
</head>
<body>
<input id="sx_userId" type="hidden" value=""/>
<div class="pic_big" id="pl_sx">
  <div class="pic_big_tip">
    <span style="font-size: 16px;font-family: 微软雅黑"><b>私信发送</b></span>
    <br/><br/>
    <span style="color:#0055aa;font-weight: bold">想对TA说点什么？</span>
    <textarea id="sx_msg" class="form-control" rows="5"></textarea>
    <br/>
    <button type="button" class="btn btn-primary" id="sx_submit">发送</button>
    <button type="button" class="btn btn-primary" id="sx_close">取消</button>
    &nbsp;&nbsp;<span id="sx_load" style="display: none; color:#9D9D9D; font-size: 14px"><img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg" style="width: 30px; height: 30px"/>&nbsp;提交中..</span>
  </div>
</div>

<div class="head" style="background-image: url('http://7xs5lv.com1.z0.glb.clouddn.com/static/spring.png')">
  <c:choose>
    <c:when test="${user2.avater != null}">
      <div class="user_pic" style="background-image: url('${user2.avater}?imageView2/1/w/89/h/89/q/95')"></div>
    </c:when>
    <c:otherwise>
      <div class="user_pic" style="background-image: url('${user2.userPic}?size=89')"></div>
    </c:otherwise>
  </c:choose>
  <c:if test="${is_login == false}">
    <br/><br/><br/><br/><br/><br/>
    <center>
    <span id="isgz">
      <button type="button" id="gz" onclick="gz()" class="btn btn-danger"><span class="glyphicon glyphicon-plus-sign"></span> 关注</button>
    </span>

      <button type="button" onclick="sx(${user2.id})" class="btn btn-primary"><span class="glyphicon glyphicon-envelope"></span> 发私信</button>
    </center>
  </c:if>

</div>

<div class="cd">
  <c:choose>
    <c:when test="${flag == 1}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-info-sign"></span> 基本资料</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/user/u6514${user2.id}/index.html'"><span class="glyphicon glyphicon-info-sign"></span> 基本资料</div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 2}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-heart-empty"></span> 关注相关</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/u6514${user2.id}/focus/subjects.html'"><span class="glyphicon glyphicon-heart-empty"></span> 关注相关</div>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${flag == 4}">
      <div class="cd_dy cd_dy_now"><span class="glyphicon glyphicon-indent-left"></span> 过往发帖</div>
    </c:when>
    <c:otherwise>
      <div class="cd_dy wx" onclick="window.location.href = '${pageContext.request.contextPath}/u6514${user2.id}/history/topic.html'"><span class="glyphicon glyphicon-indent-left"></span> 过往发帖</div>
    </c:otherwise>
  </c:choose>

  <c:if test="${is_login == true}">
    <div id = "msg_center" class="cd_dy"><span class="glyphicon glyphicon-cog"></span> 进入管理中心>></div>
  </c:if>

</div>

<input type="hidden" id="userId1" value="${user.id}"/>
<input type="hidden" id="userId2" value="${user2.id}"/>

<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/common/user_self_info.js"></script>

<div class="loading">
  <br/><br/><br/><br/><br/><br/><br/><br/><br/>
  <center><span style="color:#FF60AF;font-size: 35px">加载中..</span></center>
</div>

</body>
</html>
