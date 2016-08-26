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

import com.ritu.nanning.entity.Testwj;
import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.service.modules.TestwjService;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.base.BaseEditControl;
import com.ritu.nanning.utils.base.BaseService;

@Controller
@RequestMapping(value = "/edittestwj")
public class EditTestwjController  extends BaseEditControl<Testwj,Long>{
	
	private TestwjService testwjService;

	@RequestMapping(method = RequestMethod.POST)
	public String html(Model model) {
		return "/modules/EditTestwj";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String htmlGet(Model model) {
		return "/modules/EditTestwj";
	}

	
	@Autowired
	public void setTestwjService(TestwjService testwjService) {
		this.testwjService = testwjService;
	}
	
	@Override
	protected BaseService getEntityService() {
		return testwjService;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "Testwj";
	}
}
