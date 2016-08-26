package com.ritu.nanning.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.JsonMapper;
import com.ritu.nanning.utils.SystemCfgMap;
import com.ritu.nanning.utils.base.BaseEntity;

@Entity
@Table(name = "trff_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 2649607486421321195L;

	protected Long id; //ID
	private String numbers; //编号 
	private String loginName; //登录名
	private String realName; //真实姓名
	private String password; //密码
	private String plainPassword; //密码
	private String salt;//加盐
	private Date registerDate;  //时间
	private Boolean locked = Boolean.FALSE;
	private Role role; //角色
	private Department department;//部门
	private String job;//职位
	private String tel;//电话
	private int available;//启用
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String number) {
		this.numbers = number;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Transient
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false, updatable = true)
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Transient
	public List<SystemCfgMap.Setting> getModules() {
		List<SystemCfgMap.Setting> modules = new ArrayList<SystemCfgMap.Setting>();
		for (String module : getSettingModuleList()) {
			addToList(modules, SystemCfgMap.get(module));
		}
		return modules;
	}

	@JsonIgnore
	@Transient
	private void addToList(List<SystemCfgMap.Setting> list, String value) {
		if (value != null && !"".equalsIgnoreCase(value))
			list.add(JsonMapper.nonDefaultMapper().fromJson(value, SystemCfgMap.Setting.class));
	}

	@JsonIgnore
	@Transient
	public List<String> getSettingModuleList() {
		if (role == null)
			return new ArrayList<String>();
		return role.getSettingModuleList();
	}

	@JsonIgnore
	@Transient
	public List<String> getRoleList() {
		if (role == null)
			return new ArrayList<String>();
		return role.getModuleList();
	}
	public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
    @ManyToOne
	@JoinColumn(name = "department", nullable = false, updatable = true)
	public Department getDepartment() {
		return department;
	}
    
	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
}
