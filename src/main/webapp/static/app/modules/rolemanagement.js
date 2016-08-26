(function($, exports) {

	var PopuWndHeight = 483;
	var PopuWndWidth = 710;
	var PopuWndURL = "editrole";
	var PopuWndTitle = "角色权限";
	var isFind=false;//是否进行模糊查询
	var findData=null;//模糊查询条件保存
	
	var module = new exports.Module(exports.baseSetting.getModule("RoleManagement"));
	var addRole = (exports.baseSetting.user.role.addRole ); //添加单个具体信息的权限，用于构建页面显示的HTML
	var delRole = (exports.baseSetting.user.role.delRole ); //删除权限，用于构建页面显示的HTML 
	var showRole = (exports.baseSetting.user.role.showRole ); //查看单个具体信息的权限，用于构建页面显示的HTML
	var editRole = (exports.baseSetting.user.role.editRole ); //编辑单个具体信息的权限，用于构建页面显示的HTML
	module.$content = module.item.con.$content;
	module.showData = function(data) {
		var html = "";
		for ( var i = 0; i < data.length; i++) {
			html += this.buildTr(data[i], this.isSingle(i), i);
		}
		this.item.con.$content.find(".item-result-show tbody").html(html);this.tbodyTrHover();

	};
	
	module.buildTr = function(data, isSingle, index) {
		return "<tr indextab='" + index + "' class='"
				+ (isSingle == true ? "single" : "double") + "'><td>"
				+"<label class='"+(showRole == true ? "show-one-role show-entity": "")+"' alt='"+ data.id
				+ "'>" + (data.name || "") 
				+ "</label></td><td>" + ((data.lineManagement || false)?"是":"否") 
				+ "</td><td>" + ((data.crossManagement || false)?"是":"否")  
				+ "</td><td>" + ((data.roleManagement || false)?"是":"否")  
				+ "</td><td>" + ((data.userManagement || false)?"是":"否")  
				+ "</td><td>"
				+(editRole == true ? " <input class='modify' type='button'  alt='"+ data.id + "'>": "")
				+(delRole == true ? " <input class='delete' type='button'  alt='"+ data.id+ "'>": "")
				+"</td></tr>";
	};//<td><input type='checkbox' class='checkboxId' alt='"+ data.id + "'></td>
	module.isSingle = function(i) {
		return i % 2 == 0 ? true : false;
	};

	module.initRefresh = function() {
		this.item.con.$content.find(".refresh").click($.proxy(function() {
			isFind=false;
			findData=null;
			this.requestData(1);
			this.$content.find(".sch-name").val("");
//			alert("刷新完成");
		}, this));
	};
	
	module.buttonUser = function() {
		this.item.con.$content.find(".item-result-operate-left").click($.proxy(function(e) {
			var $target = $(e.target || e.srcElement);var alt = $target.attr("class");
			//var str = "";
			if (alt == "delete-operate") {
			/*	if(confirm("是否删除")){
				$(".checkboxId").each(function() {
					if ($(this).is(':checked')) {
						str += $(this).attr("alt") + " ,";
					}
				});
				this.deleteByIdList(this.deleteLastChar(str, ","));
				}*/
			} else if (alt == "edit") {
				
			}
		}, this));
		
	};
    
	module.initOperate = function() {
		this.item.con.$content.find("tbody").click($.proxy(function(e) {
			var $target = $(e.target || e.srcElement);
			if ($target.attr("class") == "delete") {
				  if(confirm("是否删除")){
				    	this.deleteOne($target);
				      }
			} else if ($target.attr("class") == "modify") {
				this.fidnOneByid($target);
			}else if ($target.attr("class") == "show-one-role show-entity") {
				this.showOne($target);
			}
		}, this));
	};

	module.initPageButton = function() {
		module.pageButton = new PageButton(this.item.con.$content.find("#item-result-pages"), 1, 1, 5);
		this.pageButton.init();
		this.pageButton.click($.proxy(function(page) {
			module.requestData(page);
		}), this);
	};
	module.findByPropertys = function(page,pdata){
		var data = {};
		if(pdata){
			data=pdata;//前一次模糊查询的数据获取
			data.page = (page || this.pageButton.page || 1);
		}else{//新建模糊查询数据
		data.page = (page || this.pageButton.page || 1);
		data.name = $.trim(this.$content.find(".sch-name").val());
		findData=data;
	    }
		var returnDatr ={};
		
		$.ajax({
			type : "POST",
			url : this.item.url+"/findByPropertys",
			dataType : "json",
			data : $.toJSON(data),
			async:false, 
			contentType : 'application/json;charset=UTF-8',
			success : $.proxy(function(obj) {
				if (obj.success) {
					/*this.showData(obj.data);
					this.pageButton.setButton(obj.page, obj.totalPages, 6);*/
					returnDatr = obj;
					this.defaultAllSelect();
				} else {
					alert("请求失败！");
				}
			}, this),
			error : function(msg) {
    			alert("由于网络或服务器问题，暂时无法获取数据！\n若一直出现这个提示，请联系管理员！\nusermanagement/findByPropertys");
    		}
		});
		return returnDatr;
	};
	
	//任意属性查询按钮点击事件
	module.schBtnClick = function(){
		this.$content.find(".sch-p-role").click($.proxy(function(){
			isFind=true;
			findData=null;
			var obj=this.findByPropertys(1);
			this.showData(obj.data);// 数据显示
			this.pageButton.setButton(obj.page, obj.totalPages, 5);
		}, this));
	};
	
	
	

	module.init = function() {
		this.initEditTable();
		this.initRefresh();
		this.initOperate();
		this.initPageButton();
		this.requestData(1);
		this.doAllSelect();
		this.buttonUser();
		this.hidMap();
		this.schBtnClick();
	};
	
	module.deleteByIdList = function(idList) {
		module.doDeleteByIdList(idList);
		this.requestData(1);
	};
	
	module.requestData = function(page){
		var data = {};
		if(isFind==false)
			data = this.searchAll(page);
		else{
			data = this.findByPropertys(page,findData);
		}
		 this.showData(data.data);
		this.pageButton.setButton(data.page, data.totalPages, 5);
	};
	
	module.refeshMe = function(){
		isFind=false;
		findData=null;
		this.requestData(1);
	};

	module.deleteData = function(id) {
		this.doDeletes(id);
		this.requestData(1);
	};
	
	module.deleteOne = function($target) {
		this.deleteData($target.attr('alt'));
	};

	module.fidnOneByid = function($target) {
		var _wnd = new PopuWnd({
			title : "修改" + PopuWndTitle,
			url : PopuWndURL,
			width : PopuWndWidth,
			height : 550,
			id : $target.attr("alt"),
			alt : "edit"
		});
		_wnd.init(true);
	};

	module.showOne = function($target) {
		var _wnd = new PopuWnd({
			title : "查看" + PopuWndTitle,
			url : PopuWndURL+"/showRole",
			width : PopuWndWidth,
			height : 560,
			id : $target.attr("alt"),
			alt : "show"
		});
		_wnd.init(true);
	};
	
	module.initEditTable = function() {
		var _wnd = new PopuWnd({
			title : "新增" + PopuWndTitle,
			url : PopuWndURL,
			width : PopuWndWidth,
			height : 550
		});
		this.item.con.$content.find(".add-role").click(function() {
			_wnd.init(true);
		});
	};

	module.init();
	
	exports.baseSetting.getModule("RoleManagement").module = module;

})(jQuery, window);