<#include "/custom.include">
<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">


//开始遍历
<#assign datetimeS = 0>
<#list table.columns as column>
<#if isforeignKey(column)>
//外键 ${java_p(column)}
<#else>
private ${java_p(column)} ${column.columnNameLower}; //${htm_notes(column)} <#if (column.pk)>（主键）</#if>
</#if>
</#list>

<#--检测是否是外键的字段，是就执行输出 <@isFK_do column=column > 11 -->
<#macro isFK_do column >
	<#assign truOrFalse=false>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
		<#assign truOrFalse= ((column?index_of(fkPojoClassVar) >-1) && (column?index_of("Id") >-1)) >
	</#list>
	<#if truOrFalse>
	<#else>
		<#nested>
	</#if >
</#macro>
