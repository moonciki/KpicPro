<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/3/4
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <title>${user2.name}的个人首页</title>
  <meta name="keywords" content="${user2.name},个人首页,咔哔,咔哔社区" />
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/user/user_info.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/user/level.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp"%>
<div class="main">
  <%@include file="../common/user_self_info.jsp"%>
  <div class="content">
    <div class="user_jdt">
      <div class="user_jdt_unit_01">
        <div style="width: 900px;height: auto;min-height: 1px;">
        <div class="sjjdt_hh" style="width: ${(level.score/allscore)*700}px"></div>
        <div class="sjjdt_dh"></div>
          &nbsp;&nbsp;<span style="color:#FF79BC;font-size: 12px;font-weight: bold">升级进度：
          <span style="color: #64A600">${level.score}</span>
          /
          <span style="color: #009393">${allscore}</span></span>
        </div>

        <div style="width: 700px;height: auto;min-height: 1px;">
        <div class="sjjdt">
          <div class="sjjdt_c" style="width: ${(level.score/allscore)*700}px"></div>
        </div>
        </div>
      </div>
    </div>

    <div class="user_jdt">
      <div class="user_jdt_unit_01">
        <div style="width: 900px;height: auto;min-height: 1px;">
          <div class="sjjdt_hh" style="width: ${(jc/100)*700}px"></div>
          <div class="jcjdt_dh"></div>
          &nbsp;&nbsp;<span style="color:#FF79BC;font-size: 12px;font-weight: bold">节操值：<span style="font-size: 15px;color:green">${jc}</span></span>
        </div>

        <div style="width: 700px;height: auto;min-height: 1px;">
          <div class="jcjdt">
            <div class="jcjdt_c" style="width: ${(jc/100)*700}px"></div>
          </div>
        </div>
        <div class="jcjdt_js" title="点我查看节操值规则" data-toggle="modal" data-target="#myModal"></div>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 670px">
              <div class="modal-header" style="background-color: #FF79BC;color:#FFF;border-radius: 4px">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                  &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                  <b>有关节操值的解释</b>
                </h4>
              </div>
              <div class="modal-body">
                <div class="jcjs_c"></div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>

    <div class="user_info">
      <div class="user_info_content">
        <div class="user_info_box">
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-user" style="color:#ff8fb8"></span>
            &nbsp;&nbsp;用户昵称：${user2.name}
          </div>
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-stats" style="color:#ff8fb8"></span>
            &nbsp;&nbsp;用户级别：<span style="color:#FF5151"><b>${level.level}</b></span> 级
          </div>
        </div>

        <div class="user_info_box">
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-bookmark" style="color:#ff8fb8"></span>
            &nbsp;&nbsp;性别：
            <c:choose>
              <c:when test="${user2.sex == 0}">
                <div class="boy"></div>
              </c:when>
              <c:otherwise>
                <div class="girl"></div>
              </c:otherwise>
            </c:choose>
          </div>
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-bold" style="color:#ff8fb8"></span>
            &nbsp;&nbsp;生日：${user2.birth}
          </div>
        </div>

        <div class="user_info_box">
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-list-alt" style="color:#ff8fb8"></span>
            &nbsp;&nbsp;累计发帖：<span style="color:#FF60AF"><b>${user2.postNum}</b></span>
          </div>
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-record" style="color:#ff8fb8"></span>
            &nbsp;&nbsp;当前经验：<span style="color:#9F35FF"><b>${level.score}</b></span>
          </div>
        </div>

        <div class="user_info_box">
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-fire" style="color:#ff8fb8"></span>
            &nbsp;&nbsp;总粉丝量：<span style="color:#46A3FF"><b>${fansNum}</b></span>
          </div>
          <div class="user_info_box_unit">
            <span class="glyphicon glyphicon-map-marker" style="color:#ff8fb8"></span>
            &nbsp;&nbsp;现居坐标：${user2.address}
          </div>
        </div>
      </div>
      <c:if test="${is_login == true}">
        <button type="button" class="btn btn-info" style="margin-top: 15px" onclick="window.open('${pageContext.request.contextPath}/user/management/center/edit')"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;编辑个人信息</button>
      </c:if>
    </div>
  </div>
</div>

<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/user/user_info.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
