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

  <link href="${pageContext.request.contextPath}/static/css/user/manage_center.css" rel="stylesheet"/>

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
        <span style="color:#FF95CA" class="glyphicon glyphicon-home"></span>
        申请上首页
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        写申请
      </span>
    </div>
    <div class="main_02_content">
      <span id="html" style="display: none">
        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/user/home/list'" class="btn btn-danger btn-lg btn-block">
          <span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;恭喜您，申请已经递交，请耐心等待站长审核，详情请点击查看
        </button>
      </span>
      <span id="html2">
        <div class="well">
          <span style="color:#00AEAE;font-size:14px">
          <span class="glyphicon glyphicon-info-sign" style="color:#FF60AF"></span>
          说明：如果你觉得站内的有些资源很好（或者自己写的东西很好），就可以通过这里进行申请上首页展示
          </span>
        </div>

        <c:choose>
          <c:when test="${jc <= 28}">
            <center>目前您的节操值低于<span style="font-weight: bold;color:red;">28</span>，处于禁言状态，无法写申请</center>
          </c:when>
          <c:otherwise>

            <span class="request_font">
              选择首页板块：<br/>
            <input type="radio" name="type_op" id="type1"
                   value="1" checked> 轮播图
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="type_op" id="type2"
                   value="2"> 热门圈子
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="type_op" id="type3"
                   value="3"> 优秀图集
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="type_op" id="type4"
                   value="4"> 精彩内容
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="type_op" id="type5"
                   value="5"> 牛人榜

            <br/><br/></span>
            <span id="type_c_1" class="request_font">
              请在下面的文本框内输入您想要放入首页轮播的内容【站内任意优秀内容】的链接地址（可以自荐哦）
            </span>
            <br/>
            <input type="text" class="form-control" id="url" placeholder="请在此输入链接，链接不能为空，最少5个字符，不能超出200个字符">
            <br/><br/>
            <span id="type_c_2" class="request_font">
              请在下面文本框里写一下自己为什么要对该内容在首页进行轮播
            </span>
            <br/>
            <textarea class="form-control" id="msg" placeholder="请在此输入申请理由，不能为空，最少10个字符，最多不要超出500个字符" rows="3"></textarea>
            <br/><br/>
            <button type="button" id="tj" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span>
              提交</button>
          </c:otherwise>
        </c:choose>
      </span>
    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/make_home_request.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>