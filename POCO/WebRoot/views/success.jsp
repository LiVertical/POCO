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
    
    <title>链接页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=path %>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
  	$(function(){
  		setTimeout(go,3000);
  	});
  	function go(){
  		location.href="<%=basePath%>views/productShow.jsp";
  	}
  	</script>
	<style>
		.box{
			margin:100px auto;
			text-align:center;
		}
		.box h2{
			color:#D1EEEE;
		}
	</style>
  </head>
  <body>
    <div class="box">
    	<h2>上传成功</h2><br>
    	<h3>请等待······，3秒后将为您转到作品列表页</h3>
    </div>
  </body>
</html>
