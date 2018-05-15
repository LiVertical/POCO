<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.util.UUID"%>
<%
UUID uuid = UUID.randomUUID(); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Login</title>
    
    <link rel="shortcut icon" type="image/x-icon" href="img/images/fav-icon.png" media="screen" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>/css/login.css" type="text/css" rel="stylesheet"/>		
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/login.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
</head>

<body>
 <div class="login">
	<form action="admin/adminUserLogin.action" method="post">
		<h1 class="text-center">登录POCO</h1>
		<input type="text" class="loginName" name="loginName" id="username" placeholder="用户名" /> 
		<input class="pwd" type="password" name="loginPass" id="password" placeholder="密码" />
		<div class="codeBox">
			<div class="codeInput">
				<input class="verCode" type="text" id="codeInput" name="securityCode" placeholder="请输入验证码" /> 
				<input type="hidden" name="code" value="<%=uuid%>"/>
			</div>
			<div class="code">
				<img src="<%=basePath%>admin/generateCode.action" onclick="this.src=this.src+'?code=<%=uuid%>'" title="点击图片刷新验证码" />
			</div>
		</div>
		<input class="subBtn" value="登录" type="submit" id="loginBtn">
				<br/>
	</form>
</div>
</body>
</html>
