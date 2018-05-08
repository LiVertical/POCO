$(function(){
	getAllWorksInfos(1);
});

function getAllWorksInfos(page){
	var currentPage = page;
	var recordSize = 8;
	var params = {
			currentPage : currentPage,
			recordSize : recordSize,
	};
	$.post(getRootPath() + "/admin/queryAllWorks.action", params, function(data){
		if(data.returnCode == '00'){
			$("#dataDisplay").empty();
			var pageSize = (data.worksInfo.length > recordSize ? recordSize: data.worksInfo.length);
			var tbody = "";
			for(var i = 0; i < pageSize; i++){
				var products = ""; 
				for(var j = 0; j < data.worksInfo[i].productInfos.length; j++){
					products +="<li class='img'><img src='" + getRootPath() + "/" + data.worksInfo[i].productInfos[j].productPath+"'></li>"; 
				}
				 tbody += "<tr><td>"+ (i+1) + "</td>"
	              + "<td style='height:150px;width:150px;'>"+ data.worksInfo[i].workName +"</td>"
	              + "<td style='word-break'>" + data.worksInfo[i].workComment + "</td>"
	              + "<td style='word-break'>" + data.worksInfo[i].workUploadTime.substring(0,16) + "</td>"
				  + "<td><ul>" + products + "</ul></td>"
				  + "<td>" + data.worksInfo[i].userName + "</td>" 
				  + "<td><img style='height:30px;width:44px' src='"+getRootPath()+"/img/icons/delete.jpg' class='delBtn' onclick='deleteProduct("+data.worksInfo[i].workId+")'></td></tr>";
			}
			$("#dataDisplay").html(tbody);
			$("#page").pagination(data.worksCount,{
					callback : function(index) {
									 initUserData(index+1);
								 },
					prev_text : '上一页', //上一页按钮里text
					next_text : '下一页', //下一页按钮里text
					items_per_page : pageSize, //显示条数
					current_page:currentPage-1,
					num_display_entries : 6, //连续分页主体部分分页条目数
					num_edge_entries : 2//两侧首尾分页条目数
			});
			window.parent.window.iframeHeight();
		}
	});
}