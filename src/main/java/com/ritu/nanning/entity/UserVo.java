package com.ritu.nanning.entity;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserVo extends User{
	
	private static final long serialVersionUID = 1L;

//	private String loginName; //登录名
//	private String number; //编号 
//	private String password; //密码
//	private String realName; //真实姓名
//  	private Date registerDate; //时间
	private Date registerDate_max; //时间最大（模糊查询用）
//	private String salt;
//	private Role role; //角色
  	private int page;
//	public String getLoginName() {
//		return loginName;
//	}
//	public void setLoginName(String loginName) {
//		this.loginName = loginName;
//	}
//	public String getNumber() {
//		return number;
//	}
//	public void setNumber(String number) {
//		this.number = number;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getRealName() {
//		return realName;
//	}
//	public void setRealName(String realName) {
//		this.realName = realName;
//	}
//	public Date getRegisterDate() {
//		return registerDate;
//	}
//	public void setRegisterDate(Date registerDate) {
//		this.registerDate = registerDate;
//	}
	public Date getRegisterDate_max() {
		return registerDate_max;
	}
	public void setRegisterDate_max(Date registerDate_max) {
		this.registerDate_max = registerDate_max;
	}
//	public String getSalt() {
//		return salt;
//	}
//	public void setSalt(String salt) {
//		this.salt = salt;
//	}
//	@ManyToOne
//	@JoinColumn(name = "role_id", nullable = false, updatable = true)
//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
}
