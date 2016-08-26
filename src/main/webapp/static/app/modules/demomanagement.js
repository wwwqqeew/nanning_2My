(function($, exports) {
	var module = new exports.Module(exports.baseSetting.getModule("DemoManagement"));
	module.$content = module.item.con.$content;//这里是数据显示列表的主体
	
	module.PopuWndHeight = 196;//弹出窗体的高
	module.PopuWndWidth = 726;//弹出窗体的宽
	module.PopuWndURL = "editdemo";//弹出窗体的RUL
	module.PopuWndTitle = "demo";//弹出窗体的名称
	module.isEidt = false;//这个是测试权限用的，这里取得权限，几个在代码中使用
	
	//构建数据显示列表的主体（实现的基础代码在 home.js中）
	module.showData = function(data) {
		var newDate = {
				//主键的字段
				id:"id",
				//showTable:要显示的每一行数据
				showTable:[
				/**
				 * tltle :列的标题名称
				 * value ：1、字段值（即字段属性）。
				 * 		  2、数字（【1:代表编号栏，其中编号栏加上 initBodyHtml:"class='show-entity'" 的话，会在编号下方加上下划线，同时具备查看功能】
				 * 				【0：代码按钮栏，编辑按钮 <input class='modify' type='button'> ,删除按钮 <input class='delete' type='button'>】）
				 * 
				 * initHeartHtml ：标题栏的内部属性（仅标题栏可以使用） 。（会加在<td 加入的内部属性></td> 例如 <td class='th-short '></td>）
				 * initBodyHtml ： 内容栏的内部属性 （会加在<td 加入的内部属性></td> 例如 <td class='show-entity '></td>）
				 * func : 数据处理的方法，需要把一个方法带进入（注：不要加括号，不然成执行方法了） 仅属性字段可用
				 * btTdHtml ： 操作栏的按钮HTML（仅按钮栏可用）
				 * 
				 */
				{tltle:"编号",value:"1",initHeartHtml:"class='th-short '",initBodyHtml:"class='show-entity'"},
				{tltle:"名字",value:"name"},
				{tltle:"名称2",value:"name2",func:module.func},
				{tltle:"操作",value:"0",btTdHtml:(module.isEidt ? "<input class='modify' type='button'>" : "")+"<input class='delete' type='button'>"}
				 ]};
		module.buildTable(data, newDate, "demo-table");
		//$(".test-select").myGetList({url:"demomanagement/findList"});
	};
	
	//这个是数据处理的方法，如果有数据需要处理的，可以按照这样的形式来写
	module.func = function(data){
		if (data == 0 || data) {
			return data+"(处理数据)";
		} else {
			return "(处理未数据)";
		}
	};

	//一堆的初始化方法（实现的基础代码在 home.js中）
	module.init = function() {
		this.addOne("add-demo");//添加窗口
		this.initRefresh();//刷新
		this.initPageButton();//分页按钮事件初始化
		this.requestData(1);//进入获取数据
		this.doAllSelect();//全选事件
		this.buttonUser();//批量删除事件
		this.schBtnClick("demomanagement/findByPropertys");//模糊查询事件
		//Excel导出初始化
		/**
		 * url : 请求的连接地址
		 * mailClassName : 导出的主类名称 （一般保留export-main即可）
		 * data : 属性字段
		 *        title ： 导出的时候Excel显示的列名称
		 *        value ： 对应的属性字段
		 */
		this.initExcel({url:"demomanagement",mailClassName:"export-main",data:[
		                                                                    {title:"编号",value:"id"},
		                                                                    {title:"名字",value:"name"},
		                                                                    {title:"名字2",value:"name2"}
		                                                                    ]});

		$(".imPort-Excel").click(function(){
			var _wnd = new window.PopuWnd({title:"标题",url:"demomanagement/importShow",width:600,height:400});
			_wnd.init(true);
		});

	};

	module.refeshMe = function(){
		//this.requestData(1);
	};
	
	//调用初始方法
	module.init();
	
	exports.baseSetting.getModule("DemoManagement").module = module;

})(jQuery, window);