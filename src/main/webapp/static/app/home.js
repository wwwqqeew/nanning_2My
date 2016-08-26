(function($, exports) {
	/**
	 * 加载一系列页面的东西（弹出式页面除外）
	 */
	var BaseSetting = function() {

		/**
		 * 内容框
		 */
		var ContentBase = {

			item : {},

			/**
			 * 装载页面等
			 */
			$content : {},
			
			/**
			 * 页面内容
			 */
			initContentData : function() {
				this.$content = $("<div class='item-content item-content-hidden'>" + $(".item-content-template").html()
						+ "</div>");
			},
			
			/**
			 * 隐藏地图
			 * （暂时没用到）
			 */
			activateAdd : function() {
				$("#map").addClass("hiddentMap");
			},

			//获取左边的下拉按钮
			requestContent : function() {
				$.ajax({
					type : "POST",
					url : this.item.url,
					dataType : "html",
					success : $.proxy(function(html) {
//						alert(this.item.url);
						this.$content.html(html);
						
					}, this),
					error : $.proxy(function(html) {
						setTimeout($.proxy(function() {
							this.requestContent();
						}, this), 5000);
					}, this)
				});
			},
			
			/**
			 * 页面内容加载到容器
			 */
			appendContentTo : function() {
				$(".right .main-content-container").append(this.$content);
			},
			
			/**
			 * 显示容器里的内容（点击左边的管理模块，之后执行）
			 */
			showContent : function() {
				this.$content.removeClass("item-content-hidden");
				this.showModuleMap();
				this.getDateinit();
			},
			
			defalutSet : function(){
			},
			/**
			 *   隐藏容器里的内容（点击左边的管理模块，之前执行）
			 */
			hiddenContent : function() {
				this.$content.addClass("item-content-hidden");
				this.hiddenModuleMap();
			},
			/**
			 * 显示地图
			 * 需要重载
			 */
			showModuleMap : function(){
			},
			/**
			 * 隐藏地图
			 * 需要重载
			 */
			hiddenModuleMap : function(){
			},
			/**
			 * 进入页面时候加载数据
			 * 需要重载
			 */
			getDateinit : function(){
			},
			/**
			 * 开始的时候默认执行的
			 */
			initContent : function() {
				this.initContentData();//获取页面
				this.appendContentTo();//内容加载到右边
				//this.requestContent();//获取数据
			},

			/**
			 * （要进入一个页面前执行）
			 */
			
			/* need to over */
			activate : function() {
				this.showContent();
			},
			
			/**
			 * （要离开一个页面前执行）
			 */
			deactivate : function() {
				this.hiddenContent();
			},

			init : function() {
				initContent();
			}

		};

		/**
		 * 左边栏的查找模块相应的按钮（暂时没用到）
		 */
		var SearchBase = {
			item : {},
			
			activateAdd : function() {
				$("#map").addClass("hiddentMap");
			},

			showUserSearch : function() {
				$(".user-search-nav").removeClass("search-nav-hidden");
			},

			hiddenUserSearch : function() {
				//if(this.roleCheck("OrderManagement")){
					$(".user-search-nav").addClass("search-nav-hidden");
				//}
			},
			
			showOrderSearch : function() {
				$(".order-search-nav").removeClass("search-nav-hidden");
				_MapMaplet.cleanAllObject(); //删除图标等加上去的东西
			},

			hiddenOrderSearch : function() {
				//if(this.roleCheck("OrderManagement")){
					$(".order-search-nav").addClass("search-nav-hidden");
				//}
			},

			showMenuSearch : function() {
				$(".menu-search-nav").removeClass("search-nav-hidden");
			},

			hiddenMenuSearch : function() {
				//if(this.roleCheck("FoodManagement")){
					$(".menu-search-nav").addClass("search-nav-hidden");
			//	}
			},
			//查找用，暂时没用到
			showSearch : function() {
				this.hiddenUserSearch();
				this.hiddenOrderSearch();
				this.hiddenMenuSearch();
				switch (this.item.search) {
				case "user":
					this.showUserSearch();
					break;
				case "order":
					this.showOrderSearch();
					break;
				case "menu":
					this.showMenuSearch();
					break;
				default:
					break;
				}
			},

			hiddenSearch : function() {

			},

			initSearch : function() {
			},

			activate : function() {
				this.showSearch();
			},

			deactivate : function() {
				this.hiddenSearch();
			},

			init : function() {
				this.initSearch();
			}

		};

		/**
		 * 左边栏的按钮加载
		 */
		var NavBase = {

			item : {},

			$nav : {},
			/**
			 * 管理模块按钮
			 */
			initNavData : function() {//就是这里operate-nav-item-container将标题添加到下的列表
				this.$nav = $("<div class='operate-nav-item'><div class='operate-nav-item-container'></div></div>");
				this.$nav.find(".operate-nav-item-container").text(this.item.title);
			},

			/**
			 * 点击左边的按钮执行的语句
			 */
			initNavClick : function() {
				this.$nav.click($.proxy(function() {
					this.active();
					(this.item.module ?(this.item.module.refeshMe() || 1):1);
					
				}, this));
			},
			
			/**
			 * 管理模块按钮栏，鼠标移动和移除按钮，执行的方法
			 */
			initNavHover : function() {
				//页面移动到栏目按钮上
				this.$nav.hover($.proxy(function() {
					this.$nav.addClass("operate-nav-item-hover");
				//页面移除栏目按钮
				}, this), $.proxy(function() {
					this.$nav.removeClass("operate-nav-item-hover");
				}, this));
			},
			
			/**
			 * 管理模块按钮 加到左边栏
			 */
			appendNavTo : function() {
				$(".navs .operate-nav-main").append(this.$nav);
			},

			/**
			 * 添加点击后的样式
			 */
			showNav : function() {
				this.$nav.addClass("operate-nav-item-active");
			},
			
			activateAdd : function() {
				$("#map").addClass("hiddentMap"); //默认跳转其他页面的时候隐藏地图
				_MapMaplet.cleanAllObject(); //删除图标等加上去的东西
			},
			/**
			 * 隐藏点击样式
			 */
			hiddenNav : function() {
				this.$nav.removeClass("operate-nav-item-active");
			},
			
			/**
			 * 开始的时候默认执行的
			 */
			initNav : function() {
				this.initNavData();
				this.initNavHover();
				this.initNavClick();
				this.appendNavTo();
			},
			/**
			 * （要进入一个页面前执行）
			 */
			activate : function() {
				this.showNav();
			},
			/**
			 * （要离开一个页面前执行）
			 */
			deactivate : function() {
				this.hiddenNav();
			},

			init : function() {
				initNav();
			}

		};

		var Item = function(item) {
			this.item = item;
			this.init();
		};

		$.extend(Item.prototype, ContentBase);
		$.extend(Item.prototype, SearchBase);
		$.extend(Item.prototype, NavBase);

		/**
		 * 每一个页面进入加载的时候执行的默认方法
		 */
		Item.prototype.init = function() {
			this.initNav();
			this.initContent();
			this.initSearch();
		};

		/**
		 * 页面跳转的时候执行的方法（要进入一个页面前执行）
		 */
		Item.prototype.activate = function() {
			this.showNav();
			this.showContent();
			this.showSearch();
		};

		/**
		 * 页面跳转的时候执行的方法（要离开一个页面前执行）
		 */
		Item.prototype.deactivate = function() {
			this.hiddenNav();
			this.hiddenContent();
			this.hiddenSearch();
		};

		/**
		 * 帮助链接相关的东西
		 */
		var Help = function(item) {
			this.item = item;
			this.init();
		};

		$.extend(Help.prototype, ContentBase);

		Help.prototype.init = function(item) {
			this.item = item;
			this.init();
		};

		Help.prototype.initSelf = function() {
			$(".header .header-help").click($.proxy(function() {
				this.active();
			}, this));
			
			$(".header .system-set").click($.proxy(function() {
				exports.baseSetting.getModule("SystemSetManagement").con.active();
			}, this));
			
		};

		Help.prototype.init = function() {
			this.initContent();
			this.initSelf();
		};

		Help.prototype.activate = function() {
			this.showContent();
		};

		Help.prototype.deactivate = function() {
			this.hiddenContent();
		};

		var SingleContent = function(item) {
			this.item = item;
			this.init();
		};

		$.extend(SingleContent.prototype, ContentBase);

		SingleContent.prototype.init = function(item) {
			this.item = item;
			this.init();
		};

		SingleContent.prototype.init = function() {
			this.initContent();
		};

		SingleContent.prototype.activate = function() {
			this.showContent();
		};

		SingleContent.prototype.deactivate = function() {
			this.hiddenContent();
		};

		var Events = {
			bind : function() {
				if (!this.o)
					this.o = $({});
				this.o.bind.apply(this.o, arguments);
			},
			//执行了，在按钮之后：基础的方法
			trigger : function() {
				if (!this.o)
					this.o = $({});
				this.o.trigger.apply(this.o, arguments);
			}
		};
		
		exports.StateMachine = function() {

			this.sLastItem;

			this.bind = function() {
				if (!this.o)
					this.o = $({});
				this.o.bind.apply(this.o, arguments);
			};

			this.trigger = function() {
				if (!this.o)
					this.o = $({});
				this.o.trigger.apply(this.o, arguments);
			};

			this.add = function(item) {
				this.bind("change", function(e, current) {
					if (item == current) {
						if (this.sLastItem) {
							this.sLastItem.deactivate();
						}
						item.activate();
						this.sLastItem = item;
					}
				});

				item.active = $.proxy(function() {
					item.requestContent();
					this.trigger("change", item);
				}, this);
				return item;
			};
		};
		
		this.modules = [];
		this.user = {};

		this.getModule = function(key) {
			for ( var i = 0; i < this.modules.length; i++) {
				if (key == this.modules[i].name) {
					return this.modules[i];
				}
			}
			return {};
		};

		//一次添加
		this.initNavbars = function() {
			var sm = new StateMachine();
			for ( var i = 0; i < this.modules.length; i++) {
				this.initNavbar(sm, i);
			}
		};

		//判断要添加的代码栏目
		this.initNavbar = function(sm, i) {
			switch (this.modules[i].type) {
			case "help":
				this.modules[i].con = new Help(this.modules[i]);
				break;
			case "nav":
//				alert($.toJSON(this.modules[i]));
				this.modules[i].con = new Item(this.modules[i]);
				break;
			case "other":
				this.modules[i].con = new SingleContent(this.modules[i]);
				break;
			default:
				break;
			}
			sm.add(this.modules[i].con);
		};

		this.initUserInfo = function() {
			$(".header .header-username").text(this.user.realName);
		};

		this.init = function() {
			this.requestUserData();
		};
		
		this.roleCheckFunction = function(){
			this.findByProperty();
			if($(".operate-nav-main").find("div[class*='oders-firsr']").length == 0 ){
				this.setSecondTitle();
			}
		};
		
		this.roleCheck = function(data){
			data = (data||"");
			var rt = false;
			if(data == ""){
				return false;
			}
			for(var i =0; i<exports.baseSetting.modules.length; i++){
				if(exports.baseSetting.modules[i].name == data){
					rt = true;
					break;
				};
			}
			return rt;
		};
		

		//添加带二级菜单的一级菜单项
		this.setSecondTitle = function(){
			var first = {};
			
			first = {};
			first.name = "用户管理";
			first.classTitle = "users";
			second = new Array("用户管理","权限管理");
			first.second = second;
			this.addTitle(first);
			
			first = {};
			first.name = "基础数据维护";
			first.classTitle = "bases";
			second = new Array("路口数据维护","代码维护");
			first.second = second;
			this.addTitle(first);
			
			first={};
			first.name="日志管理";
			first.classTitle="logs";
			second=new Array("日志查询","日志统计");
			first.second=second;
			this.addTitle(first);
		};
		
		//带二级菜单栏的一级菜单栏添加
		this.addTitle = function(data){
			if(data.before){
				$("div[class='operate-nav-item-container']").each(function(){
					if($(this).text().indexOf(data.before)>-1){ //如果已存在与要加入的一级菜单文本相同的菜单，则把一级菜单插到父类后
						$(this).parent().after("<div class='operate-nav-item'><div style='left:-15px;' class='operate-nav-item-container "+data.classTitle+"-firsr ' ><font class='item-sign'>+ </font>"+data.name+"</div></div>");
					};
				});
			}else{
				$(".operate-nav-main").append("<div class='operate-nav-item'><div style='left:-15px;' class='operate-nav-item-container "+data.classTitle+"-firsr ' ><font class='item-sign'>+ </font>"+data.name+"</div></div>");
			}
			//添加二级菜单
			$(".operate-nav-item").find("div[class='operate-nav-item-container']").each(function(){
				for(var i = 0 ; i<data.second.length ; i++){
					if($(this).text().indexOf(data.second[i])>-1){
						$(this).text("> "+data.second[i]);
						$(this).parent().addClass(data.classTitle+"-second");
						$(this).parent().addClass("hiddent").addClass("hiddent-border menu-sec");					
						$("."+data.classTitle+"-firsr").parent().after($(this).parent());
					};
				}
			});
			//点击事件,展开或者收缩菜单
			$("."+data.classTitle+"-firsr").parent().click(function(){
				if($(this).find(".new-hid").length == 0){//点击时候判断是收缩还是展开
					//放出前，先修改其他状态为“展开”的一级菜单为“”收缩，再把所有没有收缩的二级菜单收缩
					$(".operate-nav-item-container[class*='new-hid']").removeClass("new-hid").find(".item-sign").text("+ ");
					$(".menu-sec[class!='hiddent']").addClass("hiddent");
					//把当前一级菜单状态改为“展开”
					$("."+data.classTitle+"-second").removeClass("hiddent");
					$("."+data.classTitle+"-firsr").addClass("new-hid").empty().append("<font class='item-sign'>- </font>"+data.name);
					$(this).addClass("hiddent-border");
					$(this).addClass("top-border");
				}else{
					$("."+data.classTitle+"-second").addClass("hiddent");
					$("."+data.classTitle+"-firsr").removeClass("new-hid").empty().append("<font class='item-sign'>+ </font>"+data.name);
					$(this).removeClass("hiddent-border");
					//$(this).removeClass("top-border");
				}
			});
		};
		
		
		this.findByProperty = function(){
			
			$(".testJK").click($.proxy(function() {
				var _wnd = new PopuWnd({
					title : "接口测试",
					url : "edituser/testJK",
					width : 355,
					height : 236,
					id : this.user.id,
					alt : "edit"
				});
				_wnd.init(true);
			}, this));
			
			$(".changepw").click($.proxy(function() {
				var _wnd = new PopuWnd({
					title : "密码修改",
					url : "edituser/changepw",
					width : 710,
					height : 235,
					id : this.user.id,
					alt : "edit"
				});
				_wnd.init(true);
			}, this));
		};

		//获取标题的代码
		this.requestUserData = function() {
			$.ajax({
				type : "POST",
				url : "home/setting",
				dataType : "json",
				success : $.proxy(function(user) {
					this.user = user;
					this.modules = user.modules;
					this.initUserInfo();//登陆用户名
					this.initNavbars();//数据加载
					this.roleCheckFunction();
				}, this),
				error : function(e, m) {
					setTimeout($.proxy(function() {
						this.requestUserData();
					}, this), 5000);
				}
			});
		};

		
	
	forReturn = function( data){
		return data;
	};
	
	};
	
	exports.baseSetting = new BaseSetting();
	exports.baseSetting.init();

})(jQuery, window);

/**
 * 地图炒作相关
 */
(function($, exports) {
	var mapContral = function() {
//		113.378491,23.13104(广州)  120.2307664,31.57900925(无锡)
		//120.60266,31.306634(苏州)
		this.numMapLat = 31.306634;
		this.numMapLng = 120.60266;
//		this.numMapLat = 23.13104;
//		this.numMapLng = 113.378491;
		this.left ;
		this.top ;
		this.width ;
		this.height ;
		this.id = "map";
		this.$newMapCon;
		this.$parentCon;
		this._IsMapComplete = false;
		var _this = this;
		
		
		this.setNewMapCon = function(newMapCon){
			this._IsMapComplete = true;
			this.$newMapCon = newMapCon;
			this.setSizeAndPosition();
			this.parentConExist();
		};
		
		/**
		 * 出现上下滚动条时候，移动滚动条，地图也会改变位置
		 */
		this.parentConExist = function(){
			if(this.$newMapCon && this.$newMapCon.parents(".item-content-append-container")){
				this.$parentCon = this.$newMapCon.parents(".item-content-append-container");
				this.$parentCon.scroll(function(){
					_this.setSizeAndPosition();//从新获取地图位置的DIV的高度，宽度，左间距，右间距
					$('#'+_this.id).css({left:_this.left+"px",top:_this.top+"px"});//设置实际地图DIV的左间距、右间距
//					if (_this.$newMapCon.offset().top < _this.$parentCon.offset().top) {
//						$('#'+_this.id).css({top:(_this.$newMapCon.offset().top - 66)+"px"});
//					}else if(_this.$parentCon.scrollTop() <= 0){//滚动条到达顶部特殊处理地图左间距和上间距
//						_this.setSizeAndPosition();
//						$('#'+_this.id).css({left:_this.left+"px",top:_this.top+"px"});
//					}else{
//						
//					}
				});
			}
		};
		
		this.parentScrollChange = function(){
			this.$parentCon.scroll(function(){
//				alert(3543535);
			});
		};
		//设定地图显示的长、宽、左间距，上间距
		this.setSizeAndPosition = function(){
			//this.left = this.$newMapCon.offset().left - $(".content").find(".left").width();
			//地图相对的left位置 = 当前地图占位Div的left位置 - 左边标题列表的长度 - 右边主窗口与左边标题列表的间隔长度
			this.left = this.$newMapCon.offset().left - 200;
			//地图相对的top位置 = 当前地图占位Div的top位置 - 系统名称栏的高度
			this.top = this.$newMapCon.offset().top - 66;
			this.width = this.$newMapCon.width();
			this.height = this.$newMapCon.height();
//			alert($(window).width()+"    :    "+screen.availWidth); //浏览器当前窗口可视区域宽度
		};

		//获取地图对象
		this.initMap = function() {
//			RMapConstant.MapRoot = "http://mapdb.365ditu.cn/rt/mapdb//";
	        RMapConstant.MapMinLevel = 0;
	        RMapConstant.MapMaxLevel = 15;
//	        RMapConstant.ImgServer = 'http://xxxxx';
			var _map =  new RMap(document.getElementById(this.id), 116, 40, 5, $('#'+this.id).width(), $('#'+this.id).height());
			this.initMapComplete(_map);
			_map.setCenter(_this.numMapLng,_this.numMapLat);//设置中心点
			return _map;
		};
		
		//地图相关标注
		this.initMapComplete = function(map) {
			window.toolManager = new RToolManager(map);//获取地图对象，并赋给toolManager
			//toolManager.addCross("static/map/html5/image/cross.gif", this.numMapLat, this.numMapLng = 120.319829); //添加地图中央准心
			//toolManager.addEagleMap(150, 150, true); //添加地图右下角鹰眼图
			toolManager.addZoomDirectBar(10, 10); //添加地图左上角缩放控件
	    };
	    
		 exports._MapMaplet = this.initMap();
		 
		 //根据对象显示地图
		_MapMaplet.showMap = function(_$Map){
			_this.setNewMapCon(_$Map);	
			_MapMaplet.resize(_this.width,_this.height); //根据页面给出DIV的大小设置地图大小
			$('#'+_this.id).css({left:_this.left+"px",top:_this.top+"px"});
			_MapMaplet.show();
		};
		
		 //根据长宽，左上距离设置 地图
		_MapMaplet.showMapBySize = function(width,height,Left,Top){
			_MapMaplet.resize(width,height); //根据页面给出DIV的大小设置地图大小
			$('#'+_this.id).css({left:Left+"px",top:Top+"px"});
			_MapMaplet.show();
		};
		
		//窗口大小变化的时候重设地图大小
		$(window).resize(function() {
			if (_this._IsMapComplete) {
//				_this.setSizeAndPosition();
//				_MapMaplet.resize(_this.width,_this.height); //根据页面给出DIV的大小设置地图大小
//				$('#'+_this.id).css({left:_this.left+"px",top:_this.top+"px"});
			}
		});
		
		
		//初始化地图的时候删除地图上的标注和线路等等
		_MapMaplet.cleanAllObject = function(){
			_MapMaplet.removeAllGraphics(); //删除线路
			_MapMaplet.removeAllMarker(); //删除标注 
		};
		
		//initMapComplete();
	};
	
	exports.mapCon = new mapContral();
})(jQuery, window);

/**
 * 公共方法
 */
(function($, exports) {
	  //模块基类，定义共有方法
	exports.Module = function(item) {
		this.item = item;
		this.data = [];
	};
	Module.prototype.showMap = function(i) {
		if($("#map").attr("class").indexOf("hiddentMap") > -1){
			$("#map").removeClass("hiddentMap");
		}
		//alert(4444);
	};
	Module.prototype.hidMap = function(i) {
		if($("#map").attr("class").indexOf("hiddentMap") <= -1){
			$("#map").addClass("hiddentMap");
		}
	};
	Module.prototype.getEach = function(theClass){
		var theStr = "";
		this.item.con.$content.find(theClass).each(function(j) {
			theStr +=($.trim($(this).val())==""?" ":$.trim($(this).val()));
			theStr +=",";
		});
		return this.deleteLastChar(theStr, ",");
	};
	Module.prototype.deleteLastChar = function(str, c) {  
        var reg = new RegExp(c + "([^" + c + "]*?)$");  
        return str.replace(reg, function(w) {  
            if (w.length > 1) {  
                return w.substring(1);  
            } else {  
                return "";  
            }  
        });  
    };  
    Module.prototype.tbodyTrHover = function(){
    	$(".single").mouseover(function(){
			  $(this).addClass("hover");
			}).mouseout(function(){
				 $(this).removeClass("hover");
			});
    	$(".double").mouseover(function(){
			  $(this).addClass("hover");
			}).mouseout(function(){
				 $(this).removeClass("hover");
			});
    };
    
    Module.prototype.checkTheSameStrInArray = function(str, c) {
    	  var rt = true;
    	  var theStr = [];
    	  theStr = str.split(",");
    	  for(var i = 0;i < theStr.length ; i++){
    		  if(c!=""&&c!=" "&&c==theStr[i]){
    			  rt = false;
    			  break;
    		  }
    	  }
    	  return rt;
    };
    Module.prototype.doAllSelect = function(){
    	var _this = this;
	    this.item.con.$content.find(".all-select").change(function() {
			var thec = $(this).is(":checked");
			_this.item.con.$content.find(".checkboxId").each(function() {
				$(this).prop("checked", thec);	
			});
		});	
    };
    
    Module.prototype.defaultAllSelect = function(){
    	this.item.con.$content.find(".all-select").prop("checked", false);
    };
    
    Module.prototype.substrLenght = function(str,end,start) {  
         start = (start || 0);
         str = (str || "");
        return str.length > end?(str.substring(start,end)+"..."):str;
    };  
    
    Module.prototype.findOneData = function(theUrl,theId){
    	var returnDatr ={};
    	$.ajax({
    		type : "POST",
    		url : theUrl + "/findOneById",
    		dataType : "json",
    		data : "id=" + theId,
    		success : $.proxy(function(obj) {
    			if (obj.success) {
    				returnDatr = obj;
    			} else {
    				alert("请求失败！");
    			}
    		}, this),
    		error : function(msg) {
    			alert("由于网络或服务器问题，暂时无法获取单项数据！\n若一直出现这个提示，请联系管理员！\n"+ theUrl + "/findOneById");
    		}
    	});
    	alert(returnDatr.data.id);
    	return forReturn(returnDatr);
    };

    Module.prototype.searchAllByUrl = function(url,page) {
		var returnDatr ={};
		$.ajax({
			type : "POST",
			url : url + "/search",
			dataType : "json",
			async:false, 
			data : "page=" + page,
			success : $.proxy(function(obj) {
				if (obj.success) {
					returnDatr = obj;
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
    
	Module.prototype.searchAll = function(page) {
		var returnDatr ={};
		$.ajax({
			type : "POST",
			url : this.item.url + "/search",
			dataType : "json",
			async:false, 
			data : "page=" + page,
			success : $.proxy(function(obj) {
				if (obj.success) {
					returnDatr = obj;
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
	
	forReturn = function( data){
		return data;
	};
	
	Module.prototype.addErroy = function(className,erroyTxt){
		if($("."+className).next("span[name='"+className+"']").length == 0){
			$("."+className).after("<span class=\"error\" name='"+className+"'>"+erroyTxt+"</span>");
			$("."+className).next("span[name='"+className+"']").show();
		}
	};
	
	Module.prototype.delErroy = function(className){
		if($("."+className).next("span[name='"+className+"']").length != 0){
			$("."+className).next("span[name='"+className+"']").remove();
		}
	};
	
	Module.prototype.search = function(page) {
		this.doSearch(this.getSearchArg(), page);
	};
	Module.prototype.getSearchArg = function() {
		return "";
	};
	Module.prototype.doSearch = function(args, page) {
		$.ajax({
			type : "POST",
			url : this.item.url + "/search?page=" + page,
			data : args,
			dataType : "json",
			success : $.proxy(function(msg) {
				if (msg.success == true) {
					this.data = msg.data;
					this.showData();
				} else if (msg.success == false) {
					alert(msg.msg);
				} else {
					alert("请重新登录！");
				}
				;
			}, this),
			error : function(e, m) {
				alert(m);
			}
		});
	};
	
	Module.prototype.newone = function() {
		this.doNewone(this.getNewoneArg());
	};
	Module.prototype.getNewoneArg = function() {
		return "";
	};
	Module.prototype.doNewone = function(args) {
		$.ajax({
			type : "POST",
			url : this.item.url + "/newone",
			data : args,
			dataType : "json",
			success : $.proxy(function(msg) {
				if (msg.success == true) {
					alert("新增成功！");
				} else if (msg.success == false) {
					alert("新增失败," + msg.msg);
				} else {
					alert("新增失败,请重新登录！");
				}
			}, this),
			error : function(e, m) {
				alert("由于网络或服务器问题，暂时无法获取列表数据！\n若一直出现这个提示，请联系管理员！\n"+this.item.url+ "/newone");
			}
		});
	};

	Module.prototype.deletes = function() {
		this.doDeletes(this.getDeletesArg());
	};
	Module.prototype.getDeletesArg = function() {
		return "";
	};

	Module.prototype.doDeletes = function(args) {
		$.ajax({
			type : "POST",
			url : this.item.url + "/delete",
			async:false, 
			dataType : "json",
			data : "id=" + args,
			success : $.proxy(function(obj) {
				if (obj.success) {
//					alert("删除成功！");
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
	
	Module.prototype.doDeleteByIdList = function(idList) {
		$.ajax({
			type : "POST",
			url : this.item.url + "/deleteByIdList",
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

	Module.prototype.update = function() {
		this.doUpdate(this.getUpdateArg());
	};
	Module.prototype.getUpdateArg = function() {
		return "";
	};

	Module.prototype.doUpdate = function(args) {
		$.ajax({
			type : "POST",
			url : this.item.url + "/update",
			async:false, 
			data : args,
			dataType : "json",
			contentType : 'application/json;charset=UTF-8',
			success : $.proxy(function(msg) {
				if (msg.success == true) {
					alert("更新成功！");
				} else if (msg.success == false) {
					alert("更新失败," + msg.msg);
				} else {
					alert("更新失败,请重新登录！");
				}
			}, this),
			error : function(e, m) {
				alert(m);
			}
		});
	};
	
	Module.prototype.doFindstate = function(theUrl){
		var returnDatr ={};
		$.ajax({
    		type : "POST",
    		url : theUrl + "/bystate",
    		async:false, 
    		dataType : "json",
    		data : "state=1",
    		success : $.proxy(function(obj) {
    			if (obj.success) {
    				//this.setSelectAll(obj.data,className);
    				returnDatr = obj;
    			} else {
    				alert("请求失败！");
    			}
    		}, this),
    		error : function(msg) {
    			alert("由于网络或服务器问题，暂时无法获取列表数据！\n若一直出现这个提示，请联系管理员！\n"+theUrl + "/bystate");
    		}
    	});
		
		return forReturn(returnDatr);
	};
	
	Module.prototype.CheckMatched = function(theClass){
        var varreg=/^\d+(\.{0,1}\d+){0,1}$/;
        return this.Mycheck(varreg,theClass,"只能输入数字！");
    };
    
	Module.prototype.Mycheck = function(varreg,theClass,tis){
        if (!varreg.test(theClass.val())) {
            if (theClass.next("span").length == 0) {
                theClass.after("<span class=\"error\" name='" + theClass.attr("name")+ "'>"+tis+"</span>");
            };
            return false;
        } else {
            if (theClass.next("span[name='"+theClass.attr("name")+"']").length != 0) {
                theClass.next("span[name='"+theClass.attr("name")+"']").remove();
               
            }
            return true;
        }
    };

    //需要继承的获取数据的方法
	Module.prototype.showData = function(data) {

	};
	/*构建表格 -- Start*/
	Module.prototype.buildTable = function(getDate, newDate,tableClass){
		$("."+tableClass).empty();//清空表格
		var html = this.buildTableHeart(newDate);//构建头部
		html += this.buildTableBody(getDate,newDate);//构建内容显示
		$("."+tableClass).append(html);
		this.tbodyTrHover();//添加鼠标移动事件
		this.initOperate();//表格内的默认查看、删除、修改事件
	};
	//构建表格头
	Module.prototype.buildTableHeart = function(data){
		var showDate = data.showTable;
		var html = "";
		html +="<thead><tr>"
			+"<th class='th-short'><label><input class='all-select' type='checkbox' ></label></th>";
		for ( var int = 0; int < showDate.length; int++) {
			html += "<th "+(showDate[int].initHeartHtml || "")+">"+showDate[int].tltle+"</th>";
		}
		html +="</tr></thead>";
		return html;
	};
	//构建表格内容
	Module.prototype.buildTableBody = function(getDate,data){
		var showDate = data.showTable;
		var html = "";
		html +="<tbody>";
		for ( var int = 0; int < getDate.length; int++) {
			html +="<tr indextab='" + int + "' class='"+ ((int % 2 == 0 ? true : false) ? "single" : "double") + "' id='"+getDate[int][data.id]+"' data='"+$.toJSON(getDate[int])+"'>";
			html +="<td><input type='checkbox' class='checkboxId'></td>";
			//遍历获取的属性
			for ( var int2 = 0; int2 < showDate.length; int2++) {
				switch (showDate[int2].value) {
				case "1":
					html += "<td "+(showDate[int2].initBodyHtml || "")+">"+(showDate[int2].func ? showDate[int2].func(int): int+1)+"</td>";
					break;
				case "0"://按钮栏
					html += "<td "+(showDate[int2].initBodyHtml || "")+">"+( showDate[int2].btTdHtml || "")+"</td>";
					break;
				default:
					html += "<td "+(showDate[int2].initBodyHtml || "")+">"+(showDate[int2].func? (showDate[int2].func(getDate[int][showDate[int2].value])) : (getDate[int][showDate[int2].value] || ""))+"</td>";
					break;
				}
			}
			html += "</tr>";
		}
		html +="</tbody>";
		return html;
	};
	/*构建表格 -- End*/
	/*表格的添加、查看、修改、删除、导出Excel、方法Start*/
	Module.prototype.initOperate = function() {
		this.item.con.$content.find("tbody").click($.proxy(function(e) {
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
	//到处Excel初始化
	Module.prototype.initExcel = function(data){
		var _this = this;
		this.excelFrom = {};
		this.excelFrom = new excelFrom(data);
		this.excelFrom.init();
		//点击按钮事件
		this.item.con.$content.find(".export-Excel").click(function() {
			_this.exportExcel();
		});
	};
	//显示Excel窗口
	Module.prototype.exportExcel = function(){
			this.excelFrom.show();
	};

	Module.prototype.editOne = function($target) {
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
	
	Module.prototype.showOne = function($target) {
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

	Module.prototype.addOne = function(addBtnClassName) {
		var _wnd = new PopuWnd({
			title : "新增" + this.PopuWndTitle,
			url : this.PopuWndURL,
			width : this.PopuWndWidth,
			height : this.PopuWndHeight
		});
		this.item.con.$content.find("."+addBtnClassName).click(function() {
			_wnd.init(true);
		});
	};
	
	Module.prototype.deleteOne = function($target) {
		this.deleteData($target.attr("id"));
	};
	
	Module.prototype.deleteData = function(id) {
		this.doDeletes(id);
		this.requestData(1);
	};
	/*表格的添加、查看、修改、删除、导出Excel、方法End*/
	
	/*分页按钮事件--Start*/
	Module.prototype.initPageButton = function() {
		var _this = this;
		this.pageButton = new PageButton(this.item.con.$content.find("#item-result-pages"), 1, 1, 5);
		this.pageButton.init();
		this.pageButton.click($.proxy(function(page) {
			_this.requestData(page);
		}), this);
	};
	
	/*分页按钮事件--End*/
	
	/*批量删除--Start*/
	Module.prototype.deleteByIdList = function(idList) {
		this.doDeleteByIdList(idList);
		this.requestData(1);
	};
	/*批量删除--End*/
	
	/*获取分页数据--Start*/
	Module.prototype.requestData = function(page){
		var dateG = {};
		dateG = this.searchAll(page);
		this.showData(dateG.data);
		this.pageButton.setButton(dateG.page, dateG.totalPages, 5);
	};
	/*获取分页数据--End*/

	/*数据刷新 -- Start*/
	Module.prototype.initRefresh = function() {
		var _this = this;
		this.item.con.$content.find(".refresh").click($.proxy(function() {
			_this.requestData(1);
		}, this));
	};
	/*数据刷新 -- End*/
	
	/*数据的批量删除  -- Start*/
	Module.prototype.buttonUser = function() {
		this.item.con.$content.find(".item-result-operate-left").click($.proxy(function(e) {
			var $target = $(e.target || e.srcElement);
			var alt = $target.attr("class");
			var str = "";
			if (alt.indexOf("delete-operate") >= 0) {
				if(confirm("是否删除")){
					this.item.con.$content.find(".checkboxId").each(function() {
					if ($(this).is(':checked')) {
						str += $(this).parentsUntil("tr").parent().attr("id") + " ,";
					}
				});
				this.deleteByIdList(this.deleteLastChar(str, ","));
				}
			} else if (alt == "edit") {
				
			}
		}, this));
	};
	/*数据的批量删除  -- End*/
	
	/*模糊查询 -- Satart*/
	Module.prototype.schBtnClick = function(url){
		var _this = this;
		this.$content.find(".sch-p").click(function(){
			_this.findByPropertys(1,url);
		});
	};

	Module.prototype.findByPropertys = function(page,url){
		var data = {};
		data = this.$content.find(".FindData-form").serializeJson() ;
		data.page = (page || this.pageButton.page ||1);
		$.ajax({
			type : "POST",
			url : url,
//			dataType : "json", //这里为了能够让后台使用泛型，所有不使用JSON
			data : "propertys="+$.toJSON(data),
//			contentType : 'application/json;charset=UTF-8',
			success : $.proxy(function(obj1) {
				var obj = {};
				obj = jQuery.parseJSON(obj1);
				if (obj.success) {
					this.showData(obj.data);
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
	
	a = function(t){
		alert(t);
	};
})(jQuery, window);