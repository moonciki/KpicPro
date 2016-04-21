<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/8
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<div class="head" style="background-image: url('${pbar.backgroundLogo}');">
  <div class="head_01_center">
    <input id="gz_pbarId" type="hidden" value="${pbar.id}"/>
    <input id="gz_num" type="hidden" value="${pbar.focusNum}"/>
    <div class="head_01_center_01">
      <br/><br/>
      <center>
        <img src="${pbar.logo}?imageView2/1/w/100/h/100/q/95" style="border:solid ${pbar.color} 2px;box-shadow:0px 0px 30px ${pbar.color};border-radius: 8px;" alt=""/>
      </center>
    </div>
    <div class="head_01_center_02" style="line-height: 1.5">
      <br/>
      <span style="font-size: 18px; font-family: 微软雅黑"><b>${pbar.name}</b></span>
      <br/>
      <p style="font-family: 微软雅黑">
        <span class="glyphicon glyphicon-bookmark" style="color: #FF60AF;font-size: 10px"></span>
            <span style="font-size: 10px">分类：<span style="color: #008200">${pbar.type}</span>
                &nbsp;&nbsp;
                <span class="glyphicon glyphicon-duplicate" style="color: #FF60AF"></span>
                <span style="font-size: 10px">帖子数：<span style="color:orange">${pbar.topicNum}</span></span>
            <br/>
                标签：
              <c:forEach items="${pbar.tags}" var="tag">
                <span class="glyphicon glyphicon-tag" style="color: ${tag.color}"><span style="font-family: 微软雅黑">${tag.name}</span></span>
                &nbsp;
              </c:forEach>
            </span>
      </p>
      <p style="font-family: 微软雅黑">${pbar.msg}</p>
    </div>
    <center>


        <span id="isgz_pbar"></span>

      <c:choose>
          <c:when test="${user != null}">
            <c:if test="${user.id == pbar. userId}">
              <button type="button" onclick="window.open('${pageContext.request.contextPath}/subject/manager/sub4615${pbar.id}')" style="font-family: 微软雅黑" class="btn btn-primary">进入话题管理页&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></button>
            </c:if>
            <c:if test="${role != null and role == 2}">
                <button type="button" onclick="window.open('${pageContext.request.contextPath}/subject/manager/sub4615${pbar.id}')" style="font-family: 微软雅黑" class="btn btn-primary">进入话题管理页&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></button>
            </c:if>
          </c:when>
          <c:otherwise>
          </c:otherwise>
        </c:choose>
    </center>

  </div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/pbar.js"></script>

</div>
</body>
</html>
