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
	$.post(getRootPath()+"/user/queryWorksByType.action?workType="+type, function(data){
		if(data.returnCode == '00'){
			var html = "";
			for(var i = 0;i < data.works.length;i++){
			 html = "<li><p>"+data.works[i].workName+"</p></li>";
			}
			$("#p_content").html(html);
		}
	});
}