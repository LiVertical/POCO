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
    <title>我的空间</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/mySpace.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/custom.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/css/pagination.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/list.css">
	<script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/js/jquery.cookie.js"></script>
 	<script src="<%=basePath%>/script/mySpace.js"></script>
	<script src="<%=basePath%>/js/common.js"></script>
 	<script src="<%=basePath%>/script/custom.js"></script>
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
		    	 loadSrc('<%=basePath%>/views/myWorks.jsp')
		 });
	</script>
  </head>
  
<body data-user_name = '${sessionScope.loginName }' 
	  data-user_img ='${sessionScope.userImg }'
	  data-user_id ='${sessionScope.userId }'>
   <div class="header">
		<ul>
			<li><a href="<%=basePath%>/index.jsp">POCO首页</a></li>
			<li><a href="<%=basePath%>/views/activities.jsp?userId=${sessionScope.userId }">活动</a></li>
			<li><a href="<%=basePath%>/views/myWorks.jsp">论坛</a></li>
			<li><a href="<%=basePath%>/views/myStores.jsp">问答</a></li>
	 	</ul>

	 	<ul style="float:right;padding-right:66px">
			 <s:if test="#session.user.username== null">
				<li><a href="login-input.action">登录</a></li>
			 </s:if>
			 <s:else>
			 	<li><span id="userName">${sessionScope.loginName }</span></li>
			 	<li><span id="userId">${sessionScope.userId }</span></li>
			 	<li><a href="<%=basePath%>/views/productUpload.jsp">发作品</a></li>
				<li><span id="userImg" style='height:30px;'></span>
					<div class="loginCenter" style="display:none">
						<ul class="menus">
							<li><a href="">设置</a></li>
							<li><a href="">帮助</a></li>
							<li><a onclick="gotoCompleteUserInfo()">完善资料</a></li>
							<li><a href="<%=basePath%>/user/login-loginOut.action">退出登陆</a></li>
						</ul>
					</div>
				</li>
			 </s:else>
	 	</ul>
	</div>
 <div class="main">
 
	<nav id="leftMenu" class="ac_nav" style="float:left;">
				<section class="ac_menu">
					<li onclick="loadSrc('<%=basePath%>/views/myWorks.jsp',this.id)">我的作品</li>
		            <li onclick="loadSrc('<%=basePath%>/views/myComments.jsp',this.id)">我的评论</li>
		            <li onclick="loadSrc('<%=basePath%>/views/myLikes.jsp',this.id)">我的点赞</li>
		            <li onclick="loadSrc('<%=basePath%>/views/myStores.jsp',this.id)">我的收藏</li>
				</section>
			</nav>
	        <div class="rightBody">
	        	<article class="ac_article" style="width:94%;background:#fff;margin:0 auto">
					<iframe id="mainFrame" style="width: 100%;min-height:300px;"  frameborder="0" scrolling="no" onload="iframeHeight()"></iframe>
				</article>
	        </div>
   </div>

 <div class="footer">
		<a href="#" title="POCO" target="_blank">POCO网违法和不良信息举报电话：13928869007 举报邮箱：kent@poco.cn </a>
 </div>

</body>
</html>
