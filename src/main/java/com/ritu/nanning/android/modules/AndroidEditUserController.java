package com.ritu.nanning.android.modules;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.entity.User;
import com.ritu.nanning.service.account.AccountService;
import com.ritu.nanning.service.modules.UserService;
import com.ritu.nanning.utils.JsonBuilder;

@Controller
@RequestMapping(value = "/androidedituser")
public class AndroidEditUserController {
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	private UserService userService;
	private AccountService accountService;
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Autowired
	public void setUserService(UserService userService) {
	}

	@ResponseBody
	@RequestMapping(value = "checkpw",  produces = "text/html;charset=UTF-8")
	public String checkpw(@RequestParam(value = "id", required = false) Long id,@RequestParam(value = "passwordOld", required = false) String passwordOld,@RequestParam(value = "passwordNew", required = false) String passwordNew) {
		User user = new User();
		String theReturn = "0";
		user = accountService.getUser(id);
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			try {
				UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), passwordOld);
				token.setRememberMe(true);
				currentUser.login(token);
				user.setPlainPassword(passwordNew);
				accountService.updateUser(user);
				theReturn = "1";
			} catch (Exception e) {
				
			}
		}
		return theReturn;
	}
	
}

