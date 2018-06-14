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
    <title>大赛详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/red-datepicker.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery-ui.custom.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
	<script src="<%=basePath%>/script/contest.js"></script>
<style>
	.main{
		text-align:center;
		margin:50px auto;
		width:90%;
		overflow:hidden;
	}
	.desc{
		min-height:300px;
		width:30%;
		text-align:left;
	}
	.desc p{
		padding:20px;
	}
	.time{
		float:right;
		margin-right:20px;
	}
	.joinBtn{
		width: 100px;
	    height: 34px;
	    color: #fff;
	    letter-spacing: 1px;
	    background: #3385ff;
	    border-bottom: 1px solid #2d78f4;
	    outline: medium;
	    -webkit-appearance: none;
	    -webkit-border-radius: 0;
	    border:none;
	    float: right;
    	margin-right: 200px;
	}
	.btn{
		width:100%;
		margin-top:40px;
		margin-bottom:30px;
	}
	.work{
		height:300px;
		width:90%;
		margin-top:20px;
		border-top:2px solid #969696;
	}
	.img{
		height:100%;
		width:100%;
	}
	.right{
		float:left;
		width:57%;
		height:100%;
		overflow:hidden;
	}
	.right ul li{
		height:150px;
		width:130px;
		float:left;
	}
	.vote{
		background:red;
		color:#fff;
		border:none;
		border-radius:2px;
		height:30px;
		width:100px;
		outline:none;
	}
	.contest{
		text-align:center;
		min-height:200px;
		border-bottom:10px solid #e6e6e6;
		border-top:20px solid #e6e6e6;
	}
	.right3{
		float:left;
		width:12%;
		height:50px;
		margin-top:180px;
	}
</style>
</head>
<body>
<div class="header">
		<ul>
			<li><a href="<%=basePath%>/index.jsp">POCO首页</a></li>
			<li><a href="<%=basePath%>/views/activities.jsp">活动</a></li>
			<li><a href="<%=basePath%>/views/applyActivities.jsp?userId=${sessionScope.APP_USERINFO_SESSION_KEY.userId }">申请发起活动</a></li>
	 	</ul>
	 	<ul style="float:right;padding-right:66px">
		 <s:if test="%{#session.isEmpty()}">
				<li><a href="<%=basePath%>/user/userLogin.action">登录</a></li>
		 </s:if>
		 <s:else>
			 	<li onclick="gotoUpload()">发作品</li>
				<li><img id="userImg" style="height:30px;width:30px;margin-top:9px;" src="<%=basePath%>/${sessionScope.APP_USERINFO_SESSION_KEY.userImg}">
					<ul class="loginCenter" style="display:none">
							<li onclick="gotoMyWorks('${sessionScope.loginName }')">我的空间</li>
							<li><a href="<%=basePath%>views/accountManage.jsp">账号管理</a></li>
							<li><a href="<%=basePath%>views/notifications.jsp">系统通知</a></li>
							<li><a href="<%=basePath%>user/loginOut.action">退出登录</a></li>
					</ul>
				</li>
			</s:else>
	 	</ul>
	</div>
	<div class="contest" id="contest"></div>
	<div class="content">
	 	<ul id="p_content" class="main" style='min-height:450px'></ul>
	 </div>
	 <div class='btn'><button class='joinBtn' id='joinBtn'>点击发表作品</button></div>
 	 <div class="footer" style="margin-top:50px">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	 </div>
</body>
</html>
