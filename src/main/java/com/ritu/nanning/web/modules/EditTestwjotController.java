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

import com.ritu.nanning.entity.Testwjot;
import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.service.modules.TestwjotService;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.base.BaseEditControl;
import com.ritu.nanning.utils.base.BaseService;

@Controller
@RequestMapping(value = "/edittestwjot")
public class EditTestwjotController  extends BaseEditControl<Testwjot,Long>{
	
	private TestwjotService testwjotService;

	@RequestMapping(method = RequestMethod.POST)
	public String html(Model model) {
		return "/modules/EditTestwjot";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String htmlGet(Model model) {
		return "/modules/EditTestwjot";
	}

	
	@Autowired
	public void setTestwjotService(TestwjotService testwjotService) {
		this.testwjotService = testwjotService;
	}
	
	@Override
	protected BaseService getEntityService() {
		return testwjotService;
	}
	
	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "Testwjot";
	}
}
