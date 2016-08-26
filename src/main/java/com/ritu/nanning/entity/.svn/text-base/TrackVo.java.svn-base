package com.ritu.nanning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseInterfaceVo;
import java.util.Date  ;
public class TrackVo extends BaseEntityVo implements BaseInterfaceVo{
	  	 	private Integer carCode;
	private Integer carCode_max;
			  	 
	private String address;
	  	 
	private String lat;
	  	 
	private String lng;
	  	 	private Date tdate;
	private Date tdate_max;
			  	 	private Integer type;
	private Integer type_max;
			
	//Excel导出时候的长度
	private static final long idWidth = 20 * 150 ;//轨迹;
	private static final long carCodeWidth = 20 * 150 ;//汽车编号;
	private static final long addressWidth = 20 * 150 ;//地址;
	private static final long latWidth = 20 * 150 ;//经度;
	private static final long lngWidth = 20 * 150 ;//维度;
	private static final long tdateWidth = 20 * 150 ;//日期;
	private static final long typeWidth = 20 * 150 ;//类型;
	private static final String ExportTitle = "轨迹";
	
	/**getAndSet**/
	  	 	  		
	public Integer getCarCode() {
		return carCode;
	}
	
	public void setCarCode(Integer carCode) {
		this.carCode = carCode;
	}
	
	public Integer getCarCode_max() {
		return carCode_max;
	}
	
	public void setCarCode_max(Integer carCode_max) {
		this.carCode_max = carCode_max;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getTdate_max() {
		return tdate_max;
	}
	
	public void setTdate_max(Date tdate_max) {
		this.tdate_max = tdate_max;
	}
			  	 	  		
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getType_max() {
		return type_max;
	}
	
	public void setType_max(Integer type_max) {
		this.type_max = type_max;
	}
			
	@JsonIgnore
	public long getIdwidth() {
		return idWidth;
	}
	@JsonIgnore
	public long getCarCodewidth() {
		return carCodeWidth;
	}
	@JsonIgnore
	public long getAddresswidth() {
		return addressWidth;
	}
	@JsonIgnore
	public long getLatwidth() {
		return latWidth;
	}
	@JsonIgnore
	public long getLngwidth() {
		return lngWidth;
	}
	@JsonIgnore
	public long getTdatewidth() {
		return tdateWidth;
	}
	@JsonIgnore
	public long getTypewidth() {
		return typeWidth;
	}
	
	@JsonIgnore
	@Override
	public String getExporttitle() {
		return ExportTitle;
	}
}
