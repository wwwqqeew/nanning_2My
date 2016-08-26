<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="static/images/favicon.ico">
<style type="text/css">
input {
	width: 200px;
}

#submit {
	width: 50px;
	margin: 0px;
	height: 30px;
	border: 1px #bbbbbb solid;
	color: #555555;
}

#footer {
	position: absolute;
	bottom: 0px;
	width: 100%;
	height: 25px;
	text-align: center;
	line-height: 25px;
	font-size: 12px;
	font-size: 12px;
	width: 100%
}
</style>
<script type="text/javascript">
	function submitForm() {
		var name = document.getElementById("loginName").value;
		var password = document.getElementById("password").value;
		var rePassword = document.getElementById("re-password").value;
		if (name == null || name == "") {
			alert("超级管理员登录名！");
			return false;
		} else if (password == null || password == "") {
			alert("请输入密码！");
			return false;
		} else if (rePassword == null || rePassword == "") {
			alert("请再输入密码！");
			return false;
		} else if (password != rePassword) {
			alert("重输密码不对！");
			return false;
		} else {
			return true;
		}
	}
</script>
<title>安装点菜宝后台系统</title>
</head>
<body style="width: 98%; height: 98%; background: #ffffff; position: absolute; text-align: center;">

	<div style="width: 100%; height: 400px; background: #ffffff; z-index: 50; text-align: center; position: bsolute;">
		<table style="width: 400px; height: 50px; font-size: 30px; font-weight: bold; color: #666e78; text-align: center; margin: 80px auto;">
			<tbody>
				<tr>
					<td>初始化系统</td>
				</tr>
			</tbody>
		</table>
		<form id="settingform" action="admin" onsubmit="return submitForm()" method="post">
			<table style="width: 400px; height: 100px; color: #666e78; text-align: right; margin: 50px auto;">
				<tbody>
					<tr>
						<td>超级管理员：</td>
						<td align="left"><input id='loginName' name='loginName' type="text"></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td align="left"><input id='password' name='password' type="password"></td>
					</tr>
					<tr>
						<td>重输密码：</td>
						<td align="left"><input id='re-password' name='re-password' type="password"></td>
					</tr>
					<tr>
						<td>
							<p></p>
						</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td align="left"><input id='submit' value='提交' type="submit"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class='footer'>技术支持：公安部交通管理科学研究所&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
</body>
</html>