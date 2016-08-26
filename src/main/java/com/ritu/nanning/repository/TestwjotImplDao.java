package com.ritu.nanning.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ritu.nanning.entity.Testwjot;
import com.ritu.nanning.entity.TestwjotVo;
import com.ritu.nanning.utils.base.EntityImplDao;

/**
 * @function 外键测试持久层
 * @author cheng.G.Y
 * @date 2016-04-22
 * @latitude 1.0
 */
@Repository
public class TestwjotImplDao implements EntityImplDao<TestwjotVo>{

	@PersistenceContext
	private EntityManager em;

	public Query createQuery(String hql) {
		return em.createQuery(hql); 
	}

	
	/**
	 * 生成HQL
	 * @param isAsc 排序
	 * @param TestwjotVo (与界面交互的Testwjot类)
	 * @return HQL语句
	 */
	@Override
	public String getHql(boolean isAsc,Object testwjotVo2) {
		TestwjotVo testwjotVo = (TestwjotVo)testwjotVo2;
		StringBuilder hql = new StringBuilder("FROM Testwjot AS o WHERE 1 =1 ");
		if (testwjotVo != null) {
				if(testwjotVo.getName() != null && testwjotVo.getName() != "")
					hql.append("And o.name like :name ");
			  	if(testwjotVo.getTestwjId() != null)
					hql.append("And o.testwj.id = :testwjId ");
		}
		hql.append("ORDER BY o.id "+(isAsc == true ? "ASC": "DESC"));
		return hql.toString();
	}
	
	/**
	 * 参数设置
	 * @param Query Query对象
	 * @param TestwjotVo (与界面交互的Testwjot类)
	 */
	public void setParameters(Query query, Object testwjotVo2) {
		TestwjotVo testwjotVo = (TestwjotVo)testwjotVo2;
		if (testwjotVo != null) {
			if(testwjotVo.getName() != null && testwjotVo.getName() != "")
				query.setParameter("name", "%" + testwjotVo.getName() + "%");
		  	if(testwjotVo.getTestwjId() != null )
		  		query.setParameter("testwjId", testwjotVo.getTestwjId());
		}
	}
	
}
