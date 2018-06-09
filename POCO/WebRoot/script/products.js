$(document).ready(function(){  
        var range = 50;             //距下边界长度/单位px  
        var elemt = 500;           //插入元素高度/单位px  
        var maxnum = 20;            //设置加载最多次数  
        var num = 1;  
        var totalheight = 0;   
        var main = $("#content");                     //主体元素  
        $(window).scroll(function(){  
            var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)  
            console.log("滚动条到顶部的垂直高度: "+$(document).scrollTop()+";页面的文档高度 ："+$(document).height()+";浏览器的高度:"+$(window).height());  
            totalheight = parseFloat($(window).height()) + parseFloat(srollPos);  
            if(($(document).height()-range) <= totalheight  && num != maxnum) {  
                main.append("<div style='border:1px solid tomato;margin-top:20px;color:#ac"+(num%20)+(num%20)+";height:"+elemt+"' >hello world"+srollPos+"---"+num+"</div>");  
                num++;  
            }  
        });  
        
        queryProductsByType();
});

function queryProductsByType(){
	var type = GetRequest();
	console.log("当前加载作品类型:"+type);
	$.post(getRootPath()+"/vistor/queryProductByType.action?proType="+type, function(data){
		if(data.returnCode == '00'){	
			var html = "";
			for(var i = 0;i < data.products.length;i++){
				html += "<li><div class='boxImg'><img src='/"+data.products[i].productPath+"'></div>" 
						 +"<div class='contentBox'><ul>"
						 +"<li><p>图片名称："+data.products[i].productName+"</p></li>"
						 +"<li><p>图片描述："+data.products[i].productDesc+"</p></li>"
						 +"<li><p>上传时间："+data.products[i].uploadTime.substring(0,16)+"</p></li>"
						 +"</ul></li>";
			}
			$("#p_content").html(html);
		}
	});
}
	
	function queryProductsByProductType(productType){
		$.post(getRootPath()+"/vistor/queryProductByType.action?proType="+productType, function(data){
			if(data.returnCode == '00'){
				$("#p_content").empty();
				var html = "";
				if(data.products.length>0){
					for(var i = 0;i < data.products.length;i++){
						html += "<li><div class='boxImg'><img src='/"+data.products[i].productPath+"'></div>" 
								 +"<div class='contentBox'><ul>"
								 +"<li><p>图片名称："+data.products[i].productName+"</p></li>"
								 +"<li><p>图片描述："+data.products[i].productDesc+"</p></li>"
								 +"<li><p>上传时间："+data.products[i].uploadTime.substring(0,16)+"</p></li>"
								 +"</ul></li>";
					}
				}
				}else{
					html="<p style='text-align:center'>暂时还没有该类型的作品</p>";
				}
				$("#p_content").html(html);
		});
	};
