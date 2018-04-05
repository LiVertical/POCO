$(function(){
	queryProductsMyLike();
});

function queryProductsMyLike() {
	$.getJSON(getRootPath() + "/user/getProductByUserId.action", function(result) {
		$("#p_content").empty();
			console.log("productInfos:"+result.products);
			if (result.returnCode == '00') {
				var tbody = '';
				 if (result.products.length > 0) {
					 for (var i = 0; i < result.products.length; i++) {
						tbody += "<li><div class='boxs_left'>"+result.products[i].productName+"</div>"
								+"<div class='boxs_left'>"+result.products[i].createTime+"</div>"
								+"<div class='boxs_left'>"+result.products[i].id+"</div>"
							  	+"</li>";
					$("#p_content").append(tbody);
					 }
				 }
			}
	},'json');
}
var gotoMyWorks = function(userName){
	window.location = getPath() + "/POCO/views/myWorks.jsp";
}; 

