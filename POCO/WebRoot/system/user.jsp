<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    This is my vip page. <br>
     <p> ��ӭ�㣺${sessionScope.user }</p>
     <a href="user-complete.action">�����û���Ϣ</a>
     <a href="user-update.action">�༭�û���Ϣ</a>
     <a href="product-upload.action">������Ʒ</a>
     <a href="login-out.action">ע��</a>

  </body>
</html>
