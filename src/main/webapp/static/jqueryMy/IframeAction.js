(function($, exports) {
	 exports.AjaxIframe = function(baseDate){
		 this.ModuleName = baseDate.moduleName;//Module控制
		 this.$form = $("."+baseDate.formClassName);
		 //行为
		 this.action = $(window.parent.document).find("iframe[class='iframe-main']").attr("alt");
		 //获取数据的RUL
		 //this.findUrl = baseDate.findUrl;
		 //查询数据用的ID
		 this.id = $(window.parent.document).find("iframe[class='iframe-main']").attr("id");
		 this._this = this;
//		 this.saveCheckfunction = true;
		
		//默认的加载方法
		this.init = function(){
			this.cancel();
		};
		//取消按钮事件
		this.cancel = function(){
			$(".cancel-edit").click(function() {
				window.parent.PopuWnd.close();
			});
		};

		//新增方法
		this.saveData = function(data,url){
				this.baseAction(data, url, "添加");
		};
		 //更新方法
		this.updateData = function(data,url){
			this.baseAction(data, url, "更新");
		};
		//关闭窗口
		this.closeIframe = function(){
			window.parent.Modules.getModle(this.ModuleName).requestData(1);
			window.parent.PopuWnd.close();
		};
		
		//基础方法
		 this.baseAction = function(data, url, title) {
			$.ajax({
				type : "POST",
				url : url,
				data : $.toJSON(data),
				dataType : "json",
				contentType : 'application/json;charset=UTF-8',
				success : $.proxy(function(obj) {
					if (obj.success) {
						alert(title+"成功！");
						this.closeIframe();
					} else {
						alert(title+"失败！");
						this.closeIframe();
					}
				}, this),
				error : function(msg) {
					alert(msg.success);
				}
			});
	 };
	 
	 this.findListAction = function(url) {
			$.ajax({
				type : "POST",
				url : url+"management/findList",
				dataType : "json",
				contentType : 'application/json;charset=UTF-8',
				success : $.proxy(function(obj) {
					if (obj.success) {
						var data = obj.data;
						var htm = "<option ></option>";
						for ( var int = 0; int < data.length; int++) {
							htm +="<option value='"+data[int].id+"'>"+data[int].name+"</option>";
						}
						$("."+url).append(htm);
					} else {
						alert(url+"失败！");
					}
				}, this),
				error : function(msg) {
					alert(msg.success);
				}
			});
	 };
};
})(jQuery, window);