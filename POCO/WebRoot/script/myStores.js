$(function(){
	queryProductsMyCollects();
	
});

function queryProductsMyCollects() {
	$.getJSON(getRootPath() + "/user/queryCollectByUserId.action", function(result) {
		$("#p_content").empty();
			console.log("collectInfos:"+result.collectInfos);
			if (result.returnCode == '00') {
				var tbody = '';
				 if (result.collectInfos.length > 0) {
					 for (var i = 0; i < result.collectInfos.length; i++) {
						tbody += "<li><a target='_blank' href='"+getRootPath() +"/views/productDetails.jsp?productId="+result.collectInfos[i].productId+"'>"+result.collectInfos[i].productName.substring(0,16)+"</a>"
								+"<p>"+result.collectInfos[i].createTime.substring(0,16)+"</p>"
								+"<img onclick='delCollectByCollectId("+result.collectInfos[i].productId+")' style='height:30px;width:34px' src='"+getRootPath() +"/img/icons/delete.png'>"
							  	+"</li>";
					 }
					 $("#p_content").append(tbody);
				 }
			}
	},'json');
}

//删除收藏
function delCollectByCollectId(productId){
	$.post(getRootPath()+"/user/deleteCollect.action?productId="+productId, function(result){
		if(result.returnCode == '00'){
			alert("删除成功");
			queryProductsMyCollects();
		}else{
			alert("删除失败");
		}
	});
}


