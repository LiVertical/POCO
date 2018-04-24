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
    <title>活动</title>
    
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
	<script src="<%=basePath%>/script/activity.js"></script>
	<style>
	#p_content{
			height:100px;
			width:90%;
			margin:20px auto;
		}
		#p_content li{
			border-bottom: 1px solid cornflowerblue;
			height:100px;
			width:90%;
			float:left;
			margin-top:20px;
		}
		#p_content p{
			margin-left:20px;
			margin-top:5px;
		}
		#p_content h2{
			margin-left:20px;
			margin-top:5px;
		}
		.joinBtn{
			    width: 100px;
			    height: 34px;
			    letter-spacing: 1px;
			    background: #3385ff;
			    border-bottom: 1px solid #2d78f4;
			    outline: medium;
			    -webkit-appearance: none;
			    -webkit-border-radius: 0;
			    border:none;
			    float:right;
			    line-height: 34px;
			    text-align:center;
		}
		.joinBtn a{
			text-decoration:none;
			color:#fff;
		}
	</style>
</head>
<body>
<div class="header">
		<ul>
			<li><a href="<%=basePath%>/views/activities.jsp">活动</a></li>
			<li><a href="<%=basePath%>/views/myWorks.jsp">论坛</a></li>
			<li><a href="<%=basePath%>/views/applyActivities.jsp?userId=${sessionScope.APP_USERINFO_SESSION_KEY.userId }">申请发起活动</a></li>
	 	</ul>
	 	<ul style="float:right;padding-right:66px">
		 <s:if test="%{#session.isEmpty()}">
				<li><a href="<%=basePath%>/user/userLogin.action">登录</a></li>
		 </s:if>
		 <s:else>
			 	<li><a href="<%=basePath%>/views/productUpload.jsp">发作品</a></li>
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
 <div class="main" style='min-height:620px'>
	<div class="content">
		<ul id="p_content"></ul>
	</div>
	 <div class="pagination" id="page"></div>
 </div>
 <div class="footer">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	</div>
</body>
<script>
$(function(){
	getAllActivities();
	
	function joinActivity(activityId){
		window.location.href= getRootPath() + "/views/activityInfo.jsp?activityId="+activityId;
	}
});

function getAllActivities(){
	$.post(getRootPath()+"/vistor/getAllActivities.action", function(data){
	if(data.returnCode == '00'){
		var html = "";
		for(var i = 0; i < data.activitiesInfos.length; i++){
			html = "<li><h2>"+data.activitiesInfos[i].activityName+"</h2>"
					+  "<p>" + data.activitiesInfos[i].activityDesc +"</p>"
					+  "<div class='joinBtn'><a href='"+getRootPath()+"/views/activityInfo.jsp?activityId="+ data.activitiesInfos[i].activityId+"')'>点击进入</a></li>";	
			$("#p_content").append(html);
		}
	  }
	});
	
}
</script>
</html>
