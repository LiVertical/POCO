<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/control/js/common.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	 
	<script>
		 function loadSrc(src,id){
	    	  $("#mainFrame").attr("height",100);
	    	  $("#mainFrame").attr("src",src);
	      }
	      function iframeHeight() {
				var ifm = document.getElementById("mainFrame");
				var subWeb = document.frames ? document.frames["mainFrame"].document :ifm.contentDocument;
				if (ifm != null && subWeb != null) {
					ifm.height = subWeb.body.scrollHeight;
				}
				window.parent.window.iframeHeight();
		  }
	</script>	 
 </head>
<body>
	<script>	
		$(function() {
			initUserData(1);
		});

		var initUserData = function(page) {
			var recordSize = 8;
			var currentPage = page;
			$.getJSON(getRootPath()+"/admin/user-queryUserDetails.action?currentPage="+ currentPage + "&recordSize=" + recordSize, function(result) {
				 $("#dataDisplay").empty();
				  var pageSize = (result.userInfos.length > recordSize ? recordSize: result.userInfos.length);
				  console.log("userInfos:" + result.userInfos);
				  if (result.returnCode == "00") {
						var tbody = '';
							if (result.userInfos.length > 0) {
								for (var i = 0; i < pageSize; i++) {
									if(result.userInfos[i].role != '2'){
										var sex = result.userInfos[i].sex;
										var isMan = "";
										if(sex == 1){
											isMan = "男";
										}else{
											isMan = "女";
										}
										var userPhoto = result.userInfos[i].faceImg;
										if(userPhoto == ''){
											userPhoto = getRootPath() + "/img/icons/default.jpg";
										}else{
											userPhoto = getRootPath() + "/" + result.userInfos[i].faceImg;
										}
										tbody += "<tr><td style='word-wrap: break-word;font-size:12px;'>"+ result.userInfos[i].userId + "</td>"
												+ "<td><img alt="+userPhoto+" class='userImg' src='" + userPhoto + "'</td>"
												+ "<td>" + result.userInfos[i].loginName + "</td>"
												+ "<td>" + isMan + "</td>"
												+ "<td>" + result.userInfos[i].age+ "</td>" 
												+ "<td>" + result.userInfos[i].email + "</td>" 
												+ "<td><img style='height:30px;width:44px' src='" + getRootPath() + "/img/icons/delete.jpg' class='delBtn' onclick='deleteUser(&quot;"+result.userInfos[i].userId+"&quot;)'></td></tr>";
											}
									}
								}
								$("#dataDisplay").append(tbody);
										$("#page").pagination(result.usersCount,{
												callback : function(index) {
													initUserData(index+1);
												},
												prev_text : '上一页', //上一页按钮里text
												next_text : '下一页', //下一页按钮里text
												items_per_page : pageSize, //显示条数
												current_page:currentPage-1,
												num_display_entries : 6, //连续分页主体部分分页条目数
												num_edge_entries : 2
													//两侧首尾分页条目数
										});
									window.parent.window.iframeHeight();
								}
							});

			//翻页调用
			function Pagecallback(index, pg) {
				queryUsers(index);
				return false;
			}
		};
		
		//删除用户
		var deleteUser = function(userId){
 		   $.post(getRootPath() + "/admin/user-deleteUserInfo.action?userId=" + userId, function (data) {
        	if (data.returnCode == '00') {
           	alert("删除成功");
           		window.location.reload(); 
      	     }else{
           		alert("删除失败");
        	 }
   		 },'json');
		};
	</script>
	<div class="main">
		<div id="p_content">
			<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
				<thead>
					<tr class="tr_head">
						<td>编号</td>
						<td>头像</td>
						<td>账户名</td>
						<td>性别</td>
						<td>年龄</td>
						<td>邮箱</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody class="table" id="dataDisplay"></tbody>
			</table>
		</div>
		<div class="pagination" id="page"></div>
	</div>
</body>
</html>
