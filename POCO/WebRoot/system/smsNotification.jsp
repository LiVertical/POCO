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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/bootstrap.min.css"/>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/notifiaction.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.js"></script>
  </head>
  
  <body>
   <div class="tab">
        <div class="box">
            <ul class="menus">
                <li class="bg">已发布通知</li>
                <li>发布通知</li>
                <li>tab3</li>
            </ul>
            <div class="right">
                <div class="scroll">
                <!-- 查看通知 -->
                    <div class="tab_right">
                    	<h2 style="text-align:center;margin-bottom:30px;color:rgb(92,94,103);">已发布的通知</h2>
							<div id="p_content">
								<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
									<thead>
										<tr class='tr_head'>
											<td>序号</td>
											<td>标题</td>
											<td style="width:160px">时间</td>
											<td style="width:70px">创建人</td>
											<td style="width:70px">状态</td>
											<td>有效期</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody class="table" id="dataDisplay"></tbody>
							</table>
						</div>
					<div class="pagination" id="page"></div>
            </div>
            <!-- 发送通知 -->
            <div class="tab_right">
           		<div class="main">发布通知
  				 <p> 标题：</p>
  				 <input type="text" placeholder="请输入标题" id="notifiactionTitle" name="notifiactionTitle"><br/>
 				 <p>通知内容：</p>
 				 <textarea id="notifiactionInfo" placeholder="请输入活动介绍" maxlength="100" name="notifiactionInfo" cols="30" rows="3" align="center"></textarea>
 				 <br/>
  				 <button class="send" id="submit">发布</button>
  				</div>
           	</div>
            <div class="tab_right"></div>
            <div class="tab_right"></div>
        </div>
     </div>
   </div>
   <div class="clear"></div>
 </div>
</body>
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script> 
  <script type="text/javascript" src="<%=basePath%>/js/common.js"></script> 
  <script type="text/javascript" src="<%=basePath%>/script/notifiaction.js"></script>
  <script src="<%=basePath%>/js/jquery.pagination.js"></script>
  <script type="text/javascript">
  $(function() {
		initUserData(1);
	});

	var initUserData = function() {
		var recordSize = 10;
		var currentPage = 1;
		$.ajax({ 
			url: "<%=basePath%>/admin/queryNotifiactions.action",
			type:"get",
			data:{
				'currentPage':currentPage,
				'recordSize':recordSize
			},
			dataType:"json",
			success: function(data){
			console.log("notifiactions:" + data);
			if (data.returnCode == "00") {
				var tbody = '';
				 	$("#dataDisplay div:gt(0)").remove();
					if (data.notifiactions.length > 0) {
						var pageSize = (data.notifiactions.length > recordSize ? recordSize: data.notifiactions.length);
						for (var i = 0; i < pageSize; i++) {
						var status = "";
						if(data.notifiactions[i].curStatus == "1"){
							status="使用中";
						}else{
							status="已删除";
						}
						var usefulLife = "";
						if(data.notifiactions[i].usefulLife == "-1"){
							usefulLife="长期有效";
						}else{
							usefulLife=data.notifiactions[i].usefulLife;
						}
					tbody += "<tr><td>"+ i + "</td>"
							+ "<td>" + data.notifiactions[i].notifiactionTitle + "'</td>"
							+ "<td>" + data.notifiactions[i].createTime.substring(0,16) + "</td>"
							+ "<td>" + data.notifiactions[i].createUser + "</td>"
							+ "<td>" + status+ "</td>" 
							+ "<td>" + usefulLife + "</td>" 
							+ "<td><a class='delBtn' onclick='deleteUser("+data.notifiactions[i].userid+")'>查看</a></td></tr>";
					}
				}else{
					tbody="<tr class='tr_even'  style='color:969696'><td colspan='"+($(".tr_head").children().length)+"'>暂无通知</td></tr>";
				}
				$("#dataDisplay").append(tbody);
				$("#page").pagination(data.totalCountPro,{
						callback: function (index) {
		                    queryAllComments(index+1);
		                },
						prev_text : '上一页', //上一页按钮里text
						next_text : '下一页', //下一页按钮里text
						items_per_page : recordSize, //显示条数
						num_display_entries : 6, //连续分页主体部分分页条目数
						num_edge_entries : 2   //两侧首尾分页条目数
				});
				window.parent.window.iframeHeight();
			}
		}
	},'json');
	
	$(function() {// 初始化内容
		$("#submit").click(function(){
			var title = $("#notifiactionTitle").val();
			var info = $("#notifiactionInfo").val();
			if(title == ''){
				alert("请输入活动标题");
				return;
			}
			if(info == ''){
				alert("请输入活动介绍");
				return;
			}
			$.ajax({
			  	url:getRootPath() + "/admin/addNotifiaction.action",
			  	type:'POST', //GET
			  	async:false,    //或false,是否异步
			  	data:{
			  		"notifiaction.notifiactionTitle":title,
			  		"notifiaction.notifiactionInfo":info
			  	},
			  	success:function(data){
				  if(data.returnCode == '00'){
				  	alert("发布成功！");
				  	initUserData(1);			
				  }else{
				  	alert("发布失败！");
				  	return;
				  }
				}
			});
		});
	});
		
};
 </script>
</html>
