$(function(){
	//背景图片轮播
	(function() {
		var bgCounter = 0, backgrounds = [ "img/slider/4.jpg",
				"img/slider/2.jpg", "img/slider/1.jpg" ];
		function changeBackground() {
			bgCounter = (bgCounter + 1) % backgrounds.length;
			$('body').css(
					{
						'background' : '#000 url(' + backgrounds[bgCounter]
								+ ') no-repeat',
						'background-size' : 'cover'
					});
			setTimeout(changeBackground, 10000);
		}
		changeBackground();
	})();
	
	
	//校验验证码
	$("#loginBtn").click(function() {
		var inputval = $("#codeInput").val();
		if (inputval != vCode) {
			alert("验证码错误");
			return false;
		}
		login();
	});

	/*//点击图片获取验证码
	$("#verify").click(function() {
		alert(1);
		getCode();
	});*/
	
});

/*function getCode(){
	$('#verify').attr('src', getRootPath() + "/admin/generateCode.action");
	alert(2);
};*/

	
function login() {
	var loginName = $("#username").val();
	if (loginName == "" || loginName.length == 0) {
		alert("请输入用户名");
		return;
	}
	var loginPwd = $("#password").val();
	if (loginPwd == "" || loginPwd.length == 0) {
		alert("请输入密码");
		return;
	}
	$.ajax({
		//访问的类型
		type : "post",
		//访问地址
		url : "admin/login-adminUserLogin.action",
		//返回的json串
		data : {
			'loginName' : loginName,
			'loginPass' : loginPwd
		},
		//返回的数据类型
		dataType : "json",
		//从后台返回的结果
		success : function() {
		},
	});
};

