package com.ritu.nanning.utils.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.JsonMapper;

/**
 * custom the common attribute and method such as id and updateDate
 * 
 * @author Joe
 * 
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 15643919053873756L;

	protected Date updateDate; // 时间
	private String remark; // 备注

	public BaseEntity() {
		super();
		this.updateDate = new Date();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Override
	public String toString() {
		return JsonMapper.nonDefaultMapper().toJson(this);
	}

	@Transient
	@JsonIgnore
	@SuppressWarnings("unchecked")
	public static <T extends BaseEntity> T buildEntityFromJson(String jsonString, Class<? extends BaseEntity> clazz) {
		return (T) JsonMapper.nonDefaultMapper().fromJson(jsonString, clazz);
	}
}
