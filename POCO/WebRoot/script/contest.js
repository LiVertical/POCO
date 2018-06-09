var startTime;
var endTime;
$(document).ready(function(){
	getWorkInfosByContestId();
	queryContestInfo();
	
	$("#joinBtn").click(function(){
		var contestId =GetRequest();
		window.location.href = getRootPath() + "/views/productUpload.jsp?contestId="+contestId;
	});
	
	
});

function queryContestInfo(){
	var contestId =GetRequest();
	$.post(getRootPath()+"/user/queryContestInfoByContestId.action?contestId="+contestId, function(data){
		if(data.returnCode == '00'){
			startTime = data.contestInfo[0].startTime;
			endTime = data.contestInfo[0].endTime;
			var htm = "<h1>大赛概述：</h1><p>&nbsp;</p><h2>"+data.contestInfo[0].contestName+"</h2>"
						   + "<p>&nbsp;</p><p>"+data.contestInfo[0].contestDesc+"</p>";
			$("#contest").html(htm);
		}
	});
}

function getWorkInfosByContestId(){
	var contestId =GetRequest();
	$.post(getRootPath()+"/user/queryAllWorksByContestId.action?contestId="+contestId, function(data){
	if(data.returnCode == '00'){
		console.log(data.contestsWorkInfo);
		var html = "";
		for(var i=0; i < data.contestsWorkInfo.length; i++){
			var vote = "vote"+data.contestsWorkInfo[i].workId;
			console.log(vote);
			var htm1 = "";
			for(var j = 0; j < data.contestsWorkInfo[i].productInfos.length;j++){
				htm1 += "<li><img  class='img' src='/"+data.contestsWorkInfo[i].productInfos[j].productPath+"'></li>";
			}
			html += "<li class='work'><div class='desc'><h5 style='text-align:center;padding:10px'>"+data.contestsWorkInfo[i].workName+"</h5>"
			+data.contestsWorkInfo[i].workComment+"</div>"
			+"<div class='right'><ul>"+htm1+"</ul></div>"
			+"<div class='right3'>当前获赞数目：<span id='voteNum'>"+data.contestsWorkInfo[i].voteNum+"</span>"
			+"<p>&nbsp;</p><button class='vote'  onclick='vote(&quot;"+data.contestsWorkInfo[i].workId+"&quot;)'>点击投票</button></div></li>";
		}
		$("#p_content").append(html);
		if(isDelay(startTime, endTime)){
			$("#joinBtn").hide();
		}
	  }
	});
}

function vote(id){
	if(isDelay(startTime, endTime)){
		alert("大赛已过期");
		return false;
	}
	var params = {
			workId : id,
	};
	$.post(getRootPath() + "/user/vote.action", params, function(data){
		if(data.returnCode == '00'){
			alert("投票成功");
			window.location.reload();
		}else if(data.returnCode == '01'){
			alert("您已经为该作品投过票了");
		}else{
			alert("投票失败");
		}
	});
}

function getVoteNum(){
	$.post(getRootPath() + "/user/getVoteNum.action");
}

function isDelay(sTime, eTime){
	var date = new Date();
	now = date.valueOf();
	if((now < Date.parse(sTime)) || (now > Date.parse(eTime))){
		return true;
	};
};

