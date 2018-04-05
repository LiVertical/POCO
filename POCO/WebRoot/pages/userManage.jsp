<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传头像</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/user.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/script/user.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
  </head>  
<body data-user_name = '${sessionScope.loginName }' 
	  data-user_img='${sessionScope.userImg }'>
  <div class="main">
   <div class="header">
		<ul>
			<li><a onclick="gotoMyWorks('${sessionScope.loginName }')">我的空间</a></li>
			<li><a href="">系统通知</a></li>
			<li><a>我的评论</a></li>
			<li><a>我的收藏</a></li>
			<li><a href="<%=basePath%>/views/productUpload.jsp">发作品</a></li>
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
			<li onclick="loginOut()"><a>退出登陆</a></li>
	 	</ul>
	</div>
	<div class="upload-box">
  		 <form action="user-uploadUserImg.action" method="post" enctype="multipart/form-data">
		   	<s:hidden name="faceimg"></s:hidden><br>
	    	<s:file name="upload" label="HeadImg"></s:file>
    		<input type="submit" class="btn btn-success"/>
   		 </form>
  	</div>
  </div>
	<div class="footer">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	</div>
</body>
</html>
