var regist = function(){
	var loginName = $("input[name='loginName']").val();
	var loginPass = $("input[name='loginPass']").val();
	var userName = $("input[name='userName']").val();
		if(loginName == ''){
			alert("请先输入账户名");
			return;
		}
		if(loginPass == ''){
			alert("请先输入密码");
			return;
		}
		if(userName == ''){
			alert("请先输入昵称");
			return;
		}
	params = {
			loginName : loginName,
			loginPass : loginPass,
			userName :userName,
	};
	$.post(getRootPath() + "/admin/adminUserRegist.action", params, function(data){
		if(data.returnCode == '00'){
			alert("注册成功,返回登录！");
			window.location.href = getRootPath() + "/views/login.jsp";
		}else{
			alert("注册失败！");
		}
	});
};