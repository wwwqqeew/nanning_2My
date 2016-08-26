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

import java.util.Date  ;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @function [例子]表
 * @author cheng.G.Y
 * @date 2016-06-14
 * @latitude 1.0
 */
@Entity
@Table(name = "nann_template")
public class Template extends BaseEntity2 {
	
	//字段 START
	private Integer id; //[例子] （主键）
	private String input; //[输入框] 
	private Date inputDate; //[时间] 
	//字段 END
	
	//外键 相关 START
	//外键 相关 END
	
	//getAndSet
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input = input;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getInputDate() {
		return inputDate;
	}
	
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	
	
}
