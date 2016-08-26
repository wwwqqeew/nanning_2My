<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#include "/java_util.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 




<#list table.columns as column>
<#assign capturedOutput><@nameCheck colu=column/></#assign>
<#assign ddaa = ("${nodd(column)}" == "00")>
<#if (capturedOutput == '1')>
对滴

<#else>
不对${capturedOutput}  (capturedOutput == '1')  (capturedOutput == "1")

</#if>
</#list>

<#--输出无特殊字符的注释 ${htm_notes(column)}-->
<#function nodd colu>
<#compress>
<#if (colu.size == 255 && colu.sqlTypeName == "varchar")>
<#return "11">
<#else>
<#return "00">
</#if>
</#compress>
</#function>

<#macro nameCheck colu>
<#compress>
<#if (colu.size == 255 && colu.sqlTypeName == "varchar")>
1
<#return>
<#else>
0
<#return>
</#if>
</#compress>
</#macro>
