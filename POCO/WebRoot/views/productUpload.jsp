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
<body data-activity_id = '${param.activityId  }', 
	data-contest_id = '${param.contestId  }'>
<div class="header" style="margin-top:-10px">
		<ul>
			<li><a href="<%=basePath%>/index.jsp">POCO首页</a></li>
			<li><a href="<%=basePath%>/views/activities.jsp">活动</a></li>
	 	</ul>
	 	<ul style="float:right;padding-right:66px">
			 <s:if test="%{#session.isEmpty()}">
				<li><a href="<%=basePath%>/user/userLogin.action">登录</a></li>
			 </s:if>
			 <s:else>
				<li><img id="userImg" style="height:30px;width:30px;margin-top:-9px;" src="<%=basePath%>/${sessionScope.APP_USERINFO_SESSION_KEY.userImg}">
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
    <div class="img" id="imgBox"><ul id="pic"></ul></div>
    <div class="uploadBox">
		<p>图片标题：<input name="productName" placeholder="请输入图片标题"></p>
		<p>图片分类： 
			<select class="type" name="proType" id="workType"></select>
		</p>
		<p>图片描述：<input type="text" name="productDesc" placeholder="请输入图片描述"></p>
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














