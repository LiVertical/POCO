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

<title>账号管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/accountManage.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
	<script src="<%=basePath%>/script/accountManage.js"></script>
</head>
<body>
	<div class="header">
		<ul>
			<li><a href="<%=basePath%>/index.jsp">POCO首页</a></li>
			<li><a href="<%=basePath%>/views/activities.jsp">活动</a></li>
			<li><a href="<%=basePath%>/views/contest.jsp">大赛</a></li>
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
	
	<div class="basePage">
		<div class="leftContainer">
			<ul>
				<li><a href="#uploadHeadImg">上传头像</a></li>
				<li><a href="#accountFile">账号设置</a></li>
				<li><a href="#updatePass">修改密码</a></li>
			</ul>
		</div>
	
	<div class="rightContainer">
			<div class="uploadBox" id="uploadHeadImg">
   		 			<div class="head">
            				<img src="<%=basePath%>/${sessionScope.APP_USERINFO_SESSION_KEY.userImg}" style="width: 100%;height: 100%;"><br/>
         			</div>
       	 			<div class="btn_addPic">上传头像
	    				<input type="file" id="headImg" class="filePrew">
   					</div>
  			</div>
  			
			<div id="accountFile">
  					<h2 style="text-align: center;">个人资料</h2>
	    			<s:hidden name="userId" id="userId"></s:hidden>
	    			<p>昵称：</p>
	    			<input id="userName" type="text" maxlength="10" placeholder="请输入您的用户名"><br/>
	    			<s:hidden name="password"/>
	    			<p>年龄：</p>
				   	<input id="userAge" type="text" placeholder="请输入您的年龄"><br/>
				   	<p>电子邮件：</p>
				   	<input id="userEmail" type="text" placeholder="请输入您的邮箱"><br/>
				   	<p>性别：</p>
				   	<input type="radio" style='margin-top: 10px' name="sex" id="man" value="1">男
				   	<input type="radio" name="sex" id="women" checked value="0">女   <br/>
				   <button class="subBtn" onclick="updateProFile()">保存</button>
  			</div> 
			<div id="updatePass" style="border-bottom: none;">
					<h2>修改密码</h2>
					<p  style="margin-top:30px">当前密码：<input type="password" id="oldPass" maxlength="12"></p>
					<p>&nbsp;新&nbsp;密&nbsp;码&nbsp;：<input type="password"  id="newPass" maxlength="12"></p>
					<p>确认密码：<input type="password" id="checkPass" maxlength="12"></p>
					<button class="subBtn" onclick="updatePass()">保存</button>
			</div>
	</div>
 </div> 
	<div class="footer" style="margin-top:20px">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	</div>
</body>
</html>
