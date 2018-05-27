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
    <title>活动详情</title>
    
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
	.main{
		text-align:center;
		margin:50px auto;
		width:90%;
	}
	.desc{
		min-height:300px;
		width:100%;
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
	 <div id="p_content" class="main" style='min-height:450px'></div>
	 <div id="content" class="content"></div>
	 <div class='btn'><button class='joinBtn' id='joinBtn'>点击参加活动</button></div>
 	 <div class="footer" style="margin-top:50px">
		<a>POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn</a>
	 </div>
</body>
<script>
$(document).ready(function(){
	
	getActivityInfo();
	
	$("#joinBtn").click(function(){
		var activityId = GetRequest();
		window.location.href = getRootPath() + "/views/productUpload.jsp?activityId="+activityId;
	});
	
	getAllActivityWorks();
	
});

function getActivityInfo(){
	var activityId = GetRequest();
	$.post(getRootPath()+"/vistor/queryActivityInfo.action?activityId="+activityId, function(data){
	if(data.returnCode == '00'){
		console.log(data.activityInfos);
		var html = "<h1>"+data.activityInfos[0].activityName+"</h1>"
						+"<div class='desc'><p>"+data.activityInfos[0].activityDesc+"</p></div>"
						+"<div class='time'>活动期限："+data.activityInfos[0].createTime.substring(0,10)+"--"+data.activityInfos[0].endTime.substring(0,10)
						+"</div>";
		$("#p_content").append(html);
	  }
	});
	
}

function getAllActivityWorks(){
	var activityId = GetRequest();
	console.log("活动id:"+activityId);
	$.post(getRootPath() + "/vistor/queryWorksByActivityId.action?activityId="+activityId, function(data){
		if(data.returnCode == '00'){
			alert("hidpgnef");
		}
	});
}
</script>
</html>
