function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
function getPath(){
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj

    return(localhostPaht);
}

function lazyLoadImageWithContainer(img,$container,threshold){
    $(img).lazyload({
        effect: 'fadeIn',
        threshold: threshold,
        container : $container,
        placeholder : getPath() + '/microweb/image/load-failed.png',
        load: function () {
            dealImage2Center(this);
        }
    });
}

function lazyLoadImage(img,threshold) {
    $(img).lazyload({
        effect: 'fadeIn',
        placeholder : getPath() + '/microweb/image/load-failed.png',
        threshold: threshold,
        load: function () {
            dealImage2Center(this);
        }
    });
}


/**
 * 配合lazyload 使用
 * 图片居中显示
 * @param img
 * @private
 */
function dealImage2Center(img) {
    var self = img;
    var $this = $(img);
    var objHeight = self.naturalHeight;//图片高度
    var objWidth = self.naturalWidth;//图片宽度
    var _setImageStyle = function (width, height) {
        var parentHeight = $this.parent().height();//图片父容器高度
        var parentWidth = $this.parent().width();//图片父容器宽度
        /*console.log();*/
        var ratio = width / height;

        var tempHeight = parentWidth / ratio;
        var tempWidth = parentHeight * ratio;

        if (tempHeight >= parentHeight) {
            $this.width(parentWidth);
            $this.height(tempHeight);
            $this.css("top", (parentHeight - tempHeight) / 2 + "px")
        } else {
            $this.height(parentHeight);
            $this.width(tempWidth);
            $this.css("left", (parentWidth - tempWidth) / 2 + "px");
        }

        $this.data('loaded', 1);
        $this.css('visibility', 'visible');
    };

    !function () {
        if ($this.hasClass('scrollLoaded')) {//已处理过则跳过
            return;
        }

        if (objHeight > 0 && objWidth > 0) {
            _setImageStyle(objWidth, objHeight);
        }
        if (!objHeight || objHeight <= 0 || !objWidth || objWidth <= 0) {
            var timer = setInterval(function () {
                if (self.complete) {
                    _setImageStyle(self.naturalWidth, self.naturalHeight);
                    clearInterval(timer);
                }
            }, 300);
        }
    }();
}
/**
 * 图片处理为居中显示的jQuery插件
 */
(function ($) {
    $.fn.dealImage2Center = function () {

        return this.each(function () {
            var $this = $(this);
            var objHeight = $this[0].clientHeight;//图片高度
            var objWidth = $this[0].clientWidth;//图片宽度
            var _setImageStyle = function (width, height) {
                var parentHeight = $this.parent().height();//图片父容器高度
                var parentWidth = $this.parent().width();//图片父容器宽度
                /*console.log();*/
                var ratio = width / height;

                var tempHeight = parentWidth / ratio;
                var tempWidth = parentHeight * ratio;

                if (tempHeight >= parentHeight) {
                    $this.width(parentWidth);
                    $this.height(tempHeight);
                    $this.css("top", (parentHeight - tempHeight) / 2 + "px")
                } else {
                    $this.height(parentHeight);
                    $this.width(tempWidth);
                    $this.css("left", (parentWidth - tempWidth) / 2 + "px");
                }

                $this.data('loaded', 1);
                $this.css('visibility', 'visible');
            };

            !function () {
                if ($this.hasClass('scrollLoaded')) {//已处理过则跳过
                    return;
                }

                if (objHeight > 0 && objWidth > 0) {
                    _setImageStyle(objWidth, objHeight);
                }
                if (!objHeight || objHeight <= 0 || !objWidth || objWidth <= 0) {
                    var timer = setInterval(function () {
                        /*$this.error(function () {
                            $this[0].attr('src', getPath() + "/resource/image/defaultPic/defaultEntPic.s200.png");
                            clearInterval(timer);
                        });*/
                        if ($this[0].complete) {
                            _setImageStyle($this[0].naturalWidth, $this[0].naturalHeight);
                            clearInterval(timer);
                        }
                    }, 300);
                }
            }();
        });
    }
})($);


/**
 * 判断是否是手机号
 * @param phoneNumber
 * @returns {Boolean}
 */
function isPhoneNumber(phoneNumber){
	var isPhone = true;
	if(phoneNumber == null || phoneNumber == ''){
		isPhone =  false;
	}
	if(phoneNumber.length != 11){
		isPhone = false;
	}
	var str = "^[1][0-9]{10}$";
	if(!phoneNumber.match(str)){
		isPhone = false;
	}
	return isPhone;
}
function createCookie(name,value,days) {
    expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    document.cookie = name+"="+value+expires+";path=/";
}
function createCookieMinute(name,value,minutes) {
    expires = "";
    if (minutes) {
        var date = new Date();
        date.setTime(date.getTime()+(minutes*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    document.cookie = name+"="+value+expires+";path=/";
}
function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' '){
            c = c.substring(1,c.length);
        }
        if (c.indexOf(nameEQ) == 0) {
            return c.substring(nameEQ.length,c.length);
        }
    }
    return null;
}

/**
 * 保存至本地存储
 * @param key
 * @param value
 */
function saveJson2LocalStorage(key, value){
    if(localStorage){
        localStorage.setItem(key, JSON.stringify(value));
    }
}

function getFromLocalStorage(key){
    if(localStorage){
       var value =  localStorage.getItem(key);
        if(!!value && $.trim(value) != '' && value!= 'undefined'){
            return JSON.parse(value);
        }
        return null;
    }
    return null;
}

function removeFromLocalStorage(key){
    localStorage.removeItem(key);
}


//判断是否是微信内置浏览器
window.isInWeixinApp = function() {
    return /MicroMessenger/.test(navigator.userAgent);
};
//判断是否是糯米浏览器
window.isInNuoMiApp = function() {
    return /BDNuomiAppAndroid/.test(navigator.userAgent)||/BDNuomiAppIOS/.test(navigator.userAgent);
};
//判断是否是支付宝app
window.isInAliApp = function() {
    return /AlipayClient/.test(navigator.userAgent);
};
function loginbyPhone(phoneNumber,domainHacks){
    var code="-1";
    var channel = $('body').data().channel;
    var url = getPath()+"/"+domainHacks+"/loginbyphone?phoneNumber="+phoneNumber;
    var openId = $('body').data().open_id;
    if(channel == 10){
    	url =  getPath()+"/loginbyphone.action?&channel=10&openId="+openId+"&phoneNumber="+phoneNumber;
    }
    $.ajax({
        url :url,
        type : "post",
        async : false,
        dataType : "json",
        error : function() {
            /*alert("网络异常");*/
        },
        success : function(json) {
            code=json.returnCode;
        }
    });
    return code;
}
//返回上一页
function returnIndex(){
    window.history.go(-1);
}
function inApp(){
	return window.isInWeixinApp()||window.isInNuoMiApp()||window.isInAliApp();
}
if(window.isInWeixinApp()||window.isInNuoMiApp()||window.isInAliApp()){
    $(".backHome").css("display","block");
}else{
    $(".backHome").css("display","none");
}
if(window.isInWeixinApp()||window.isInNuoMiApp()||window.isInAliApp()){
    $("#head").hide();
    $("#submitContainer .header").hide();
    $("#choseCouponContainer .header").hide();
    $("#payFail .header").hide();
    $("#paySuccess1 .header").hide();
    $("#paySuccess2 .header").hide();


}else{
    $("#head").show();
    $("#submitContainer .header").show();
    $("#choseCouponContainer .header").show();
    $("#payFail .header").show();
    $("#paySuccess1 .header").show();
    $("#paySuccess2 .header").show();
}
if(window.isInWeixinApp()||window.isInNuoMiApp()||window.isInAliApp()){
    $(".nowx-header").hide();
}else{
    $(".nowx-header").show();
}
function returnBack(){
    console.log($('body').data().domain_hacks);
    var url = encodeURI(getPath()+"/"+$('body').data().domain_hacks);
    window.location.href = url;
    console.log(getPath()+"/"+$('body').data().domain_hacks+"/index.action");
}

function getAddList() {
    var domainHack = $('body').data('domain_hacks');
    var addDishList = getFromLocalStorage(domainHack+'_addDishList');
    if (!addDishList) {
        addDishList = [];
    }
    return addDishList;
}

function removeAddDishList() {
    var domainHack = $('body').data('domain_hacks');
    removeFromLocalStorage(domainHack+'_addDishList');
}

function saveChosenDish2Local(addDishList) {
    var domainHack = $('body').data('domain_hacks');
    saveJson2LocalStorage(domainHack + '_addDishList', addDishList);
}

var pageControl = {
    historylog: [],
    prehistory: [],
    isback: false,
    configs: [],
    _goto: function(name) {
        var config = this._find(name);
        if (config == null) {
            return;
        }
        config._show();
        var hashName = config.hash;
        location.hash = hashName;
        this.isback = false;
        this.historylog.push(hashName);
    },
    _find: function(key) {
        for (var ele in this.configs) {
            if (this.configs[ele].hash == key) {
                return this.configs[ele];
            }
        }
        return null;
    },
    _getPre: function() {
        var length = this.prehistory.length;
        if (length > 0) {
            return this.prehistory[length - 1];
        }
        return null;
    },
    _addPage: function(config) {
        this.configs.push(config);
    },
    _init: function() {
        window.onhashchange = function() {

            if (pageControl.isback) { //返回

                var hash = pageControl.historylog.pop();

                var config = pageControl._find(hash);
                config._back();
                pageControl.prehistory.push(hash);
            }
            pageControl.isback = true;
        }
    }
};

function nextPage(url){
    for(var n=0;n<pageControl.historylog.length;n++){
        history.back();
    }
    setTimeout(function(){window.location.href=url},3);

}


function initPageHash(){
    pageControl._addPage(allPage.login);
    pageControl._init();
}

var loginHash = "#login";

var allPage = {
	    'login' :  {
	        sel: "#login",
	        hash: "#login",
	        _back: function() {
	            $('.dish-verify-code').addClass('hidden').siblings('.container').removeClass('hidden');
	            removeLoginPage();
	        },
	        _show: function() {
	        }
	    },
	};

function showTip(msg) {
	var topTip = $("<div class='tip'><div class='fadeIn sel-none'>"+msg+"</div></div>");
	$("body").append(topTip);
	setTimeout(function() {topTip.remove();},4000);
}

/*提示工具类*/
var toast ={	
	load: '',
	showTip:function(msg){
		var topTip = $("<div class='tip'><div class='fadeIn sel-none'>"+msg+"</div></div>");
		$("body").append(topTip);
		setTimeout(function() {topTip.remove();},2000);
		event.preventDefault();
	},
	loadingStart:function(){		
		this.load = $("<div id='loadingToast' style='display:none;'>	<div class='weui-mask_transparent'></div>	<div class='weui-toast'>	<i class='weui-loading weui-icon_toast'></i>	<p class='weui-toast__content'>数据加载中</p>	</div>	</div>");
		$("body").append(this.load);
 		this.load.fadeIn(100);
	},
	loadingEnd:function(){
		this.load.fadeOut(300);
		var that=this;
 		setTimeout(function() {
 			that.load.remove();
 		}, 300);
	},	
	pageLoadEnd:function(){
		$(".mw-load").fadeOut();
	}
	
};

function showBlank(){
	
	$(".blank").show();
	$(".blank").on("touchmove",function(){
		event.preventDefault();
	});
}

/*
 * 有弹窗时禁止底部页面滑动方法
 */
function banTouchMove(){
	$('body').bind('touchmove', function(e){ 
		e.stopPropagation();
		e.preventDefault();
	});
}

/*
 * 关闭弹窗时底部页面恢复滑动
 */
function unBindTouchMove(){
	$('body').unbind('touchmove');
}

/**
 * 滚动效果
 */
; (function ($, window, document, undefined) {    
    var defaults = {       
        interval:2000        
    };   
	function ScrollBar($ele, options) {		
		this.$ele = $ele;
        this.options = options = $.extend(defaults, options || {});
        this.init();      
	}
	ScrollBar.prototype = {
	 	constructor:ScrollBar,
	 	init:function(){
	 		var obj =this.$ele;
	 		var content = obj.find("ul");	
	 		var itemNum = content.find("li").size();
	 		
	 		if(itemNum==1){
				if(obj.find("ul").find("li").height() <= obj.height()){
					return;
				}
	 			content.append(content.html());//若为单条内容则复制一条相同内容
	 		}
	 		this.scrollStart();
	 	},
	 	scrollStart:function(){	 		
	 		var obj =this.$ele;
	 		var that = this;
	 		var content = obj.find("ul");
	 		var liHeight = obj.find("li:first").height();
	 		
	 		setInterval(function(){
	 			that.autoScroll();
	 		},this.options.interval);
	 	},
	 	autoScroll:function(){	
	 		
	 		var obj =this.$ele;
	 		var contentHeight = obj.height();
	 		var liHeight = obj.find("li:first").height();
			var liTop = Math.abs(obj.find("ul:first").css('margin-top').replace('px',''));
			//剩余的行数
			var times = (liHeight-liTop)/contentHeight-1;
			//向上移动的marginTop
			var moveTop = contentHeight;
	
			if((liHeight/contentHeight-times)>=0&&times>=0){
				moveTop=(liHeight/contentHeight-times)*contentHeight;
			}
			
		   	obj.find("ul:first").animate({
		        marginTop: "-"+moveTop+"px" 
		    }, 450, function () {
		    	if((liHeight-liTop)==contentHeight){
		    		$(this).css({ marginTop: "0px" }).find("li:first").appendTo(this);
		    	}
		    });    
	 	}
	}
    $.fn.scrollBar = function (options) {
        options = $.extend(defaults, options || {});
        return new ScrollBar($(this), options);
    }
})(jQuery, window, document);
function canMerchantOrderDish(orderTimeLimitString){
	if(orderTimeLimitString=='')
		return true;
	var orderTimeLimitArray=orderTimeLimitString.split(';');
	var presentDate=new Date();
	for(var i=0;i<orderTimeLimitArray.length;i++){
		var presentHour=presentDate.getHours();
	 	var presentMinute=presentDate.getMinutes();
		var time=orderTimeLimitArray[i].split('-');
		var startTime=time[0];
		var endTime=time[1];
		var startHour=parseInt(startTime.substring(0,startTime.indexOf(':')));
	 	var startMinute=parseInt(startTime.substring(startTime.indexOf(':')+1,startTime.length));
	 	var endHour=parseInt(endTime.substring(0,endTime.indexOf(':')));
	 	var endMinute=parseInt(endTime.substring(endTime.indexOf(':')+1,endTime.length));
	 	if(startHour>endHour){
//	 		endHour+=24;
//	 		presentHour=presentHour>12?presentHour:(presentHour+24);
	 		orderTimeLimitArray[orderTimeLimitArray.length]="0:0-"+endHour+":"+endMinute;
	 		endHour=24;
	 		endMinute=0;
	 	}
	 	if(startHour<presentHour&&presentHour<endHour)
	 		return true;
	 	else if(startHour==presentHour&&presentMinute>=startMinute)
	 		return true;
	 	else if(endHour==presentHour&&presentMinute<endMinute)
	 		return true;
	}
	return false;
}
function getUrlParam(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//时间格式化
function format(date,str){
    var mat={};
    mat.M=date.getMonth()+1;//月份记得加1
    mat.H=date.getHours();
    mat.s=date.getSeconds();
    mat.m=date.getMinutes();
    mat.Y=date.getFullYear();
    mat.D=date.getDate();
    mat.d=date.getDay();//星期几
    mat.d=check(mat.d);
    mat.H=check(mat.H);
    mat.M=check(mat.M);
    mat.D=check(mat.D);
    mat.s=check(mat.s);
    mat.m=check(mat.m);
    console.log(typeof mat.D)
    if(str.indexOf(":")>-1){
　　　　　mat.Y=mat.Y.toString().substr(2,2);
　　　　 return mat.Y+"/"+mat.M+"/"+mat.D+" "+mat.H+":"+mat.m+":"+mat.s;
    }
    if(str.indexOf("/")>-1){
        return mat.Y+"/"+mat.M+"/"+mat.D+" "+mat.H+"/"+mat.m+"/"+mat.s;
    }
    if(str.indexOf("-")>-1){
        return mat.Y+"-"+mat.M+"-"+mat.D+" "+mat.H+"-"+mat.m+"-"+mat.s;
    }
}
//检查是不是两位数字，不足补全
function check(str){
    str=str.toString();
    if(str.length<2){
        str='0'+ str;
    }
    return str;
}
//获取日期相差的天数
function dateDiff(date1,date2){
	iDays  =  parseInt((date1  -  date2)  /  1000  /  60  /  60  /24);
	return iDays;
}
function splitPhone(phone){
	if(isPhoneNumber(phone)){
		return phone.substring(0,3)+"-"+phone.substring(3,7)+"-"+phone.substring(7,11);
	}
	return phone;
}
function Arabia_To_SimplifiedChinese(Num) {
    for (i = Num.length - 1; i >= 0; i--) {
        Num = Num.replace(",", "")//替换Num中的“,”
        Num = Num.replace(" ", "")//替换Num中的空格
    }    
    if (isNaN(Num)) { //验证输入的字符是否为数字
        //alert("请检查小写金额是否正确");
        return;
    }
    //字符处理完毕后开始转换，采用前后两部分分别转换
    part = String(Num).split(".");
    newchar = "";
    //小数点前进行转化
    for (i = part[0].length - 1; i >= 0; i--) {
        if (part[0].length > 10) {
            //alert("位数过大，无法计算");
            return "";
        }//若数量超过拾亿单位，提示
        tmpnewchar = ""
        perchar = part[0].charAt(i);
        switch (perchar) {
            case "0":  tmpnewchar = "零" + tmpnewchar;break;
            case "1": tmpnewchar = "一" + tmpnewchar; break;
            case "2": tmpnewchar = "二" + tmpnewchar; break;
            case "3": tmpnewchar = "三" + tmpnewchar; break;
            case "4": tmpnewchar = "四" + tmpnewchar; break;
            case "5": tmpnewchar = "五" + tmpnewchar; break;
            case "6": tmpnewchar = "六" + tmpnewchar; break;
            case "7": tmpnewchar = "七" + tmpnewchar; break;
            case "8": tmpnewchar = "八" + tmpnewchar; break;
            case "9": tmpnewchar = "九" + tmpnewchar; break;
        }
        switch (part[0].length - i - 1) {
            case 0: tmpnewchar = tmpnewchar; break;
            case 1: if (perchar != 0) tmpnewchar = tmpnewchar + "十"; break;
            case 2: if (perchar != 0) tmpnewchar = tmpnewchar + "百"; break;
            case 3: if (perchar != 0) tmpnewchar = tmpnewchar + "千"; break;
            case 4: tmpnewchar = tmpnewchar + "万"; break;
            case 5: if (perchar != 0) tmpnewchar = tmpnewchar + "十"; break;
            case 6: if (perchar != 0) tmpnewchar = tmpnewchar + "百"; break;
            case 7: if (perchar != 0) tmpnewchar = tmpnewchar + "千"; break;
            case 8: tmpnewchar = tmpnewchar + "亿"; break;
            case 9: tmpnewchar = tmpnewchar + "十"; break;
        }
        newchar = tmpnewchar + newchar;
    }   
    //替换所有无用汉字，直到没有此类无用的数字为止
    while (newchar.search("零零") != -1 || newchar.search("零亿") != -1 || newchar.search("亿万") != -1 || newchar.search("零万") != -1) {
        newchar = newchar.replace("零亿", "亿");
        newchar = newchar.replace("亿万", "亿");
        newchar = newchar.replace("零万", "万");
        newchar = newchar.replace("零零", "零");      
    }
    //替换以“一十”开头的，为“十”
    if (newchar.indexOf("一十") == 0) {
        newchar = newchar.substr(1);
    }
    //替换以“零”结尾的，为“”
    if (newchar.lastIndexOf("零") == newchar.length - 1) {
        newchar = newchar.substr(0, newchar.length - 1);
    }
    return newchar;
}