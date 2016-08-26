var name = "track";
var theUrl = "edittrack";

$(document).ready(function() {
	var myAlidation = new checkIframe("edit-track-form",{});//初始化数据监测
	//初始化数据框
	var iframeAction = new AjaxIframe({moduleName:"track", formClassName:"iframe-form" });
	
	//myAlidation.init();
	//默认方法
	iframeAction.init();
	iframeAction.findListAction("demo");
	$(".save-edit").click(function() {
		if(checkDate()){
			if (iframeAction.action == "edit") {
				//这里的路径位置不同，所有路径前不用加其他的前缀
				iframeAction.updateData($(".iframe-form").serializeJson(),(theUrl+"/update"));
			}else if (iframeAction.action == "add") {
				iframeAction.saveData($(".iframe-form").serializeJson(),(theUrl+"/newone"));
			}
		}
	});
	//数据监测
	checkDate = function(){
		//数据监测，监测那些利用类名称来判断监测的输入框等
		myAlidation.postCheck();
		var rt = true;
		rt = myAlidation.result == true? rt : false ;
		return rt;
	};
	

});