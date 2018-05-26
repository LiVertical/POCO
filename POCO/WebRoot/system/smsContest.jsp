<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>大赛管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css/red-datepicker.css">
	<script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>script/activity.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
	<script src="<%=basePath%>script/custom.js"></script>
	<script src="<%=basePath%>js/jquery-ui.js"></script> 
	<script src="<%=basePath%>script/smsContest.js"></script>
  </head>  
  <style>
  	.time-input{
  		height:30px;
  		width:200px;
  		border:1px solid;
  		border-radius:5px;
  		outline:none;
  		padding-left:3px;
  	}
  	.desc{
  		outline:none;
  		height:200px;
  		width:300px;
  		border:1px solid;
  		border-radius:3px;
  		resize:none;
  	}
  	.post{
		background:#00B2EE;
		border-radius:3px;
		outline:none;
		width:150px;
		height:30px;
		border:none;
		color:#fff;
		margin-top:50px;
		float:right;
}
  </style>
<body>
	<h1 style="text-align:center">发布大赛</h1>	
	<div class="container">
		<p>大赛标题：<input type="text" placeholder="请输入大赛标题" id="contestName" class='time-input'></p>
		<p>大赛概述：</p><textarea placeholder="请描述大赛....." class="desc" id="contestDesc"></textarea>
		<p>&nbsp;</p>
		开始时间：<input id="startTime" class="time-input " type="text"> --
		结束时间：<input id="endTime" class="time-input " type="text"><br/>
		<button class="post" id="post">点击发布</button>
	</div>
</body> 
</html>
