<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统通知</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/nfs.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css/pagination.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/script/notification.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
  </head>
<body>
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
				<li><img id="userImg" style="height:30px;width:30px;margin-top:9px;" src="<%=basePath%>${sessionScope.userImg}">
						<ul class="loginCenter" style="display:none">
								<li onclick="gotoMyWorks('${sessionScope.loginName }')">我的空间</li>
								<li><a href="<%=basePath%>views/accountManage.jsp">账号管理</a></li>
								<li><a href="<%=basePath%>views/notifications.jsp">系统通知</a></li>
								<li><a href="<%=basePath%>user/login-loginOut.action">退出登录</a></li>
						</ul>
				</li>
			</s:else>
	 	</ul>
</div>
	<div class="content">
		<ul id="p_content"></ul>
	</div>
	 <div class="pagination" id="page"></div>
	 <div class="footer">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	</div>
</body>
</html>
