<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传作品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/upload.css">
	<link rel="stylesheet" href="<%=path %>/control/css/zyUpload.css" type="text/css">
	
	<script src="<%=path %>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=path %>/script/zyFile.js"></script>
	<script src="<%=path %>/control/js/zyUpload.js"></script>
	<script src="<%=path %>/control/js/common.js"></script>
	<script src="<%=path %>/script/jq22.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
<body>
		<div id="demo" class="demo"></div>
		<div class="content">
	    	<p>请选择作品分类： <select class="type" name="proType">
         			    <option value="">请选择作品分类</option>
                        <option value="0" >人像摄影</option>
                        <option value="1" >生态摄影</option>
                        <option value="2" >运动摄影</option>
                        <option value="3" >生活摄影</option>
                        <option value="4" >夜景摄影</option>
                        <option value="5" >风景摄影</option>
                        <option value="6" >纪实摄影</option>
                        <option value="7" >人体摄影</option>
                        <option value="9" >自拍摄影</option>
                        <option value="8" >其他摄影</option>
                </select></p>
	           <p>标题：</p>
	           <p><input class="productName" type="text" name="productName" placeholder="请输入标题·······"></p>
	           <p>内容(选填)：</p>
	           <p><input class="productDesc" type="text" name="productDesc"></p>          	            
	           <div class="upload_btn">发布</div>
     </div> 
  </body>
</html>














