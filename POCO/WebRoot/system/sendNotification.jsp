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
    
    <title>发布系统通知</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/js/common.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/script/notifiaction.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery.pagination.js"></script>
	
  </head>
  
  <body>
  
  	<div>发布通知
  		  标题：<input type="text" placeholder="请输入标题" id="notificationTitle" name="notificationTitle"><br/>
 		 通知内容：<textarea id="notificationInfo" name="notificationInfo" cols="30" rows="3" align="center"></textarea>
  		<button id="submit">发布</button>
  	</div>
  </body>
</html>
