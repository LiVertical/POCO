$(function(){
	$("#upload").click(function(){
		var productName = $("input[name='productName']").val().trim();
		var proType = $("select option:selected").val().trim();
		var image =  document.getElementById("image").files[0];
		var productDesc = $("input[name='productDesc']").val();
		if(productName == ""){
			alert("请输入作品名！");
			$("#upload").attr("disabled", true);
			$("#upload").attr("disabled", false);
		}else if(proType == ""){
			alert("请输入作品类型！");
			$("#upload").attr("disabled", true);
			$("#upload").attr("disabled", false);
		}else if(image == ""){
			alert("请选择作品！");
			$("#upload").attr("disabled", true);
			$("#upload").attr("disabled", false);
		} else if(productDesc == ""){
			alert("请输入作品描述！");
			$("#upload").attr("disabled", true);
			$("#upload").attr("disabled", false);
		}
		$("#upload").attr("disabled", false);
		upload();
	});



var upload = function(){
	var productName = $("input[name='productName']").val().trim();
	var proType = $("select option:selected").val().trim();
	var image = document.getElementById("files")[0];
	var productDesc = $("input[name='productDesc']").val();
	var formData = new FormData();
	formData.append("productName",productName);
	formData.append("proType",proType);
	formData.append("image",image);
	formData.append("productDesc",productDesc);
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
        		 console.log(data.returnCode+";"+data.returnMsg);
                 alert("上传成功");
                /* window.location.href = getRootPath() + "/views/productShow.jsp";*/
        	}
        },
        error: function (data) {
            console.log(data);
            alert("上传失败");
        }
    });
};
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



