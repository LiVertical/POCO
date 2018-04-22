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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/summernote.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/js/summernote.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/upload.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
   </head>
<body>
     <div class="header" style="margin-top:-10px">
		<ul>
			<li onclick="gotoMyWorks('${loginName }')">POCO</li>
			<li><a href="">活动</a></li>
			<li><a href="<%=basePath%>/views/myWorks.jsp">论坛</a></li>
			<li><a href="<%=basePath%>/views/myStores.jsp">问答</a></li>
	 	</ul>
	 	<ul style="float:right;padding-right:66px">
			 <s:if test="loginName == null">
				<li><a href="<%=basePath%>/user/login-userLogin.action">登录</a></li>
			 </s:if>
			 <s:else>
			 	<li><a href="<%=basePath%>/views/productUpload.jsp">发作品</a></li>
				<li><img id="userImg" style="height:30px;width:30px;margin-top:9px;" src="<%=basePath%>${sessionScope.APP_USERINFO_SESSION_KEY.userImg}">
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
    <div class="img" id="imgBox"><ul id="pic"></ul></div>
    <div class="uploadBox">
		<p>图片标题：<input name="productName"></p>
		<p>图片分类： 
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
		<p>图片描述：<input type="text" name="productDesc"></p>
		<p>选择文件：<input type="file" id="image" class="glyphicon glyphicon-folder-open"></p>
	</div>
	<div class="work">
		<p>作品标题：<input type="text" id="workTitle" placeholder="请输入作品标题"></p>
		<p>作品内容：</p>
		<div id="summernote"></div>
		<button onclick="uploadWork()" id="submit">提交</button>
	</div>
  </body>
</html>














