package com.ritu.nanning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseInterfaceVo;
public class TestwjVo extends BaseEntityVo implements BaseInterfaceVo{
	  	 
	private String name;
	
	//Excel导出时候的长度
	private static final long idWidth = 20 * 150 ;//外键测试;
	private static final long nameWidth = 20 * 150 ;//名称;
	private static final String ExportTitle = "外键测试";
	
	/**getAndSet**/
	  	 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public long getIdwidth() {
		return idWidth;
	}
	@JsonIgnore
	public long getNamewidth() {
		return nameWidth;
	}
	
	@JsonIgnore
	@Override
	public String getExporttitle() {
		return ExportTitle;
	}
}
