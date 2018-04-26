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

});