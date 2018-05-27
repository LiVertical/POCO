<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>活动管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/smsActivity.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/admin.css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>control/js/common.js"></script>
	<script src="<%=basePath%>script/smsActivity.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
  </head>  
  <script>
  $(function(){
		
		$(".menu li").click(function() {
			$(".menu li").removeClass("on");
			$(this).addClass("on");
		});
		
	});
  </script>
<body>
	<div class="topNav">
			 <ul class="menu">
                <li class="on"><button class="btn" id="ready">已审核活动</button></li>
                 <li><button id="wait" class="btn">待审核活动</button></li>
            </ul>
	</div>
	
	<div class="container">
		<div id="passed" class="box">
				<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
						<thead>
							<tr class='tr_head'>
								<td style="width:130px">活动标题</td>
								<td style="width:130px">申请时间</td>
								<td style="width:80px">申请人</td>
								<td style="width:210px">活动有效期</td>
								<td style="width:80px">操作</td>
							</tr>
						</thead>
						<tbody class="table" id="dataDisplay"></tbody>
					</table>
					<div class="pagination" id="page"></div>
		</div>
	</div>
	
</body> 
</html>
