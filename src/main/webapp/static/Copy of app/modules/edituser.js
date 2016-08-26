var name = "user";
var theUrl = "edituser";
var AllData ;
var formerName = "";
$(document).ready(function() {
	var idByiframe = null;
	idByiframe = $(window.parent.document).find("iframe[class='iframe-main']").attr("alt");
	
	if (idByiframe == "edit") {
		findOneData($(window.parent.document).find("iframe[class='iframe-main']").attr("id"));
		  $(":password").removeClass("required");
		  $(":password").parent().find("label").html("为空默认密码");
	}else if(idByiframe == "show"){	
		$(".cancel-edit-" + name).click(function() {
			window.parent.PopuWnd.close();
		});
         return;
	}else{
    	 setInputData();
     }
	var myAlidation = new checkIframe("edit-user-form",{});
	//myAlidation.init();
	
	$("#edit-"+name+"-form").find(".save-edit-" + name).click(function() {
		myAlidation.postCheck();
		if(checkDate()){
			if (idByiframe == "edit") {
				var data =getInputData();
				if (checkPass(data.password,data.plainPassword)) {
					updateData(data);
				}
			} else if (idByiframe == "add") {
				var data =getInputData();
				if (checkPass(data.password,data.plainPassword)) {
					saveData(data);
				}
			};
		}
	});

	$("#edit-"+name+"-form").find(".cancel-edit-" + name).click(function() {
		window.parent.PopuWnd.close();
	});

	checkDate = function(){
		var rt = true;
		rt = myAlidation.result == true? rt : false ;
		if(!(formerName!="" && formerName == $.trim($("#edit-"+name+"-form").find(".loginName").val()))){
			rt = checkNameOrNuber(theUrl,$("#edit-"+name+"-form").find(".loginName"),"loginName","已存在","loginName",$.trim($("#edit-"+name+"-form").find(".loginName").val())) == true? rt : false ;
		}
		return rt;
	};
});

setCross = function(data){
	
};
setInputData = function(data) {
	data = (data || {});
	formerName = (data.loginName || "");
	$("#edit-"+name+"-form").find("."+name+"Id").val(data.id || "");
	$("#edit-"+name+"-form").find(".loginName").val(data.loginName || "");
	$("#edit-"+name+"-form").find(".realName").val(data.realName || "");
    $("#edit-"+name+"-form").find(".remark").val(data.remark || "");
    $("#edit-"+name+"-form").find(".password").val("");
    $("#edit-"+name+"-form").find(".plainPassword").val("");
	findCrossList((data.role || ""),"role");

};

getInputData = function(data) {
	data = (data || {});
	data.id = $("#edit-"+name+"-form").find("."+name+"Id").val();
	data.loginName = $("#edit-"+name+"-form").find(".loginName").val();
    data.realName = $("#edit-"+name+"-form").find(".realName").val();
    data.password = $("#edit-"+name+"-form").find(".password").val();
    data.plainPassword = $("#edit-"+name+"-form").find(".plainPassword").val();
    data.remark = $("#edit-"+name+"-form").find(".remark").val();
    data.role={};
    data.role.id = $("#edit-"+name+"-form").find(".role").val();
	return data;
};

checkPass = function(dataPwn,dataPwr){
	var rt = true;
	if($.trim(dataPwn) == $.trim(dataPwr)){
			if($.trim(dataPwn)!=''){
				var zz =  /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{6,}$/; 
				if(!zz.test($.trim(dataPwn))){
					alert("密码长度必须6位以上，必须同时包含数字和字母！");
					rt = false;
				}
			}
	}else{
		alert("两次新密码输入不一致，请检查!");
		rt = false;
	}
	
	return rt;
};


setCrossSelect = function(data,className,roleID){
	var html = "";
	for ( var i = 0; i < data.length; i++) {
		html += "<option value='"+data[i].id+"' "+(roleID == data[i].id? "selected" : "" )+">"+data[i].name+"</option>";
	}
	$("#edit-"+name+"-form").find("."+className).empty().html(html);
};

findCrossList = function(role,className){
	if (!AllData) {
		$.ajax({
			type : "POST",
			url :  "rolemanagement/alllist",
			dataType : "json",
			contentType : 'application/json;charset=UTF-8',
			success : $.proxy(function(obj) {
				if (obj.success) {
					//setInputData(obj.data);
					AllData = obj.data;
					setCrossSelect(AllData,className,role.id);
				} else {
					alert("请求失败！");
				}
			}, this),
			error : function(msg) {
				alert(msg.success);
			}
		});
	}else{
		setCrossSelect(AllData,className,crossID);
	}
	

};


saveData = function(data) {
	$.ajax({
		type : "POST",
		url : theUrl + "/newone",
		data : $.toJSON(data),
		dataType : "json",
		contentType : 'application/json;charset=UTF-8',
		success : $.proxy(function(obj) {
			if (obj.success) {
				alert("新增成功！");
				window.parent.baseSetting.getModule("UserManagement").module.requestData(1);
				window.parent.PopuWnd.close();
			} else {
				alert("新增失败！");
			}
		}, this),
		error : function(msg) {
			alert(msg.success);
		}
	});
};

findOneData = function(ids) {
	$.ajax({
		type : "POST",
		url : theUrl + "/findOneById",
		dataType : "json",
		data : "id=" + ids,
		success : $.proxy(function(obj) {
			if (obj.success) {
				  setInputData(obj.data);
			} else {
				alert("请求失败！");
			}
		}, this),
		error : function(msg) {
			alert(msg.success);
		}
	});
};


updateData = function(data) {
	$.ajax({
		type : "POST",
		url : theUrl + "/update",
		data : $.toJSON(data),
		dataType : "json",
		contentType : 'application/json;charset=UTF-8',
		success : $.proxy(function(obj) {
			if (obj.success) {
				alert("更新成功！");
				window.parent.baseSetting.getModule("UserManagement").module.requestData(1);
				window.parent.PopuWnd.close();
			} else {
				alert("更新失败！");
			}
		}, this),
		error : function(msg) {
			alert(msg.success);
		}
	});
};