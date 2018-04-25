$(document).ready(function(){
//鼠标悬停事件
    $("#userImg").mouseover(function (){
		$(".loginCenter").show();
	});
    $("#userImg").mouseout(function (){
		$(".loginCenter").hide();
	});
    $(".loginCenter").mouseover(function (){
    	$(".loginCenter").show();
    }).mouseout(function (){
		$(".loginCenter").hide();
	});

 //完善用户信息
var gotoCompleteUserInfo = function(){
		window.location = getRootPath() + "/pages/userInfoEdit.jsp";
};

//我的空间
var gotoMyWorks = function(){
	window.location = getRootPath() + "/views/mySpace.jsp";
};

});