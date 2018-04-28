<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.util.UserInfo"%>
<%@page import="com.util.LoginUserUtil"%>
<%
	UserInfo commonUser = (UserInfo) session.getAttribute(LoginUserUtil.USERINFO_SESSION_KEY);
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/list.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css/pagination.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/index.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
</head>
<body>
	<div class="header">
		<ul>
			<li><a href="<%=basePath%>views/activities.jsp">活动</a></li>
			<li><a href="<%=basePath%>views/works.jsp">作品集锦</a></li>
	 	</ul>
	 	<ul style="float:right;padding-right:66px">
			 <s:if test="%{#session.isEmpty()}">
				<li><a href="<%=basePath%>user/userLogin.action">登录</a></li>
			 </s:if>
			 <s:else>
			 	<li onclick="gotoUpload()">发作品</li>
				<li><img id="userImg" style="height:30px;width:30px;margin-top:9px;" src="<%=basePath%>${sessionScope.APP_USERINFO_SESSION_KEY.userImg}">
						<ul class="loginCenter" style="display:none">
								<li><a href="<%=basePath%>views/mySpace.jsp">我的空间</a></li>
								<li><a href="<%=basePath%>views/accountManage.jsp">账号管理</a></li>
								<li><a href="<%=basePath%>views/notifications.jsp">系统通知</a></li>
								<li><a href="<%=basePath%>user/loginOut.action">退出登录</a></li>
						</ul>
				</li>
			</s:else>
	 	</ul>
	</div>
	<div class="recommend ">
		<span style="color:#76EE00">*</span>
			首页推荐
		<span style="color:#76EE00">*</span>
	</div>
	<div class="main">
	   <div class="p_content"> <ul class='boxs' id ="p_content"></ul></div>
	   <div class="pagination" id="page"></div>
    </div>
    <div class="container">
    	<ul class="nav">
	    	<li onclick="queryProductsByType(100)"><p>全部类型</p></li>
			<li onclick="queryProductsByType(1)"><p>花鸟</p></li>
			<li onclick="queryProductsByType(2)"><p>人物</p></li>
			<li onclick="queryProductsByType(3)"><p>山水</p></li>
			<li onclick="queryProductsByType(4)"><p>建筑</p></li>
			<li onclick="queryProductsByType(5)"><p>生态</p></li>
			<li onclick="queryProductsByType(6)"><p>纪实</p></li>
			<li onclick="queryProductsByType(7)"><p>LOMO</p></li>
			<li onclick="queryProductsByType(8)"><p>风景</p></li>
		</ul>
    </div>
	<div class="footer">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	</div>
</body>
</html>
