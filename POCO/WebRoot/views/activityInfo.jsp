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
	 
	 <div id="content" class="content"></div>
	 <!-- <div id="p_content"> -->
	 <div id="p_content" class="main" style='min-height:450px'>
		<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
			<thead>
				<tr class="tr_head">
					<td style="width:40px">序号</td>
					<td style="width:100px">作品标题</td>
					<td style="width:240px">作品内容</td>
					<td style="width:140px">上传时间</td>
					<td style="width:240px">作品配图</td>
					<td style="width:100px">所属用户</td>
				</tr>
			</thead>
			<tbody class="table" id="dataDisplay"></tbody>
		</table>
	</div>
    <div class="pagination" id="page"></div>
	 	
	 </div>
	 
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
	$.post(getRootPath() + "/vistor/queryWorksByActivityId.action?activityId="+activityId, function(data){
			$("#dataDisplay").empty();
//			var pageSize = (data.worksInfos.length > recordSize ? recordSize: data.worksInfos.length);
			var tbody = "";
			for(var i = 0; i < data.worksInfos.length; i++){
				var products = ""; 
				for(var j = 0; j < data.worksInfos[i].productInfos.length; j++){
					products +="<li class='img'><img src='" + getRootPath() + "/" + data.worksInfos[i].productInfos[j].productPath+"'></li>"; 
				}
				 tbody += "<tr><td>"+ (i+1) + "</td>"
	              + "<td style='height:150px;width:150px;'>"+ data.worksInfos[i].workName +"</td>"
	              + "<td style='word-break'>" + data.worksInfos[i].workComment + "</td>"
	              + "<td style='word-break'>" + data.worksInfos[i].workUploadTime.substring(0,16) + "</td>"
				  + "<td><ul>" + products + "</ul></td>"
				  + "<td>" + data.worksInfos[i].userName + "</td>" 
				  + "<td><img style='height:30px;width:44px' src='"+getRootPath()+"/img/icons/delete.jpg' class='delBtn' onclick='deleteProduct("+data.worksInfos[i].workId+")'></td></tr>";
			}
			$("#dataDisplay").html(tbody);
			$("#page").pagination(data.worksCount,{
					callback : function(index) {
									 initUserData(index+1);
								 },
					prev_text : '上一页', //上一页按钮里text
					next_text : '下一页', //下一页按钮里text
					items_per_page : pageSize, //显示条数
					current_page:currentPage-1,
					num_display_entries : 6, //连续分页主体部分分页条目数
					num_edge_entries : 2//两侧首尾分页条目数
			});
			window.parent.window.iframeHeight();
		}
	);
}
</script>
</html>
