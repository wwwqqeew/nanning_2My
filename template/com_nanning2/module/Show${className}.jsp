<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看${table.columns[0].columnAlias}</title>
 <script type="text/javascript" src="/static/jquery/jquery-1.9.1.min.js"></script> 
 <link type="text/css" rel="stylesheet" href="/static/styles/home.css" />
<link type="text/css" rel="stylesheet" href="/static/styles/iframe.css" />
</head>
<body>

	        <form id='edit-user-form' class="iframe-form edit-form">
					<table class="iframe-table user-iframe edit-table" style="font-size: 16px" align="center"><!-- rules="rows" -->
						<tbody>
							<#list table.columns as column>
							 <#if !column.pk>
							 <#if !isforeignKey(column)>
							  	<tr class="user-remove">
								 <td class="left">${htm_notes(column)}：</td>
								 <td class="right"><label style="width: 312px">${'$'}{entity.${column.columnNameLower}}</label></td>
							    </tr>
							<@htm column=column function="textarea">
							<tr class="user-remove">
								<td valign="top" class="left"><#if !column.null><span class="iframe-not-null">*</span></#if>${htm_notes(column)}：</td>
								<td  class="td-input"><div  style="width:440px;height:100px;overflow: auto;">${user.remark}</div></td>
							</tr>
							</@htm>
							<#else>
							<tr class="user-remove">
								 <td class="left">${htm_notes(column)}：</td>
								 <td class="right"><label style="width: 312px">${'$'}{entity.${fk_notes(column.columnNameLower)}Name}</label></td>
							    </tr>
							</#if>
							 </#if>
							</#list>
						</tbody>
					</table>
					<div class="iframe-button-div" >
								<input value="取消" class='btn-base-style cancel-edit' />
								</div> 
				</form>    
</body>
<script type="text/javascript">
$(function(){
	$(".cancel-edit").click(function() {
		window.parent.PopuWnd.close();
	});
});
</script>
</html>