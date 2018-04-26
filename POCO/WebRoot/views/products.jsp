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
    <title>分类查看</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/products.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
	<script src="<%=basePath%>/script/products.js"></script> 
  </head>
  
<body>
<div class="header">
		<ul>
			<li><a href="<%=basePath%>/views/activities.jsp">活动</a></li>
	 	</ul>
	 	<ul style="float:right;padding-right:66px">
			 <s:if test="%{#session.isEmpty()}">
				<li><a href="<%=basePath%>/user/userLogin.action">登录</a></li>
			 </s:if>
			 <s:else>
			 	<li><a href="<%=basePath%>/views/productUpload.jsp">发作品</a></li>
				<li><img id="userImg" style="height:30px;width:30px;margin-top:9px;" src="<%=basePath%>/${sessionScope.APP_USERINFO_SESSION_KEY.userImg}">
						<ul class="loginCenter" style="display:none">
								<li><a href="<%=basePath%>/views/mySpace.jsp">我的空间</a></li>
								<li><a href="<%=basePath%>/views/accountManage.jsp">账号管理</a></li>
								<li><a href="<%=basePath%>/views/notifications.jsp">系统通知</a></li>
								<li><a href="<%=basePath%>/user/loginOut.action">退出登录</a></li>
						</ul>
				</li>
			</s:else>
	 	</ul>
	</div>
 <div class="main" style='min-height:300px'>
 	<div class="navContainer">
    	<ul class="nav">
	    	<li onclick="queryProductsByProductType(100)"><p>全部类型</p></li>
			<li onclick="queryProductsByProductType(1)"><p>花鸟</p></li>
			<li onclick="queryProductsByProductType(2)"><p>人物</p></li>
			<li onclick="queryProductsByProductType(3)"><p>山水</p></li>
			<li onclick="queryProductsByProductType(4)"><p>建筑</p></li>
			<li onclick="queryProductsByProductType(5)"><p>生态</p></li>
			<li onclick="queryProductsByProductType(6)"><p>纪实</p></li>
			<li onclick="queryProductsByProductType(7)"><p>LOMO</p></li>
			<li onclick="queryProductsByProductType(8)"><p>风景</p></li>
		</ul>
    </div>
	<div class="content">
		<ul id="p_content"></ul>
	</div>
 </div>
</body>
</html>
