<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">

<title>首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/list.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/pagination.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/index.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
</head>
<body data-user_name = '${sessionScope.loginName }' 
	  data-user_img='${sessionScope.userImg }'>
	<div class="header">
		<ul>
			<li onclick="gotoMyWorks('${sessionScope.loginName }')">POCO</li>
			<li><a href="">活动</a></li>
			<li><a href="<%=basePath%>/views/myWorks.jsp">论坛</a></li>
			<li><a href="<%=basePath%>/views/myStores.jsp">问答</a></li>
	 	</ul>
	 	<ul style="float:right;padding-right:66px">
			 <s:if test="#session.loginName== null">
				<li><a href="<%=basePath%>user/login-userLogin.action">登录</a></li>
			 </s:if>
			 <s:else>
			 	<li><a href="<%=basePath%>/views/productUpload.jsp">发作品</a></li>
				<li><span id="userImg"></span>
						<ul class="loginCenter" style="display:none">
								<li onclick="gotoMyWorks('${sessionScope.loginName }')">我的空间</li>
								<li><a href="">账号管理</a></li>
								<li><a href="">系统通知</a></li>
								<li><a href="<%=basePath%>user/login-loginOut.action">退出登录</a></li>
						</ul>
				</li>
			</s:else>
	 	</ul>
	</div>
	   <ul class="nav">
	    	<li onclick="queryProducts(100)"><p>全部类型</p></li>
			<li onclick="queryProducts(1)"><p>花鸟</p></li>
			<li onclick="queryProducts(2)"><p>人物</p></li>
			<li onclick="queryProducts(3)"><p>山水</p></li>
			<li onclick="queryProducts(4)"><p>建筑</p></li>
			<li onclick="queryProducts(5)"><p>生态</p></li>
			<li onclick="queryProducts(6)"><p>纪实</p></li>
			<li onclick="queryProducts(7)"><p>LOMO</p></li>
			<li onclick="queryProducts(8)"><p>风景</p></li>
		</ul>
	<div class="main">
	   <div class="p_content"> <ul class='boxs' id ="p_content"></ul></div>
	   <div class="pagination" id="page"></div>
    </div>
	<div class="footer">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	</div>
</body>
</html>
