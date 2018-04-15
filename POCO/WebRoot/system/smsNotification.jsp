<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统通知</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/notification.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css/jquery.dialogbox-1.0.css"/>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery.dialogbox-1.0.js"></script>
  
  </head>
  <style>
  	.on{
	    height: 110px !important;
	    background: rgb(0,159,233) !important;
	    line-height: 110px !important;
	}
  </style>
    <script type="text/javascript">
  $(function() {
		initUserData(1);
		
		$("#submit").click(function(){
			var title = $("#notificationTitle").val();
			var info = $("#notificationInfo").val();
			if(title == ''){
				alert("请输入活动标题");
				return;
			}
			if(info == ''){
				alert("请输入活动介绍");
				return;
			}
			$.ajax({
			  	url:getRootPath() + "/admin/addNotification.action",
			  	type:'POST', //GET
			  	async:false,    //或false,是否异步
			  	data:{
			  		"notificationTitle":title,
			  		"notificationInfo":info
			  	},
			  	success:function(data){
				  if(data.returnCode == '00'){
				  	alert("成功发布"+data.count+"条通知");
				  	$("#a").show();
				  	$("#aa").addClass('on');
					$("#bb").removeClass('on');
				  	initUserData(1);			
				  }else{
				  	alert("发布失败！");
				  	return;
				  }
				}
			});
		});
	
		$("#aa").click(function(){
			$("#aa").addClass('on');
			$("#bb").removeClass('on');
			$("#a").show();
			$("#b").hide();
		});
		
		$("#bb").click(function(){
			$("#aa").removeClass('on');
			$("#bb").addClass('on');
			$("#b").show();
			$("#a").hide();
		});
		
  });
  
  var look = function(title, info, count){
          $('body').dialogbox({
				type:"normal",
				title:title,
				message:info+"<br/>共有" + count + "人接收到这条通知",
			});
  }

	var initUserData = function(page) {
		var recordSize = 10;
		var currentPage = page;
		var params={
				currentPage:currentPage,
				recordSize:recordSize,
		};
		$.post(getRootPath()+"/admin/queryNotifications.action", params, function(data){
			console.log("notifications:" + data);
			if (data.returnCode == "00") {
				var tbody = '';
					$("#dataDisplay").html("");
	    			$("#page").html("");
					if (data.notifications.length > 0) {
						var pageSize = (data.notifications.length > recordSize ? recordSize: data.notifications.length);
						for (var i = 0; i < pageSize; i++) {
							var status = "";
							if(data.notifications[i].curStatus == "1"){
								status="使用中";
							}else{
								status="已删除";
							}
							var usefulLife = "";
							if(data.notifications[i].usefulLife == "-1"){
								usefulLife="长期有效";
							}else{
								usefulLife=data.notifications[i].usefulLife;
							}
							var title= '"'+data.notifications[i].notificationTitle+'"';
							var info = '"'+data.notifications[i].notificationInfo+'"';
							tbody += "<tr><td>"+ (i+(currentPage-1)*recordSize+1) + "</td>"
								+ "<td>" + data.notifications[i].notificationTitle + "</td>"
								+ "<td>" + data.notifications[i].createTime.substring(0,16) + "</td>"
								+ "<td>" + data.notifications[i].createUserName + "</td>"
								+ "<td>" + status+ "</td>" 
								+ "<td>" + usefulLife + "</td>" 
								+ "<td><button class='look' onclick='look("+title+","+info+","+data.receiverCount+")'>查看详情</button></td></tr>";
						}
						}else{
								tbody="<tr class='tr_even'  style='color:969696'><td colspan='"+($(".tr_head").children().length)+"'>暂无通知</td></tr>";
						}
						$("#dataDisplay").append(tbody);
				$("#page").pagination(data.totalNotifiactionsCount,{
						callback: function (index) {
							initUserData(index+1);
		                },
		                prev_text: '上一页',       //上一页按钮里text
                        next_text: '下一页',       //下一页按钮里text
                        items_per_page: recordSize, 
                        current_page:currentPage-1,
                        num_display_entries: 6,    //连续分页主体部分分页条目数
                        num_edge_entries: 2        //两侧首尾分页条目数
				});
				window.parent.window.iframeHeight();
			}
	}, 'json');
		
};
 </script>
  <body>
   <div class="tab">
        <div class="box">
            <ul class="menus">
                <li class="on" id="aa">查看通知</li>
                <li id="bb">发布通知</li>
            </ul>
            <div class="right">
                <div class="scroll">
                <!-- 查看通知 -->
                    <div class="tab_right" id="a" style="display:block">
                    	<h2 style="text-align:center;margin-bottom:30px;color:rgb(92,94,103);">已发布的通知</h2>
							<div id="p_content">
								<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
									<thead>
										<tr class='tr_head'>
											<td style="width:40px">序号</td>
											<td>标题</td>
											<td style="width:130px">时间</td>
											<td style="width:200px">创建人</td>
											<td style="width:70px">状态</td>
											<td style="width:70px">有效期</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody class="table" id="dataDisplay"></tbody>
							</table>
						</div>
					<div class="pagination" id="page"></div>
            </div>
            <!-- 发送通知 -->
            <div class="tab_right" id="b">
           		<div class="main">发布通知
  				 <p> 标题：</p>
  				 <input type="text" placeholder="请输入标题" id="notificationTitle" name="notificationTitle"><br/>
 				 <p>通知内容：</p>
 				 <textarea id="notificationInfo" placeholder="请输入活动介绍" maxlength="100" name="notificationInfo" cols="30" rows="3" align="center"></textarea>
 				 <br/>
  				 <button class="send" id="submit">发布</button>
  				</div>
           	</div>
        </div>
     </div>
   </div>
   <div class="clear"></div>
 </div>
</body>
</html>
