package com.ritu.nanning.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ritu.nanning.utils.base.BaseEntity;
@Entity
@Table(name = "sys_job")
public class Job  extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
    private Long id; //编号
    private String name; //名称

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

}
