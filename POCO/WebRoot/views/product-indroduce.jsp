<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'product-indroduce.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	

  </head>
  
  <body>
    This is my JSP page. <br>

    	<table border="1">
    		<tr>
    			<td>PRODUCTID</td>
    			<td>PRODUCT</td>
    			<td>PRODUCTNAME</td>
    			<td>UPLOADTIME</td>
    			<td>评论</td>
    			<td>添加评论</td>
    		</tr>
    		
    	<s:iterator value="#request.productInfo">
    		<tr>
    			<td>${productId }</td>
    			<td><img src="${productPath }"></img></td>
    			<td>${productName }</td>
    			<td>${uploadTime }</td>
    			<td><a href="content-list.action?productId=${productId }">查看评论</a></td>
    			<td><a href="content-add.action?productId=${productId }">添加评论</a></td>  
    		</tr>			
    	</s:iterator> 
    		
    	

    			

    	</table>    
   	     	
  </body>
</html>













