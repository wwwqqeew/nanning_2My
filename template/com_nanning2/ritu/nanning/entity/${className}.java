<#include "/custom.include">
<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package com.ritu.${pjName}.entity;
import java.beans.Transient;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritu.nanning.entity.Track;
import com.ritu.nanning.utils.base.BaseEntity2;

<#assign datetimeS = 0>
<#list table.columns as column>
<@htm_css_js column=column function="datetime" theT=datetimeS>
import java.util.Date  ;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
<#assign datetimeS = 1>
</@htm_css_js>
</#list>
/**
 * @function ${table.columns[0].columnAlias}表
 * @author cheng.G.Y
 * @date ${table.columns[0].nowDate}
 * @latitude 1.0
 */
@Entity
@Table(name = "${pjjx}_${classNameLower}")
public class ${className} extends BaseEntity2 {
	
	<#if table.compositeId>
<#else>
	//字段 START
	<#list table.columns as column>
	<#--<#if (!column.pk) && (column.columnNameLower!="remark") && (column.columnNameLower?index_of("$sm$") >-1>-->
	<#if !isforeignKey(column)>
	private ${java_p(column)} ${column.columnNameLower}; //${htm_notes(column)} <#if (column.pk)>（主键）</#if>
	</#if>
	<#--</#if>-->
	</#list>
	//字段 END
	
	//外键 相关 START
	<@generateJavaOneToManyColumns/>
	<@generateJavaManyToOneColumns/>
	//外键 相关 END
	
	//getAndSet
	<#list table.columns as column>
	<#--<#if !column.pk && (column.columnNameLower!="remark")>-->
	<#if column.isDateTimeColumn>
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public ${java_p(column)} get${column.columnName}() {
		return ${column.columnNameLower};
	}
	
	public void set${column.columnName}(${java_p(column)} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	<#elseif column.pk>
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public ${java_p(column)} get${column.columnName}() {
		return ${column.columnNameLower};
	}

	public void set${column.columnName}(${java_p(column)} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	<#else>
	<#if !isforeignKey(column)>
	public ${java_p(column)} get${column.columnName}() {
		return ${column.columnNameLower};
	}
	
	public void set${column.columnName}(${java_p(column)} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	</#if>

	<#--</#if>-->
	</#if>
	</#list>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>
	
	<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	@JsonIgnore
	@OneToMany(targetEntity = com.ritu.nanning.entity.${fkPojoClass}.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "${classNameLower}")
	@Fetch(FetchMode.SELECT)
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	@Transient
	public String get${fkPojoClass}Name() {
		return (${fkPojoClassVar} == null ? "" : ${fkPojoClassVar}.getName());
	}

	public void set${fkPojoClass}Name(String demoName) {
		this.${fkPojoClassVar}Name = ${fkPojoClassVar}Name;
	}
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	//@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	<#list foreignKey.parentColumns?values as fkColumn>
	//@JoinColumn(name = "${fkColumn}",nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "${fkPojoClass?uncap_first}Id")
	</#list>
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>

<#macro generateJavaOneToManyColumns>
<#list table.exportedKeys.associatedTables?values as foreignKey>
<#assign fkSqlTable = foreignKey.sqlTable>
<#assign fkTable    = fkSqlTable.className>
<#assign fkPojoClass = fkSqlTable.className>
<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	private Set <${fkPojoClass}> ${fkPojoClassVar}s = new HashSet<${fkPojoClass}>(0);
</#list>
</#macro>

<#macro generateJavaManyToOneColumns>
<#list table.importedKeys.associatedTables?values as foreignKey>
<#assign fkSqlTable = foreignKey.sqlTable>
<#assign fkTable    = fkSqlTable.className>
<#assign fkPojoClass = fkSqlTable.className>
<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	private String ${fkPojoClassVar}Name;//外键名称
	private ${fkPojoClass} ${fkPojoClassVar};
</#list>
</#macro>
	
</#if>
}
