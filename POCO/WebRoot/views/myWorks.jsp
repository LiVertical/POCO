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
    <title>我的作品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/myWorks.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/pagination.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/list.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/script/myWorks.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
	<script>
		 function loadSrc(src,id){
	    	  $("#mainFrame").attr("height",100);
	    	  $("#mainFrame").attr("src",src);
	    	  $.cookie("id", id);
	    	  $.cookie("flag", "4");//flag:标志位：1.处于商户发展 2.活跃度 3.营销 4.用户
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
  
<body data-user_name = '${sessionScope.loginName }' 
	  data-user_img ='${sessionScope.userImg }'
	  data-user_id ='${sessionScope.userId }'>
 <div class="main" style='min-height:300px'>
	<div class='btns'>
		<button class="button" onclick="delBatch()">删除</button>
	</div>
	<div class="content">
	   <table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
				<thead>
					<tr class="tr_head">
						<td><input type="checkbox" name="products">标题</td>
						<td>作品</td>
						<td>作品描述</td>
						<td>作品类型</td>
						<td>上传时间</td>
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
