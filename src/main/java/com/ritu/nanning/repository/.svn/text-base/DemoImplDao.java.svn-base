package com.ritu.nanning.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ritu.nanning.entity.DemoVo;
import com.ritu.nanning.utils.base.EntityImplDao;

/**
 * @function demo持久层
 * @author cheng.G.Y
 * @date 2014-09-25
 * @latitude 1.0
 */
@Repository
public class DemoImplDao implements EntityImplDao<DemoVo>{

	@PersistenceContext
	private EntityManager em;

	public Query createQuery(String hql) {
		return em.createQuery(hql); 
	}

	
	/**
	 * 生成HQL
	 * @param isAsc 排序
	 * @param DemoVo (与界面交互的Demo类)
	 * @return HQL语句
	 */
	@Override
	public String getHql(boolean isAsc,Object demoVo2) {
		DemoVo demoVo = (DemoVo)demoVo2;
		StringBuilder hql = new StringBuilder("FROM Demo AS o WHERE 1 =1 ");
		if (demoVo != null) {
			if(demoVo.getName() != null && demoVo.getName() != "")
				hql.append("And o.name like :name ");
			if(demoVo.getName2() != null && demoVo.getName2() != "")
				hql.append("And o.name2 like :name2 ");
		}
		hql.append("ORDER BY o.id "+(isAsc == true ? "ASC": "DESC"));
		return hql.toString();
	}
	
	/**
	 * 参数设置
	 * @param Query Query对象
	 * @param DemoVo (与界面交互的Demo类)
	 */
	public void setParameters(Query query, Object demoVo2) {
		DemoVo demoVo = (DemoVo)demoVo2;
		if (demoVo != null) {
			if(demoVo.getName() != null && demoVo.getName() != "")
				query.setParameter("name", "%" + demoVo.getName() + "%");
			if(demoVo.getName2() != null && demoVo.getName2() != "")
				query.setParameter("name2", "%" + demoVo.getName2() + "%");
		}
	}
	
}
