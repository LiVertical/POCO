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
    
    
    var data=$('body').data();
    var userImg = data.user_img;
	console.log(userImg);
	loadUserImg(userImg);
});

//加载用户头像
var loadUserImg = function(userImg){
	 var htm = "<img class='userImg' src="+getRootPath() + "/" + userImg + ">";
	 $("#userImg").append(htm);
 };
 
 //完善用户信息
var gotoCompleteUserInfo = function(){
		window.location = getRootPath() + "/pages/userInfoEdit.jsp";
};

//我的空间
var gotoMyWorks = function(){
	window.location = getPath() + "/POCO/views/mySpace.jsp";
};
