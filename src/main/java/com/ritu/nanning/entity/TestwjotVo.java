package com.ritu.nanning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseInterfaceVo;
public class TestwjotVo extends BaseEntityVo implements BaseInterfaceVo{


  	 
	private String name;

  	   	private Long testwjId;
			
	//Excel导出时候的长度
	private static final long idWidth = 20 * 150 ;//
	private static final long nameWidth = 20 * 150 ;//
	private static final String ExportTitle = "外键测试";
	
	/**getAndSet**/
	  	 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Long getTestwjId() {
		return testwjId;
	}
	
	public void setTestwjId(Long testwjId) {
		this.testwjId = testwjId;
	}
	
	@JsonIgnore
	public long getIdwidth() {
		return idWidth;
	}
	@JsonIgnore
	public long getNamewidth() {
		return nameWidth;
	}
	
		private static final long testwjNameWidth = 20 * 150 ;//
		
		@JsonIgnore
		public long gettestwjNamewidth() {
			return testwjNameWidth;
		}
	
	@JsonIgnore
	@Override
	public String getExporttitle() {
		return ExportTitle;
	}
}
