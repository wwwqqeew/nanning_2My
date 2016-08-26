package com.ritu.nanning.web.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.service.account.AccountService;
import com.ritu.nanning.service.account.RoleService;
import com.ritu.nanning.utils.DateParser;

/**
 * 初始化注册
 * Realm
 * @author Joe
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private AccountService accountService;
	
	private RoleService roleService;

	
	private static int isInit = 0;

	@RequestMapping(method = RequestMethod.GET)
	public String admin() {
		if (isInit == 0) {
			if (!accountService.isExist(1l))
				isInit = 2;
			else {
				isInit = 1;
			}
		}
		if (isInit == 1) {
			return "redirect:login";
		}
		return "account/Admin";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String adminSetting(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
		if (!accountService.isExist(1l))
			isInit = 2;
		else {
			isInit = 1;
		}
		if (isInit == 2) {
			initAccount(loginName, password);
			initRoleS();
			isInit = 1;
		}
		return "redirect:login";
	}

	private void initAccount(String loginName, String password) {
		User user = new User();
		user.setId(1l);
		user.setNumbers("0");
		user.setLoginName(loginName);
		user.setPlainPassword(password);
		user.setRole(initRole());
		user.setUpdateDate(new Date());
		user.setRegisterDate(new Date());
		user.setRealName("系统管理员");
		user.setRemark("系统管理员");

		roleService.add(user.getRole());
		accountService.registerUser(user);
		
		Setconfigure();
	}

	//配置管理信息
	private void Setconfigure() {
	}

	public Role initRole() {
		Role role = new Role();
		role.setId(1L);
		role.setName("超级管理员");
		role.setRemark("超级管理员");
		
		/*role.setUserManagement(true);
//		role.setAddUser(true);
//		role.setDelUser(true);
//		role.setEditUser(true);
//		role.setShowUser(true);
		
		role.setRoleManagement(true);
//		role.setAddRole(true);
//		role.setDelRole(true);
//		role.setEditRole(true);
//		role.setShowRole(true);
		
		role.setTestManagement(true);
		role.setDemoManagement(true);
		role.setProcessManagement(true);
		role.setTestMapdemoManagement(true);
		role.setMirrorvoManagement(true);*/
		
		
		role.setUpdateDate(DateParser.getCurrentDate());
		return role;
	}

	public void initRoleS() {
		Role role = new Role();

		role.setId(2l);
		role.setName("管理员");
		role.setRemark("管理员");
		role.setUpdateDate(DateParser.getCurrentDate());
		roleService.add(role);

		role = new Role();
		role.setId(3l);
		role.setName("后台人员");
		role.setRemark("后台人员");
		role.setUpdateDate(DateParser.getCurrentDate());
		roleService.add(role);

	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
