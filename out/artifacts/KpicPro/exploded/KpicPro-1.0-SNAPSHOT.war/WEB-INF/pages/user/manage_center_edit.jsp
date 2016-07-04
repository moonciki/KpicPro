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
        个人信息修改
      </span>
    </div>
    <input type="hidden" value="${user.name}" id="userName"/>
    <div class="main_02_content">
      <span class="glyphicon glyphicon-folder-open" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">个人资料</span>
      <div class="panel panel-default">
        <ul class="list-group">
          <input type="hidden" style="width: 500px" id="id" value="${user.id}"/>
          <li class="list-group-item">用户昵称：
            <span class="yz" id="yz_name_1">*输入内容不合法</span>
            <span class="yz" id="yz_name_2">*该昵称已被占用，请更换其他昵称</span>
            <input type="text" style="width: 500px" id="name" class="form-control" value="${user.name}" placeholder="请输入您的昵称(必填，请不要超过6个字符)"/>
            </li>
          <li class="list-group-item">出生年月：<span class="yz" id="yz_birth">*输入内容不合法</span>
            <input type="text" style="width: 500px" id="birth" class="form-control" value="${user.birth}" placeholder="请输入出生年月，格式：2000-01-01"/>
            </li>
          <li class="list-group-item">密码：<span class="yz" id="yz_pwd">*输入的新密码不合法</span>
            <input type="password" style="width: 500px" id="password" class="form-control" value="${user.password}" placeholder="必填，请输入您的密码(不少于6个字符，不超过20个字符)"/>
            </li>
          <li class="list-group-item">现居坐标：<span class="yz" id="yz_address">*输入内容不合法</span>
            <input type="text" style="width: 500px" id="address" class="form-control" value="${user.address}" placeholder="请输入您的所在地区，格式：省-市"/>
            </li>
        </ul>

      </div>
      <c:if test="${level.level >= 2}">
        <span class="glyphicon glyphicon-camera" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">头像管理</span>
        <span style="color:#BEBEBE;font-size: 12px"><b> *上传的图片宽度为100px × 100px 最佳，大小小于25K</b></span>
        <div class="panel panel-default">
          <div class="panel-body">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" id="touxiang" class="btn btn-info">修改头像</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="font-size: 25px; font-weight: bold; color:#ADADAD"><span class="glyphicon glyphicon-transfer"/></span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span id="touxiang_xs">
              <c:choose>
                <c:when test="${user.avater != null}">
                  <img src="${user.avater}?imageView2/1/w/100/h/100/q/95"/>
                </c:when>
                <c:otherwise>
                  <img src="${user.userPic}?size=100"/>
                </c:otherwise>
              </c:choose>
            </span>

            <span id="avater" style="display: none">
                ${user.avater}
            </span>

          </div>
        </div>
      </c:if>


      <span class="glyphicon glyphicon-pencil" style="color: #00AEAE"></span>&nbsp;&nbsp;<span style="color:#FF8F59; line-height: 2.5">个人签名</span>
      <div class="panel panel-default">
        <div class="panel-body">
          <span class="yz" id="yz_tag">*输入内容不合法</span>
          <input type="text" style="width: 770px" id="tag" class="form-control" value="${user.tag}" placeholder="非必填，请输入个性签名，字数不少于5个字，不超过20字">
        </div>
      </div>

      <button id="submit" type="button" class="btn btn-success">确认提交</button>


    </div>
  </div>
</div>
<div class="loading2">
  <div class="panel panel-default" style="width: 500px; height: 300px; margin-top: 10%; margin-left: 40%;">
    <div class="panel-heading">
      <span class="glyphicon glyphicon-bookmark" style="color: #FF60AF"></span>
      &nbsp;&nbsp;咔哔提醒
    </div>
    <div class="panel-body" style="text-align: center">
      <span style="font-size: 15px">
        <span class="glyphicon glyphicon-ok-circle" style="font-size:25px;color:#73BF00"></span>
        恭喜您，修改成功O(∩_∩)O~ 不过效果要想看到效果需要重新登录系统哦~现在请您重新登录看看效果吧~
      </span>
      <br/><br/><br/>
      <button id="submit2" type="button" class="btn btn-success">重新登录</button>
      &nbsp;&nbsp;
      <button id="submit3" type="button" class="btn btn-success">取消</button>

    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qiniu.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/manager_center_edit.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
