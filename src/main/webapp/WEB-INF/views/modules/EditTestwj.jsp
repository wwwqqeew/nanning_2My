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
<title>增加外键测试</title>
<script type="text/javascript" src="static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="static/jquery-json/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="static/app/modules/edittestwj.js"></script>
<link type="text/css" rel="stylesheet" href="static/styles/iframe.css?date=2016-3-29 15:36:09" />
<script src="static/jquery-validation/checkAfter.js" type="text/javascript"></script>
<script src="static/jqueryMy/IframeAction.js?date=2016-3-29 15:36:09" type="text/javascript"></script>
<script type="text/javascript" src="static/jqueryMy/jqueryMy.js?date=2016-3-29 15:36:09"></script>
</head>
<body>
	<form id='edit-testwj-form' class="iframe-form">
		<table class='iframe-table' align="center">
 				  
        				 <tr>
				 	<td class="title">名称：</td>
				 	<td  class="td-input"><input value='${entity.name}' class='text-input name  iframe-radius' name='name'  style="width: 345px;" ></td>
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