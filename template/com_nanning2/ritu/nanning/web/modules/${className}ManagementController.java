<#include "/custom.include">
<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#include "/java_util.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package com.ritu.${pjName}.web.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.${pjName}.entity.${className};
import com.ritu.${pjName}.entity.${className}Vo;
import com.ritu.${pjName}.service.modules.${className}Service;
import com.ritu.${pjName}.utils.JsonBuilder;
import com.ritu.${pjName}.utils.base.BaseControl;
import com.ritu.${pjName}.utils.base.BaseEntityVo;
import com.ritu.${pjName}.utils.base.BaseService;

/**
 * @function ${table.columns[0].columnAlias}控制层
 * @author cheng.G.Y
 * @date ${table.columns[0].nowDate}
 * @latitude 1.0
 */
@Controller
@RequestMapping(value = "/${classNameLower}management")
public class ${className}ManagementController extends BaseControl<${className},<@getTablePkType/>>{
	
	private ${className}Service ${classNameLower}Service;//业务层对象
	private ${className}Vo ${classNameLower}Vo = new ${className}Vo();

	@Autowired
	public void set${className}Service(${className}Service ${classNameLower}Service) {
		this.${classNameLower}Service = ${classNameLower}Service;
	}

	/**
	 * POST方法要返回的页面位置
	 * @param model
	 * @return 页面位置
	 */
	@RequestMapping()
	public String html(Model model) {
		return "/modules/${className}Management";
	}
	
	@Override
	protected BaseService getEntityService() {
		return ${classNameLower}Service;
	}

	@Override
	protected BaseEntityVo getBaseEntityVo() {
		return ${classNameLower}Vo;
	}

}
