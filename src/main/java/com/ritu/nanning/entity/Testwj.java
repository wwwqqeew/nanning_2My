package com.ritu.nanning.entity;
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
 * @date 2016-04-17
 * @latitude 1.0
 */
@Entity
@Table(name = "nann_testwj")
public class Testwj extends BaseEntity2 {
	
	//字段 START
	private Long id; //外键测试 （主键）
	private String name; //名称 
	//字段 END
	
	//外键 相关 START
	private Set <Testwjot> testwjots = new HashSet<Testwjot>(0);
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
	
	
	public void setTestwjots(Set<Testwjot> testwjot){
		this.testwjots = testwjot;
	}
	
	@JsonIgnore
	@OneToMany(targetEntity = com.ritu.nanning.entity.Testwjot.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "testwj")
	@Fetch(FetchMode.SELECT)
	public Set<Testwjot> getTestwjots() {
		return testwjots;
	}
	
	
}
