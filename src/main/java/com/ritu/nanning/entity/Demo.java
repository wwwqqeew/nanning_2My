package com.ritu.nanning.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.utils.base.BaseEntity2;
/**
 * @function demo表
 * @author cheng.G.Y
 * @date 2016-04-04
 * @latitude 1.0
 */
@Entity
@Table(name = "nann_demo")
public class Demo extends BaseEntity2 {
	
	//字段 START
	private Long id; //demo （主键）
	private String name; //名称1 
	private String name2; //名称2 
	//字段 END
	
	//外键 相关 START
	private Set<Track> tracks = new HashSet<Track>(0);
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
	
	
	public void setTracks(Set<Track> track){
		this.tracks = track;
	}
	
	@JsonIgnore
	@OneToMany(targetEntity = com.ritu.nanning.entity.Track.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "demo")
	@Fetch(FetchMode.SELECT)
	public Set<Track> getTracks() {
		return tracks;
	}
	
	
}
