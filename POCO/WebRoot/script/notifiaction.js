$(function() {// 初始化内容
	  $("#submit").click(function(){
		  var titel = $("#notifiactionTitle").val();
		  var info = $("#notifiactionInfo").val();
	  		$.ajax({
	  			url:getRootPath() + "/admin/addNotifiaction.action",
	  			type:'POST', //GET
	  		    async:false,    //或false,是否异步
	  		    data:{
	  		    	"notifiaction.notifiactionTitle":titel,
	  		    	"notifiaction.notifiactionInfo":info
	  		    },
	  		  	success:function(data){
		  			if(data.returnCode == '00'){
		  				alert("发布成功！");
		  				
		  			}else{
		  				alert("发布失败！");
		  			}
		  		}
	  		});
	  	});
});

//tab标签翻页
$('.tab .menus li').each(function(){
    $('.tab .menus li').mouseover(function(){
        $('.tab .menus li').removeClass('bg');
        $(this).addClass('bg');
        var index = $(this).index();
       
        $('.tab .scroll').css('margin-top',-index*800+'px');
    });
});