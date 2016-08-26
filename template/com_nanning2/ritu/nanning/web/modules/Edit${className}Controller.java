<#include "/custom.include">
<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#include "/java_util.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package com.ritu.${pjName}.web.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.${pjName}.entity.${className};
import com.ritu.${pjName}.entity.Demo;
import com.ritu.${pjName}.service.modules.${className}Service;
import com.ritu.${pjName}.utils.JsonBuilder;
import com.ritu.${pjName}.utils.base.BaseEditControl;
import com.ritu.${pjName}.utils.base.BaseService;

@Controller
@RequestMapping(value = "/edit${classNameLower}")
public class Edit${className}Controller  extends BaseEditControl<${className},<@getTablePkType/>>{
	
	private ${className}Service ${classNameLower}Service;

	@RequestMapping(method = RequestMethod.POST)
	public String html(Model model) {
		return "/modules/Edit${className}";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String htmlGet(Model model) {
		return "/modules/Edit${className}";
	}

	<#list table.columns as column>
	 <#if !column.pk>
	 <@java_my column=column function="sm">
	 /**
	  * 同名检测
	  * @param ${column.columnNameLower} 要检测的名字
	  * @return 检测结果
	  */
	@ResponseBody
	@RequestMapping(value = "checkname", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String countBy${column.columnName}(@RequestParam(value = "${column.columnNameLower}", required = false) String ${column.columnNameLower}) {
		return JsonBuilder.toJson(true,${classNameLower}Service.countBy${column.columnName}(${column.columnNameLower}));
	}
	 </@java_my>
	 </#if>
	</#list>
	
	@Autowired
	public void set${className}Service(${className}Service ${classNameLower}Service) {
		this.${classNameLower}Service = ${classNameLower}Service;
	}
	
	@Override
	protected BaseService getEntityService() {
		return ${classNameLower}Service;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "${className}";
	}
}
