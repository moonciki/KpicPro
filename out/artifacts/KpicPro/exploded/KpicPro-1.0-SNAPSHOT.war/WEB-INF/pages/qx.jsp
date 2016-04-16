<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/2 0002
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hh</title>
</head>
<body>
<shiro:hasPermission name="user">
  <shiro:hasPermission name="add">
    <a href="${pageContext.request.contextPath}/operate/add">Add</a>
  </shiro:hasPermission>
</shiro:hasPermission>

<br/>
<shiro:hasPermission name="user">
  <shiro:hasPermission name="del">
    <a href="${pageContext.request.contextPath}/operate/add2">Add2</a>
  </shiro:hasPermission>
</shiro:hasPermission>

</body>
</html>
