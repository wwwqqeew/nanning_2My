package com.ritu.nanning.web.account;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.util.WebUtils;

import com.ritu.nanning.entity.User;
import com.ritu.nanning.service.account.AccountService;
import com.ritu.nanning.service.account.ShiroDbRealm.ShiroUser;

/**
 * 
 * @author Joe
 * 
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private AccountService accountService;


	@RequestMapping(method = RequestMethod.GET)
	public String html() {
		return "account/Login";
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletResponse response, @RequestParam(value = "username", required = true) String name, @RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "rememberMe", required = false) boolean rememberMe, @RequestParam(value = "verifycode", required = true) String verifycode, HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();

		String sessionVcode = (String) WebUtils.getSessionAttribute(request, "verifyCode");
		// (String) ActionContext.getContext().getSession().get("verifyCode");
		// System.out.println("sessionVcode = " + sessionVcode);
		// System.out.println("verifycode = " + verifycode);
		
		if (!(verifycode != null && sessionVcode != null && sessionVcode.equals(verifycode))) {// 验证码检测
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED, "验证码不正确！");
			return "验证码不正确！";
		}

		if (!currentUser.isAuthenticated()) {
			try {
				UsernamePasswordToken token = new UsernamePasswordToken(name, password);
				token.setRememberMe(true);
				currentUser.login(token);
				Session session = currentUser.getSession();
				String ip = getIp(request);
				session.setAttribute( "IP", ip); 
				session.setAttribute( "serialNO", name+"PC" ); 
//				System.out.println("完成了登录验证");
//				saveWebLoginLog
//				session.setAttribute( "pid", pid ); 
//				String userID = logsSaveService.getLoginIDByLoginName(name);
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
		return u.toString();
	}

	/**获取IP
	 * @param request
	 */
	public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getRemoteAddr();
	       }
//	       System.out.println("这是ID:"+ip);
	       return ip;
	}
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
