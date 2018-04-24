<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>申请活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/activity.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/pagination.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/red-datepicker.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/script/activity.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
	<script src="<%=basePath%>/js/jquery-ui.js"></script>
	<script>
		 function loadSrc(src,id){
	    	  $("#mainFrame").attr("height",100);
	    	  $("#mainFrame").attr("src",src);
	    	  $.cookie("id", id);
	      }
	      function iframeHeight() {
				var ifm = document.getElementById("mainFrame");
				var subWeb = document.frames ? document.frames["mainFrame"].document :ifm.contentDocument;
				if (ifm != null && subWeb != null) {
					ifm.height = subWeb.body.scrollHeight;
				}
				window.parent.window.iframeHeight();
		  }
		
	</script>	 
  </head>
  
<body>
 <div class="main" style='min-height:300px'>
	<div class="content">
		<h2 style="text-align:center;margin-bottom:30px;">活动申请</h2>
		活动标题：<input type="text" id="activityName"><br/>
		<p>活动介绍：</p>
		<textarea class="desc" id="activityDesc"></textarea><br/>
		开始时间：<input id="startTime" class="time-input " type="text"> --
		结束时间：<input id="endTime" class="time-input " type="text"><br/>
		<button class="apply" id="apply">点击申请</button>
	</div>
 </div>
</body>
</html>
