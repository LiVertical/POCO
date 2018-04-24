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
	
	

	$("#apply").click(function applyActivity(){
		var activityName = $("#activityName").val();
		var activityDesc = $("#activityDesc").val();
		var createTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var userId = $("body").data().user_id;
		if(activityName == ''){
			alert("请输入活动标题");
			return;
		}
		if(activityDesc == ''){
			alert("请输入活动介绍");
			return;
		}
		if(createTime == ''){
			alert("请输入活动开始时间");
			return;
		}
		if(endTime == ''){
			alert("请输入活动结束时间");
			return;
		}
		params = {
				activityName:activityName,
				activityDesc:activityDesc,
				createTime:createTime,
				endTime:endTime,
				userId:userId,
		};
		$.post(getRootPath()+"/user/applyActivity.action", params, function(data){
			if(data.returnCode == '00'){
				alert("申请成功");
			}else{
				alert("申请失败");
			}
		},'json');
	});
	
});
