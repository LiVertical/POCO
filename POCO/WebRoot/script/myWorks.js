$(function(){
	var data=$('body').data();
	var userName = data.user_name;
	$("#userName").text(userName);
	var userId = data.user_id;
	$("#userId").text(userId);
	queryProducts(1,userName);
	
	$("input[name='products']").click(function (){
		if($(this).is(':checked')){  
		    $('input[name="product"]').each(function(){    
		        $(this).prop("checked",true);  //此处如果用attr，会出现第三次失效的情况
		    });  
		}else{  
		$('input[name="product"]').each(function(){  
		    $(this).removeAttr("checked",false);  
		});  
		} 
	});
	
});

function queryProducts(currentPage,userName) {
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
						 if(i%2 == 0){
							 tbody += "<tr class='tr_even'>";
						}else{
								tbody += "<tr class='tr_odd'>";
						}
						tbody += "<td><input class='hide' name='product' type='checkbox' value='"+result.productInfos[i].productId+"'>"+result.productInfos[i].productName+"</td> "
							  + "<td><img style='height:50px;width:50px' src='/POCO/" + result.productInfos[i].productPath+ "'></td>" 
							  +"<td>"+ result.productInfos[i].productDesc+ "</td>"
							  +"<td>"+ types(result.productInfos[i].productTypes)+ "</td>"
							  + "<td>"+ result.productInfos[i].uploadTime.substring(0,16) + "</td>"
							  +"<td><button class='button' onclick='delBatch()'>删除</button></td>";
					$("#dataDisplay").append(tbody);
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
//判断作品类型
function types(type){
	 switch(type){
	 case 1: return "山水";break;
	 case 2: return "花鸟";break;
	 case 3: return "人物";break;
	 case 4: return "建筑";break;
	 case 5: return "生态";break;
	 case 6: return "纪实";break;
	 case 7: return "LOMO";break;
	 case 8: return "风景";break;
 }
}








