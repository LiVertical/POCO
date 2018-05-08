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
    
    <title>添加系统管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>/css/addAdmin.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>/css/regist.css" type="text/css" rel="stylesheet"/>	
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/control/js/common.js"></script>
  </head>
  <script>
  function addAdminUser(){
	  var loginName = $("#loginName").val();
	  var userName = $("#userName").val();
	  var params = {
			  loginName : loginName,
			  userName : userName,
	  };
	  if(loginName == ''){
		  alert("请输入账户");
		  return;
	  }
	  if(userName == ''){
		  alert("请输入姓名");
		  return;
	  }
	  if(!loginName.match(/^1[34578]\d{9}$/)){
		  alert("格式不正确");
		  return;
	  }
	  $.post(getRootPath() + "/admin/adminUserRegist.action", params, function(data){
		  if(data.returnCode == '00'){
			  alert("添加成功");
			  window.location.reload();
		  }else if(data.returnCode == '20'){
			  alert("该账户已经被注册");
		  }else{
			  alert("添加失败！");
		  }
	  });
  }
  </script>
  
  <body>
  	<div class="regist_box">
  		<div class="adminRegist"><h3>添加系统管理员</h3></div>
	  		<p>账号：<input name="loginName" id="loginName" placeholder="手机号/邮箱" maxlength="20"></p>
	  		<p>姓名：<input name="userName" id="userName" placeholder="请输入姓名" maxlength="12"></p>
  			<br/>
  		<button onclick="addAdminUser()" class="regBtn">添加</button>
  	</div>
  </body>
</html>
