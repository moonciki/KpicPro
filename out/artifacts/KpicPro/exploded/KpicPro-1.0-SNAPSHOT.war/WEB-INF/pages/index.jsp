<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/3/21
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kpic</title>
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
</head>
<body>
<h2>Kpic-----找到你的兴趣所在</h2>
<hr/>
<h3>快起来发帖，咸鱼们~</h3>

<a href="${pageContext.request.contextPath}/logout">退出</a>
<br/>
<form action="${pageContext.request.contextPath}/realogin" method="post" id="form">
  name:<input type="text" name="name" id="name"/>
  <br/>
  paswd:<input type="text" name="password" id="password"/>
  <br/>

</form>
<button id="juge">submit</button>

<br/>
<br/>
<a href="${pageContext.request.contextPath}/pbar_index.html">南阳理工学院团</a><br/>
<a href="${pageContext.request.contextPath}/pbar/pbar_add">申请团子</a>

<script type="application/javascript">
  $().ready(function(){
    $("#juge").click(function(){

      var name = $("#name").val();
      var password = $("#password").val();
      $.post("${pageContext.request.contextPath}/jugelogin", {"name":name,"password":password}, function(data){

        if(data.success){
          $("#form").submit();
        }else{
          alert("ERROR!");
        }
      });
    });
  });
</script>
</body>
</html>
