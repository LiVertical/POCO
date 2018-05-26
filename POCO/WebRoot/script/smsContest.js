$(function(){
	$('#endTime').datepicker({
   	 	numberOfMonths:1,//显示几个月  
        showButtonPanel:true,//是否显示按钮面板  
        dateFormat: 'yy-mm-dd',//日期格式  
        closeText:"关闭",//关闭选择框的按钮名称  
        yearSuffix: '年', //年的后缀  
        minDate:new Date,
        showMonthAfterYear:true,//是否把月放在年的后面  
        showButtonPanel:false,
        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
        dayNamesMin: ['周日','周一','周二','周三','周四','周五','周六']
   });
	$('#startTime').datepicker({
   	 	numberOfMonths:1,//显示几个月  
        showButtonPanel:true,//是否显示按钮面板  
        dateFormat: 'yy-mm-dd',//日期格式  
        closeText:"关闭",//关闭选择框的按钮名称  
        yearSuffix: '年', //年的后缀  
        minDate:new Date,
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
	
	$("#startTime").val(new Date().Format("yyyy-MM-dd"));
	$("#endTime").val(new Date().Format("yyyy-MM-dd"));
	
	$("#post").click(function applyActivity(){
		var contestName = $("#contestName").val();
		var contestDesc = $("#contestDesc").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(contestName == ''){
			alert("请输入大赛标题");
			return;
		}
		if(contestDesc == ''){
			alert("请输入大赛概述");
			return;
		}
		if(startTime == ''){
			alert("请输入大赛开始时间");
			return;
		}
		if(endTime == ''){
			alert("请输入大赛结束时间");
			return;
		}
		params = {
				contestName:contestName,
				contestDesc:contestDesc,
				startTime:startTime,
				endTime:endTime,
		};
		$.post(getRootPath()+"/admin/postContest.action", params, function(data){
			if(data.returnCode == '00'){
				alert("发布成功！");
				window.location.reload();       //刷新当前页面
			}else{
				alert("发布失败！");
			}
		},'json');
	});
	
});
