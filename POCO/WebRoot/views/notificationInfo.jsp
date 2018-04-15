<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>通知详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/list.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/accountManage.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/pagination.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
	<script src="<%=basePath%>/script/accountManage.js"></script>
</head>
<body data-notification_id="${notifiactionId }">
	<div class="header">
		<ul>
			<li><a onclick="gotoMyWorks('${sessionScope.loginName }')">我的空间</a></li>
			<li><a href="">系统通知</a></li>
			<li><a href="<%=basePath%>/views/myWorks.jsp">我的评论</a></li>
			<li><a href="<%=basePath%>/views/myStores.jsp">我的收藏</a></li>
			<li><a href="<%=basePath%>/views/productUpload.jsp">发作品</a></li>
	 	</ul>
	 	<ul style="float:right;margin-right:20px">
			 <s:if test="#session.user.username== null">
				<li><a href="<%=basePath%>/user/login-userLogin.action">登录</a></li>
			 </s:if>
			 <s:else>
				<li><span id="userImg"></span>
					<div class="loginCenter" style='display:none'>
						<div><a href="">我的空间</a></div>
						<div><a href="">设置</a></div>
						<div><a href="">帮助</a></div>
						<div><a href="">我的空间</a></div>
						<div><a href="">退出</a></div>
					</div>
				</li>
			 </s:else>
	 	</ul>
	</div>
	<div class="main">
		<div class="title" id="title"></div>
		<div class="info" id="info"></div>
		<div class="time" id="time"></div>
	</div>
	<div class="footer">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	</div>
</body>
<script>
$(document).ready(function(){
	var notificationId = $("body").data().notification_id;
	console.log(notificationId);
	$.post(getRootPath()+"/user/showNotification.action?notificationId="+notificationId, function(data){
		if(data.returnCode= '00'){
			$("#title").html(data.notificationInfos.notificationTitle);
			$("#info").html(data.notificationInfos.notificationInfo);
			$("time").html(data.notificationInfos.createTime);
		}
	});
	
});
</script>
</html>
