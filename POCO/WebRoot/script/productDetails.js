$(function(){
	queryProductInfos();
	
	$("#look").click(function(){
		queryAllComments(1);
		$('#look').attr('disabled',"true");
		$("#look").html("精彩评论");
	});
	queryLikeNum();
});

var  queryProductInfos = function(){
	var productId = GetRequest();
	$.post(getRootPath()+ "/vistor/queryPorductInfosByProductId.action?productId="+productId, function(data){
		if(data.returnCode=='00'){
			if(data.productInfos.length>0){
				var html = "<div class='left_div'><span class='hide' id='productId'>"+productId+"</span>"
				+"<img style='height:100%;width:100%' src='/POCO/"+ data.productInfos[0].productPath+ "'>"
				+ "</div><div class='right_div'><p>标题:" + data.productInfos[0].productName + "</p><br/>"
				+ "<p>作品描述:" + data.productInfos[0].productDesc + "</p><br/>"
				+ "<p>上传时间:" + data.productInfos[0].uploadTime + "</p>"
				+"</div></div>";
			}else{
				html="<p>暂无作品信息</p>";
			}
			$("#p_content").html(html);
		}
	},'json');
};
//添加评论
var addComment = function(){
	var commentDesc = $("#discuss").val();
	var productId = GetRequest();
	$.post(getRootPath()+"/user/addComments.action?productId="+productId+"&commentDesc="+commentDesc, function(result){
		if(result.returnCode == '00'){
			alert("评论成功！");
			window.location.reload();
		}else{
			alert("评论失败");
		}
	},'json');
};

//查询所有评论
var queryAllComments = function(currentPage){
	var recordSize = 8;
	var pageSize = 6;
	var productId = $("#productId").text();
	$.post(getRootPath()+"/vistor/queryAllComments.action?productId="+productId+"&currentPage="+currentPage+"&recordSize="+recordSize, function(result){
		if(result.returnCode == '00'){
			var commentList = result.commentsInfos;
			var html = "";
			 pageSize = (result.commentsTotalsCount > recordSize ? recordSize: result.commentsTotalsCount);
			 if(result.commentsInfos.length > 0){
				for(var i = 0; i < commentList.length; i++){
					html = "<li><div class='commentsInfo'><p>&nbsp;</p>"+commentList[i].commentDesc
							+  "<p>"+commentList[i].createTime.substring(0,16)+"</p></li>";
				}
			 }else{
				 html = "<p style='text-align:center'>暂时还没有评论</p>";
			 }
			$("#hotDiscuss").html(html);
			if ($("#page").html() == '') {
                $("#page").pagination(result.commentsTotalsCount, {
                      callback: function (index) {
                    	  queryAllComments(index+1);
                      },
                      prev_text: '上一页',       //上一页按钮里text
                      next_text: '下一页',       //下一页按钮里text
                      items_per_page: pageSize, 
                      current_page:currentPage-1,
                      num_display_entries: 6,    //连续分页主体部分分页条目数
                      num_edge_entries: 2        //两侧首尾分页条目数
                  });
			}
		}
	},'json');
};
//查询当前点赞数目
var queryLikeNum = function(productId){
	var productId = GetRequest();
	var url = getRootPath() + "/vistor/getLikesNum.action";
	var param = {
			productId:productId,
	};
	$.post(url, param, function(result){
		if(result.returnCode == '00'){
			/*alert(result.returnMsg+result.likesNum);*/
		}else{
			alert("点赞失败");
		}
	},'json');
};
