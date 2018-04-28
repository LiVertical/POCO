$(function(){
	queryAllNotificationsByUserId(1);
});

function queryAllNotificationsByUserId(page){
	var currentPage = page;
	var recordSize = 6;
	params = {
			currentPage : currentPage,
			recordSize : recordSize,
	};
	$.getJSON(getRootPath() + "/user/notificationList.action", params, function(result) {
			$("#p_content").empty();
			$("#page").empty();
			console.log("notificationList:"+result.notificationList);
			if (result.returnCode == "00") {
				var htm = '';
				if (result.notificationTotalCount > 0) {
					var pageSize = (result.notificationList.length > recordSize ? recordSize: result.notificationList.length);
					 for (var i = 0; i < pageSize; i++) {
						htm += "<li><button class='on' onclick='queryNotificationInfo(&quot;"+result.notificationList[i].notificationId+"&quot;)'>点击查看详情</button>"
									+"<div class='nfsInfo' id='nfsInfo"+result.notificationList[i].notificationId+"'></div>"
									+ result.notificationList[i].notificationTitle + "</a>"
									+"<div style='display:none;background:#000;' id='infoBox'>" + result.notificationList[i].notificationTitle + "</div>"
									+ "<p>" + result.notificationList[i].updateTime.substring(0,16)
									+"<img style='height:30px;width:44px' src='"+getRootPath()+"/img/icons/delete.jpg' class='delBtn' onclick='deleteNotification(&quot;"+result.notificationList[i].notificationId+"&quot;)'></p></li>";
					 }
				}else{
					 htm="暂无通知";
				}
			  $("#p_content").append(htm);
			 if ($("#page").html() == '') {
		           $("#page").pagination(result.notificationTotalCount, {
		                callback: function (index) {
		                    queryAllNotificationsByUserId(index+1);
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
};
function deleteNotification(notificationId){
	$.post(getRootPath()+"/user/delNotification.action?notificationId="+notificationId, function(data){
		if(data.returnCode == '00'){
			alert("删除成功");
			queryAllNotificationsByUserId(1);
		}else{
			alert("删除失败");
		}
	});
	
};

function queryNotificationInfo(notificationId){
	$.post(getRootPath() + "/user/showNotification.action?notificationId="+notificationId , function(data){
		var on = "#nfsInfo"+notificationId;
		var off = "#off" + notificationId;
		if(data.returnCode == '00'){
			$(on).show();
			var html = "<p style='float:left'>"+data.notificationInfos.notificationInfo+"</p>"
							+"<button class='off' id='off"+notificationId+"'>点击收起</button>";
			$(on).html(html);
			$(off).click(function(){
				$(on).hide();
			});
		}
	});
	
} 

