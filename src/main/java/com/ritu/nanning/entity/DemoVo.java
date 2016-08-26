package com.ritu.nanning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseInterfaceVo;
public class DemoVo extends BaseEntityVo implements BaseInterfaceVo{
	  	 
	private String name;
	  	 
	private String name2;
	
	//Excel导出时候的长度
	private static final long idWidth = 20 * 150;
	private static final long nameWidth = 25 * 150;
	private static final long name2Width = 30 * 150;
	private static final String ExportTitle = "例子管理"; //导出的Excel的名称
	
	/**getAndSet**/
	  	 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	  	 
	public String getName2() {
		return name2;
	}
	
	public void setName2(String name2) {
		this.name2 = name2;
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
	public long getName2width() {
		return name2Width;
	}
	
	@JsonIgnore
	@Override
	public String getExporttitle() {
		return ExportTitle;
	}
	
}
