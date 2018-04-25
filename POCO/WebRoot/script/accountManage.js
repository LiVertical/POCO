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
	$.post(getRootPath()+"/user/getUserInfo.action", function(data){
		if(data.returnCode == '00'){
			alert("eytr");
		}
	});
}

function getUserInfo(){
	$.post(getRootPath()+"/user/getUserInfo.action", function(data){
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
