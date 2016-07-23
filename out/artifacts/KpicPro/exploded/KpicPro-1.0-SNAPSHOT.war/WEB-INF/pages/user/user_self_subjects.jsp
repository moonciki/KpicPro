<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <title>${user2.name}的关注</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/user/user_info.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/user/level.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="main">

  <%@include file="../common/user_self_info.jsp"%>

  <div class="content">
    <input type="hidden" id="userId" value="${user2.id}"/>
    <ul id="myTab" class="nav nav-tabs">
      <li class="active"><a href="#home" data-toggle="tab">
        <span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;关注的圈子</a>
      </li>
      <li><a href="#ios" data-toggle="tab"><span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;关注的人</a></li>

      <li><a href="#fans" data-toggle="tab"><span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;粉丝们</a></li>

    </ul>
    <div id="myTabContent" class="tab-content">
      <div class="tab-pane fade in active" id="home">
        <input type="hidden" value="0" id="gzht_page"/>
        <input type="hidden" value="0" id="gzr_page"/>
        <input type="hidden" value="0" id="fans_page"/>
        <div class="panel panel-default" style="border-top: 1px #FFF solid">
          <div class="panel-body">

            <span id="gzht">

            </span>

            <center><button type="button" onclick="jz(true)" class="btn btn-info" id="btn_jz1"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
            <br/><span id="jzz1" style="display: none">加载中..</span><span id="my1" style="display: none">没有更多了</span>
            </center>

          </div>
        </div>
      </div>
      <div class="tab-pane fade" id="ios">
        <div class="panel panel-default" style="border-top: 1px #FFF solid">
          <div class="panel-body">

            <span id="gzr">

            </span>

            <center><button type="button" onclick="jz2(true)" class="btn btn-info" id="btn_jz2"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
              <br/><span id="jzz2" style="display: none">加载中..</span><span id="my2" style="display: none">没有更多了</span>
            </center>

          </div>
        </div>
      </div>

      <div class="tab-pane fade" id="fans">
        <div class="panel panel-default" style="border-top: 1px #FFF solid">
          <div class="panel-body">

            <span id="fensi">

            </span>

            <center><button type="button" onclick="jz3(true)" class="btn btn-info" id="btn_jz3"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
              <br/><span id="jzz3" style="display: none">加载中..</span><span id="my3" style="display: none">没有更多了</span>
            </center>

          </div>
        </div>
      </div>

    </div>
    <script>
      $(function () {
        $('#myTab li:eq(0) a').tab('show');
      });
    </script>

  </div>

</div>

<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/user/user_self_subjects.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
