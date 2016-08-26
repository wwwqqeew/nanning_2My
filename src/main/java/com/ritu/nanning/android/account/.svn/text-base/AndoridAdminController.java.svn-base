package com.ritu.nanning.android.account;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ritu.nanning.entity.User;
import com.ritu.nanning.service.account.AccountService;
import com.ritu.nanning.service.account.ShiroDbRealm.ShiroUser;
import com.ritu.nanning.utils.JsonBuilder;

/**
 * 
 * @author Joe
 * 
 */
@Controller
@RequestMapping(value = "/android")
public class AndoridAdminController {

	private AccountService accountService;

	
	@ResponseBody
	@RequestMapping(value = "login")
	public String login(HttpServletResponse response, @RequestParam(value = "username", required = true) String name, @RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "pid", required = true) String pid) {
		Subject currentUser = SecurityUtils.getSubject();
//		System.out.println("这是获取的硬件ID："+pid);
		if (!currentUser.isAuthenticated()) {
			try {
				UsernamePasswordToken token = new UsernamePasswordToken(name, password);
				token.setRememberMe(true);
				currentUser.login(token);
				
				Session session = currentUser.getSession();
				session.setAttribute( "serialNO", pid ); 
				
				System.out.println("Android完毕");
				
			} catch (Exception e) {
				User user = accountService.findUserByLoginName(name);
				if (user == null) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED, "登录失败，用户名不存在！");
					return "登录失败，用户名不存在！";
				} else {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED, "登录失败，密码错误！");
					return "登录失败，密码错误！";
				}
			}
		}

		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user == null || user.roleId == null) {
			return null;
		}
		
		User u = accountService.getUser(user.id);
		u.setRole(null);
		return u.toString();

	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	

	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
		}
		return JsonBuilder.toJson(true);
	}

}
