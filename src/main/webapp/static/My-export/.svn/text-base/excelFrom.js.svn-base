(function($, exports) {
	exports.excelFrom = function(formClass){
		
		this.mailClassName = ( formClass.mailClassName || "");//放置导出的类名称
		this.data = ( formClass.data || {});//要显示的导出数据
		this.line = 2;//行数（用来控制导出页面的高度）
		this.$main = $("."+this.mailClassName);//放置主类的对象，这样获取的对象不会和页面内其他的对象冲突
		this.url = ( formClass.url || ""); //导出的地址
		this.$findFrom = $("#"+formClass.findFrom);
		this.mainHeight = 0;
		var _this = this;
		
		//按钮事件
		this.btnDefault = function(){
			this.selectAllAction();
			this.selectCantelAction();
			this.closeAction();
			this.saveAction();
			this.draggableAction();
		};
		this.draggableAction = function(){
			this.$main.draggable();
		};
		//导出
		this.saveAction = function(){
			this.$main.on('click',".export-save",function(){
				var data = "exportTitle={";
				var getDate = "";
				_this.$main.find(".sl").each(function(e){
					if ($(this).is(':checked')) {
						getDate += (e == 0 ? "" : (getDate == "" ? "": ","));
						getDate += "\""+$(this).attr("name")+"\":\""+$(this).attr("title")+"\"";
					}
				});
				
				data +=getDate;
				data += "}";
				var data2 = "";
				data2 = "propertys="+$.toJSON(_this.$findFrom.serializeJson());//查询数据用的，暂时没用到
				//.serializeJson() ;$.toJSON(data)
				window.location.href = _this.url+"/export?"+data+"&"+data2;
			});
			
		};

		//全选
		this.selectAllAction = function(){
			this.$main.on('change',".slt-all", function(){
				if (_this.$main.find(".slt-all").is(':checked')) {
					_this.$main.find(".sl").prop("checked",true);
				}else{
					_this.$main.find(".sl").prop("checked",false);
				}
			});
		};
		//反选
		this.selectCantelAction = function(){
			this.$main.on('change',".cent-all",function(){
				_this.$main.find(".sl").each(function(){
					if ($(this).is(':checked')) {
						$(this).prop("checked",false);
					}else{
						$(this).prop("checked",true);
					}
				});
			});
		};
		//关闭按钮事件
		this.closeAction = function(){
			this.$main.on('click',".export-cat ,.export-del",function(){
				_this.close();
			});
		};
		//关闭导出页面的方法
		this.close = function(){
			this.$main.empty();
		};
		//显示页面的方法
		this.show = function(){
			this.addHtml();
		};
		
		//获取当个的HTML
		this.getMainHtml = function(data){
			var html = "";
			for ( var int = 0; int < data.length; int++) {
				html += "<label class='cb-label'><input type='checkbox' class='sl' name='"+( data[int].value || "" )+"' title='"+( data[int].title || "")+"'>"+( data[int].title || "")+"</label>";
				if (int != 0 && (int%4 == 0)) {
					html += "<br>";
					this.line ++;
				}
			}
			return html;
		};
		//初始化的默认事件
		this.init = function(){
			this.btnDefault(); //按钮事件
		};
		//添加Html内容
		this.addHtml = function(){
			var bodyHtml = this.getMainHtml(this.data);//获取要显示的导出内容HTML
			this.mainHeight = 100+this.line * 20;
			var hei = ($(window).height()/2 - this.mainHeight/2*3);
			var html = 
		"<div class='export-div' id='divBody'  style='height: "+(100+this.line * 20)+"px;top:"+hei+"px;'>"+
			"<div class='export-del'></div>"+
			"<div class='export-contain'>"+
			    "<div class='export-slt-all'>"+
			    	"<label><input type='checkbox' class='slt-all'>全选</label>"+
			    	"<label><input type='checkbox' class='cent-all'>取消</label>"+
			    "</div>"+
			    "<div class='main-contain'>"+bodyHtml+
			    "</div>"+
				"<div class='export-btn' >"+
					"<input class='export-save export-button my-if-btn' value='确定'>"+
					"<input class='export-cat export-button my-if-btn' value='取消' style='left:10px;'>"+
				"</div>"+
			"</div>"+
		"</div>";
			
		this.$main.empty().append(html);//主类的清空，然后添加新的HTML
		
		
//		var posX = 0; 
//		var posY = 0; 
//		$(".export-div").mousedown(function(e){
//			if(!e) e = window.event; //IE 
//			posX = e.clientX - $(".export-div").offset().left; 
//			posY = e.clientY - $(".export-div").offset().top;
//			$(".export-div").mousemove(mv);
//			});
//		
//		$(".export-div").mouseup(function(e){
//			$(".export-div").mousemove();
//			
//			});
//		$(".export-div").mouseout(function(){
//			$(".export-div").mousemove();
//			});
		
//		mv = function(ev){
//			if(ev==null) ev = window.event;//IE 
////			alert(ev.clientX);
//			if((ev.clientX) && ev.clientY){
//				$(".export-div").css({left:(ev.clientX - 201)+"px",top:ev.clientY+"px"});
//			};
////			$(".export-div").offset().left = (ev.clientX - posX) ; 
////			$(".export-div").offset().top = (ev.clientY - posY) ; 
//			};

		
//		var posX; 
//		var posY; 
//		var fdiv = $(".export-div"); 
//		$(".export-div").mousedown=function(e) 
//		{ 
//			alert(333);
//		if(!e) e = window.event; //IE 
//		posX = e.clientX - parseInt(fdiv.style.left); 
//		posY = e.clientY - parseInt(fdiv.style.top); 
//		document.onmousemove = mousemove; 
//		} ;
//		document.onmouseup = function() 
//		{ 
//		document.onmousemove = null; 
//		}; 
//		function mousemove(ev) 
//		{ 
//		if(ev==null) ev = window.event;//IE 
//		fdiv.style.left = (ev.clientX - posX) + "px"; 
//		fdiv.style.top = (ev.clientY - posY) + "px"; 
//		}; 

		};
	};
})(jQuery, window);
