<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />
    <script src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>/js/common.js"></script>
<script type="text/javascript">
$(function() {
	$("#login").click(function() {
		var name_state = $('#name');
		var psd_state = $('#psd');
		var name = $('#name').val();
		var psd = $('#psd').val();
		if (name == '') {
			name_state.parent().next().next().css("display", "block");
			return false;
		} else if (psd == '') {
			name_state.parent().next().next().css("display", "none");
			psd_state.parent().next().next().css("display", "block");
			return false;
		} else {
			name_state.parent().next().next().css("display", "none");
			psd_state.parent().next().next().css("display", "none");
			$('.login').submit();
		}
		

	});
	$("#register").click(function() {
		var username_state = $('#username');
		var password_state = $('#password');
		var affirm_psd_state = $('#affirm_psd');
		var username = $('#username').val();
		var password = $('#password').val();
		var affirm_psd = $('#affirm_psd').val();
		if (username == '') {
			username_state.parent().next().next().css("display", "block");
			return false;
		} else if (password == '') {
			password_state.parent().next().next().css("display", "block");
			return false;
		} else if (affirm_psd == '') {
			affirm_psd_state.parent().next().next().css("display", "block");
			return false;
		} else if (password != affirm_psd) {
			return false;
		} else {
			$('.register').submit();
		}
	});
});

function ok_or_errorBylogin(l) {
	var content = $(l).val();
	if (content != "") {
		$(l).parent().next().next().css("display", "none");
	}
}

function ok_or_errorByRegister(r) {
	var affirm_psd = $("#affirm_psd");
	var password = $("#password");
	var affirm_psd_v = $("#affirm_psd").val();
	var password_v = $("#password").val();
	var content = $(r).val();
	if (content == "") {
		$(r).parent().next().next().css("display", "block");
		return false;
	} else {
		$(r).parent().next().css("display", "block");
		$(r).parent().next().next().css("display", "none");
		if (password_v == "") {
			$(password).parent().next().css("display", "none");
			$(password).parent().next().next().css("display", "none");
			$(password).parent().next().next().css("display", "block");
			return false;
		}
		if (affirm_psd_v == "") {
			$(affirm_psd_v).parent().next().css("display", "none");
			$(affirm_psd_v).parent().next().next().css("display", "none");
			$(affirm_psd_v).parent().next().next().css("display", "block");
			return false;
		}
		if (password_v == affirm_psd_v) {
			$(affirm_psd).parent().next().css("display", "none");
			$(affirm_psd).parent().next().next().css("display", "none");
			$(password).parent().next().css("display", "none");
			$(password).parent().next().next().css("display", "none");
			$(affirm_psd).parent().next().css("display", "block");
			$(password).parent().next().css("display", "block");
		} else {
			$(affirm_psd).parent().next().css("display", "none");
			$(affirm_psd).parent().next().next().css("display", "none");
			$(password).parent().next().css("display", "none");
			$(password).parent().next().next().css("display", "none");
			$(password).parent().next().css("display", "block");
			$(affirm_psd).parent().next().next().css("display", "block");
			return false;
		}
	}
}

function gotoRegist() {
	$("#login_div").hide();
	$("#regist_div").show();
}

function gotoLogin() {
	$("#login_div").show();
	$("#regist_div").hide();
}

    </script>
  </head>

  <body>
    <div class="login_div" id="login_div">
      <form action="<%=basePath%>user/userLogin.action" method="post">
        <div class="col-xs-12 login_title">登录</div>
        <div class="login">
            <div class="nav">
                <div class="nav login_nav">
                    <div class="col-xs-4 login_username">
                        用户名:
                    </div>
                    <div class="col-xs-6 login_usernameInput">
                        <input type="text" name="loginName" id="name" value="" placeholder="&nbsp;&nbsp;用户名/手机号" onblur="javascript:ok_or_errorBylogin(this)" />
                    </div>
                    <div class="col-xs-1 ok_gou">
                        √
                    </div>
                    <div class="col-xs-1 error_cuo">
                        ×
                    </div>
                </div>
                <div class="nav login_psdNav">
                    <div class="col-xs-4">
                        密&nbsp;&nbsp;&nbsp;码:
                    </div>
                    <div class="col-xs-6">
                        <input type="password" name="loginPass" id="psd" value="" placeholder="&nbsp;&nbsp;密码" onBlur="javascript:ok_or_errorBylogin(this)" />
                    </div>
                    <div class="col-xs-1 ok_gou">
                        √
                    </div>
                    <div class="col-xs-1 error_cuo">
                        ×
                    </div>
                </div>
                <div class="col-xs-12 login_btn_div">
                    <button class="sub_btn" id="login">登录</button>
                </div>
            </div>
        </div>
     </form>
     <div class="col-xs-12 barter_btnDiv">
            <button class="barter_btn" onclick="gotoRegist()">没有账号?前往注册</button>
        </div>
    </div>

    <div class="register_body" id="regist_div"> 
        <div class="col-xs-12 register_title">注册</div>
        <form action="userInfoSave.action" class="register" method="get">
            <div class="nav">
                <div class="nav register_nav">
                    <div class="col-xs-4">
                        账号:
                    </div>
                    <div class="col-xs-6">
                        <input type="text" name="loginName" id="username" value="" placeholder="&nbsp;&nbsp;1-8位英文字符" onBlur="javascript:ok_or_errorByRegister(this)" />
                    </div>
                    <div class="col-xs-1 ok_gou">
                        √
                    </div>
                    <div class="col-xs-1 error_cuo">
                        ×
                    </div>
                </div>
                <div class="nav register_psdnav">
                    <div class="col-xs-4">
                        密&nbsp;&nbsp;&nbsp;码:
                    </div>
                    <div class="col-xs-6">
                        <input type="password" name="loginPass" id="password" value="" placeholder="&nbsp;&nbsp;密码" onBlur="javascript:ok_or_errorByRegister(this)" />
                    </div>
                    <div class="col-xs-1 ok_gou">
                        √
                    </div>
                    <div class="col-xs-1 error_cuo">
                        ×
                    </div>
                </div>
                <div class="nav register_affirm">
                    <div class="col-xs-4">
                        确认密码:
                    </div>
                    <div class="col-xs-6">
                        <input type="password" name="" id="affirm_psd" value="" placeholder="&nbsp;&nbsp;确认密码" onBlur="javascript:ok_or_errorByRegister(this)" />
                    </div>
                    <div class="col-xs-1 ok_gou">
                        √
                    </div>
                    <div class="col-xs-1 error_cuo">
                        ×
                    </div>
                </div>
                <div class="col-xs-12 register_btn_div">
                    <input type="submit" class="sub_btn" value="注册" id="register" />
                </div>
            </div>
        </form>
        <div class="col-xs-12 barter_register">
            <button class="barter_registerBtn" onclick="gotoLogin()">已有秘籍?返回登录</button>
        </div>
    </div>
   
</body>
</html>
