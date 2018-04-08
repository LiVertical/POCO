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
    
    <title>POCO管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/sysIndex.css" />
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/sysIndex.js"></script>
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
	       $(function(){
		     loadSrc('<%=basePath%>/system/works.jsp');
		     $.ajax({
		    	url: '<%=basePath%>/admin/checkIsLogin.action',
		    	type: 'post',
		    	dataType: 'json',
		    	async:'false',
		    	data: {},
		    	success : function(json) {
		    		if (json.returnCode == "00") {
		    			 $("#mainFrame").attr("src",src);
		    			$("#mainFrame").attr("height",100);
		    		}else{
		    			logout();
		    		} 
		    	}
		   });
	 });
	</script>
  </head>  
<body>
  	 <div id="head">
       <h1 style="font-size: 55px;">POCO 后台管理</h1>
     </div>
     <div class="header">
         <ul>
			<li onclick="loadSrc('<%=basePath%>system/updatePwd.jsp',this.id)">修改密码</li>
			<li><a href="<%=basePath%>admin/login-loginOut.action">退出登录</a></li>
         </ul>
   </div> <!-- END Header -->
   <div class="main">
        	<nav id="leftMenu" class="ac_nav" style="float:left;">
				<section class="ac_menu">
					<li onclick="loadSrc('<%=basePath%>/system/works.jsp',this.id)">作品管理</li>
		            <li onclick="loadSrc('<%=basePath%>/system/sms.jsp',this.id)">用户管理</li>
		            <li onclick="loadSrc('<%=basePath%>/system/smsNotification.jsp',this.id)">系统通知</li>
		            <li onclick="loadSrc('<%=basePath%>/system/addAdminUser.jsp',this.id)">添加管理员</li>
		            <li><a href="reports.html" id="menu-report">Reports</a></li>
		            <li><a href="tasks.html" id="menu-task">Tasks</a></li>
				</section>
			</nav>
	        <div class="rightBody">
	        	<article class="ac_article" style="width:94%;background:#fff;margin:0 auto">
					<iframe id="mainFrame" style="width: 100%;min-height:500px;"  frameborder="0" scrolling="no" onload="iframeHeight()"></iframe>
				</article>
	        </div>
   </div>
   <footer style="color:#585858;clear:both;">
		<div style="height:44px; background: #f7f7f7;"></div>
		<div style="text-align: center; margin:6px 0 10px 0">POCO摄影平台©版权所有&nbsp;&nbsp;京ICP备15044077号-3</div>
	</footer>
  </body>
</html>
