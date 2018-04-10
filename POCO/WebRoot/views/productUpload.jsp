<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+ "://" + request.getServerName() + ":" +request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>文件上传</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/upload.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/upload.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
   </head>
<body data-user_img='${sessionScope.userImg }'>
     <div class="header">
		<ul>
			<li><a onclick="gotoMyWorks('${sessionScope.loginName }')">我的空间</a></li>
			<li><a href="">系统通知</a></li>
			<li><a>我的评论</a></li>
			<li><a>我的收藏</a></li>
	 	</ul>
	 	<ul style="float:right;margin-right:20px">
			 <s:if test="#session.user.username== null">
				<li><a href="login-input.action">登录</a></li>
			 </s:if>
			 <s:else>
				<li><span id="userImg"></span></li>
				<li><a>${sessionScope.loginName }</a></li>
			 </s:else>
			<li><a onclick="gotoCompleteUserInfo()">完善资料</a></li>
			<li><a href="<%=basePath%>/user/login-loginOut.action">退出登陆</a></li>
	 	</ul>
	</div>
 	<a href="<%=basePath%>/pages/userInfoEdit.jsp">编辑用户资料</a>
    	<div class="uploadBox">
			productName:<input name="productName"><br/>
			<p>请选择作品分类： 
				<select class="type" name="proType">
					<option value="">请选择作品分类</option>
					<option value="0">人像摄影</option>
					<option value="1">生态摄影</option>
					<option value="2">运动摄影</option>
					<option value="3">生活摄影</option>
					<option value="4">夜景摄影</option>
					<option value="5">风景摄影</option>
					<option value="6">纪实摄影</option>
					<option value="7">人体摄影</option>
					<option value="9">自拍摄影</option>
					<option value="10">商业摄影</option>
					<option value="11">LOMO</option>
					<option value="8">其他摄影</option>
				</select>
			</p>
			<p>file:</p>
			<div id="files">
				<input type="file" name="image" id="image">
				<input type="file" name="image">
				<input type="file" name="image">
				<input type="file" name="image">
				<input type="file" name="image">
			</div>
			<p>作品描述：<input type="text" name="productDesc"></p>
			<button class="submit" id="upload">上传</button>
	</div>
  </body>
</html>














