$(function(){
	var workId = $("body").data().work_id;
	console.log("作品详情展示："+workId);
	getAllWorkInfos();
});

function getAllWorkInfos(){
	var workId = $("body").data().work_id;
	$.post(getRootPath()+"/vistor/queryWorksInfosByWorkId.action?workId="+workId, function(data){
		if(data.returnCode= '00'){
			if(data.worksInfos[0].productInfos.length>0){
				var pHtml = "";
				for(var i = 0; i < data.worksInfos[0].productInfos.length; i++){
					pHtml += "<li><img src='/"+data.worksInfos[0].productInfos[i].productPath+"'></li>"; 
				}
				$("#products").html(pHtml);
			}
			$("#name").html(data.worksInfos[0].workName);
			$("#workInfo").html(data.worksInfos[0].workComment);
			$("#time").html("<p>来自"+data.worksInfos[0].userName+"的作品</p><p>"+data.worksInfos[0].workUploadTime.substring(0,16)+"</p>");
		}
	});
};