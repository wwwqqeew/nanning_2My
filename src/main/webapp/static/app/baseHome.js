(function($, exports) {
	
		window.Modules = {};//装载所有对象
		
		Modules.modules = [];
		/**
		 * 返回module 实体
		 * key 唯一ID
		 */
		Modules.getModle = function(key){		
			for ( var i = 0; i < Modules.modules.length; i++) {
				if (key == Modules.modules[i].ModuleName) {
					return Modules.modules[i];
				}
			}
			return {};
		};
		
		/**
		 * 全局实体变量保存，方便到时候的调用
		 * key 唯一ID，一半为实体名
		 * module 实体
		 */
		Modules.setModle = function(key,module){
			var isFind = false;
			for ( var i = 0; i < Modules.modules.length; i++) {
				if (key == Modules.modules[i].ModuleName) {
					Modules.modules[i] = module;
					isFind = true;
					break;
				}
			}
			//队列里没有，从新加一个
			if (!isFind) {
				module.name = key;
				Modules.modules.push(module);
			}
		};
		
		/*公共方法 -- Start*/
		//获取外键下拉列表数据
		Modules.findListAction = function(url) {
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
		 /*公共方法 -- Start*/
	
		/**demoMainId：主DIV的id
		 * url :控制层地址
		 * tableId:表格ID
		 **/
		exports.Modle = function(demoMainId,url,editUrl,tableId,moduleName) {
			this.ModuleName = moduleName;//Module控制
			this.demoMainId = demoMainId;//主DIV的id
			this.url = url;//控制层地址
			this.tableId = tableId;//表格ID
			this.pageId = "item-result-pages";//分页插件框框的ID
			
			this.PopuWndTitle = "测试";//弹框标题
			this.PopuWndURL = editUrl;//弹出框URL
			this.PopuWndWidth = 600;//弹出框宽度
			this.PopuWndHeight = 400;//弹出框高度
			
			this.addBtn = "add-demo";
			this.demoMain = $("#"+this.demoMainId);//主DIV绑定，后边可以防止读取都别的页面数据
		};
		//初始化调用
		Modle.prototype.init = function() {
			this.initPageButton();//初始化分页按钮
			//this.search(this.url,1,this.tableId);
			this.requestData(1);//初始化数据
			this.doAllSelect();//全选事件
//			this.addOne(this.addBtn);//添加绑定，其实也可以写在this.buttonUser()里
			this.initOperate();//数据行里的按钮方法
			this.buttonUser();//按钮栏的事件绑定
			this.schBtnClick();//查询按钮绑定事件
			this.initImport(this.url,this.PopuWndWidth,this.PopuWndHeight);//绑定导入按钮
		};
		
		/*获取分页数据--Start*/
		Modle.prototype.requestData = function(page){
			var dateG = {};
			dateG = this.search(this.url,page,this.tableId);
			this.showTableDate(dateG.data,this.tableId);
			this.pageButton.setButton(dateG.page, dateG.totalPages, 5);
		};
		/*获取分页数据--End*/
		
		/*分页按钮事件--Start*/
		Modle.prototype.initPageButton = function() {
			var _this = this;
			this.pageButton = new PageButton(this.demoMain.find("#item-result-pages"), 1, 1, 5);
			this.pageButton.init();
			this.pageButton.click($.proxy(function(page) {
				_this.requestData(page);
			}), this);
		};
		/*分页按钮事件--End*/
		
		//获取数据
		Modle.prototype.search = function(url,page,tableId) {
			var data = {};
			data = this.demoMain.find(".FindData-form").serializeJson() ;
			data.page = (page || this.pageButton.page ||1);
			$.ajax({
				type : "POST",
				url : url+"/findByPropertys",
//				dataType : "json", //这里为了能够让后台使用泛型，所有不使用JSON
				async:false,
				data : "propertys="+$.toJSON(data),
//				contentType : 'application/json;charset=UTF-8',
//			$.ajax({
//				type : "POST",
//				url : url + "/search",
//				dataType : "json",
//				async:false, 
//				data : "page=" + page,
				success : $.proxy(function(obj1) {
//					alert($.toJSON(obj));
					var obj = {};
					obj = jQuery.parseJSON(obj1);
					if (obj.success) {
						returnDatr = obj;
						//this.showTableDate(returnDatr.data,tableId);
					} else {
						alert("请求失败！");
					}
				}, this),
				error : function(msg) {
					alert("由于网络或服务器问题，暂时无法获取列表数据！\n若一直出现这个提示，请联系管理员！\n"+this.item.url);
				}
			});
			this.defaultAllSelect();
			return forReturn(returnDatr);
		};
		//单纯用于返回数据
		forReturn = function( data){
			return data;
		};
		
		//显示数据
		Modle.prototype.showTableDate = function(date,tableId){
			var htm = "";
			htm = this.buildHtmlBody(date);
			this.demoMain.find("#"+tableId).find("tbody").empty();
			this.demoMain.find("#"+tableId).find("tbody").append(htm);
		};
		
		//设定全选为没选中
		Modle.prototype.defaultAllSelect = function(){
			this.demoMain.find(".all-select").prop("checked", false);
	    };
	   // 多选框全选事件
	    Modle.prototype.doAllSelect = function(){
	    	var _this = this;
	    	this.demoMain.find(".all-select").change(function() {
				var thec = $(this).is(":checked");
				this.demoMain.find(".checkboxId").each(function() {
					$(this).prop("checked", thec);	
				});
			});	
	    };
	    
		//显示数据(需要自己构建)
		Modle.prototype.buildHtmlBody = function(date){

		};
		
		/*表格的添加、查看、修改、删除、导出Excel、方法Start*/
		Modle.prototype.initOperate = function() {
			this.demoMain.find("tbody").click($.proxy(function(e) {
				var $target = $(e.target || e.srcElement);
				if ($target.attr("class") == "delete") {
					  if(confirm("是否删除")){
					    	this.deleteOne($target.parentsUntil("tr").parent());
					      }
				} else if ($target.attr("class") == "modify") {
					this.editOne($target.parentsUntil("tr").parent());
				}else if ($target.attr("class") == "show-entity") {
					this.showOne($target.parent());
				}
			}, this));
		};
		//导入初始化
		Modle.prototype.initImport = function(url,PopuWndWidth,PopuWndHeight){
			var _this = this;
			this.demoMain.find(".imPort-Excel").click(function(){
				var _wnd = new window.PopuWnd({alt:_this.ModuleName,title:"标题",url:url+"/importShow",width:PopuWndWidth,height:PopuWndHeight});
				_wnd.init(true);
			});
		};
		//到处Excel初始化
		Modle.prototype.initExcel = function(data){
			var _this = this;
			this.excelFrom = {};
			this.excelFrom = new excelFrom(data);
			this.excelFrom.init();
			//点击按钮事件
			this.demoMain.find(".export-Excel").click(function() {
				_this.exportExcel();
			});
		};
		//显示Excel窗口
		Modle.prototype.exportExcel = function(){
				this.excelFrom.show();
		};

		Modle.prototype.editOne = function($target) {
			var _wnd = new PopuWnd({
				title : "修改" + this.PopuWndTitle,
				url : this.PopuWndURL+"/findOneById?id="+$target.attr("id"),
				width : this.PopuWndWidth,
				height : this.PopuWndHeight,
				id : $target.attr("id"),
				alt : "edit"
			});
			_wnd.init(true);
		};
		
		Modle.prototype.showOne = function($target) {
			var _wnd = new PopuWnd({
				title : "查看" + this.PopuWndTitle,
				url : this.PopuWndURL+"/show?id="+$target.attr("id"),
				width : this.PopuWndWidth,
				height : this.PopuWndHeight,
				id : $target.attr("id"),
				alt : "show"
			});
			_wnd.init(true);
		};

		Modle.prototype.addOne = function($target) {
			var _wnd = new PopuWnd({
				title : "新增" + this.PopuWndTitle,
				url : this.PopuWndURL+"/showAdd",
				width : this.PopuWndWidth,
				height : this.PopuWndHeight
			});
			_wnd.init(true);
		};
		
		Modle.prototype.deleteOne = function($target) {
			this.deleteData($target.attr("id"));
		};
		
		Modle.prototype.deleteData = function(id) {
			this.doDeletes(this.url,id);
//			this.requestData(1);
		};
		
		Modle.prototype.deletes = function() {
			this.doDeletes(this.url,this.getDeletesArg());
		};
		
		Modle.prototype.doDeletes = function(url,id) {
			$.ajax({
				type : "POST",
				url : url + "/delete",
				async:false, 
				dataType : "json",
				data : "id=" + id,
				success : $.proxy(function(obj) {
					if (obj.success) {
//						alert("删除成功！");
						this.requestData(1);
					} else {
						if(obj.data)
							   alert(obj.data);
							else 
								 alert("请求失败！");
					}
				}, this),
				error : function(msg) {
					alert("由于网络或服务器问题，暂时无法删除数据！\n若一直出现这个提示，请联系管理员！\n"+this.item.url);
				}
			});
		
		};
		
		Modle.prototype.doDeleteByIdList = function(url,idList) {
			$.ajax({
				type : "POST",
				url : url + "/deleteByIdList",
				async:false, 
				dataType : "json",
				data : "idList=" + idList,
				success : $.proxy(function(obj) {
					if (obj.success) {
						alert("批量删除成功！");
						this.requestData(1);
					} else {
						alert("批量删除失败！");
					}
				}, this),
				error : function(msg) {
					alert("批量删除过程中遇到网络或服务器问题。\n如果一直出现该提示请联系管理员！\n"+this.item.url + "/deleteByIdList");
				}
			});
		};
		
		/*按钮栏的批量删除 、修改、查看 -- Start*/
		Modle.prototype.buttonUser = function() {
			this.demoMain.find(".item-btn").click($.proxy(function(e) {
				var $target = $(e.target || e.srcElement);
				var alt = $target.attr("class");
				var str = "";
				if (alt.indexOf("delete-operate") >= 0) {
					//删除按钮
					this.demoMain.find(".checkboxId").each(function() {
						if ($(this).is(':checked')) {
							str += $(this).parentsUntil("tr").parent().attr("id") + " ,";
						}
					});
					if (str == null || str == "") {
						alert("请先选择至少一条数据");
					} else {
						if(confirm("是否删除")){
							this.deleteByIdList(this.deleteLastChar(str, ","));
						}
					}
				} else if (alt.indexOf("edit-operate") >= 0) {
					//按钮栏修改
					var $ptr = {};
					this.demoMain.find(".checkboxId").each(function() {
						if ($(this).is(':checked')) {
							str += $(this).parentsUntil("tr").parent().attr("id") + " ,";
							 $ptr = $(this).parentsUntil("tr").parent();
						}
					});
					str = this.deleteLastChar(str, ",");
					if (str.indexOf(",") >= 0) {
						alert("多条数据无法同时进行修改");
					} else if(str == null || str == ""){
						alert("请先选择至少一条数据");
					}  else {
						this.editOne($ptr);
					}
				} else if (alt.indexOf("show-operate") >= 0) {
					//按钮栏查看
					var $ptr = {};
					this.demoMain.find(".checkboxId").each(function() {
						if ($(this).is(':checked')) {
							str += $(this).parentsUntil("tr").parent().attr("id") + " ,";
							 $ptr = $(this).parentsUntil("tr").parent();
						}
					});
					str = this.deleteLastChar(str, ",");
					if (str.indexOf(",") >= 0) {
						alert("多条数据无法同时进行查看");
					} else if(str == null || str == ""){
						alert("请先选择至少一条数据");
					} else {
						this.showOne($ptr);
					}
				}else if(alt.indexOf("add-demo") >= 0){
					var $ptr = {};
					this.addOne($ptr);
				}
			}, this));
		};
		/*按钮栏的批量删除、修改、查看  -- End*/
		
		Modle.prototype.deleteLastChar = function(str, c) {  
	        var reg = new RegExp(c + "([^" + c + "]*?)$");  
	        return str.replace(reg, function(w) {  
	            if (w.length > 1) {  
	                return w.substring(1);  
	            } else {  
	                return "";  
	            }  
	        });  
	    };  
	    
		/*批量删除--Start*/
	    Modle.prototype.deleteByIdList = function(idList) {
			this.doDeleteByIdList(this.url,idList);
			//this.requestData(1);
		};
		/*批量删除--End*/
		/*表格的添加、查看、修改、删除、导出Excel、方法End*/
		
		/*模糊查询 -- Satart*/
		Modle.prototype.schBtnClick = function(){
			var _this = this;
			this.demoMain.find(".search-btn").click(function(){
				_this.findByPropertys(1,_this.url);
			});
		};

		Modle.prototype.findByPropertys = function(page,url){
		
			var data = {};
			data = this.demoMain.find(".FindData-form").serializeJson() ;
			data.page = (page || this.pageButton.page ||1);
			$.ajax({
				type : "POST",
				url : url+"/findByPropertys",
//				dataType : "json", //这里为了能够让后台使用泛型，所有不使用JSON
				data : "propertys="+$.toJSON(data),
//				contentType : 'application/json;charset=UTF-8',
				success : $.proxy(function(obj1) {
					var obj = {};
					obj = jQuery.parseJSON(obj1);
					if (obj.success) {
						this.showTableDate(obj.data,this.tableId);
						this.pageButton.setButton(obj.page, obj.totalPages, 6);
					} else {
						alert("请求失败！");
					}
				}, this),
				error : function(msg) {
	    			alert("由于网络或服务器问题，暂时无法获取数据！\n若一直出现这个提示，请联系管理员！\n testmanagement/findByPropertys");
	    		}
			});
		};
		/*模糊查询 -- End*/
		

	})(jQuery, window);
