$(function() {
	initUserData(1);
});

var initUserData = function(page) {
	var recordSize = 6;
	var currentPage = page;
	$.getJSON(getRootPath()+"/user/product-queryAllProducts.action?currentPage="+ currentPage + "&recordSize=" + recordSize,function(result) {
			$("#dataDisplay").empty();
			if (result.returnCode == "00") {
				var tbody = '';
				$("#dataDisplay").empty();
				if (result.productInfos.length > 0) {
					var pageSize = (result.productInfos.length > recordSize ? recordSize: result.productInfos.length);
					 for (var i = 0; i < pageSize; i++) {		
						var productType = "LOMO";
						switch(result.productInfos[i].productTypes){
							 case 1: productType = "花鸟";
							 break;
							 case 2: productType = "山水";
							 break;
							 case 3: productType = "人物";
							 break;
							 case 4: productType = "静物";
							 break;
							 case 5: productType = "运动";
							 break;
							case 6: productType = "城市";
							break;
						}
						 tbody += "<tr><td>"+ (i+1) + "</td>"
						              + "<td style='height:150px;width:150px;'><img class='product' src='" + getRootPath() + "/" + result.productInfos[i].productPath + "'></td>"
						              + "<td style='word-break'>" + result.productInfos[i].productName + "</td>"
						              + "<td style='word-break'>" + result.productInfos[i].productDesc + "</td>"
									  + "<td>" + result.productInfos[i].uploadTime.substring(0,16) + "</td>"
									  + "<td>" + productType+ "</td>" 
									  + "<td>" + result.productInfos[i].productUserName + "</td>" 
									  + "<td><img style='height:30px;width:44px' src='"+getRootPath()+"/img/icons/delete.jpg' class='delBtn' onclick='deleteProduct("+result.productInfos[i].productId+")'></td></tr>";
					 }
				}else{
					tbody = "<td>暂时还没有任何作品</td>";
				}
				$("#dataDisplay").html(tbody);
				$("#page").pagination(result.productsCount,{
						callback : function(index) {
										 initUserData(index+1);
									 },
						prev_text : '上一页', //上一页按钮里text
						next_text : '下一页', //下一页按钮里text
						items_per_page : pageSize, //显示条数
						current_page:currentPage-1,
						num_display_entries : 6, //连续分页主体部分分页条目数
						num_edge_entries : 2//两侧首尾分页条目数
				});
				window.parent.window.iframeHeight();
			}
   });
};

var deleteProduct = function(productId){
 	$.post(getRootPath() + "/user/product-deleteProductInfo.action?productId=" + productId, function (data) {
        	if (data.returnCode == '00') {
           	alert("删除成功");
           		window.location.reload(); 
      	     }else{
           		alert("删除失败");
        	 }
   		 },'json');
};