package com.ritu.nanning.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ritu.nanning.entity.Template;
import com.ritu.nanning.entity.TemplateVo;
import com.ritu.nanning.utils.base.EntityImplDao;

/**
 * @function [例子]持久层
 * @author cheng.G.Y
 * @date 2016-06-14
 * @latitude 1.0
 */
@Repository
public class TemplateImplDao implements EntityImplDao<TemplateVo>{

	@PersistenceContext
	private EntityManager em;

	public Query createQuery(String hql) {
		return em.createQuery(hql); 
	}

	
	/**
	 * 生成HQL
	 * @param isAsc 排序
	 * @param TemplateVo (与界面交互的Template类)
	 * @return HQL语句
	 */
	@Override
	public String getHql(boolean isAsc,Object templateVo2) {
		TemplateVo templateVo = (TemplateVo)templateVo2;
		StringBuilder hql = new StringBuilder("FROM Template AS o WHERE 1 =1 ");
		if (templateVo != null) {
				if(templateVo.getInput() != null && templateVo.getInput() != "")
					hql.append("And o.input like :input ");
			  	if(templateVo.getInputDate() != null ){
			  		hql.append("And o.inputDate >= :inputDate ");
			  	}if( templateVo.getInputDate_max() != null){
			  		hql.append("And o.inputDate <= :inputDate_max ");
			  	}
		}
		hql.append("ORDER BY o.id "+(isAsc == true ? "ASC": "DESC"));
		return hql.toString();
	}
	
	/**
	 * 参数设置
	 * @param Query Query对象
	 * @param TemplateVo (与界面交互的Template类)
	 */
	public void setParameters(Query query, Object templateVo2) {
		TemplateVo templateVo = (TemplateVo)templateVo2;
		if (templateVo != null) {
			if(templateVo.getInput() != null && templateVo.getInput() != "")
				query.setParameter("input", "%" + templateVo.getInput() + "%");
		  	if(templateVo.getInputDate() != null){
		  		query.setParameter("inputDate", templateVo.getInputDate());
		  	}if( templateVo.getInputDate_max() != null){
				query.setParameter("inputDate_max", templateVo.getInputDate_max());
		  	}
		}
	}
	
}
