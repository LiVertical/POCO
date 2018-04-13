<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>更新密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/commonsdm.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/control/js/common.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/custom.js"></script>
	 
	<script>
		 function loadSrc(src,id){
	    	  $("#mainFrame").attr("height",100);
	    	  $("#mainFrame").attr("src",src);
	      }
	      function iframeHeight() {
				var ifm = document.getElementById("mainFrame");
				var subWeb = document.frames ? document.frames["mainFrame"].document :ifm.contentDocument;
				if (ifm != null && subWeb != null) {
					ifm.height = subWeb.body.scrollHeight;
				}
				window.parent.window.iframeHeight();
		  }
		
	</script>	 
 </head>
	<script type="text/javascript">
	 function updatePwd(){
		 var oldPwd=document.getElementsByName("oldPwd");
		 var newPwd=document.getElementsByName("supportSysUser.password");
		 var repeatNewPwd=document.getElementsByName("repeatPassword");
		 if(oldPwd[0].value==""){
			 alert("请输入当前密码!");
			 return false;
		 }
		 if(newPwd[0].value==""){
			 alert("新密码不能为空!");
			 return false;
		 }
		 if(newPwd[0].value!=repeatNewPwd[0].value){
			 alert("新密码与确认密码不一致!")
			 return false;
		 }
		return true;
	} 
	$(document).ready(function(){
	     top.$("#ui-layout-north").contents().find('.header_right .off li').removeClass('sell');
	     $('input[name=oldPwd]').val('');
	})
	
	function updatePass(){
		if(!updatePwd())
		{
		return;
		}
		var oldPass = $("#old_pass").val();
		var newPass = $("#new_pass").val();
		$.post(getRootPath()+'/admin/updatePass.action', {'oldPass':oldPass,'newPass':newPass},
				   function(data){
						if(data.returnCode == '00'){
							alert("更新密码成功");
							window.top.location.href = getRootPath() + "/system/login.jsp";
						}else{
							alert("更新密码失败");
						}
				   },"json");
	}
	</script>
	<body style="background: white">
	<div class="conter_right" style="min-height:400px;">
		<div class="conter_content">
			<s:if test="message!=null">
				<s:property value="message"/>
			</s:if>
			<div class="nav" style="margin-top: 0px;">
				<p class="sty_nav">
					修改密码
				</p>
			</div>
			<div class="dh">
			</div>
			<div class="add_name">
			<form method="post" action="" id="updateP">
				<input type="hidden" name="supportSysUser.loginName" value="" id="login_name"/>
				<input type="hidden" name="supportSysUser.userId" value="" id="user_id"/>
				<p><span class="ak47"><label>当前密码：</label></span><input type="password" name="oldPwd" id="old_pass"/></p>
				<p><span class="ak47"><label>新密码：</label></span><input type="password" name="supportSysUser.password" id="new_pass"/></p>
				<p><span class="ak47"><label>确认密码：</label></span><input type="password" name="repeatPassword" /></p>
			</form>
			<p class="sum add_btn" style="display:inline;"><button style="margin-left:100px" onclick="updatePass();">保存</button></p>
			<p style="display:inline;"><button style="min-width:100px;height:30px;margin-left:30px" onclick="javascript:history.go(-1);">返回</button></p>
			</div>
			</div>	<br/>&nbsp;&nbsp;
		</div>
</body>
</html>
