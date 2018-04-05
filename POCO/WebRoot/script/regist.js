var regist = function(){
	var loginName = $("input[name='loginName']").val();
	var loginPass = $("input[name='loginPwd']").val();
	var userName = $("input[name='userName']").val();
	params = {
			loginName : loginName,
			loginPass : loginPass,
			userName :userName
	};
	$.post(getRootPath() + "/regist-adminUserRegist.action", params, function(data){
		if(data.returnCode == '00'){
			alert("注册成功,返回登录！");
			window.location.href = getRootPath() + "/views/login.jsp";
		}else{
			alert("注册失败！");
		}
	});
};