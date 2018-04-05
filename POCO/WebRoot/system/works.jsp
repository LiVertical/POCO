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
    
    <title>作品管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/works.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/control/js/common.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/script/works.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
  </head>
  
  <body>
  <div class="main">
  	<div id="p_content">
		<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
			<thead>
				<tr>
					<td>序号</td>
					<td>作品</td>
					<td>作品描述</td>
					<td>上传时间</td>
					<td>所属类型</td>
					<td>所属用户</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody class="table" id="dataDisplay"></tbody>
		</table>
	</div>
    <div class="pagination" id="page"></div>
   </div>
   <div class="footer">
		<a href="#" title="POCO" target="_blank">POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn </a>
	</div>
  </body>
</html>
