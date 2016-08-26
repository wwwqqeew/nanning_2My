package com.ritu.nanning.entity;
import java.beans.Transient;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.entity.Track;
import com.ritu.nanning.utils.base.BaseEntity2;

/**
 * @function 外键测试表
 * @author cheng.G.Y
 * @date 2016-04-22
 * @latitude 1.0
 */
@Entity
@Table(name = "nann_testwjot")
public class Testwjot extends BaseEntity2 {
	
	//字段 START
	private Long id; //外键测试 （主键）
	private String name; //名称 
	//字段 END
	
	//外键 相关 START
	private String testwjName;//外键名称
	private Testwj testwj;
	//外键 相关 END
	
	//getAndSet
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	
	@Transient
	public String getTestwjName() {
		return (testwj == null ? "" : testwj.getName());
	}

	public void setTestwjName(String demoName) {
		this.testwjName = testwjName;
	}
	
	public void setTestwj(Testwj testwj){
		this.testwj = testwj;
	}
	
	//@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	//@JoinColumn(name = "testwjId",nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "testwjId")
	public Testwj getTestwj() {
		return testwj;
	}
	
	
}
