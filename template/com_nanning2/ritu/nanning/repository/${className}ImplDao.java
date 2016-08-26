<#include "/custom.include">
<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package com.ritu.${pjName}.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ritu.${pjName}.entity.${className};
import com.ritu.${pjName}.entity.${className}Vo;
import com.ritu.${pjName}.utils.base.EntityImplDao;

/**
 * @function ${table.columns[0].columnAlias}持久层
 * @author cheng.G.Y
 * @date ${table.columns[0].nowDate}
 * @latitude 1.0
 */
@Repository
public class ${className}ImplDao implements EntityImplDao<${className}Vo>{

	@PersistenceContext
	private EntityManager em;

	public Query createQuery(String hql) {
		return em.createQuery(hql); 
	}

	
	/**
	 * 生成HQL
	 * @param isAsc 排序
	 * @param ${className}Vo (与界面交互的${className}类)
	 * @return HQL语句
	 */
	@Override
	public String getHql(boolean isAsc,Object ${classNameLower}Vo2) {
		${className}Vo ${classNameLower}Vo = (${className}Vo)${classNameLower}Vo2;
		StringBuilder hql = new StringBuilder("FROM ${className} AS o WHERE 1 =1 ");
		if (${classNameLower}Vo != null) {
		<#list table.columns as column>
		<#if !isforeignKey(column)>
			  <#if column.isNotIdOrVersionField>
			  	<#if column.isDateTimeColumn||column.isNumberColumn>
			  	<#assign my_used=0>
			  	<@java_my column=column function="_id">
			  	if(${classNameLower}Vo.get${column.columnName}() != null )
					hql.append("And o.${column.columnNameLower} = :${column.columnNameLower} ");
			  	<#assign my_used=1>
			  	</@java_my>
			  	<#if my_used == 1>
			  	<#else>
			  	if(${classNameLower}Vo.get${column.columnName}() != null ){
			  		hql.append("And o.${column.columnNameLower} >= :${column.columnNameLower} ");
			  	}if( ${classNameLower}Vo.get${column.columnName}_max() != null){
			  		hql.append("And o.${column.columnNameLower} <= :${column.columnNameLower}_max ");
			  	}
			  	</#if>
				<#elseif column.isStringColumn>
				if(${classNameLower}Vo.get${column.columnName}() != null && ${classNameLower}Vo.get${column.columnName}() != "")
					hql.append("And o.${column.columnNameLower} like :${column.columnNameLower} ");
				<#else>
				if(${classNameLower}Vo.get${column.columnName}() != null)
					hql.append("And o.${column.columnNameLower} = :${column.columnNameLower} ");
				</#if>
			  </#if>
			<#else>
			  	if(${classNameLower}Vo.get${column.columnName}() != null)
					hql.append("And o.${fk_notes(column.columnNameLower)}.id = :${column.columnNameLower} ");
			</#if>
		</#list>
		}
		hql.append("ORDER BY o.id "+(isAsc == true ? "ASC": "DESC"));
		return hql.toString();
	}
	
	/**
	 * 参数设置
	 * @param Query Query对象
	 * @param ${className}Vo (与界面交互的${className}类)
	 */
	public void setParameters(Query query, Object ${classNameLower}Vo2) {
		${className}Vo ${classNameLower}Vo = (${className}Vo)${classNameLower}Vo2;
		if (${classNameLower}Vo != null) {
		<#list table.columns as column>
		  <#if column.isNotIdOrVersionField>
		  	<#if column.isDateTimeColumn||column.isNumberColumn>
		  	<#assign my_used=0>
		  	<@java_my column=column function="_id">
		  	if(${classNameLower}Vo.get${column.columnName}() != null )
		  		query.setParameter("${column.columnNameLower}", ${classNameLower}Vo.get${column.columnName}());
		  	<#assign my_used=1>
		  	</@java_my>
		  	<#if my_used == 1>
		  	<#else>
		  	if(${classNameLower}Vo.get${column.columnName}() != null){
		  		query.setParameter("${column.columnNameLower}", ${classNameLower}Vo.get${column.columnName}());
		  	}if( ${classNameLower}Vo.get${column.columnName}_max() != null){
				query.setParameter("${column.columnNameLower}_max", ${classNameLower}Vo.get${column.columnName}_max());
		  	}
		  	</#if>
			<#elseif column.isStringColumn>
			if(${classNameLower}Vo.get${column.columnName}() != null && ${classNameLower}Vo.get${column.columnName}() != "")
				query.setParameter("${column.columnNameLower}", "%" + ${classNameLower}Vo.get${column.columnName}() + "%");
			<#else>
			if(${classNameLower}Vo.get${column.columnName}() != null)
				query.setParameter("${column.columnNameLower}", ${classNameLower}Vo.get${column.columnName}());
			</#if>
		 </#if>
		</#list>
		}
	}
	
}
