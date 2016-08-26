package com.ritu.nanning.android.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.entity.User;
import com.ritu.nanning.service.modules.UserService;


public class AndroidUserManagementController {
	private UserService userService;


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@ResponseBody
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String search(@RequestParam(value = "page", required = false) int page) {
		Page<User> results = userService.findAll(page);
		return results.getContent().toString();
	}

}
