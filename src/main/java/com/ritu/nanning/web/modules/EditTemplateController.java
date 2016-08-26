package com.ritu.nanning.web.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.entity.Template;
import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.service.modules.TemplateService;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.base.BaseEditControl;
import com.ritu.nanning.utils.base.BaseService;

@Controller
@RequestMapping(value = "/edittemplate")
public class EditTemplateController  extends BaseEditControl<Template,Integer>{
	
	private TemplateService templateService;

	@RequestMapping(method = RequestMethod.POST)
	public String html(Model model) {
		return "/modules/EditTemplate";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String htmlGet(Model model) {
		return "/modules/EditTemplate";
	}

	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Override
	protected BaseService getEntityService() {
		return templateService;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "Template";
	}
}
