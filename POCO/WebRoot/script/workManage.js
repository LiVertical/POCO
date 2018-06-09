$(function(){
	getAllWorksInfos(1);
	
	$('#endDate').datepicker({
   	 	numberOfMonths:1,//显示几个月  
        showButtonPanel:true,//是否显示按钮面板  
        dateFormat: 'yy-mm-dd',//日期格式  
        closeText:"关闭",//关闭选择框的按钮名称  
        yearSuffix: '年', //年的后缀  
        maxDate:new Date,
        showMonthAfterYear:true,//是否把月放在年的后面  
        showButtonPanel:false,
        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
        dayNamesMin: ['周日','周一','周二','周三','周四','周五','周六']
   });
	$('#biginDate').datepicker({
   	 	numberOfMonths:1,//显示几个月  
        showButtonPanel:true,//是否显示按钮面板  
        dateFormat: 'yy-mm-dd',//日期格式  
        closeText:"关闭",//关闭选择框的按钮名称  
        yearSuffix: '年', //年的后缀  
        maxDate:new Date,
        showMonthAfterYear:true,//是否把月放在年的后面  
        showButtonPanel:false,
        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
        dayNamesMin: ['周日','周一','周二','周三','周四','周五','周六']
   });
	
	Date.prototype.Format = function (fmt) {    
	    var o = {    
	        "M+": this.getMonth() + 1, //月份     
	        "d+": this.getDate(), //日     
	        "H+": this.getHours(), //小时     
	        "m+": this.getMinutes(), //分     
	        "s+": this.getSeconds(), //秒     
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度     
	        "S": this.getMilliseconds() //毫秒     
	    };    
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));    
	    for (var k in o)    
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));    
	    return fmt;    
	};  
	
	$("#biginDate").val(new Date().Format("yyyy-MM-dd"));
	$("#endDate").val(new Date().Format("yyyy-MM-dd"));
	
});

function getAllWorksInfos(page){
	var currentPage = page;
	var recordSize = 8;
	var workName = $("#workName").val();
	var userName = $("#userName").val();
	var biginDate = $("#biginDate").val();
	var endDate = $("#endDate").val();
	var workType = $("#workType").val();
	if(biginDate > endDate){
		alert("请重新选择检索时间段");
		return false;
	}
	var params = {
			currentPage : currentPage,
			recordSize : recordSize,
			workName:workName,
			userName:userName,
			biginDate:biginDate,
			endDate:endDate,
			workType:workType
	};
	$.post(getRootPath() + "/admin/queryAllWorks.action", params, function(data){
		if(data.returnCode == '00'){
			$("#dataDisplay").empty();
			var pageSize = data.worksInfo.length > recordSize ? recordSize: data.worksInfo.length;
			var tbody = "";
			for(var i = 0; i < pageSize; i++){
				var products = ""; 
				for(var j = 0; j < data.worksInfo[i].productInfos.length; j++){
					products +="<li class='img'><img src='/" + data.worksInfo[i].productInfos[j].productPath+"'></li>"; 
				}
				 tbody += "<tr><td>"+ (i+1) + "</td>"
	              + "<td style='height:150px;width:150px;'>"+ data.worksInfo[i].workName +"</td>"
	              + "<td style='word-break'>" + data.worksInfo[i].workComment + "</td>"
	              + "<td style='word-break'>" + data.worksInfo[i].workUploadTime.substring(0,16) + "</td>"
				  + "<td><ul>" + products + "</ul></td>"
				  + "<td>" + data.worksInfo[i].userName + "</td>" 
				  + "<td><img style='height:30px;width:44px' src='"+getRootPath()+"/img/icons/delete.jpg' class='delBtn' onclick='deleteProduct(&quot;"+data.worksInfo[i].workId+"&quot;)'></td></tr>";
			}
			$("#dataDisplay").html(tbody);
			$("#page").pagination(data.worksCount,{
					callback : function(index) {
						getAllWorksInfos(index+1);
								 },
								 prev_text: '上一页',       //上一页按钮里text
					             next_text: '下一页',       //下一页按钮里text
					             items_per_page: recordSize, 
					             current_page:currentPage-1,
					             num_display_entries: 6,    //连续分页主体部分分页条目数
					             num_edge_entries: 2        //两侧首尾分页条目数
			});
			window.parent.window.iframeHeight();
		}
	});
}

function deleteProduct(id){
	$.post(getRootPath()+"/admin/deleteWorkByWorkId.action?workId="+id, function(data){
		if(data.returnCode == '00'){
			alert("删除成功");
			getAllWorksInfos(1);
		}else{
			alert("删除失败");
		}
	});
}