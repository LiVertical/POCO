$(function(){
	initUserInfo();
	$("#file").change(function (e) {
        var file = e.target.files[0] || e.dataTransfer.files[0];
        $('#photoCover').html(document.getElementById("file").files[0].name);
        if (file) {
            var reader = new FileReader();
            reader.onload = function () {
                $("img").attr("src", this.result);
            };
            reader.readAsDataURL(file);
        }
    });
});

var gotoCompleteUserInfo = function(){
	window.location = getRootPath() + "/pages/userInfoEdit.jsp";
};

var initUserInfo = function(){
	$.post(getRootPath() + '/admin/user-getUserInfo.action',function(data){
		if(data.returnCode == '00'){
			console.log("success");
			if(data.role != '2'){
			$("#userId").val(data.userId);
			 $("img").attr("src", getRootPath()+"/"+data.userImg);
			$("#userName").val(data.userName);
			$("#userAge").val(data.userAge);
			$("#userEmail").val(data.userEmail);
			if(data.userSex=='1'){
				$("#man").attr('checked', true);
			}else{
				$("#women").attr('checked', true);
			}
		}else{
			console.log("failed");
		}}
	},'json');
};

var gotoMyWorks = function(userName){
	window.location = getPath() + "/POCO/views/myWorks.jsp";
	queryProducts(userName);
}; 

//更新用户信息
var saveOrUpdateUserInfo = function(){
	var userId = $("#userId").val();
	var userName = $("#userName").val();
	var age = $("#userAge").val();
	var email = $("#userEmail").val();
	var sex = $("input[name='sex']:checked").val();
	params = {
		userId : userId,
		userName : userName,
		age : age,
		email : email,
		sex : sex
	};
	$.post(getRootPath() + '/admin/user-saveOrUpdateUserInfo.action', params, function(data){
		if(data.returnCode == '00'){
			alert("更新数据成功");
			window.location.href = getRootPath() + "/views/myWorks.jsp";
		}else{
			alert("更新数据失败");
		}
	},'json');
};


