<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看[例子]</title>
 <script type="text/javascript" src="/static/jquery/jquery-1.9.1.min.js"></script> 
 <link type="text/css" rel="stylesheet" href="/static/styles/home.css" />
<link type="text/css" rel="stylesheet" href="/static/styles/iframe.css" />
</head>
<body>

	        <form id='edit-user-form' class="iframe-form edit-form">
					<table class="iframe-table user-iframe edit-table" style="font-size: 16px" align="center"><!-- rules="rows" -->
						<tbody>
							  	<tr class="user-remove">
								 <td class="left">[输入框]：</td>
								 <td class="right"><label style="width: 312px">${entity.input}</label></td>
							    </tr>
  							  	<tr class="user-remove">
								 <td class="left">[时间]：</td>
								 <td class="right"><label style="width: 312px">${entity.inputDate}</label></td>
							    </tr>
  						</tbody>
					</table>
					<div class="iframe-button-div" >
								<input value="取消" class='btn-base-style cancel-edit' />
								</div> 
				</form>    
</body>
<script type="text/javascript">
$(function(){
	$(".cancel-edit").click(function() {
		window.parent.PopuWnd.close();
	});
});
</script>
</html>