$(function(){
	queryAllActivities();
});

function queryAllActivities(){
	$.post(getRootPath() + "/admin/queryAllActivities.action",  function(data){
		if(data.returnCode == '00'){
			$("#dataDisplayA").empty();
			$("#dataDisplayB").empty();
			$("#dataDisplayC").empty();
			if(data.activitiesInfos.length > 0){
				var htmlA="";
				var htmlC="";
				for(var i = 0; i < data.activitiesInfos.length; i++){
					var status = data.activitiesInfos[i].auditStatus;
					var curStatus = data.activitiesInfos[i].curStatus;
					var statusDesc = "已通过审核";
					if(curStatus == 0){
						statusDesc = "未通过审核";
					}
					if(status == 1){//已审核
						htmlA = "<tr><td>" + data.activitiesInfos[i].activityName + "</td>"
									  + "<td>" + data.activitiesInfos[i].applyTime.substring(0,16) + "</td>"
									  + "<td>" + data.activitiesInfos[i].userName + "</td>" 
									  + "<td>"+ data.activitiesInfos[i].createTime.substring(0,16) +"--"+data.activitiesInfos[i].endTime.substring(0,16)+"</td>" 
									  +"<td>" + statusDesc + "</td></tr>";
					}
					if(status == 0){//待审核						
						htmlC= "<tr> <td>" + data.activitiesInfos[i].activityName + "</td>"
								  + "<td>" + data.activitiesInfos[i].applyTime.substring(0,16) + "</td>"
								  + "<td>" + data.activitiesInfos[i].userName + "</td>" 
								  + "<td>"+ data.activitiesInfos[i].createTime.substring(0,16) +"--"+data.activitiesInfos[i].endTime.substring(0,16)+"</td>" 
								  + "<td><button class='auditBtn' onclick='pass(&quot;"+data.activitiesInfos[i].activityId+"&quot;)'>通过</button>" 
								  + "<button style='background:red' class='auditBtn' onclick='out(&quot;"+data.activitiesInfos[i].activityId+"&quot;)'>否决</button></td></tr>";
					}
					$("#dataDisplayA").append(htmlA);
					$("#dataDisplayC").append(htmlC);
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
