<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
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
    
    <title>�ϴ���Ʒ</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/upload.css">
	<link rel="stylesheet" href="<%=path %>/control/css/zyUpload.css" type="text/css">
	
	<script src="<%=path %>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=path %>/script/jquery.form.js"></script>
	<script src="<%=path %>/script/zyFile.js"></script>
	<script src="<%=path %>/control/js/zyUpload.js"></script>
	<script src="<%=path %>/control/js/common.js"></script>
	<script src="<%=path %>/script/jq22.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
<body>
		<div id="demo" class="demo"></div>
		<div class="content">
	    	<p>��ѡ����Ʒ���ࣺ <select class="type" name="proType">
         			    <option value="">��ѡ����Ʒ����</option>
                        <option value="0" >������Ӱ</option>
                        <option value="1" >��̬��Ӱ</option>
                        <option value="2" >�˶���Ӱ</option>
                        <option value="3" >������Ӱ</option>
                        <option value="4" >ҹ����Ӱ</option>
                        <option value="5" >�羰��Ӱ</option>
                        <option value="6" >��ʵ��Ӱ</option>
                        <option value="7" >������Ӱ</option>
                        <option value="9" >������Ӱ</option>
                        <option value="8" >������Ӱ</option>
                </select></p>
	           <p>���⣺</p>
	           <p><input class="productName" type="text" name="productName" placeholder="��������⡤������������"></p>
	           <p>����(ѡ��)��</p>
	           <p><input class="productDesc" type="text" name="productDesc"></p>          	            
	           <div class="upload_btn">����</div>
     </div> 
  </body>
</html>














