package com.ritu.nanning.web.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.service.modules.DemoService;
import com.ritu.nanning.utils.base.BaseEditControl;
import com.ritu.nanning.utils.base.BaseService;

@Controller
@RequestMapping(value = "/editdemo")
public class EditDemoController extends BaseEditControl<Demo,Long>{
	
	private DemoService demoService;

	
	@Autowired
	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	@Override
	protected BaseService getEntityService() {
		return demoService;
	}

	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return "Demo";
	}

}
