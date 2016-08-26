<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加轨迹</title>
<script type="text/javascript" src="static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="static/jquery-json/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="static/app/modules/edittrack.js?date=2016-3-29 15:36:12"></script>
<link type="text/css" rel="stylesheet" href="static/styles/iframe.css?date=2016-3-29 15:36:09" />
<script src="static/jquery-validation/checkAfter.js" type="text/javascript"></script>
<script src="static/jqueryMy/IframeAction.js?date=2016-3-29 15:36:10" type="text/javascript"></script>
<script type="text/javascript" src="static/jqueryMy/jqueryMy.js?date=2016-3-29 15:36:15"></script>
<link type="text/css" rel="stylesheet" href="static/My97DatePicker/skin/WdatePicker.css" />
<script src="static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
<!-- 这里识别form用的是id， 自定义 -->
	<form id='edit-track-form' class="iframe-form">
		<table class='iframe-table' align="center">
 				  
           				  
        				 <tr>
				 	<td class="title">地址：</td>
				 	<td  class="td-input"><input value='${entity.address}' class='text-input address  iframe-radius' name='address'  style="width: 345px;" ></td>
					<td></td>
				</tr>
   				  
        				 <tr>
				 	<td class="title">经度：</td>
				 	<td  class="td-input"><input value='${entity.lat}' class='text-input lat  iframe-radius' name='lat'  style="width: 345px;" ></td>
					<td></td>
				</tr>
   				  
        				 <tr>
				 	<td class="title">维度：</td>
				 	<td  class="td-input"><input value='${entity.lng}' class='text-input lng  iframe-radius' name='lng'  style="width: 345px;" ></td>
					<td></td>
				</tr>
   				   <tr>
				 	<td class="td-select">外键：</td>
				 	<td  class="td-input">
				 	<select class="demo iframe-radius iframe-select" name="demo.id" style="width: 345px;" value='${entity.demo.id}'>
					</select></td>
					<td></td>
				</tr>
    					<tr>
					<td class="title">日期：</td>
					<td class="td-input"><input  value='${entity.tdate}' class='text-input tdate  iframe-radius Wdate' style="width: 345px;" name='tdate' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
					<td></td>
					</tr>
       				  
            				</table>
		<table class="iframe-btn-table" align="center" cellspacing="1" cellpadding="0" width="585">
		<tr class="iframe-button-tr">
					<td align="center"><input value="保存" class='iframe-button save-edit iframe-radius'
						type="button"></input><input type="hidden" name='id' value='${entity.id}' class='id' ></td>
					<td align="center"><input value="取消" class='iframe-button cancel-edit iframe-radius'
						type="button"></input></td>
				<td></td>
				</tr>
		</table>
	</form>
</body>
</html>