<#include "/custom.include">
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>${table.columns[0].columnAlias}管理</title>
    <link rel="stylesheet" href="${r'${pageContext.request.contextPath}'}/static/css/css.css">
    
 <style type="text/css">
	a{text-decoration:none;background:none;}
	a:hover{background:#CCCCCC; }
</style>
</head>
<body>


<div class = "main" id="${classNameLower}Main" >
 	<div class = "search-container">
 	<form class="FindData-form" id = "FindData-form">
 		<div class=" search-top">${table.columns[0].columnAlias}管理</div>
		<#list table.columns as column>
			<#if !column.pk>
				<#if !isforeignKey(column)>
					<#if (column.sqlTypeName == "datetime")>
					<div class = "searchItem">${htm_notes(column)}:<input type="text" class="search-input Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="${column.columnNameLower}"></div>
					<div class = "searchItem">${htm_notes(column)}最大:<input type="text" class="search-input Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="${column.columnNameLower}_max"></div>
					<#else>
					<div class = "searchItem">${htm_notes(column)}:<input type="text" class="search-input" name="${column.columnNameLower}"></div>
					</#if>
				<#else>
				<div class = "searchItem">${htm_notes(column)}:<select class="${fk_notes(column)}  search-input" name="${column.columnNameLower}"  ></select></div>
				</#if>
			</#if>
			</#list>
		<div class = "searchSub-item">
			<input type="button" class="search-btn btn-base" value="查询">
		</div>
		</form>
 	</div>
 	
 <div class = "item-list">
 <div class = "item-btn">
 <shiro:hasPermission name="user:create">
   <%--  <a href="${r'${pageContext.request.contextPath}'}/testdemomanagement/create">添加</a> --%>
   <div class = "btn-container">
     	<a class='linkEdit btn-base add-demo'  href='javascript:void(0);'>添加</a>
     </div>
  
</shiro:hasPermission>
 <shiro:hasPermission name="user:update">
     <%-- <a  href="${r'${pageContext.request.contextPath}'}/testdemomanagement/${r'${user.id}'}/update">修改</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base edit-operate'  href='javascript:void(0);'>修改</a>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:delete">
    <%--  <a href="${r'${pageContext.request.contextPath}'}/testdemomanagement/${r'${user.id}'}/delete">删除</a> --%>
       <div class = "btn-container">
     	<a class='linkEdit btn-base delete-operate' href='javascript:void(0);'>删除</a>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:update">
    <%--  <a href="${r'${pageContext.request.contextPath}'}/testdemomanagement/${r'${user.id}'}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base' onclick='reset()' href='javascript:void(0);'>初始化密码</a>
     </div>
 </shiro:hasPermission>
 
  <shiro:hasPermission name="user:show">
    <%--  <a href="${r'${pageContext.request.contextPath}'}/testdemomanagement/${r'${user.id}'}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base show-operate' href='javascript:void(0);'>查看</a>
     </div>
 </shiro:hasPermission>
 
   <shiro:hasPermission name="user:excel">
    <%--  <a href="${r'${pageContext.request.contextPath}'}/testdemomanagement/${r'${user.id}'}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base  export-Excel' href='javascript:void(0);'>导出</a>
     </div>
 </shiro:hasPermission>

   <shiro:hasPermission name="user:excel">
    <%--  <a href="${r'${pageContext.request.contextPath}'}/testdemomanagement/${r'${user.id}'}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base  imPort-Excel' href='javascript:void(0);'>导入</a>
     </div>
 </shiro:hasPermission>

 </div>
	<table cellspacing="0" cellpadding="0" class="demo-table" id="${classNameLower}-table">
			<thead>
				<tr>
					<th class="th-short"><label><input class="all-select" type="checkbox" ></label></th>
					<th class="th-short">编号</th>
						<#list table.columns as column>
							<#if !column.pk>
							<th>${htm_notes(column)}</th>
						</#if></#list>
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
	var ${className}Module = new exports.Modle("${classNameLower}Main","${classNameLower}management","edit${classNameLower}","${classNameLower}-table","${classNameLower}");
	//debugger;
	//构建获取的每一行
	${className}Module.buildHtmlBody = function(date){
		var htm = "";
    	for ( var i = 0; i < date.length; i++) {
    		htm += ("<tr data='' id='"+date[i].id+"' class='' ><td><input type='checkbox' class='checkboxId'></td><td class='show-entity'>"+(i+1)+"</td>"
			<#list table.columns as column>
	          <#if !column.pk>
		      	<#if !isforeignKey(column)>
	          	+ "<td>"+(date[i].${column.columnNameLower} || "")+"</td>"
	          	<#else>
		      	+ "<td>"+(date[i].${fk_notes(column.columnNameLower)}Name || "")+"</td>"
		    	</#if>
			  </#if>
			</#list>
    		+"<td><input type='button' class='delete'></td></tr>");
		}
    	return htm;
	};
	${className}Module.initExcel({url:"${classNameLower}management",findFrom:"FindData-form",mailClassName:"export-main",data:[
																			<#list table.columns as column>
																			<#if !column.pk>
																			<#if !isforeignKey(column)>
																			{title:"${htm_notes(column)}",value:"${column.columnNameLower}"},
																          	<#else>
																			{title:"${htm_notes(column)}",value:"${fk_notes(column.columnNameLower)}Name"},
																	    	</#if>
																			</#if>
																			</#list>
		                                                                    ]});
	${className}Module.init();
	<@generateJavaManyToOneColumns/>
	
	<#macro generateJavaManyToOneColumns>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	window.Modules.findListAction("${fkPojoClassVar}");
	</#list>
	</#macro>
	window.Modules.setModle("${classNameLower}",${className}Module);

})(jQuery, window);
</script>
</body>
</html>