package com.ritu.nanning.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ritu.nanning.entity.Testwj;
import com.ritu.nanning.entity.TestwjVo;
import com.ritu.nanning.utils.base.EntityImplDao;

/**
 * @function 外键测试持久层
 * @author cheng.G.Y
 * @date 2016-04-17
 * @latitude 1.0
 */
@Repository
public class TestwjImplDao implements EntityImplDao<TestwjVo>{

	@PersistenceContext
	private EntityManager em;

	public Query createQuery(String hql) {
		return em.createQuery(hql); 
	}

	
	/**
	 * 生成HQL
	 * @param isAsc 排序
	 * @param TestwjVo (与界面交互的Testwj类)
	 * @return HQL语句
	 */
	@Override
	public String getHql(boolean isAsc,Object testwjVo2) {
		TestwjVo testwjVo = (TestwjVo)testwjVo2;
		StringBuilder hql = new StringBuilder("FROM Testwj AS o WHERE 1 =1 ");
		if (testwjVo != null) {
			if(testwjVo.getName() != null && testwjVo.getName() != "")
				hql.append("And o.name like :name ");
		}
		hql.append("ORDER BY o.id "+(isAsc == true ? "ASC": "DESC"));
		return hql.toString();
	}
	
	/**
	 * 参数设置
	 * @param Query Query对象
	 * @param TestwjVo (与界面交互的Testwj类)
	 */
	public void setParameters(Query query, Object testwjVo2) {
		TestwjVo testwjVo = (TestwjVo)testwjVo2;
		if (testwjVo != null) {
			if(testwjVo.getName() != null && testwjVo.getName() != "")
				query.setParameter("name", "%" + testwjVo.getName() + "%");
		}
	}
	
}
