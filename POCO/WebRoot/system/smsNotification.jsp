<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统通知</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>

  </head>
  
  <body>
  	<div>已发布的通知</div>
  	<div>发布通知
  		  标题：<input type="text" placeholder="请输入标题" id="notifiactionTitle" name="notifiactionTitle"><br/>
 		 通知内容：<textarea id="notifiactionInfo" name="notifiactionInfo" cols="30" rows="3" align="center"></textarea>
  		<button id="submit">发布</button>
  	</div>
  	
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
		<s:debug/>
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
		var recordSize = 6;
		var currentPage = 1;
		$.getJSON("<%=basePath%>admin/queryNotifiactions.action?currentPage="+ currentPage + "&recordSize=" + recordSize,
				function(result) {
					$("#dataDisplay").empty();
					console.log("userInfos:" + result.userInfos);
					if (result.returnCode == "00") {
						var tbody = '';
						$("#dataDisplay div:gt(0)").remove();
						if (result.userInfos.length > 0) {
								var pageSize = (result.userInfos.length > recordSize ? recordSize: result.userInfos.length);
									for (var i = 0; i < pageSize; i++) {
										var sex = result.userInfos[i].sex;
										var isMan = "";
										if(sex == 1){
											isMan = "男";
										}else{
											isMan = "女";
										}
										tbody += "<tr><td>"+ result.userInfos[i].userid + "</td>"
												+ "<td><img class='userImg' src='" + getRootPath() + "/" + result.userInfos[i].faceimg + "'</td>"
												+ "<td>" + result.userInfos[i].loginName + "</td>"
												+ "<td>" + isMan + "</td>"
												+ "<td>" + result.userInfos[i].age+ "</td>" 
												+ "<td>" + result.userInfos[i].email + "</td>" 
												+ "<td><a class='delBtn' onclick='deleteUser("+result.userInfos[i].userid+")'>销户</a></td></tr>";
									}
								}
								$("#dataDisplay").append(tbody);
								if (isFirst == 0) {
									$("#page").pagination(result.totalCountPro,{
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
						});

		//翻页调用
		function Pagecallback(index, pg) {
			queryUsers(index);
			return false;
		}
	}
  
  
  </script>

</html>
