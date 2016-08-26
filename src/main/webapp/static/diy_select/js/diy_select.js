function diy_select(data){
	this.init.apply(this,arguments);
	};
diy_select.prototype={
	 init:function(opt)
	 {
		this.setOpts(opt);
		this.o=this.getByClass(this.opt.TTContainer,document,'div');//容器
		this.b=this.getByClass(this.opt.TTDiy_select_btn);//按钮
		this.t=this.getByClass(this.opt.TTDiy_select_txt);//显示
		this.l=this.getByClass(this.opt.TTDiv_select_list);//列表容器
		this.ipt=this.getByClass(this.opt.TTDiy_select_input);//列表容器
		this.JQt = $("."+this.opt.TTDiy_select_txt);
		this.JQipt = $("."+this.opt.TTDiy_select_input);
		this.JQl = $("."+this.opt.TTDiv_select_list);
		this.lengths=this.o.length;
		this.showSelect();
		this.showLi();
		this.oldValue = "";//原来参数
		this.oldShow = "";//原来显示的参数
		this.isSelected = false;//是否选择了新li
		this.isClickIpt = false;//是否点击了输入框
		this.afterSelectedFuc = (opt.func || null);//点击选择后执行的方法，或点出下拉，但是不选择继续点击空白处
		this.noSelectedClear = (opt.noSelectedClear == false ? false : true );//未选择，情况输入框
	 },
	 showLi:function(){//绑定键盘事件
		 var _this = this;
		 this.JQipt.keyup(function(){
			 _this.JQt.val("");//输入后，清空保存的数据
			 _this.checkShow();
		 });
		 this.JQipt.focusout(function(){
			 if (this.noSelectedClear && _this.JQt.val() == "") {
				 _this.JQipt.val("");
			}
			 });
	 },
	 checkShow:function(){
		 var _this = this;
		 //显示包含了文本框内容的LI
		 _this.JQl.find('li').each(function(){
			 if ($(this).text().indexOf(_this.JQipt.val().trim()) != -1) {
				 $(this).css({ display: "block"});
			}else{
				 $(this).css({ display: "none"});
			}
		 });
	 },
	 addClass:function(o,s)//添加class
	 {
		o.className = o.className ? o.className+' '+s:s;
	 },
	 removeClass:function(o,st)//删除class
	 {
		var reg=new RegExp('\\b'+st+'\\b');
		o.className=o.className ? o.className.replace(reg,''):'';
	 },
	 addEvent:function(o,t,fn)//注册事件
	 {
		return o.addEventListener ? o.addEventListener(t,fn,false):o.attachEvent('on'+t,fn);
	 },
	 showSelect:function()//显示下拉框列表
	 {
		var This=this;
		var iNow=0;
		//点击空白出隐藏下拉
		this.addEvent(document,'click',function(){
			//alert(This.oldShow+":是否选择了2："+This.isSelected);
			//alert(This.oldShow+":是否选择了noSelectedClear3："+This.noSelectedClear);
			//没选择，是否执行相应处理
			//alert(This.noSelectedClear+":"+!This.isSelected);
			if (This.isClickIpt && This.noSelectedClear) {
				//若果没选择，还原
				if (!This.isSelected) {
					// 输入框数据在未选择情况下与原来不一致
					if (This.JQipt.val() != This.oldShow) {
						This.JQt.val(This.oldValue);//原来参数
						This.JQipt.val(This.oldShow);//原来显示的参数
					}
				}
			}
			 for(var i=0;i<This.lengths;i++)
			 {
				This.l[i].style.display='none';
			 }
			 This.isClickIpt = false;
		})
		//鼠标焦点移开，隐藏下拉框
		/*this.JQipt.focusout(function(e){
			var a = e.target;
			var d =  e.currentTarget;
			alert(d.html());
			This.JQl.find('li').css({ display: "none"});
		});*/
		for(var i=0;i<this.lengths;i++)
		{
			this.l[i].index=this.b[i].index=this.ipt[i].index=i;
			//点击输入框或者下拉按钮
			this.ipt[i].onclick=this.b[i].onclick=function(ev)  
			{
				This.isClickIpt = true;
				This.isSelected = false;
				This.oldValue = This.JQt.val();//原来参数
				This.oldShow = This.JQipt.val();//原来显示的参数
				//alert("显示下拉："+This.isSelected);
				This.JQl.find('li').css({ display: "block"});
				var e=window.event || ev;
				var index=this.index;
				This.item=This.l[index].getElementsByTagName('li');

				This.l[index].style.display= This.l[index].style.display=='block' ? 'none' :'block';
				
				for(var j=0;j<This.lengths;j++)
				{
					if(j!=index)
					{
						This.l[j].style.display='none';
					}
				}
				This.addClick(This.item);
				e.stopPropagation ? e.stopPropagation() : (e.cancelBubble=true); //阻止冒泡
			}
		}
	 },
	 addClick:function(o)//点击回调函数
	 {
		if(o.length>0)
		{
			var This=this;
			for(var i=0;i<o.length;i++)
			{
				o[i].onmouseover=function()
				{
					This.addClass(this,This.opt.TTFcous);
				}
				o[i].onmouseout=function()
				{
					This.removeClass(this,This.opt.TTFcous);
				}
				o[i].onclick=function()//选择下拉选择
				{
					var index=this.parentNode.index;//获得列表
					//This.t[index].innerHTML= ;
//					alert(This.JQt.attr("class")+"ss:"+this.getAttribute("value"));
//					This.JQt.attr("dd",this.getAttribute("value"));
					This.JQt.val(this.getAttribute("value"));
					This.isSelected = true;
					This.isClickIpt = false;
					//alert("点击了下拉");
					This.ipt[index].value=this.innerHTML.replace(/^\s+/,'').replace(/\s+&/,'');
					This.l[index].style.display='none';
					//执行 带入的方法
					if (This.afterSelectedFuc != null) {
						This.afterSelectedFuc();
					}
				}
			}
		}
	 },
	 getByClass:function(s,p,t)//使用class获取元素
	 {
		var reg=new RegExp('\\b'+s+'\\b');
		var aResult=[];
		var aElement=(p||document).getElementsByTagName(t || '*');

		for(var i=0;i<aElement.length;i++)
		{
			if(reg.test(aElement[i].className))
			{
				aResult.push(aElement[i])
			}
		}
		return aResult;
	 },

	 setOpts:function(opt) //以下参数可以不设置  //设置参数
	 { 
		this.opt={
			 TTContainer:'diy_select',//控件的class
			 TTDiy_select_input:'diy_select_input',//用于提交表单的class
			 TTDiy_select_txt:'diy_select_txt',//diy_select用于显示当前选中内容的容器class
			 TTDiy_select_btn:'diy_select_btn',//diy_select的打开按钮
			 TTDiv_select_list:'diy_select_list',//要显示的下拉框内容列表class
			 TTFcous:'focus'//得到焦点时的class
		}
		for(var a in opt)  //赋值 ,请保持正确,没有准确判断的
		{
			this.opt[a]=opt[a] ? opt[a]:this.opt[a];
		}
	 }
}
