$(function(){
	queryAllWors(1);
	
});

function queryAllWors(page){
	var currentPage = page;
	var recordSize = 5;
	var productName = $("#productName").val();
	var params = {
			currentPage : currentPage,
			recordSize : recordSize,
			productName : productName,
	};
	$.post(getRootPath() + "/user/queryWorks.action", params, function(data){
		if(data.returnCode == '00'){
			var pageSize = (data.worksInfo.length > recordSize ? recordSize: data.worksInfo.length);
			$("#p_content").empty();
			if(data.worksInfo.length>0){
				var html = "";
				for(var i = 0;i < pageSize; i++){
					html += "<li><div class='leftDiv'><img src='" + getRootPath() +"/"+  data.worksInfo[i].productPath+"'></div>"
							 +"<div class='rightDiv'><p>作品名称："+ data.worksInfo[i].workName+"</p>"
							 +"<p>上传时间："+data.worksInfo[i].uploadTime.substring(0,16)+"</p>"
							 +"<a href='" +getRootPath() + "/views/workDetails.jsp?workId="+data.worksInfo[i].workId+"'>点击查看作品详情</a></div></li>";
				};
			}else{
				html = "<p>暂时还没有作品</p>";
			}
			$("#p_content").append(html);
			$("#page").pagination(data.worksCount,{
					callback : function(index) {
						queryAllWors(index+1);
					},
					prev_text : '上一页', //上一页按钮里text
					next_text : '下一页', //下一页按钮里text
					items_per_page : pageSize, //显示条数
					current_page:currentPage-1,
					num_display_entries : 6, //连续分页主体部分分页条目数
					num_edge_entries : 2//两侧首尾分页条目数
			});
		}
	});
}