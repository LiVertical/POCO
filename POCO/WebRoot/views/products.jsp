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
    <title>活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/red-datepicker.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/pagination.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/activity.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/script/dateinput-ch-ZN.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
	<script src="<%=basePath%>/script/custom.js"></script>
	<script>
		 function loadSrc(src,id){
	    	  $("#mainFrame").attr("height",100);
	    	  $("#mainFrame").attr("src",src);
	    	  $.cookie("id", id);
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
 	<a href="<%=basePath%>/views/applyActivities.jsp?userId=${sessionScope.userId }">申请活动</a>
	<div class="content">
		<ul id="p_content"></ul>
	</div>
	 <div class="pagination" id="page"></div>
 </div>
</body>
</html>
