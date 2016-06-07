
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){

      $(".def-nav,.info-i").hover(function(){
        $(this).find(".pulldown-nav").addClass("hover");
        $(this).find(".pulldown").show();
      },function(){
        $(this).find(".pulldown").hide();
        $(this).find(".pulldown-nav").removeClass("hover");
      });

    });
  </script>
  <link href="${pageContext.request.contextPath}/static/css/pbar.css" rel="stylesheet">
</head>
<body>
<ol class="breadcrumb" style="padding-top:10px; width: 100%; font-size: 16px; font-family: 微软雅黑; border-bottom: 1px solid #F0F0F0; box-shadow:0px 0px 8px #FFD9EC; height: 70px; background-color: #FFF">
  <div class="top_main">

    <div class="logo" onclick="window.location.href='/'" title="咔哔社区"></div>


    <input type="text" class="form-control" id="top_kw"
           placeholder="输入你感兴趣的话题" style="margin-left:30px;margin-top:8px;border:2px #ffaad5 solid;width:460px;display:inline;">
    <button type="button" id="top_s" class="btn btn-info" style="margin-top:-3px;background-color:#ffaad5;border:1px solid #ffaad5;display: inline"><span class="glyphicon glyphicon-search"></span>&nbsp;搜索</button>

    <div class="top_content">
      <c:choose>
        <c:when test="${user != null}">
          &nbsp;&nbsp;&nbsp;&nbsp;

          <div class="hd-main clearfix" style="width:250px;" id="header">
            <div class="info">
              <ul>
                <li class="info-i user-name has-pulldown">
                  <a class="dropdown-toggle" data-toggle="dropdown" style="cursor:pointer; text-decoration: none;">
                    <c:choose>
                      <c:when test="${user.avater != null}">
                        <img src="${user.avater}?imageView2/2/w/35/h/35/q/95" style="margin-top:-10px;border-radius: 100px;border: 1px solid #ffaad5;box-shadow:0px 0px 15px #ffaad5;">
                      </c:when>
                      <c:otherwise>
                        <img src="${user.userPic}?size=35" style="margin-top:-10px; border-radius: 100px;border: 1px solid #ffaad5;box-shadow:0px 0px 15px #ffaad5;">
                      </c:otherwise>
                    </c:choose>
                  </a>
                  <div class="pulldown user-info">
                    <em class="arrow"></em>
                    <div class="content">
                      <span class="li"><a href="">个人资料</a></span>
                      <span class="li"><a href="">购买容量</a></span>
                      <span class="li"><a href="">免费扩容</a></span>
                      <span class="li"><a href="">会员中心</a></span>
                      <span class="separate-li no-height"></span>
                      <span class="li"><a href="javascript:;" id="signout">退出</a></span>
                    </div>
                  </div>
                </li>

                <li class="info-i user-name has-pulldown">
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <span style="font-size:16px;color:#3c70ff;font-family:Microsoft Yahei;"><span class="glyphicon glyphicon-bell" style="color:#D9B300"></span>&nbsp;消息 <span id="user_news"></span></span>
                  <div class="pulldown user-info">
                    <em class="arrow"></em>
                    <div class="content">
                      <span class="li"><a href="">个人资料</a></span>
                      <span class="li"><a href="">购买容量</a></span>
                      <span class="li"><a href="">免费扩容</a></span>
                      <span class="li"><a href="">会员中心</a></span>
                      <span class="separate-li no-height"></span>
                    </div>
                  </div>
                </li>
                <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/top.js"></script>

                &nbsp;&nbsp;&nbsp;&nbsp;
                <span class="glyphicon glyphicon-cog" style="color:#ff7066"></span>&nbsp;<a href="${pageContext.request.contextPath}/user/management/center" title="我的管理中心" target="_blank">管理中心</a>

              </ul>
            </div>
          </div>
        </c:when>
        <c:otherwise>
          <button type="button" class="btn btn-info btn-sm">
            登录
          </button>
          <button type="button" class="btn btn-warning btn-sm">
            注册
          </button>
        </c:otherwise>
      </c:choose>
    </div>
<script type="text/javascript">
  $("#top_s").click(function(){
    var kw = $("#top_kw").val().trim();
    if(kw == ""){
      alert("要输入内容哦~");
      return;
    }
    window.open("/kabi/search/kw_"+kw);
  });
</script>
  </div>
<%--</ol>--%>


</body>
</html>
