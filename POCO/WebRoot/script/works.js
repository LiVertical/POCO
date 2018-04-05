$(function() {
	initUserData(0);
});

var initUserData = function(isFirst) {
	var recordSize = 6;
	var currentPage = 1;
	$.getJSON(getRootPath()+"/product-queryAllProducts.action?currentPage="+ currentPage + "&recordSize=" + recordSize,
					function(result) {
						$("#dataDisplay").empty();
						if (result.returnCode == "00") {
							var tbody = '';
							$("#dataDisplay div:gt(0)").remove();
							if (result.productInfos.length > 0) {
									var pageSize = (result.productInfos.length > recordSize ? recordSize: result.productInfos.length);
									 for (var i = 0; i < pageSize; i++) {		
										 var productType = "";
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
													+ "<td><img class='product' src='" + getRootPath() + "/" + result.productInfos[i].productPath + "'</td>"
													+ "<td>" + result.productInfos[i].productDesc + "</td>"
													+ "<td>" + result.productInfos[i].uploadTime.substring(0,16) + "</td>"
													+ "<td>" + productType+ "</td>" 
													+ "<td>" + result.productInfos[i].productUser + "</td>" 
													+ "<td><a class='delBtn' onclick='deleteProduct("+result.productInfos[i].productId+")'>删除</a></td></tr>";
										}
									}
									$("#dataDisplay").append(tbody);
									if (isFirst == 0) {
										$("#page").pagination(result.productInfos.length,{
													callback : function(index) {
													 if (isFirst == 1) {initUserData(index,1);}},
														prev_text : '上一页', //上一页按钮里text
														next_text : '下一页', //下一页按钮里text
														items_per_page : recordSize, //显示条数
														num_display_entries : 6, //连续分页主体部分分页条目数
														num_edge_entries : 2
													//两侧首尾分页条目数
													});
									}
									window.parent.window.iframeHeight();
								}
							});

			//翻页调用
			function Pagecallback(index, pg) {
				initUserData(index);
				return false;
			}
		};
		
		var deleteProduct = function(productId){
 		   $.post(getRootPath() + "/product-deleteProductInfo.action?productId=" + productId, function (data) {
        	if (data.returnCode == '00') {
           	alert("删除成功");
           		window.location.reload(); 
      	     }else{
           		alert("删除失败");
        	 }
   		 },'json');
		};