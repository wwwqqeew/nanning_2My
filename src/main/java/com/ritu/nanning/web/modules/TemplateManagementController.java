package com.ritu.nanning.web.modules;

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

import com.ritu.nanning.entity.Template;
import com.ritu.nanning.entity.TemplateVo;
import com.ritu.nanning.service.modules.TemplateService;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.base.BaseControl;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseService;

/**
 * @function [例子]控制层
 * @author cheng.G.Y
 * @date 2016-06-14
 * @latitude 1.0
 */
@Controller
@RequestMapping(value = "/templatemanagement")
public class TemplateManagementController extends BaseControl<Template,Integer>{
	
	private TemplateService templateService;//业务层对象
	private TemplateVo templateVo = new TemplateVo();

	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	/**
	 * POST方法要返回的页面位置
	 * @param model
	 * @return 页面位置
	 */
	@RequestMapping()
	public String html(Model model) {
		return "/modules/TemplateManagement";
	}
	
	@Override
	protected BaseService getEntityService() {
		return templateService;
	}

	@Override
	protected BaseEntityVo getBaseEntityVo() {
		return templateVo;
	}

}
