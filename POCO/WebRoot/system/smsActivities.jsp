<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>活动管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
  </head>  
<body>
	<ul class="nav nav-tabs">
	  <li class="active"><a href="#">Home</a></li>
	  <li><a href="#">发布通知</a></li>
	  <li><a href="#">查看已发布通知</a></li>
	  <li><a href="#">VB.Net</a></li>
	  <li><a href="#">Java</a></li>
	  <li><a href="#">PHP</a></li>
   </ul>
</body> 
</html>
