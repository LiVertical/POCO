<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>作品管理</title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/workManage.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>/control/js/common.js"></script>
	<script src="<%=basePath%>/js/jquery.pagination.js"></script>
	<script src="<%=basePath%>/script/workManage.js"></script>
	<style>
	.time-input{
  		height:30px;
  		width:200px;
  		border:1px solid;
  		border-radius:5px;
  		outline:none;
  		padding-left:3px;
  	}
	</style>
  </head>
  	<script>
		 function loadSrc(src,id){
	    	  $("#mainFrame").attr("height",100);
	    	  $("#mainFrame").attr("src",src);
	      }
	      function iframeHeight() {
				var ifm = document.getElementById("mainFrame");
				var subWeb = document.frames ? document.frames["mainFrame"].document :ifm.contentDocument;
				if (ifm != null && subWeb != null) {
					ifm.height = subWeb.body.scrollHeight;
				}
				window.parent.window.iframeHeight();
		  }
	</script>	 
  <body>
  <div class="main">
  	<div class="searchDiv">
					<span>作品名：</span>
					<input type="text" name="workName" id="workName"/>
					<span>用户名：</span>
					<input type="text" name="userName" id="userName"/>
					<span>开始时间：</span>
					<input class="time-input " type="text" name="biginDate" id="biginDate"/>
					<span>结束时间：</span>
					<input class="time-input " type="text" name="endDate" id="endDate"/>
					<span>作品分类：</span>
					<select class="type" name="workType" id="workType">
						<option value="">请选择作品分类</option>
						<option value="0">人像摄影</option>
						<option value="1">生态摄影</option>
						<option value="2">运动摄影</option>
						<option value="3">生活摄影</option>
						<option value="4">夜景摄影</option>
						<option value="5">风景摄影</option>
						<option value="6">纪实摄影</option>
						<option value="7">人体摄影</option>
						<option value="9">自拍摄影</option>
						<option value="10">商业摄影</option>
						<option value="11">LOMO</option>
						<option value="8">其他摄影</option>
					</select>
					<button class="sBtn" onclick="getAllWorksInfos(1)">查询</button>
				</div>
  	<div id="p_content">
		<table cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
			<thead>
				<tr class="tr_head">
					<td style="width:40px">序号</td>
					<td style="width:100px">作品标题</td>
					<td style="width:240px">作品内容</td>
					<td style="width:140px">上传时间</td>
					<td style="width:240px">作品配图</td>
					<td style="width:100px">所属用户</td>
					<td style="width:60px">操作</td>
				</tr>
			</thead>
			<tbody class="table" id="dataDisplay"></tbody>
		</table>
	</div>
    <div class="pagination" id="page"></div>
   </div>
  </body>
</html>
