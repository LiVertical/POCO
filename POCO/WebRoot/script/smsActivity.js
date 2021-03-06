var auditStatus = 1;
$(function(){
	queryAllActivities(1);
	
	$("#ready").click(function(){
		auditStatus = 1;
		queryAllActivities(1);
	});
	
	$("#wait").click(function(){
		auditStatus = 0;
		queryAllActivities(1);
	});
	
});

function queryAllActivities(page){
	var currentPage = page;
	var recordSize = 6;
	params = {
			currentPage : currentPage,
			recordSize : recordSize,
			auditStatus : auditStatus,
	};
	$.post(getRootPath() + "/admin/queryAllActivities.action", params,  function(data){
		if(data.returnCode == '00'){
			$("#dataDisplay").empty();
			if(data.activitiesInfos.length > 0){
				var pageSize = data.activitiesInfos.length > recordSize ? recordSize: data.activitiesInfos.length;
				for(var i = 0; i < pageSize; i++){
					var html="";
					var status = data.activitiesInfos[i].auditStatus;
					var curStatus = data.activitiesInfos[i].curStatus;
					html = "<tr><td>" + data.activitiesInfos[i].activityName + "</td>"
						 + "<td>" + data.activitiesInfos[i].activityDesc + "</td>"
						 + "<td>" + data.activitiesInfos[i].applyTime.substring(0,16) + "</td>"
						 + "<td>" + data.activitiesInfos[i].activityUser + "</td>" 
						 + "<td>"+ data.activitiesInfos[i].startTime.substring(0,16) +"--"+data.activitiesInfos[i].endTime.substring(0,16)+"</td>";
					if(status == 1){//已审核
						var statusDesc = "已通过审核";
						if(curStatus == 0){
							statusDesc = "未通过审核";
						}
						html += "<td>" + statusDesc + "</td></tr>";
					}else if(status == 0){
						html += "<td><button class='auditBtn' onclick='pass(&quot;"+data.activitiesInfos[i].activityId+"&quot;)'>通过</button>" 
						  + "<button style='background:red' class='auditBtn' onclick='out(&quot;"+data.activitiesInfos[i].activityId+"&quot;)'>否决</button></td></tr>";
					}
					$("#dataDisplay").append(html);
				}
				 if ($("#page").html() == '') {
			           $("#page").pagination(data.totalActivitiesCount, {
			                callback: function (index) {
			                	queryAllActivities(index+1);
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
		}
	});
}

function pass(id){
	var params = {
		activityId : id,
		curStatus : 1,
	};
	$.post(getRootPath() + "/admin/auditActivity.action", params, function(data){
		if(data.returnCode == '00'){
			alert("审批成功");
			queryAllActivities();
		}else{
			alert("审核失败");
		}
	});
}

function out(id){
	var params = {
			activityId : id,
			curStatus : 2,
		};
	$.post(getRootPath() + "/admin/auditActivity.action", params, function(data){
		if(data.returnCode == '00'){
			alert("审批成功");
			queryAllActivities();
		}else{
			alert("审核失败");
		}
	});
}
