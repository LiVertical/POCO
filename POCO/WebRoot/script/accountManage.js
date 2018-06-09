$(function(){
	$("#headImg").on("change", function(){
		var image = document.getElementById("headImg").files[0];
		var formData = new FormData();
		formData.append("upload",image);
		$.ajax({
	        url: getRootPath() + "/user/uploadUserImg.action",
	        type: "post",
	        data: formData,
	        dataType:'json',
	        contentType: false,
	        processData: false,
	        mimeType: "multipart/form-data",
	        success: function (data) {
	        	if(data.returnCode == '00'){
	        		 console.log("上传头像："+data.returnCode+";"+data.returnMsg);
	                 alert("上传成功");
	        	}
	        },
	        error: function (data) {
	            console.log(data);
	            alert("上传失败");
	        }
	    });
	});
	
	getUserInfo();
	
});

function updateProFile(){
	var userName  = $("#userName").val();
	var email = $("#userEmail").val();
	var age = $("#userAge").val();
	var sex = $("input[name='sex']").val();
	if(userName == ''){
		alert("请输入昵称");
		return;
	}
	if(email == ''){
		alert("请输入您的邮箱");
		return;
	}
	if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)){
		alert("请输入正确的邮箱");
		return;
	}
	if(age == ''){
		alert("请输入您的年龄");
		return;
	}
	if(!age.match(/^(?:[1-9]?\d|100)$/)){
		alert("请输入正确的年龄");
		return;
	}
	var params = {
			userName:userName,
			email:email,
			age:age,
			sex:sex
	};
	$.post(getRootPath()+"/user/saveOrUpdateUserInfo.action", params, function(data){
		if(data.returnCode == '00'){
			alert("更新资料成功！");
			getUserInfo();
		}else{
			alert("更新资料失败");
		}
	});
}

function getUserInfo(){
	$.post(getRootPath() + "/user/getUserInfo.action", function(data){
		if(data.returnCode == '00'){
			if(data.userInfos.userName != ''){
				$("#userName").val(data.userInfos.userName);
			}
			if(data.userInfos.age != 0){
				$("#userAge").val(data.userInfos.age);
			}
			if(data.userInfos.email != ''){
				$("#userEmail").val(data.userInfos.email);
			}
			if(data.userInfos.sex == 0){
				$("#women").attr("checked",true);
			}else{
				$("#women").attr("checked",false);
			}
		}
	});
}

	
	function updatePass(){
		var oldPass = $("#oldPass").val();
		var newPass = $("#newPass").val();
		var checkPass = $("#checkPass").val();
		if(oldPass == ''){
			alert("请输入旧密码");
		}
		if(newPass == ''){
			alert("请输入新密码");
		}
		if(checkPass == ''){
			alert("请再次确认密码");
		}
		if(newPass != '' && checkPass != null){
			if(newPass != checkPass){
				alert("两次输入密码不一致");
			}
		}
	
	var params = {
			oldPass : oldPass,
			newPass : newPass,
	};
	$.post(getRootPath() + "/user/updatePass.action", params ,function(data){
		if(data.returnCode == '00'){
			alert("修改成功");
		}else{
			alert("修改失败");
		}
	});	
}
