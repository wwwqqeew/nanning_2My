var name = "role";
var theUrl = "editrole";
var formerName = "";
$(document).ready(function() {
	var idByiframe = null;
	idByiframe = $(window.parent.document).find("iframe[class='iframe-main']").attr("alt");
	if (idByiframe == "edit") {
		//编辑操作时获取数据
		findOneData($(window.parent.document).find("iframe[class='iframe-main']").attr("id"));
	}else if(idByiframe == "show"){//查看操作	
		showOneById($(window.parent.document).find("iframe[class='iframe-main']").attr("id"));
		
		return;
	}
	

	
	var myAlidation = new checkIframe("edit-role-form",{});
	$("#edit-"+name+"-form").find(".name").focus();
	//myAlidation.init();
	checkboxChange("ht");
	checkboxChange("yh");
	$("#edit-"+name+"-form").find(".save-edit-" + name).click(function() {
		myAlidation.postCheck();
		if(checkDate()){
			if (idByiframe == "edit") {
				updateData(getInputData());
			} else if (idByiframe == "add") {
				saveData(getInputData());
			};
		}
	});

	$("#edit-"+name+"-form").find(".cancel-edit-" + name).click(function() {
		window.parent.PopuWnd.close();
	});

	checkDate = function(){
		var rt = true;
		rt = myAlidation.result == true? rt : false ;
		if(!(formerName!="" && formerName == $.trim($("#edit-"+name+"-form").find(".name").val()))){
			rt = checkNameOrNuber(theUrl,$("#edit-"+name+"-form").find(".name"),"name","已存在","name",$.trim($("#edit-"+name+"-form").find(".name").val())) == true? rt : false ;
		}
		return rt;
	};
});


checkboxChangeSet = function(className){
	$("#edit-"+name+"-form").find("."+className+"-second").each(function(){
		if($(this).prop("checked")){
			if(!$("#edit-"+name+"-form").find("."+className+"-first").prop("checked")){
				$("#edit-"+name+"-form").find("."+className+"-first").prop("checked",true);
				return false;
			}
		};
	});
};

checkboxChange = function(className){
	secondCheckboxChange(className);
	firsrCheckboxChange(className);
};

secondCheckboxChange = function (className){
	$("#edit-"+name+"-form").find("."+className+"-second").change(function(){
		if($(this).prop("checked")){
			if(!$("#edit-"+name+"-form").find("."+className+"-first").prop("checked")){
				$("#edit-"+name+"-form").find("."+className+"-first").prop("checked",true);
			}
		};
	});
};

firsrCheckboxChange = function (className){
	$("#edit-"+name+"-form").find("."+className+"-first").click(function(){
		if(!$(this).prop("checked")){
			$("#edit-"+name+"-form").find("."+className+"-second").prop("checked",false);
		}else{
			$("#edit-"+name+"-form").find("."+className+"-second").prop("checked",true);
		};
	});
};

radioSet_df = function (className ,date){
	var theb = true;
	var strBoolean = (date==true?"true":(date==false?"false":date));
	 $("#edit-"+name+"-form").find("input[type='radio'][class='"+className+"']").each(function(){
		 if($(this).val()==strBoolean){
			 $(this).prop("checked", true);
			 theb = false;
		 }
	 });
	 if(theb){
		 $("#edit-"+name+"-form").find("input[type='radio'][class='"+className+"'][value='other']").prop("checked", true);
		 $("#edit-"+name+"-form").find("."+className+"-input").val(date);
	 }
};
radioGet_df = function (name){
	 if($("#edit-"+name+"-form").find("input[type='radio'][class='"+className+"']:checked").val()!="other"){
		 return $("#edit-"+name+"-form").find("input[type='radio'][class='"+className+"']:checked").val();
	 }else{
		 return $.trim($("#edit-"+name+"-form").find("."+className+"-input").val());
	 }
};

showOneById = function(ids) {
	$.ajax({
		type : "POST",
		url : "findOneById",
		dataType : "json",
		data : "id=" + ids,
		success : $.proxy(function(obj) {
			if (obj.success) {
    	   showData(obj.data);
			} else {
				alert("请求失败！");
			}
		}, this),
		error : function(msg) {
			alert(msg.success);
		}
	});
};
showData = function(data) {
	data = (data || {});
	var titleData ={"addLine":"添加线路","lineManagement":"线路管理",
		"lineControl":"线路控制","crossManagement":"路口管理",
		"linkManagement":"路段管理","taskManagement":"任务管理",
		"tasktypeManagement":"任务类型","equipmentsetManagement":"设备管理权限"
		,"equipmentManagement":"设备管理","configureManagement":"配置管理",
		"logsManagement":"日志查询","logsstatisticsManagement":"日志统计","testdemoManagement":"测试管理"};
	
	 var str='<tr><td colspan=5 style="text-indent: 35px;"><label class="namelb">角色名称</label>:'+data.name+'</td></tr>';
	 str+='<tr><td class="one" colspan=5>终端模块</td></tr><tr>';
	 data.name=null;
	 data.id=null;
	 
	 var str2='<tr><td class=one colspan=5><label>后台模块</label></td></tr>';
//	 data.userManagement==true?str2+='<tr><td class=second colspan="3">用户管理</td ></tr>':false;
	//配置
	 data.configureManagement==true?str2+='<tr><td class="second" colspan="5">配置管理</td>':false;
	//设备
	 data.equipmentManagement==true?str2+='<tr><td class="second" colspan="1">设备管理</td>':false;
	 data.addEquipment==true?str2+='<td class="third" colspan="1">添加设备</td>':false;
	 data.delEquipment==true?str2+='<td class="third" colspan="1">删除设备</td>':false;
	 data.showEquipment==true?str2+='<td class="third" colspan="1">查看设备</td>':false;
	 data.editEquipment==true?str2+='<td class="third" colspan="1">修改设备</td></tr>':false;
	//线路
	 data.lineManagement==true?str2+='<tr><td class="second" colspan="1">线路管理</td>':false;
	 data.addLine==true?str2+='<td class="third" colspan="1">添加线路</td>':false;
	 data.delLine==true?str2+='<td class="third" colspan="1">删除线路</td>':false;
	 data.showLine==true?str2+='<td class="third" colspan="1">查看线路</td>':false;
	 data.editLine==true?str2+='<td class="third" colspan="1">修改线路</td></tr>':false;
	//监控
	 data.lineControl==true?str2+='<tr><td class="second" colspan="5">监控管理</td>':false;
	//任务
	 data.taskManagement==true?str2+='<tr><td class="second" colspan="1">任务管理</td>':false;
	 data.addTask==true?str2+='<td class="third" colspan="1">添加任务</td>':false;
	 data.delTask==true?str2+='<td class="third" colspan="1">删除任务</td>':false;
	 data.showTask==true?str2+='<td class="third" colspan="1">查看任务</td>':false;
	 data.editTask==true?str2+='<td class="third" colspan="1">修改任务</td></tr>':false;
	 //用户
	 data.userManagement==true?str2+='<tr><td class="second" colspan="1">用户管理</td>':false;
	 data.addUser==true?str2+='<td class="third" colspan="1">添加用户</td>':false;
	 data.delUser==true?str2+='<td class="third" colspan="1">删除用户</td>':false;
	 data.showUser==true?str2+='<td class="third" colspan="1">查看用户</td>':false;
	 data.editUser==true?str2+='<td class="third" colspan="1">修改用户</td></tr>':false;
	//权限
	 data.roleManagement==true?str2+='<tr><td class="second" colspan="1">权限管理</td>':false;
	 data.addRole==true?str2+='<td class="third" colspan="1">添加权限</td>':false;
	 data.delRole==true?str2+='<td class="third" colspan="1">删除权限</td>':false;
	 data.showRole==true?str2+='<td class="third" colspan="1">查看权限</td>':false;
	 data.editRole==true?str2+='<td class="third" colspan="1">修改权限</td></tr>':false;
	//路口
	 data.crossManagement==true?str2+='<tr><td class="second" colspan="1">路口管理</td>':false;
	 data.editCross==true?str2+='<td class="third" colspan="4">修改路口</td></tr>':false;
	//代码
	 data.tasktypeManagement==true?str2+='<tr><td class="second" colspan="5">代码管理</td>':false;
	//日志查询
	 data.logsManagement==true?str2+='<tr><td class="second" colspan="5">日志查询管理</td>':false;
	//日志统计
	 data.logsstatisticsManagement==true?str2+='<tr><td class="second" colspan="5">日志统计管理</td>':false;
	 
	data.userManagement=null;
	data.roleManagement=null;
	data.remark=null;
	var index=0;
//	for(var item in data){
//		if(data[item]){
//			str+='<td class=second>'+titleData[item]+'</td >';
//			if(index==2){
//				str+='</tr><tr>';
//				index=-1;
//			}
//			index++;
//		}
//		
//	}
//	while(index<3&&index!=0) {str+='<td>&nbsp;</td>';index++;}
//	str+='</tr>';
	 str=str+str2;
	 
	 $("#edit-"+name+"-form").find("tbody").append(str);
	 $(".showRole_div").after('<div class="iframe-button-div"><input value="返回" class="btn-base-style cancel-edit-role left_save" type="button"></div>');
	 $(".iframe-button-div").find(".cancel-edit-" + name).click(function() {
			window.parent.PopuWnd.close();
		});

};




setInputData = function(data) {
	data = (data || {});
	
	formerName = (data.name || "");
	$("#edit-"+name+"-form").find("."+name+"Id").val(data.id || "");
	$("#edit-"+name+"-form").find(".name").val(data.name || "");
	//权限
	$("#edit-"+name+"-form").find(".roleManagement").prop("checked",data.roleManagement==true?true:false);
	$("#edit-"+name+"-form").find(".addRole").prop("checked",data.addRole==true?true:false);
	$("#edit-"+name+"-form").find(".delRole").prop("checked",data.delRole==true?true:false);
	$("#edit-"+name+"-form").find(".showRole").prop("checked",data.showRole==true?true:false);
	$("#edit-"+name+"-form").find(".editRole").prop("checked",data.editRole==true?true:false);
	//用户
	$("#edit-"+name+"-form").find(".userManagement").prop("checked",data.userManagement==true?true:false);
	$("#edit-"+name+"-form").find(".addUser").prop("checked",data.addUser==true?true:false);
	$("#edit-"+name+"-form").find(".delUser").prop("checked",data.delUser==true?true:false);
	$("#edit-"+name+"-form").find(".showUser").prop("checked",data.showUser==true?true:false);
	$("#edit-"+name+"-form").find(".editUser").prop("checked",data.editUser==true?true:false);
	//线路
	$("#edit-"+name+"-form").find(".lineManagement").prop("checked",data.lineManagement==true?true:false);
	$("#edit-"+name+"-form").find(".addLine").prop("checked",data.addLine==true?true:false);
	$("#edit-"+name+"-form").find(".delLine").prop("checked",data.delLine==true?true:false);
	$("#edit-"+name+"-form").find(".showLine").prop("checked",data.showLine==true?true:false);
	$("#edit-"+name+"-form").find(".editLine").prop("checked",data.editLine==true?true:false);
	//监控
	$("#edit-"+name+"-form").find(".lineControl").prop("checked",data.lineControl==true?true:false);
	//任务
	$("#edit-"+name+"-form").find(".taskManagement").prop("checked",data.taskManagement==true?true:false);
	$("#edit-"+name+"-form").find(".addTask").prop("checked",data.addTask==true?true:false);
	$("#edit-"+name+"-form").find(".delTask").prop("checked",data.delTask==true?true:false);
	$("#edit-"+name+"-form").find(".showTask").prop("checked",data.showTask==true?true:false);
	$("#edit-"+name+"-form").find(".editTask").prop("checked",data.editTask==true?true:false);
	//配置
	$("#edit-"+name+"-form").find(".configureManagement").prop("checked",data.configureManagement==true?true:false);
	//代码
	$("#edit-"+name+"-form").find(".tasktypeManagement").prop("checked",data.tasktypeManagement==true?true:false);
	//路口
	$("#edit-"+name+"-form").find(".crossManagement").prop("checked",data.crossManagement==true?true:false);
	$("#edit-"+name+"-form").find(".editCross").prop("checked",data.editCross==true?true:false);
	//设备
	$("#edit-"+name+"-form").find(".equipmentManagement").prop("checked",data.equipmentManagement==true?true:false);
	$("#edit-"+name+"-form").find(".addEquipment").prop("checked",data.addEquipment==true?true:false);
	$("#edit-"+name+"-form").find(".delEquipment").prop("checked",data.delEquipment==true?true:false);
	$("#edit-"+name+"-form").find(".showEquipment").prop("checked",data.showEquipment==true?true:false);
	$("#edit-"+name+"-form").find(".editEquipment").prop("checked",data.editEquipment==true?true:false);
//	$("#edit-"+name+"-form").find(".equipmentsetManagement").prop("checked",data.equipmentsetManagement==true?true:false);
	//日志
	$("#edit-"+name+"-form").find(".logsManagement").prop("checked",data.logsManagement==true?true:false);
	$("#edit-"+name+"-form").find(".logsstatisticsManagement").prop("checked",data.logsstatisticsManagement==true?true:false);
	//辅助开发
	$("#edit-"+name+"-form").find(".linkManagement").prop("checked",data.linkManagement==true?true:false);
	checkboxChangeSet("ht");
	checkboxChangeSet("yh");
 //lineControl
};

getInputData = function(data) {
	data = (data || {});
	data.id = $("#edit-"+name+"-form").find("."+name+"Id").val();
	data.name = $("#edit-"+name+"-form").find(".name").val();
	//权限
	data.roleManagement =  $("#edit-"+name+"-form").find(".roleManagement").prop("checked");
	data.addRole =  $("#edit-"+name+"-form").find(".addRole").prop("checked");
	data.delRole =  $("#edit-"+name+"-form").find(".delRole").prop("checked");
	data.showRole =  $("#edit-"+name+"-form").find(".showRole").prop("checked");
	data.editRole =  $("#edit-"+name+"-form").find(".editRole").prop("checked");
	//用户
	data.userManagement =  $("#edit-"+name+"-form").find(".userManagement").prop("checked");
	data.addUser =  $("#edit-"+name+"-form").find(".addUser").prop("checked");
	data.delUser =  $("#edit-"+name+"-form").find(".delUser").prop("checked");
	data.showUser =  $("#edit-"+name+"-form").find(".showUser").prop("checked");
	data.editUser =  $("#edit-"+name+"-form").find(".editUser").prop("checked");
	//线路
	data.lineManagement =  $("#edit-"+name+"-form").find(".lineManagement").prop("checked");
	data.addLine =  $("#edit-"+name+"-form").find(".addLine").prop("checked");
	data.delLine =  $("#edit-"+name+"-form").find(".delLine").prop("checked");
	data.showLine =  $("#edit-"+name+"-form").find(".showLine").prop("checked");
	data.editLine =  $("#edit-"+name+"-form").find(".editLine").prop("checked");
	//监控
	data.lineControl =  $("#edit-"+name+"-form").find(".lineControl").prop("checked");
	//路口
	data.crossManagement =  $("#edit-"+name+"-form").find(".crossManagement").prop("checked");
	data.editCross =  $("#edit-"+name+"-form").find(".editCross").prop("checked");
	//任务
	data.taskManagement =  $("#edit-"+name+"-form").find(".taskManagement").prop("checked");
	data.addTask =  $("#edit-"+name+"-form").find(".addTask").prop("checked");
	data.delTask =  $("#edit-"+name+"-form").find(".delTask").prop("checked");
	data.showTask =  $("#edit-"+name+"-form").find(".showTask").prop("checked");
	data.editTask =  $("#edit-"+name+"-form").find(".editTask").prop("checked");
	//代码
	data.tasktypeManagement =  $("#edit-"+name+"-form").find(".tasktypeManagement").prop("checked");
	//配置
	data.configureManagement =  $("#edit-"+name+"-form").find(".configureManagement").prop("checked");
	//设备
	data.equipmentManagement =  $("#edit-"+name+"-form").find(".equipmentManagement").prop("checked");
	data.addEquipment =  $("#edit-"+name+"-form").find(".addEquipment").prop("checked");
	data.delEquipment =  $("#edit-"+name+"-form").find(".delEquipment").prop("checked");
	data.showEquipment =  $("#edit-"+name+"-form").find(".showEquipment").prop("checked");
	data.editEquipment =  $("#edit-"+name+"-form").find(".editEquipment").prop("checked");
	//日志
	data.logsManagement =  $("#edit-"+name+"-form").find(".logsManagement").prop("checked");
	data.logsstatisticsManagement =  $("#edit-"+name+"-form").find(".logsstatisticsManagement").prop("checked");
	
	//辅助
	data.linkManagement = false;
	data.equipmentsetManagement =  false;
	data.testdemoManagement=false;
	data.crosstsetManagement=false;
	return data;
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
				window.parent.baseSetting.getModule("RoleManagement").module.requestData(1);
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
				window.parent.baseSetting.getModule("RoleManagement").module.requestData(1);
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