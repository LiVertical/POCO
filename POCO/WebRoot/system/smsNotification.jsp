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
    
    <title>系统通知</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>

  </head>
  
  <body>
  	<div>已发布的通知</div>
  	<div>发布通知
  		  标题：<input type="text" placeholder="请输入标题" id="notifiactionTitle" name="notifiactionTitle"><br/>
 		 通知内容：<textarea id="notifiactionInfo" name="notifiactionInfo" cols="30" rows="3" align="center"></textarea>
  		<button id="submit">发布</button>
  	</div>
  	
  </body>
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script> 
  <script type="text/javascript" src="<%=basePath%>/js/common.js"></script> 
  <script type="text/javascript" src="<%=basePath%>/script/notifiaction.js"></script>
</html>
