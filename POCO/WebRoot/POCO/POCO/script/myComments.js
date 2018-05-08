$(function(){
	queryAllComments(1);
});

function queryAllComments(page){
	var recordSize = 5;
	var currentPage = page;
	var params = {
			recordSize : recordSize,
			currentPage : currentPage
	};
	$.post(getRootPath()+"/user/queryAllCommentsByUser.action", params,  function(data){
		if(data.returnCode == '00'){
			var html = '';
			var commentList = data.commentsInfosOfUser;
    		$("#dataDisplay").html("");
    		$("#page").html("");
			if(commentList.length>0){
				for(var i = 0; i < commentList.length; i++){
					html = "<li><p>"+commentList[i].commentDesc
								+"</p><span>"+commentList[i].createTime.substring(0,16)
								+"<button class='delBtn' onclick='deleteComment("+commentList[i].commentId+")'>删除</button></span></li>";
					$("#dataDisplay").append(html);
				}
			}else{
				$("#dataDisplay").html("<p style='text-align: center;margin-top:30px'>您暂时还没有评论</p>");
			}
				if ($("#page").html() == '') {
	                  $("#page").pagination(data.commentsTotalsCountOfUser, {
	                        callback: function (index) {
	                        	queryAllComments(index+1);
	                        },
	                        prev_text: '上一页',       //上一页按钮里text
	                        next_text: '下一页',       //下一页按钮里text
	                        items_per_page: recordSize, 
	                        current_page:currentPage-1,
	                        num_display_entries: 6,    //连续分页主体部分分页条目数
	                        num_edge_entries: 2        //两侧首尾分页条目数
	                    });
				}
			}
	},'json');
}

function deleteComment(commnetId){
	$.post(getRootPath()+"/user/deleteCommentByCommentId.action?commentId="+commentId, function(data){
		if(data.returnCode='00'){
			alert("删除成功！");
			queryAllComments(1);
		}
	});
}