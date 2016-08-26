package com.ritu.nanning.utils.base;

import java.util.Date;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.JsonMapper;

/**
 * custom the common attribute and method such as id and updateDate
 * 
 * @author Joe
 * 
 */
public abstract class BaseEntityVo implements BaseInterfaceVo{

	private int page; //页码
	
	protected Date updateDate; // 更新时间（同时也是查询时候用的最小时间）
	protected Date updateDate_max; // 更新时间大（同时也是查询时候用的最大时间）
	
	public static final long updateDateWidth = 30 * 150;//时间显示长度(Excel)

	@JsonIgnore
	@Override
	public String toString() {
		return JsonMapper.nonDefaultMapper().toJson(this);
	}

	@Transient
	@JsonIgnore
	@SuppressWarnings("unchecked")
	public static <T extends BaseEntityVo> T buildEntityFromJson(String jsonString, Class<? extends BaseEntityVo> clazz) {
		return (T) JsonMapper.nonDefaultMapper().fromJson(jsonString, clazz);
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUpdateDate_max() {
		return updateDate_max;
	}

	public void setUpdateDate_max(Date updateDate_max) {
		this.updateDate_max = updateDate_max;
	}

	public long getUpdateDatewidth() {
		return updateDateWidth;
	}

	public String getExporttitle() {
		// TODO Auto-generated method stub
		return "Excel";
	}

	
}
