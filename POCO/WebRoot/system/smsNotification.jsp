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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css">
	<link href="<%=basePath%>/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/notifiaction.js"></script>
  </head>
  
  <body>
  
  <ul id="myTab" class="nav nav-tabs">
	  <li class="active"><a href="#">已发布通知</a></li>
	  <li><a href="<%=basePath%>/system/smsNotification.jsp">发布通知</a></li>
	  <li><a href="#">VB.Net</a></li>
	  <li><a href="#">Java</a></li>
	  <li><a href="#">PHP</a></li>
   </ul>
  	<h2 style="text-align:center">已发布的通知</h2>
  	<div class="main">
		<div id="p_content">
			<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
				<thead>
					<tr>
						<td>序号</td>
						<td>标题</td>
						<td>时间</td>
						<td>创建人</td>
						<td>状态</td>
						<td>有效期</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody class="table" id="dataDisplay"></tbody>
			</table>
		</div>
		<div class="pagination" id="page"></div>
	</div>
  	
  </body>
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script> 
  <script type="text/javascript" src="<%=basePath%>/js/common.js"></script> 
  <script type="text/javascript" src="<%=basePath%>/script/notifiaction.js"></script>
  <script src="<%=basePath%>/js/jquery.pagination.js"></script>
  <script type="text/javascript">
  $(function() {
		initUserData(0);
	});

	var initUserData = function(isFirst) {
		var recordSize = 10;
		var currentPage = 1;
		
		
		$.ajax({ 
			url: "./admin/queryNotifiactions.action",
			type:"get",
			data:{
				'currentPage':currentPage,
				'recordSize':recordSize
			},
			dataType:"json",
			success: function(data){
				console.log("notifiactions:" + data);
				console.log("notifiactions:" + data.notifiactions);
				if (data.returnCode == "0") {
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
											+ "<td>" + format(data.notifiactions[i].createTime,"yyyy-mm-dd HH-mm-ss") + "</td>"
											+ "<td>" + data.notifiactions[i].createUser + "</td>"
											+ "<td>" + status+ "</td>" 
											+ "<td>" + usefulLife + "</td>" 
											+ "<td><a class='delBtn' onclick='deleteUser("+data.notifiactions[i].userid+")'>查看</a></td></tr>";
								}
							} 
							$("#dataDisplay").append(tbody);
							if (isFirst == 0) {
								$("#page").pagination(data.totalCountPro,{
											callback : function(index) {
											 if (isFirst == 1) {queryUsers(index,1);}},
												prev_text : '上一页', //上一页按钮里text
												next_text : '下一页', //下一页按钮里text
												items_per_page : recordSize, //显示条数
												num_display_entries : 6, //连续分页主体部分分页条目数
												num_edge_entries : 2
											//两侧首尾分页条目数
											});
							}
							window.parent.window.iframeHeight();
						}
	      }});
		
		
		<%-- $.getJSON("<%=basePath%>admin/queryNotifiactions.action?currentPage="+ currentPage + "&recordSize=" + recordSize,
				function(result) {
					$("#dataDisplay").empty();
					console.log("notifiactions:" + result);
					console.log("notifiactions:" + result.notifiactions);
						}); --%>

		//翻页调用
		function Pagecallback(index, pg) {
			queryUsers(index);
			return false;
		};
	
  
  
  </script>

</html>
