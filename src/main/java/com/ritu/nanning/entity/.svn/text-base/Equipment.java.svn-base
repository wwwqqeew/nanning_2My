package com.ritu.nanning.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ritu.nanning.utils.base.BaseEntity;

@Entity
@Table(name = "nann_equipment")
public class Equipment extends BaseEntity {
	@Id
	@GeneratedValue
	Long id;
	String owner;
	@Temporal(TemporalType.TIMESTAMP)
	Date createdate;
	
	@ManyToOne
	@JoinColumn(name="creater")
	User creater;
	
	@ManyToOne
	@JoinColumn(name = "department")
	Department department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	

}
