package com.ritu.nanning.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.base.BaseEntity;

@Entity
@Table(name = "trff_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 5827901479241439156L;

	protected Long id; // 权限ID
	private String name; // 权限名称
	 private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private String resources; //拥有的资源
	private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	private Set<User> users;
	@OneToMany(mappedBy="role", fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	/**
	 * 获取Setting参数的时候获取权限列表
	 * 
	 * @return 能加载的权限列表
	 */
	@Transient
	@JsonIgnore
	public List<String> getSettingModuleList() {
		List<String> modulesList = new ArrayList<String>();
		modulesList.add("Help");
		 if(resources != null) {
        	 String[] arr =resources.split(",");
             for(String s : arr){
            	 if(Long.parseLong(s)==90){
               		 modulesList.add("UserManagement2"); 
               		 modulesList.add("roleManagement2"); 
               	 }
               	 if(100<Long.parseLong(s)&&Long.parseLong(s)<106){
               		 modulesList.add("UserManagement2"); 
               	 }
               	 if(Long.parseLong(s)==121){
               		 modulesList.add("roleManagement2"); 
               	 }
             }
        }
		
		return modulesList;
	}

	/**
	 * 页面初始化的时候根据权限加载相应的页面（和页面显示内容相关）
	 * 控制页面权限
	 * @return 能加载的页面列表
	 */
	@Transient
	@JsonIgnore
	public List<String> getModuleList() {
		List<String> modulesList = new ArrayList<String>();
		 if(resources != null) {
       	 String[] arr =resources.split(",");
            for(String s : arr){
           	 if(Long.parseLong(s)==90){
           		 System.out.println(Long.parseLong(s));
           		 modulesList.add("UserManagement2"); 
           		 modulesList.add("roleManagement2"); 
           	 }
           	if(100<Long.parseLong(s)&&Long.parseLong(s)<106){
           		 System.out.println(Long.parseLong(s));
           		 modulesList.add("UserManagement2"); 
           	 }
           	 if(Long.parseLong(s)==121){
           		 System.out.println(Long.parseLong(s));
           		 modulesList.add("roleManagement2"); 
           	 }
            }
       }
		return modulesList;
	}
}
