package com.ritu.nanning.web.account;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.entity.Resource;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.service.account.AccountService;
import com.ritu.nanning.service.account.ShiroDbRealm.ShiroUser;

/**
 * 
 * @author Joe
 * 
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String html(Model model) {
		Set<String> permissions = accountService.findPermissions(getCurrentUser().getRole().getId());
        List<Resource> menus = accountService.findMenus(permissions);
        System.out.println(menus);
        model.addAttribute("menus", menus);
		return "/home/Home";
	}

	/**
	 * 获取Setting参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "setting", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getSetting() {
//		System.out.println("11111:"+getCurrentUser().toString());
		return getCurrentUser().toString();
	}

	private User getCurrentUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user == null || user.roleId == null) {
			return null;
		}
		return accountService.getUser(user.id);
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
