(function($, exports) {
	var btn = false;
	//更换验证码
	changeValidateCode = function (obj){
		var timeNow = new Date().getTime();
		obj.src="verifycode?time="+timeNow;
	};
	exports.theKep = (exports.theKep || 1);
	//点击提交按钮
	submit = function(){
		$(".submit_btn").click(function(){
			btn = true;
			//检测空值
			var theRt = checkEpByArr([{eryAlt:"用户名",calssName:"username"}
			,{eryAlt:"密码",calssName:"password"}
			,{eryAlt:"验证码",calssName:"verifycode"}]);
			if(theRt){
				if (btn) {
					$.ajax({
						type : "POST",
						url : "?" +$(".loginForm").serialize(),
						dataType : "text",
						success : function(html) {
//							alert(window.location.pathname);
//							alert(window.location.href);
							window.location.href = ""+window.location.pathname.replace("/login","");
							btn = false;
						},
						error : function(xmlHttp) {
							btn = false;
							alert(xmlHttp.responseText);
						}
					});
				}else{
					alert("操作中，请等待...");
				}
				
			};
	});
	};
	
	//监听的键盘事件
	kepUp = function(){
		$(document).keydown(function(event){
			if(event.keyCode == 13 && theKep == 1){
				//检测空值
				btn = true;
				theKep = 0;
				var theRt = checkEpByArr([{eryAlt:"用户名",calssName:"username"}
				,{eryAlt:"密码",calssName:"password"}
				,{eryAlt:"验证码",calssName:"verifycode"}]);
				if(theRt){
					if (btn) {
						$.ajax({
							type : "POST",
							url : "?" +$(".loginForm").serialize(),
							dataType : "text",
							success : function(html) {
								window.location.href = ""+window.location.pathname.replace("/login","");
								btn = false;
							},
							error : function(xmlHttp) {
								btn = false;
								alert(xmlHttp.responseText);
							}
						});
					}else{
						alert("操作中，请等待...");
					}
				};
		
			}
		}); 
	};
	
	//进入页面时候的默认事件
	init = function(){
		kepUp();
		submit();
	};
	
	init();
})(jQuery, window);