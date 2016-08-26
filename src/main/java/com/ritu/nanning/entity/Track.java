package com.ritu.nanning.entity;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.base.BaseEntity2;
/**
 * @function 轨迹表
 * @author cheng.G.Y
 * @date 2016-04-04
 * @latitude 1.0
 */
@Entity
@Table(name = "nann_track")
public class Track extends BaseEntity2 {
	
	//字段 START
	private Long id; //轨迹 （主键）
	private Integer carCode; //汽车编号 
	private String address; //地址 
	private String lat; //经度 
	private String lng; //维度 
	private Date tdate; //日期 
	private Integer type; //类型 
	private String dd;
	private String demoName;
	//字段 END
	
	@Transient
	public String getDemoName() {
		return (demo == null ? "" : demo.getName());
	}

	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	//外键 相关 START
	private Demo demo;
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
	public Integer getCarCode() {
		return carCode;
	}
	
	public void setCarCode(Integer carCode) {
		this.carCode = carCode;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLat() {
		return lat;
	}
	
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLng() {
		return lng;
	}
	
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getTdate() {
		return tdate;
	}
	
	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	public void setDemo(Demo demo){
		this.demo = demo;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "demoId")
	public Demo getDemo() {
		return demo;
	}
	
	
}
