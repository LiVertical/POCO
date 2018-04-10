<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审批活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>/css/addAdmin.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>/css/regist.css" type="text/css" rel="stylesheet"/>	
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/regist.js"></script>
	<script src="<%=basePath%>/control/js/common.js"></script>

  </head>
  
  <body>
  	<div class="regist_box">
  		<div class="adminRegist"><h3>添加系统管理员</h3></div>
	  		<input name="loginName" id="loginName" placeholder="账号">
	  		<input name="loginPass" id="loginPwd" placeholder="密码">
	  		<input name="userName" id="userName" placeholder="昵称">
  			<br/>
  		<button onclick="regist()" class="regBtn">注册</button>
  	</div>
  </body>
</html>
