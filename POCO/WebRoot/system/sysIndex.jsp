<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>POCO管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/sysIndex.css" />
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/sysIndex.js"></script>
	<script type="text/javascript">
	      function loadSrc(src){
	    	  $("#mainFrame").attr("height",100);
	    	  $("#mainFrame").attr("src",src);
	      }
	      
	       function iframeHeight() {
			 	var ifm = document.getElementById("mainFrame");
				var subWeb = document.frames ? document.frames["mainFrame"].document :ifm.contentDocument;
				var height = $("#leftMenu").height();
				if (ifm != null && subWeb != null) {
					ifm.height = subWeb.body.scrollHeight;
					if(height<subWeb.body.scrollHeight){
						height = subWeb.body.scrollHeight;
					}
				} 
		  }
	       $(function(){
		     loadSrc('<%=basePath%>/system/products.jsp');
		     $.ajax({
		    	url: '<%=basePath%>/admin/checkIsLogin.action',
		    	type: 'post',
		    	dataType: 'json',
		    	async:'false',
		    	data: {},
		    	success : function(json) {
		    		if (json.returnCode == "00") {
		    			$("#mainFrame").attr("src",'<%=basePath%>/system/products.jsp');
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
  	 <div class="head">
       <h1 style="margin-left:70px;">POCO 后台管理</h1>
     </div>
     <div class="header">
         <ul>
			<li onclick="loadSrc('<%=basePath%>system/updatePwd.jsp',this.id)">修改密码</li>
			<li><a href="<%=basePath%>admin/loginOut.action">退出登录</a></li>
         </ul>
   </div>
   <div class="main">
        	<nav id="leftMenu" class="ac_nav" style="float:left;">
				<section class="ac_menu">
					<li onclick="loadSrc('<%=basePath%>/system/products.jsp',this.id)" class="on">图片管理</li>
		            <li onclick="loadSrc('<%=basePath%>/system/smsUsers.jsp',this.id)">用户管理</li>
		            <li onclick="loadSrc('<%=basePath%>/system/smsNotification.jsp',this.id)">系统通知</li>
		            <li onclick="loadSrc('<%=basePath%>/system/addAdminUser.jsp',this.id)">添加管理员</li>
		            <li onclick="loadSrc('<%=basePath%>/system/smsActivities.jsp',this.id)">活动管理</li>
		            <li onclick="loadSrc('<%=basePath%>/system/works.jsp',this.id)">作品管理</li>
		            <li onclick="loadSrc('<%=basePath%>/system/smsContest.jsp',this.id)">大赛管理</li>
		            <li onclick="loadSrc('<%=basePath%>/system/typeManage.jsp',this.id)">作品类别管理</li>
				</section>
			</nav>
	        <div class="rightBody">
	        	<article class="ac_article" style="width:94%;background:#fff;margin:0 auto">
					<iframe id="mainFrame" style="width: 100%;min-height:600px;"  frameborder="0" scrolling="no" onload="iframeHeight()"></iframe>
				</article>
	        </div>
   </div>
   <footer style="color:#585858;clear:both;">
		<div style="height:44px; background: #f7f7f7;"></div>
		<div style="text-align: center; margin:6px 0 10px 0">POCO摄影平台©版权所有&nbsp;&nbsp;京ICP备15044077号-3</div>
	</footer>
  </body>
</html>
