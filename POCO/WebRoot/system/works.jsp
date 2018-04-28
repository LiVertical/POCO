<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>作品管理</title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/workManage.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/control/js/common.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/script/workManage.js"></script>
  </head>
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
  <body>
  <div class="main">
  	<div id="p_content">
		<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
			<thead>
				<tr class="tr_head">
					<td style="width:40px">序号</td>
					<td style="width:100px">作品标题</td>
					<td style="width:240px">作品内容</td>
					<td style="width:140px">上传时间</td>
					<td style="width:240px">作品配图</td>
					<td style="width:100px">所属用户</td>
					<td style="width:60px">操作</td>
				</tr>
			</thead>
			<tbody class="table" id="dataDisplay"></tbody>
		</table>
	</div>
    <div class="pagination" id="page"></div>
   </div>
  </body>
</html>
