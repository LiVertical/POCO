$(document).ready(function(){
	$("#image").on("change", function(){
		upload();
	});

	//初始化文本编辑器
	 $('#summernote').summernote();


function upload(){
	var productName = $("input[name='productName']").val().trim();
	var proType = $("select option:selected").val().trim();
	var image = document.getElementById("image").files[0];
	var productDesc = $("input[name='productDesc']").val();
	var productGroupId = GetRequest();
	var activityId = $("body").data().activity_id;
	var contestId = $("body").data().contest_id;
	console.log("当前组id:"+productGroupId);
	console.log("活动id:"+activityId+"；大赛id："+contestId);
	var formData = new FormData();
	formData.append("productName",productName);
	formData.append("proType",proType);
	formData.append("image",image);
	formData.append("productDesc",productDesc);
	formData.append("productGroupId", productGroupId);
	if(activityId != undefined){
		formData.append("activityId", activityId);
	}
	if(contestId != undefined){
		formData.append("contestId", contestId);
	}
	$.ajax({
        url: getRootPath() + "/user/product-publishImages.action",
        type: "post",
        data: formData,
        dataType:'json',
        contentType: false,
        processData: false,
        mimeType: "multipart/form-data",
        success: function (data) {
        	if(data.returnCode == '00'){
        		 console.log("上传图片："+data.returnCode+";"+data.returnMsg);
                 alert("上传成功");
        	}
        },
        error: function (data) {
            console.log(data);
            alert("上传失败");
        }
    });
};

$("#image").on("change",function(){
	var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
	console.log("图片路径:"+objUrl);
	var htm = "";
	if (objUrl) {
		htm = "<li class='uploadDiv'><img class='upload_box' src='" +   objUrl + "' id='img'></li>";
		$("#pic").append(htm);
		$("#imgBox").show();
	}
});

});

//上传用户头像
var uploadUserImg = function(){
	var upload =  document.getElementById("file").files[0];
	var formData = new FormData();
	formData.append("upload",upload);
	$.ajax({
        url: getRootPath() + "/user-uploadUserImg.action",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        mimeType: "multipart/form-data",
        success: function (data) { 
            console.log(data);
            alert("上传成功");
        },
        error: function (data) {
            console.log(data);
            alert("上传失败");
        }
    });
};

function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
		return url ;
}

//上传作品
function uploadWork(){
	var workName = $("#workTitle").val();
	var workComment = $('#summernote').summernote('code');
	var productGroupId = GetRequest();
	var contestId = $("body").data().contest_id;
	if(workName == ''){
		alert("请输入作品标题");
		return;
	}
	if(workComment == ''){
		alert("请输入作品内容");
		return;
	}
	var params = {
			workName:workName,
			workComment : workComment,
			productGroupId:productGroupId,
			contestId:contestId,
	};
	$.post(getRootPath()+"/user/addWork.action", params, function(data){
		if(data.returnCode == '00'){
			alert("作品上传成功");
			window.location = getRootPath() + "/index.jsp";
		}else{
			alert("上传失败");
		}
	});
}


