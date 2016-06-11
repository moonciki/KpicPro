<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/16
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pbar/cd.js"></script>
</head>
<body>
<div class="loading">
  <div class="loading_jz"></div>
</div>


<div class="main_01">
  <div class="main_01_title"><span class="glyphicon glyphicon-leaf" style="color:#FF95CA"></span>&nbsp;&nbsp;${pbar.name}</div>

  <div class="main_01_title2">
    <span class="glyphicon glyphicon-cog" style="color:#FF9797"></span>&nbsp;&nbsp;话题基本信息管理
  </div>
  <c:choose>
    <c:when test="${flag == 1}">
      <div class="main_01_unit_point">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        话题基本信息
      </div>
    </c:when>
    <c:otherwise>
      <div class="main_01_unit" onclick="window.location.href='${pageContext.request.contextPath}/subject/manager/sub4615${pbar.id}'">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        话题基本信息
      </div>
    </c:otherwise>
  </c:choose>

  <c:if test="${role == '1'}">
  <c:choose>
    <c:when test="${flag == 2}">
      <div class="main_01_unit_point">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        编辑基本信息
      </div>
    </c:when>
    <c:otherwise>
      <div class="main_01_unit" onclick="window.location.href='${pageContext.request.contextPath}/subject/manager/upd4615${pbar.id}'">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        编辑基本信息
      </div>
    </c:otherwise>
  </c:choose>
  </c:if>
  <div class="main_01_title2">
    <span class="glyphicon glyphicon-signal" style="color:#FF9797"></span>&nbsp;&nbsp;话题统计
  </div>

  <c:choose>
    <c:when test="${flag == 66}">
      <div class="main_01_unit_point">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        访问量统计
      </div>
    </c:when>
    <c:otherwise>
      <div class="main_01_unit" onclick="window.location.href='${pageContext.request.contextPath}/subject/manager/tj4615${pbar.id}__'">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        访问量统计
      </div>
    </c:otherwise>
  </c:choose>

  <div class="main_01_title2"><span class="glyphicon glyphicon-flag" style="color:#FF9797"></span>&nbsp;&nbsp;举报事务处理</div>
  <c:choose>
    <c:when test="${flag == 3}">
      <div class="main_01_unit_point">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        查看举报信息
      </div>
    </c:when>
    <c:otherwise>
      <div class="main_01_unit" onclick="window.location.href='${pageContext.request.contextPath}/subject/manager/tip4615${pbar.id}'">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        查看举报信息
      </div>
    </c:otherwise>
  </c:choose>

  <c:if test="${role == '1'}">
  <div class="main_01_title2"><span class="glyphicon glyphicon-edit" style="color:#FF9797"></span>&nbsp;&nbsp;申请事务处理</div>
  <c:choose>
    <c:when test="${flag == 4}">
      <div class="main_01_unit_point">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        小管理猿申请表
      </div>
    </c:when>
    <c:otherwise>
      <div class="main_01_unit" onclick="window.location.href='${pageContext.request.contextPath}/subject/manager/apply4615${pbar.id}'">
        <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
        小管理猿申请表
      </div>
    </c:otherwise>
  </c:choose>
  </c:if>

  <c:if test="${role == '1'}">
  <div class="main_01_title2"><span class="glyphicon glyphicon-user" style="color:#FF9797"></span>&nbsp;&nbsp;小管理猿们</div>
    <c:choose>
      <c:when test="${flag == 41}">
        <div class="main_01_unit_point">
          <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
          小管理猿信息管理
        </div>
      </c:when>
      <c:otherwise>
        <div class="main_01_unit" onclick="window.location.href='${pageContext.request.contextPath}/subject/manager/small4615${pbar.id}'">
          <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
          小管理猿信息管理
        </div>
      </c:otherwise>
    </c:choose>
  </c:if>

  <c:if test="${role == '1'}">
  <div class="main_01_title2"><span class="glyphicon glyphicon-fire" style="color:#FF9797"></span>&nbsp;&nbsp;推广&活动</div>
  <div class="main_01_unit">
    <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
    活动设置
  </div>
  <div class="main_01_unit">
    <span class="glyphicon glyphicon-tag" style="color:#73BF00"></span>
    推广设置
  </div>
  </c:if>
  <div class="main_01_foot"></div>

</div>
</body>
</html>
