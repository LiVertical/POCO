$(document).ready(function() {
	queryProducts(1);
});

//解析json   
function queryProducts(page) {
	var recordSize = 6;
	var currentPage = page;
	var proType = 100;
	$.getJSON(getRootPath()+ "/vistor/queryProductByCondition.action?proType="+ proType + "&currentPage=" + currentPage+ "&recordSize=" + recordSize,
		function(result) {
			console.log(result.productInfos);
			if (result.returnCode == "00") {
				$("#p_content").empty();
				var tbody = '';
				if (result.total > 0) {
					for (var i = 0; i < result.productInfos.length; i++) {
						tbody += "<li><div class='boxs_middle'> "
						  + "<img onclick='queryProductInfos(&quot;"+ result.productInfos[i].productId +"&quot;)' src='/POCO/"
						  + result.productInfos[i].productPath
						  + "'></div><div class='boxs_right'><p style='color:indianred'>***********************</p><p class='title'>标题 "
						  + result.productInfos[i].productName
			    		  + "</p><p>作品描述："
						  + result.productInfos[i].productDesc
						  + "</p><p>上传时间："
						  + result.productInfos[i].uploadTime.substring(0,16)
						  + "</p><p>&nbsp;</p><a href=" + getRootPath() + "/views/productDetails.jsp?productId=" + result.productInfos[i].productId+">点我去评论</a></br>" 
						  +"<button class='like' id='like"+result.productInfos[i].productId+"' onclick='like(&quot;"+result.productInfos[i].productId+"&quot;,&quot;"+result.productInfos[i].productName+"&quot;"+")'>为我点赞</button>" 
						  +"<button class='noLike' id='noLike"+result.productInfos[i].productId+"' style='display:none' onclick='nolike(&quot;"+result.productInfos[i].productId+"&quot;,&quot;"+result.productInfos[i].productName+"&quot;"+")'>取消点赞</button></br>"
						  +"<button id='collect"+result.productInfos[i].productId+"' class='collect' onclick='addCollect(&quot;"+result.productInfos[i].productId+"&quot;,&quot;"+result.productInfos[i].productName+"&quot;"+")'>收藏</button>" 
						  +"<button style='display:none' id='cancleColl"+result.productInfos[i].productId+"' class='collect' onclick='deleteCollect(&quot;"+result.productInfos[i].productId+"&quot;)'>取消收藏</button></div></li>";
						$("#p_content").html(tbody);
					}
				}
			}else{
				window.location.href= getRootPath() + "/views/userLogin.jsp";
			}
	});
};
//查看作品详情
var queryProductInfos = function(productId){
	window.location.href = getRootPath() + "/views/productDetails.jsp?productId=" + productId;
}; 

//顶
var like = function(productId,productName){
	var url = getRootPath() + "/user/getTags.action";
	var param = {
			productId:productId,
			productName:productName,
	};
	var like = "#like"+productId;
	var noLike = "#noLike"+productId;
	$.post(url, param, function(data){
		if(data.returnCode == '00'){
			alert("点赞成功");
			$(like).hide();
			$(noLike).show();
		}else{
			alert("点赞失败");
		}
	},'json');
};

//取消点赞
var nolike = function(productId){
	var url = getRootPath() + "/user/cancleLike.action";
	var param = {
			productId:productId,
	};
	var like = "#like"+productId;
	var noLike = "#noLike"+productId;
	$.post(url, param, function(data){
		if(data.returnCode == '00'){
			alert("取消成功");
			$(like).show();
			$(noLike).hide();
		}else{
			alert("点赞失败");
		}
	},'json');
};

//添加收藏
function addCollect(productId,productName){
	var coll = "#collect"+ productId;
	var canColl = "#cancleColl"+productId;
	$.post(getRootPath() + "/user/addToCollect.action?productId="+productId+"&productName="+productName, function(data){
		if(data.returnCode == '00'){
			alert("收藏成功！");
			$(coll).attr("disabled", true);
			$(coll).hide();
			$(canColl).show();
		}else{
			alert("收藏失败");
		}
	},'json');
};

//取消收藏
function deleteCollect(productId){
	var coll = "#collect"+ productId;
	var canColl = "#cancleColl"+productId;
	$.post(getRootPath() + "/user/deleteCollect.action?productId="+productId, function(data){
		if(data.returnCode == '00'){
			alert("取消收藏成功！");
			$(canColl).attr("disabled", true);
			$(coll).show();
			$(canColl).hide();
		}else{
			alert("取消收藏失败");
		}
	},'json');
};

//分类查看作品
function queryProductsByType(type){
	window.location.href = getRootPath() + "/views/products.jsp?productType="+type;
}