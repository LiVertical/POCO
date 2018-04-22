$(function(){
	var data=$('body').data();
	var userName = data.user_name;
	$("#userName").text(userName);
	var userId = data.user_id;
	$("#userId").text(userId);
	console.log(userName+userImg);
	queryProducts(1);
});

function queryProducts(currentPage) {
	var recordSize = 6;
	var pageSize = 6;
	var userName=$("#userName").text();
	var userId= 11;
	$.getJSON(getRootPath() + "/user/product-queryProductByUser.action?userName="+userName+"&currentPage="+currentPage+"&recordSize="+recordSize,
		function(result) {
		$("#p_content").empty();
		 $("#page").empty();
			console.log("productInfos:"+result.productInfos);
			if (result.returnCode == "00") {
				var tbody = '';
				 if (result.totals > 0) {
					 pageSize = (result.totals > recordSize ? recordSize: result.totals);
					 for (var i = 0; i < pageSize; i++) {
						tbody += "<li><div class='boxs_left'><input name='product' type='checkbox' value='"+result.productInfos[i].productId+"'></div><div class='boxs_middle'> "
							  + "<img src='/POCO/"
							  + result.productInfos[i].productPath
							  + "'></div><div class='boxs_right'><p class='title'>标题 "
							  + result.productInfos[i].productName
				    		  + "</p><br/><p>作品描述："
							  + result.productInfos[i].productDesc
							  + "</p>"
							  + "<p>上传时间："
							  + result.productInfos[i].uploadTime.substring(0,16)
							  + "</p><a href=" + getRootPath() + "/user/product-queryProductByUser.action?>评论</a></br>" 
							  +"<a href=" + getRootPath() + "/user/product-queryProductByUser.action?>点赞</a></br>"
							  +"<button onclick='addCollect("+userId+","+result.productInfos[i].productId+")'>收藏</button>" 
							  +"<button onclick='deleteCollect("+result.productInfos[i].productId+")'>取消收藏</button></div></li>";
					$("#p_content").append(tbody);
					 }
				 }
				if ($("#page").html() == '') {
	                  $("#page").pagination(result.totals, {
	                        callback: function (index) {
		                        queryProducts(index+1);
	                        },
	                        prev_text: '上一页',       //上一页按钮里text
	                        next_text: '下一页',       //下一页按钮里text
	                        items_per_page: pageSize, 
	                        current_page:currentPage-1,
	                        num_display_entries: 6,    //连续分页主体部分分页条目数
	                        num_edge_entries: 2        //两侧首尾分页条目数
	                    });
				}
			}
	},'json');
}
var gotoMyWorks = function(userName){
	window.location = getPath() + "/POCO/views/myWorks.jsp";
}; 

//全选
var selectAll = function(){
	$("[type='checkbox']").prop("checked","checked");
};
//反选
var inverse = function(){
	$("[type='checkbox']").prop("checked",false);
};
//删除
function delBatch(){
	var checked_num = $("input[name='product']:checked").length;
	if(checked_num == 0){
		alert("至少选择一项");
		return;
	}
	//多项选择操作
	var idLists = "";	
	if(confirm("确定要删除这些选项么？")){
		var check_obj = $("input[name='product']");
		for(var i=0;i<check_obj.length;i++){
			if(check_obj.get(i).checked == true){
				idLists += "," + check_obj.get(i).value;
		    }
		}
		idLists = idLists.substring(1,idLists.length);
	params = {
			productIds:idLists,
	};
	$.post(getRootPath() + '/user/product-delBatch.action', params, function(msg){
			console.log(msg);
			if(msg.returnCode=='00'){
				alert("删除成功!");
				//刷新页面
				location.reload();
			}else{
				alert("删除失败！");
			}
		},'json');
	}
} 
 
var gotoCompleteUserInfo = function(){
		window.location = getRootPath() + "/pages/userInfoEdit.jsp";
};

//收藏
var addCollect = function(userId, productId){
	params = {
			productId:productId,
			userId:userId
	};
	$.post(getRootPath() + '/user/addToCollect.action', params, function(msg){
			if(msg.returnCode=='00'){
				alert("收藏成功!");
			}else{
				alert("收藏失败！");
			}
		},'json');
};
//取消收藏
var deleteCollect = function(productId){
	params = {
			productId:productId,
	};
	$.post(getRootPath() + '/user/deleteCollect.action', params, function(msg){
			if(msg.returnCode=='00'){
				alert("已取消收藏!");
			}else{
				alert("取消收藏失败！");
			}
		},'json');
};

function gotoUpload(){
	var uuid = guid();
	window.location = getRootPath() + "/views/productUpload.jsp?productGroupId="+uuid;
}







