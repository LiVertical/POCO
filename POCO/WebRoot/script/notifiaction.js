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
		  			
		  		}
	  		});
	  	});
});

//tab标签翻页
$('#myTab a').click(function (e) {
	  e.preventDefault();
	  $(this).tab('show');
});