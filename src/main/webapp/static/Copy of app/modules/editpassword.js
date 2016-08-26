var name = "password";
var theUrl = "edituser";

$(document).ready(function() {
	var theId =  $(window.parent.document).find("iframe[class='iframe-main']").attr("id");
	idByiframe = $(window.parent.document).find("iframe[class='iframe-main']").attr("alt");
	$("#edit-"+name+"-form").find("."+name+"Id").val(theId);
	$("#edit-"+name+"-form").find(".save-edit-" + name).click(function() {
		var myAlidation = new checkIframe("edit-password-form",{});
		//myAlidation.init();
		myAlidation.postCheck();
		if(myAlidation.result){
			getInputData();
		}
	});
	$("#edit-"+name+"-form").find(".theopassword").val("");
	$("#edit-"+name+"-form").find(".thenpassword").val("");
	$("#edit-"+name+"-form").find(".therpassword").val("");
	
	$("#edit-"+name+"-form").find(".theopassword").focus();
	
	$("#edit-"+name+"-form").find(".cancel-edit-" + name).click(function() {
		window.parent.PopuWnd.close();
	});
});

getInputData = function() {
	var data = {};
	data.id = $("#edit-"+name+"-form").find("."+name+"Id").val();
	data.opassword = $.trim($("#edit-"+name+"-form").find(".theopassword").val());
	data.npassword = $.trim($("#edit-"+name+"-form").find(".thenpassword").val());
	data.rpassword = $.trim($("#edit-"+name+"-form").find(".therpassword").val());
	if (checkDate(data.npassword,data.rpassword)) {
		updateData(data.id,data.opassword,data.npassword);
	}
	
};

checkDate = function(dataPwn,dataPwr){
	var rt = true;
	if($.trim(dataPwn) == $.trim(dataPwr)){
		var zz =  /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{6,}$/; 
		if(!zz.test($.trim(dataPwn))){
			alert("密码长度必须6位以上，必须同时包含数字和字母！");
			rt = false;
		}
	}else{
		alert("两次新密码输入不一致，请检查!");
		rt = false;
	}
	
	return rt;
};

updateData = function(id,opw,npw) {
	$.ajax({
		type : "POST",
		url : "/edituser/checkpw",
		dataType : "json",
		async:false, 
		data : "id="+id+"&opw="+opw+"&npw="+npw,
		success : $.proxy(function(obj) {
			if (obj.success) {
				if (obj.data) {
					alert("密码修改成功！");
					window.parent.PopuWnd.close();
				}else{
					alert("旧密码不正确！");
				}
			} else {
				alert("数据获取失败！");
			}
		}, this),
		error : function(msg) {
			//alert(msg.success);
		}
	});
};