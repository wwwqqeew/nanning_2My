<!-- <#include "/custom.include">
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do"> -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>车种管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
    
 <style type="text/css">
	a{text-decoration:none;background:none;}
	a:hover{background:#CCCCCC; }
</style>
</head>
<body>

<div class = "main" id="demoMain" >
 	<div class = "search-container">
 	<form class="FindData-form" id = "FindData-form">
 		<div class=" search-top">用户管理</div>
 		
		<div class = "searchItem">
		名字:<input type="text" class="search-input" name="name" nameCn="名字">
		</div>
		<div class = "searchItem">
		名称2:<input type="text" class="search-input name2" name="name2"  nameCn="名称2">
		</div>
		<div class = "searchItem">
		下拉:
		<div class='diy_select diy_province'>
							<input  class='form-control diy_select_input provinceIP required' />
							<input  name='province' type="hidden"  class='diy_select_txt province'/>
							<div class='diy_select_btn provinceBT'></div>
							<ul class='diy_select_list provinceSL'>
							</ul>
						</div>
		</div>
		<div class = "searchSub-item">
			<input type="button" class="search-btn btn-base" value="查询">
		</div>
		</form>
 	</div>
 	<div class = "yy" style="width: 100px;height: 100px; position: absolute;">
 	<input type="text" class="search-input yysr" name="yysr" >
 	</div>
 <div class = "item-list">
 <div class = "item-btn">
 <shiro:hasPermission name="user:create">
   <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/create">添加</a> --%>
   <div class = "btn-container">
     	<a class='linkEdit btn-base add-demo'  href='javascript:void(0);'>添加</a>
     </div>
  
</shiro:hasPermission>
 <shiro:hasPermission name="user:update">
     <%-- <a  href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/update">修改</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base edit-operate'  href='javascript:void(0);'>修改</a>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:delete">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/delete">删除</a> --%>
       <div class = "btn-container">
     	<a class='linkEdit btn-base delete-operate' href='javascript:void(0);'>删除</a>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:update">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base' onclick='reset()' href='javascript:void(0);'>初始化密码</a>
     </div>
 </shiro:hasPermission>
 
  <shiro:hasPermission name="user:show">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base show-operate' href='javascript:void(0);'>查看</a>
     </div>
 </shiro:hasPermission>
 
   <shiro:hasPermission name="user:excel">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base  export-Excel' href='javascript:void(0);'>导出</a>
     </div>
 </shiro:hasPermission>

   <shiro:hasPermission name="user:excel">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base  imPort-Excel' href='javascript:void(0);'>导入</a>
     </div>
 </shiro:hasPermission>

 </div>
	<table cellspacing="0" cellpadding="0" class="demo-table" id="demo-table">
			<thead>
				<tr>
					<th class="th-short"><label><input class="all-select" type="checkbox" ></label></th>
					<th class="th-short">编号</th>
						<th>名字</th>
						<th>名称2</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody ></tbody>
		</table>
	<div class='item-result-operate'>
		<div class='item-result-pages' id='item-result-pages'></div>
	</div>
 </div>
	<div class="yemian1" id="newpage1" ></div>
	<div class="export-main"></div>
</div>

<script type="text/javascript">
(function($, exports) {
	var DeomModule = new exports.Modle("demoMain","demomanagement","editdemo","demo-table","demo");
	//debugger;
	//构建获取的每一行
	DeomModule.buildHtmlBody = function(date){
		var htm = "";
    	for ( var i = 0; i < date.length; i++) {
    		htm += "<tr data='' id='"+date[i].id+"' class='' ><td><input type='checkbox' class='checkboxId'></td><td class='show-entity'>"+(i+1)+"</td><td>"+date[i].name+"</td><td>"+date[i].name2+"</td><td><input type='button' class='delete'></td></tr>"
		}
    	return htm;
	};
	DeomModule.initExcel({url:"demomanagement",findFrom:"FindData-form",mailClassName:"export-main",data:[
		                                                                    {title:"编号",value:"id"},
		                                                                    {title:"名字",value:"name"},
		                                                                    {title:"名字2",value:"name2"}
		                                                                    ]});
	DeomModule.init();

	window.DeomModule = DeomModule;
	window.Modules.setModle("demo",DeomModule);
	
	$(".yysr").keyup(function() {
		if ($(".FindData-form").find(".search-input[nameCn='"+$(".yysr").val()+"']").length>0) {
		$(".search-input[nameCn='"+$(".yysr").val()+"']").focus().val($(".yysr").val());
		}
		//$(".name2").val($(".yysr").val());
	});

	//省份
	var TTDiy_select=new diy_select({  
		TTContainer:'diy_province',//控件的class
		TTDiy_select_input:'provinceIP',//用于提交表单的class
		TTDiy_select_txt:'province',//diy_select用于显示当前选中内容的容器class
		TTDiy_select_btn:'provinceBT',//diy_select的打开按钮
		TTDiv_select_list:'provinceSL',//要显示的下拉框内容列表class
		TTFcous:'focus'//得到焦点时的class
	});//如同时使用多个时请保持各class一致.

    function initProvince(){
    	var htm = GetArea22(1);//省份
    	$(".provinceSL").html(htm);
    };
    initProvince();
})(jQuery, window);
</script>
</body>
</html>