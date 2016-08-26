(function($, exports) {

	var PopuWndHeight = 418;
	var PopuWndWidth = 770;
	var PopuWndURL = "edituser";
	var PopuWndTitle = "用户";
	var isFind=false;//是否进行模糊查询
	var findData=null;//模糊查询条件保存
	
	var module = new exports.Module(exports.baseSetting.getModule("UserManagement"));
	var addUser = (exports.baseSetting.user.role.addUser ); //添加单个具体信息的权限，用于构建页面显示的HTML
	var delUser = (exports.baseSetting.user.role.delUser ); //删除权限，用于构建页面显示的HTML 
	var showUser = (exports.baseSetting.user.role.showUser ); //查看单个具体信息的权限，用于构建页面显示的HTML
	var editUser = (exports.baseSetting.user.role.editUser ); //编辑单个具体信息的权限，用于构建页面显示的HTML
	
	module.$content = module.item.con.$content;
	module.showData = function(data) {
		var html = "";
		for ( var i = 0; i < data.length; i++) {
			html += this.buildTr(data[i], this.isSingle(i), i);
		}
		this.item.con.$content.find(".item-result-show tbody").html(html);this.tbodyTrHover();
	};
	module.buildTr = function(data, isSingle, index) {
		return "<tr indextab='"
				+ index
				+ "' class='"
				+ (isSingle == true ? "single" : "double")
				+ "'><td>"
				+ (index+1)
				+ "</td><td><label class='"+(showUser == true ? "show-one-user show-entity": "")+"' alt='"+ data.id+ "'>"
				+ (data.loginName || "")
				+ "</label><td>"
				+ (data.role ? data.role.name : "")
				+ "</td><td>"
				+(editUser == true ? " <input class='modify' type='button' alt='"+ data.id + "'> ": "")
				+(data.id!=1? (delUser == true ? "<input class='delete' type='button' alt='"+ data.id + "'>": ""):"")
				+" </td></tr>";
	};//<td><input type='checkbox' class='checkboxId' alt='"+ data.id + "'></td>
	module.isSingle = function(i) {
		return i % 2 == 0 ? true : false;
	};

	module.initOperate = function() {
		this.item.con.$content.find("tbody").click($.proxy(function(e) {
			var $target = $(e.target || e.srcElement);
			if ($target.attr("class") == "delete") {
			    if(confirm("是否删除？")){
			    	this.deleteOne($target);
			      }
			}else if($target.attr("class") == "modify"){
				this.fidnOneByid($target);
			}else if($target.attr("class") == "show-one-user show-entity"){
				this.showOne($target);
			}
		}, this));
	};

	module.deleteOne = function($target) {
		/*var _wnd = new PopuWnd({title:"删除"+PopuWndTitle,url:PopuWndURL,width:PopuWndWidth,height:187,id:$target.attr("alt"),alt:"delete"});
		_wnd.init(true);*/
		this.doDeletes($target.attr("alt"));
		
	};
	
	module.showOne = function($target) {
		var _wnd = new PopuWnd({title:"查看"+PopuWndTitle,url:PopuWndURL+"/showUser?id="+$target.attr("alt"),width:750,height:316,id:$target.attr("alt"),alt:"show"});
		_wnd.init(true);
	};

	module.fidnOneByid = function($target) {
		var _wnd = new PopuWnd({title:"修改"+PopuWndTitle,url:PopuWndURL,width:PopuWndWidth,height:PopuWndHeight,id:$target.attr("alt"),alt:"edit"});
		_wnd.init(true);
	};
	
	module.initEditUser = function() {
		var _wnd = new PopuWnd({title:"新增"+PopuWndTitle,url:PopuWndURL,width:PopuWndWidth,height:PopuWndHeight,alt:"add"});
		this.item.con.$content.find(".add-user").click(function() {
			_wnd.init(true);
		});
	};

	module.delUser = function(data) {
		
			$.ajax({
				type : "POST",
				url : this.item.url+"/deluser",
				data : $.toJSON(data),
				dataType : "json",
				contentType : 'application/json;charset=UTF-8',
				success : $.proxy(function(obj) {
					if (obj.success) {
						alert("删除成功！");
					} else {
						alert("删除失败！\n用户名或真实姓名不正确");
					}
				}, this),
				error : function(msg) {
					alert("由于网络或服务器问题，暂时无法进行操作！\n若一直出现这个提示，请联系管理员！\n"
							+ this.item.url);
				}
			});
	};
	
	module.initRefresh = function() {
		this.item.con.$content.find(".refresh").click($.proxy(function(){
			isFind=false;
			findData=null;
			this.requestData(1);
			$(".sch-role").val("");
			$(".sch-loginName").val("");
			$(".sch-realName").val("");
//			alert("刷新完成");
		}, this));
		
	};
	module.initPageButton = function() {
		module.pageButton = new PageButton(this.item.con.$content.find("#item-result-pages"), 1, 1, 6);
		this.pageButton.init();
		this.pageButton.click($.proxy(function(page) {
			module.requestData(page);
		}), this);
	};
	
	module.pageButtonClick = function(data){
		this.pageButton.click($.proxy(function(page) {
		module.findByPropertys(page);
		}),this);
	};
	
	module.findByPropertys = function(page,pdata){
		var data = {};
		var role = {};
		
		if(pdata){
			data=pdata;//前一次模糊查询的数据获取
			data.page = (page || this.pageButton.page || 1);
		}else{//新建模糊查询数据
			if($.trim(this.$content.find(".sch-role").val())){
				role.name = $.trim(this.$content.find(".sch-role").val());
				data.role = role;
			}
			data.page = (page || this.pageButton.page ||1);
			data.loginName = $.trim(this.$content.find(".sch-loginName").val()||'');
			data.realName = $.trim(this.$content.find(".sch-realName").val()||'');
			findData=data;
	}
		var returnDatr ={};	
		$.ajax({
			type : "POST",
			url : "usermanagement/findByPropertys",
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
		this.$content.find(".sch-p-user").click($.proxy(function() {
			isFind=true;
			findData=null;
			var obj=this.findByPropertys(1);
			this.showData(obj.data);// 数据显示
			this.pageButton.setButton(obj.page, obj.totalPages, 5);
		}, this));
	};
	
	
	
	module.init = function() {
		this.initEditUser();
		this.initRefresh();
		this.initOperate();
		this.initPageButton();
		this.requestData(1);
		this.doAllSelect();
		this.hidMap();
		this.schBtnClick();
	};
	
	module.requestData = function(page){
		var data = {};
		if(isFind==false)
			data = this.searchAll(page);
		else{
			data = this.findByPropertys(page,findData);
		}
		 this.showData(data.data);
		this.pageButton.setButton(data.page, data.totalPages, 6);
		
	};
	
	
	module.refeshMe = function(){
		isFind=false;
		findData=null;
		this.requestData(1);
	};

	module.init();
	
	exports.baseSetting.getModule("UserManagement").module = module;
})(jQuery, window);
