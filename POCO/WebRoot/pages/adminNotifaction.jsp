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
    <title>系统通知（管理）</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/list.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/script/myWorks.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
  </head>
  
<body data-user_name = '${sessionScope.loginName }' 
	  data-user_img ='${sessionScope.userImg }'>
  <div class="main">
   <div class="header">
		<ul>
			<li><a href="<%=basePath%>/views/productShow.jsp">POCO首页</a></li>
			<li><a href="">活动</a></li>
			<li><a href="<%=basePath%>/views/myWorks.jsp">论坛</a></li>
			<li><a href="<%=basePath%>/views/myStores.jsp">问答</a></li>
	 	</ul>
	 	<ul style='margin-left:1150px'>
			 <s:if test="#session.user.username== null">
				<li><a href="login-input.action">登录</a></li>
			 </s:if>
			 <s:else>
			 	<li><a href="<%=basePath%>/views/productUpload.jsp">发作品</a></li>
				<li><span id="userImg" style='height:30px;'></span>
					<div class="loginCenter" style="display:none">
						<div><a onclick="gotoMyWorks('${sessionScope.loginName }')">我的空间</a></div>
						<div><a href="">设置</a></div>
						<div><a href="">帮助</a></div>
						<div><a onclick="gotoCompleteUserInfo()">完善资料</a></div>
						<div><a href="<%=basePath%>/user/login-loginOut.action">退出登陆</a></div>
					</div>
				</li>
			 </s:else>
	 	</ul>
	</div>
 <div class="main">
	<div class='btns'>
		<button class="button" onclick="selectAll()">全选</button>
		<button class="button" onclick="inverse()">取消</button>
		<button class="button" onclick="delBatch()">删除</button>
	</div>
	<div class="p_content">
	    <ul class='boxs' id ="p_content"></ul>
	    <div class="pagination" id="page"></div>
	</div>
	<div id="dbtn"></div>
 </div>
	<div class="footer">
		<a href="#" title="POCO" target="_blank">POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn </a>
	</div>
</div>
</body>
</html>
