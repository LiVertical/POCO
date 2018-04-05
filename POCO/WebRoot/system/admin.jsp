<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>admin</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css" />
	<script type="text/javascript" src="<%=basePath%>/script/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/script/jquery.cookie.js"></script>
	<script type="text/javascript">
	      function loadSrc(src,id){
	    	  $("#mainFrame").attr("height",100);
	    	  $("#mainFrame").attr("src",src);
	    	  $.cookie("id", id);
	      }
	       function iframeHeight() {
			 	var ifm = document.getElementById("mainFrame");
				var subWeb = document.frames ? document.frames["mainFrame"].document :ifm.contentDocument;
				var height = $("#leftMenu").height()+20;
				if (ifm != null && subWeb != null) {
					ifm.height = subWeb.body.scrollHeight+50;
					if(height<subWeb.body.scrollHeight){
						height = subWeb.body.scrollHeight+50;
					}
				} 
		  }
		</script>
  </head>  
<body>
  <div class="main">
  	 <div id="head">
       <h1 style="font-size: 55px;">POCO 后台管理</h1>
     </div>
     <div class="header">
         <ul class="leftTopNav">
            <li onclick="loadSrc('<%=basePath%>/views/works.jsp',this.id)">作品管理</a></li>
            <li onclick="loadSrc('<%=basePath%>/views/sms.jsp',this.id)">用户管理</li>
            <li><a href="assets.html" id="menu-asset">系统通知</a></li>
            <li><a href="events.html" id="menu-event">用户留言</a></li>
            <li><a href="reports.html" id="menu-report">Reports</a></li>
            <li><a href="tasks.html" id="menu-task">Tasks</a></li>
         </ul>
         <ul class="rightTopNav">
            <li><s:if test="#session.user.username== null">
					<a href="login-input.action">登录</a>
		        </s:if>
				<s:else>
					<a class="userName">${sessionScope.loginName}</a>
			    </s:else>
		    </li>
            <li><a href="<%=basePath%>/login-out.action">退出登陆</a></li>
         </ul>
        </div> <!-- END Header -->
        <article class="ac_article" style="width:94%;background:#fff;margin:0 auto">
			<iframe id="mainFrame" style="width: 100%;"  frameborder="0" scrolling="no" onload="iframeHeight()"></iframe>
		</article>
   </div>
   <div class="footer">
       <a>POCO ©版权所有  京ICP备15044077号-3</a>
   </div>
  </body>
</html>
