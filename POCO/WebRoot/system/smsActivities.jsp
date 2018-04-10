<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>活动管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		table{
			text-align:center;
		}
	</style>
	
	<script type="text/javascript" src="script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">		
	$(function(){
	
		$(".delete").click(function(){

			var flag = confirm("确定删除这条评论吗?");
			
			if(flag){
				var $tr = $(this).parent().parent();
				//删除 用ajax
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url,args,function(data){
					//如果data的返回值是1，则删除成功
					if(data == "0"){
						alert("删除成功！");
						$tr.remove();
						location.reload();
					}else{
						alert("删除失败!");
					}
				});
			}
			//去下破超链接行为
			return false;
		});
	
	});
	</script>
  </head>  
  <body>
  <table border="1" align="center">
  	<tr>
  		<td>楼层</td>
  		<td>用户</td>
  		<td>评论内容</td>
  		<td>评论时间</td>
  		<td colspan="2">操作</td>
  	</tr>
	<c:forEach var="c" items="${content }">  
	   <tbody>              
	  	<tr> 
	  		<td>${c.contentId }</td>  		  
		  	<td><h3>${c.contentUser }</h3></td>
		  	<td>${c.contentDesc}</td>
		  	<td><h6>${c.contentTime}</h6></td>
		  	<td><a href="reply-add.action?productId=${c.productId }">回复此评论</a></td>
		  	<td><a href="content-delete.action?contentId=${c.contentId }" class="delete">删除此评论</a></td>
		 </tr>
		</tbody>
	</c:forEach>
</table>
  </body> 
</html>
