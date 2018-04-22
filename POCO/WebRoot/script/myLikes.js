$(function(){
	queryProductsMyLike();
});

function queryProductsMyLike() {
	$.getJSON(getRootPath() + "/user/getProductByUserId.action", function(result) {
		$("#p_content").empty();
			console.log("我的点赞：productInfos:"+result.products);
			if (result.returnCode == '00') {
				var tbody = '';
				 if (result.products.length > 0) {
					 for (var i = 0; i < result.products.length; i++) {
						tbody += "<li class='box'><a href='"+getRootPath() +"/views/productDetails.jsp?productId="+result.products[i].id+"'>"+result.products[i].productName+"</a>"
								+"<p>"+result.products[i].createTime.substring(0,16)+"</p>"
								+"<img onclick='cancleLike("+result.products[i].productId+")' style='height:30px;width:34px' src='"+getRootPath() +"/img/icons/delete.png'>"
							  	+"</li>";
					} 
				}else{
					tbody="暂时还未对任何作品点赞";
				}
			 $("#p_content").append(tbody);
				
				 
			}
	},'json');
}
var gotoMyWorks = function(userName){
	window.location = getPath() + "/POCO/views/myWorks.jsp";
}; 

