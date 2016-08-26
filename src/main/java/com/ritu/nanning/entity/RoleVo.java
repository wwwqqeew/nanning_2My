package com.ritu.nanning.entity;

import java.util.Set;


public class RoleVo extends Role{
	
	private static final long serialVersionUID = 1L;

//	private String name; //登录名
//	private Set<User> user; //角色
  	private int page;
	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public Set<User> getUser() {
//		return user;
//	}
//	public void setUser(Set<User> user) {
//		this.user = user;
//	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
}
