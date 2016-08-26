<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../static/jquery/jquery-1.9.1.min.js"></script>
<body>
	<form id="form" action="importAction" method="post"
		 enctype="multipart/form-data">
		<input id="fileText" name="myfiles" type="file" />
		<p>
			<button type="submit" class="btn">导入数据</button>
		</p>
		<p  class="showOrh">
			<button type="button" class="btn " onclick="clos();">关闭窗口并刷新数据</button>
		</p>
		<p class="showOrh">此次导入数据总条数：${AllDataInt}</p>
		<p class="showOrh">成功导入数据数：${SuccessSaveInt}</p>
		<p class="showOrh">导入失败数据数：${FaltSaveInt}</p>
		<p class="showOrh">导入失败数据提示：<br>${MSG}</p>
		<input type="hidden" id="data" value='${AllDataInt}'> <input
			type="hidden" id="rtNull" value='1${data}'>
	</form>
</body>
<script type="text/javascript">
	$(function() {
		showOrhid();
	});
	//关闭窗口
	function clos(){
		 parent.f_colse();
	};
	showOrhid = function() {
		if ($("#data").val() != "") {
			$(".showOrh").show();
		} else {
			$(".showOrh").hide();
		}

		if ($("#rtNull").val() != "10") {
			$(".nullFile").hide();
		} else {
			$(".nullFile").show();
		}
	}

	hidAll = function() {
		$(".showOrh").hide();
		$(".nullFile").hide();
		$(".wait").hide();
	}

	subCheck = function() {
		hidAll();
		var obj = document.getElementById('fileText');
		if (obj.value == '') {
			alert('请选择要上传的Excel文件');
			return false;
		}

		var file = jQuery("input[name='myfiles']").val() //获取文件路径
		var filename = file.replace(/.*(\/|\\)/, ""); //获取文件名
		var fileExt = (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename
				.toLowerCase()) : ''; //获取文件后缀名

		if (fileExt != 'xls' && fileExt != 'xlsx') {
			alert('文件类型不正确，请选择xls文件');
			return false;
		}
		ajaxLoading();
		return true;
	}

	//采用jquery easyui loading css效果   
	function ajaxLoading() {
		$("<div class=\"datagrid-mask\"></div>").css({
			display : "block",
			width : "100%",
			height : $(window).height()
		}).appendTo("body");
		$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。")
				.appendTo("body").css({
					display : "block",
					left : ($(document.body).outerWidth(true) - 190) / 2,
					top : ($(window).height() - 45) / 2
				});
	}
	function ajaxLoadEnd() {
		$(".datagrid-mask").remove();
		$(".datagrid-mask-msg").remove();
	}
</script>
</html>