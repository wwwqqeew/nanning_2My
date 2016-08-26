<#include "/custom.include">
<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#include "/java_util.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package com.ritu.${pjName}.service.modules;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.${pjName}.entity.${className};
import com.ritu.${pjName}.entity.${className}Vo;
import com.ritu.${pjName}.entity.*;
import com.ritu.${pjName}.repository.${className}Dao;
import com.ritu.${pjName}.repository.${className}ImplDao;
import com.ritu.${pjName}.repository.*;
import com.ritu.${pjName}.utils.base.EntityImplDao;
import com.ritu.${pjName}.utils.base.BaseService;
import com.ritu.${pjName}.utils.base.EntityDao;
import com.ritu.${pjName}.utils.PageSetting;
import com.ritu.nanning.utils.DateUtil;
import com.ritu.nanning.utils.excel.ImportMsg;

/**
 * @function ${table.columns[0].columnAlias}业务层
 * @author cheng.G.Y
 * @date ${table.columns[0].nowDate}
 * @latitude 1.0
 */
@Component
@Transactional(readOnly = true)
public class ${className}Service extends BaseService<${className},<@getTablePkType/>>{

	private ${className}Dao ${classNameLower}Dao;//JPA持久层对象

	private EntityImplDao ${classNameLower}ImplDao;//持久层对象
	
	@Override
	protected EntityDao getEntityDao() {
		return this.${classNameLower}Dao;
	}
	@Override
	protected PagingAndSortingRepository getPASRDao() {
		// TODO Auto-generated method stub
		return this.${classNameLower}Dao;
	}
	@Override
	protected EntityImplDao getEntityImplDao() {
		// TODO Auto-generated method stub
		return ${classNameLower}ImplDao;
	}
	
	<#list table.columns as column>
	 <#if !column.pk>
	 <@java_my column=column function="sm">
	/**
	 * 同名检测
	 * @param 要检测的参数
	 * return true 
	 */
	public  boolean  countBy${column.columnName}(String ${column.columnNameLower}){
		return ${classNameLower}Dao.countBy${column.columnName}(${column.columnNameLower})==0l?true:false;
	}
	 </@java_my>
	 </#if>
	</#list>

	@Autowired
	public void set${className}Dao(${className}Dao ${classNameLower}Dao) {
		this.${classNameLower}Dao = ${classNameLower}Dao;
	}
	
	@Autowired
	public void set${className}ImplDao(EntityImplDao ${classNameLower}ImplDao) {
		this.${classNameLower}ImplDao = ${classNameLower}ImplDao;
	}
	

	/**
	 * 导入Excel
	 * @param list
	 */
	@Override
	@Transactional(readOnly = false)
	public ImportMsg importExcel(List<List<String>> list) {
		
//		List<List<String>> list = testPoiU("d:\\新文件名.xls");
//		System.out.println("数据条数："+list.size());
//		for (int i = 0; i < list.size(); i++) {
//			System.out.print(i +" ");
//			for (int j = 0; j < list.get(i).size(); j++) {
//				System.out.print("["+j+"]" + list.get(i).get(j)+"  ");
//			}
//			System.out.println(" ");
//		}
		
		ImportMsg msg = new ImportMsg();
		msg.allDataInt = list.size()-1;
		StringBuffer MSG = new StringBuffer();

		//外键List
		<#list table.importedKeys.associatedTables?values as foreignKey>
		<#assign fkSqlTable = foreignKey.sqlTable>
		<#assign fkTable    = fkSqlTable.className>
		<#assign fkPojoClass = fkSqlTable.className>
		<#assign fkPojoClassVar = fkPojoClass?uncap_first>
		HashMap<String, Long> ${fkPojoClassVar}HashMap = new HashMap<String, Long>();//${fkPojoClassVar}
		get${fkPojoClass}HashMap();
		</#list>
		//外键List
		
		for (int i = 1; i < list.size(); i++) {
		//	for (int j = 0; j < list.get(i).size(); j++) {
				//System.out.print("["+j+"] " + list.get(i).get(j));
				
				int statrVal = 1;//读取数据的开始行数，第一列为列序号，第二为编号，数据第三开始
				${className} ${classNameLower} = new ${className}();
				//Excel列表检测
				<#list table.columns as column>
				<#if !isforeignKey(column)>
					<#if column.isDateTimeColumn>
					//时间类型检测
					//${htm_notes(column)}
					String data${column.columnName} = list.get(i).get(statrVal++).trim();//
					try {
						${classNameLower}.set${column.columnName}(DateUtil.StringToDate(data${column.columnName}));//日期
					} catch (Exception e) {
						msg.FaltSaveInt++;
						MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，日期格式不正确<br>\n");
						continue;
					}
					<#elseif column.pk>
					//主键
					<#elseif !column.null>
					//${htm_notes(column)}
					String data${column.columnName} = list.get(i).get(statrVal++).trim();
					try {
						${classNameLower}.set${column.columnName}(data${column.columnName});
					} catch (Exception e) {
						msg.FaltSaveInt++;
						MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，${htm_notes(column)}不正确能为空<br>\n");
						continue;
					}
					<#else>
					//${htm_notes(column)}
					${classNameLower}.set${column.columnName}(list.get(i).get(statrVal++).trim());
					</#if>
					<#else>
				//设置货品名称(外键)
				<#if !column.null>
				String ${fk_notes(column.columnNameLower)}s = list.get(i).get(statrVal++).trim();
				${fk_notes(column.columnName)} ${fk_notes(column.columnName)} = new ${fk_notes(column.columnName)}(); 
				if (${fk_notes(column.columnNameLower)}s != null && !"".equals(${fk_notes(column.columnNameLower)}s)) {
					//不存在，则新增
					if (${fk_notes(column.columnNameLower)}HashMap.get(${fk_notes(column.columnNameLower)}s) != null) {
						${fk_notes(column.columnName)}.setId(${fk_notes(column.columnNameLower)}HashMap.get(${fk_notes(column.columnName)}));
						${fk_notes(column.columnName)}.setName(${fk_notes(column.columnNameLower)}s);
						${classNameLower}.set${fk_notes(column.columnName)}(${fk_notes(column.columnName)});
					}else{
						msg.FaltSaveInt++;
						MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，${fk_notes(column.columnName)}名称["+${fk_notes(column.columnName)}+"]不存在<br>\n");
						continue;
					}
					
				}else{
					msg.FaltSaveInt++;
					MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，${fk_notes(column.columnName)}名称["+${fk_notes(column.columnName)}+"]不能为空<br>\n");
					continue;
				}
				<#else>
				String ${fk_notes(column.columnNameLower)}s = list.get(i).get(statrVal++).trim();
				${fk_notes(column.columnName)} ${fk_notes(column.columnName)} = new ${fk_notes(column.columnName)}(); 
				if (${fk_notes(column.columnNameLower)}s != null && !"".equals(${fk_notes(column.columnNameLower)}s)) {
					//不存在，则新增
					if (${fk_notes(column.columnNameLower)}HashMap.get(${fk_notes(column.columnNameLower)}s) != null) {
						${fk_notes(column.columnName)}.setId(${fk_notes(column.columnNameLower)}HashMap.get(${fk_notes(column.columnName)}));
						${fk_notes(column.columnName)}.setName(${fk_notes(column.columnNameLower)}s);
						${classNameLower}.set${fk_notes(column.columnName)}(${fk_notes(column.columnName)});
					}else{
						msg.FaltSaveInt++;
						MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，${fk_notes(column.columnName)}名称["+${fk_notes(column.columnName)}+"]不存在<br>\n");
						continue;
					}
				}
				</#if>
				</#if>
				</#list>
				try {
					add(${classNameLower});
					msg.successSaveInt++;
					System.out.println("保存成功："+${classNameLower});
				} catch (Exception e) {
					msg.FaltSaveInt++;
					System.out.println("信息添加失败:"+${classNameLower});
				}
			}
		msg.setMsg(MSG.toString());
		System.out.println(MSG);
		return msg;
	
}

//外键List2
<#list table.importedKeys.associatedTables?values as foreignKey>
<#assign fkSqlTable = foreignKey.sqlTable>
<#assign fkTable    = fkSqlTable.className>
<#assign fkPojoClass = fkSqlTable.className>
<#assign fkPojoClassVar = fkPojoClass?uncap_first>

private ${fkPojoClass}Dao ${fkPojoClassVar}Dao;

@Autowired
public void set${fkPojoClass}Dao(${fkPojoClass}Dao ${fkPojoClassVar}Dao) {
	this.${fkPojoClassVar}Dao = ${fkPojoClassVar}Dao;
}
//初始化${fkPojoClassVar}
private HashMap<String, Long> get${fkPojoClass}HashMap() {
	 List<${fkPojoClass}> list = (List<${fkPojoClass}>)${fkPojoClassVar}Dao.findAll();
	 HashMap<String, Long> hs = new HashMap<String, Long>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				hs.put(list.get(i).getName(), list.get(i).getId());
			}
		}else{
			System.out.println("获取${fkPojoClassVar}哈希为空");
		}
		return hs;
}
}
</#list>
//外键List2
