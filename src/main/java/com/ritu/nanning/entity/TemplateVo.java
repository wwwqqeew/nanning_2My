package com.ritu.nanning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseInterfaceVo;
import java.util.Date  ;
public class TemplateVo extends BaseEntityVo implements BaseInterfaceVo{


  	 
	private String input;

  	 	private Date inputDate;
	private Date inputDate_max;
			
	//Excel导出时候的长度
	private static final long idWidth = 20 * 150 ;//
	private static final long inputWidth = 20 * 150 ;//
	private static final long inputDateWidth = 20 * 150 ;//
	private static final String ExportTitle = "[例子]";
	
	/**getAndSet**/
	  	 
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getInputDate_max() {
		return inputDate_max;
	}
	
	public void setInputDate_max(Date inputDate_max) {
		this.inputDate_max = inputDate_max;
	}
			
	@JsonIgnore
	public long getIdwidth() {
		return idWidth;
	}
	@JsonIgnore
	public long getInputwidth() {
		return inputWidth;
	}
	@JsonIgnore
	public long getInputDatewidth() {
		return inputDateWidth;
	}
	
	
	@JsonIgnore
	@Override
	public String getExporttitle() {
		return ExportTitle;
	}
}
