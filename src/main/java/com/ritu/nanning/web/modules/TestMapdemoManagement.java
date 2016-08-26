package com.ritu.nanning.web.modules;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/testmapdemomanagement")
public class TestMapdemoManagement {

	@RequestMapping(method = RequestMethod.POST)
	public String html(Model model) {
		return "/modules/TestMapdemoManagement";
	}
}
