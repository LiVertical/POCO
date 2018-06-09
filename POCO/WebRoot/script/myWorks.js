$(function(){
	var userId = $("body").data().user_id;
	$("#userId").text(userId);
	
	queryProducts(1);
	
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

function queryProducts(page) {
	var recordSize = 3;
	var currentPage = page;
	$.getJSON(getRootPath() + "/user/product-queryProductByUser.action?currentPage="+currentPage+"&recordSize="+recordSize,function(data) {
		$("#p_content").empty();
		$("#page").empty();
		console.log("productInfos:"+data.worksInfo);
		if (data.returnCode == "00") {
			var tbody = '';
			var pageSize = data.worksInfo.length > recordSize ? recordSize: data.worksInfo.length;
			if (data.totals > 0) {
				for(var i = 0; i < pageSize; i++){
					if(i%2 == 0){
						tbody += "<tr class='tr_even'>";
					}else{
							tbody += "<tr class='tr_odd'>";
					}
					var products = ""; 
					for(var j = 0; j < data.worksInfo[i].productInfos.length; j++){
						products +="<li class='img'><img src='/" + data.worksInfo[i].productInfos[j].productPath+"'></li>"; 
					}
					 tbody += "<td><input class='hide' name='product' type='checkbox' value='"+data.worksInfo[i].workId+"'></td>"
		              + "<td style='height:150px;width:150px;'>"+ data.worksInfo[i].workName +"</td>"
		              + "<td><ul>" + products + "</ul></td>"
		              + "<td style='word-break'>" + data.worksInfo[i].workComment + "</td>"
		              + "<td style='word-break'>" + types(data.worksInfo[i].workType) + "</td>"
					  
					  + "<td style='word-break'>" + data.worksInfo[i].workUploadTime.substring(0,16) + "</td>"
					  + "<td><img style='height:30px;width:44px' src='"+getRootPath()+"/img/icons/delete.jpg' class='delBtn' onclick='deleteWork(&quot;"+data.worksInfo[i].workId+"&quot;)'></td></tr>";
				}
				 }else{
						tbody="<tr class='tr_even'  style='color:969696'><td colspan='"+($(".tr_head").children().length)+"'>暂无作品</td></tr>";
				}
				$("#dataDisplay").html(tbody);
				if ($("#page").html() == '') {
	                  $("#page").pagination(data.totals, {
	                        callback: function (index) {
		                        queryProducts(index+1);
	                        },
	                        prev_text: '上一页',       //上一页按钮里text
	                        next_text: '下一页',       //下一页按钮里text
	                        items_per_page: pageSize, 
	                        current_page:currentPage-1,
	                        num_display_entries: 3,    //连续分页主体部分分页条目数
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
			workIds:idLists,
	};
	$.post(getRootPath() + '/user/delBatch.action', params, function(msg){
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

function deleteWork(id){
	$.post(getRootPath()+"/user/deleteWorkByWorkId.action?workId="+id, function(data){
		if(data.returnCode == '00'){
			alert("删除成功！");
			queryProducts(1);
		}else{
			alert("删除失败！");
		}
	});
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
	 case 0:return "人像摄影";break;
	 case 1:return "生态摄影";break;
	 case 2:return "运动摄影";break;
	 case 3:return "生活摄影";break;
	 case 4:return "夜景摄影";break;
	 case 5:return "风景摄影";break;
	 case 6:return "纪实摄影";break;
	 case 7:return "人体摄影";break;
	 case 8:return "其他摄影";break;
	 case 9:return "自拍摄影";break;
	 case 10:return "商业摄影";break;
	 case 11:return "LOMO";break;
//	 case 1: return "山水";break;
//	 case 2: return "花鸟";break;
//	 case 3: return "人物";break;
//	 case 4: return "建筑";break;
//	 case 5: return "生态";break;
//	 case 6: return "纪实";break;
//	 case 7: return "LOMO";break;
//	 case 8: return "风景";break;
	 
 }
}








