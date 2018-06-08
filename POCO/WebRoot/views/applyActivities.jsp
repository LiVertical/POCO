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
  </head>
  
<body data-user_id='${param.userId  }'>
<div class="header">
		<ul>
			<li><a href="<%=basePath%>/views/activities.jsp">活动</a></li>
	 	</ul>
	 	<ul style="float:right;padding-right:66px">
		 <s:if test="%{#session.isEmpty()}">
				<li><a href="<%=basePath%>/user/userLogin.action">登录</a></li>
		 </s:if>
		 <s:else>
			 	<li onclick="gotoUpload()">发作品</li>
				<li><img id="userImg" style="height:30px;width:30px;margin-top:9px;" src="<%=basePath%>/${sessionScope.APP_USERINFO_SESSION_KEY.userImg}">
					<ul class="loginCenter" style="display:none">
							<li onclick="gotoMyWorks('${sessionScope.loginName }')">我的空间</li>
							<li><a href="<%=basePath%>/views/accountManage.jsp">账号管理</a></li>
							<li><a href="<%=basePath%>/views/notifications.jsp">系统通知</a></li>
							<li><a href="<%=basePath%>/user/loginOut.action">退出登录</a></li>
					</ul>
				</li>
			</s:else>
	 	</ul>
	</div>
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
