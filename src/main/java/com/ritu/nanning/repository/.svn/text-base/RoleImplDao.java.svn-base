package com.ritu.nanning.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.RoleVo;
import com.ritu.nanning.entity.User;

@Repository
public class RoleImplDao {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 根据参数查询
	 * @param roleVo 页面VO类
	 * @param pageSize 每页显示数
	 * @param page 页面
	 * @param isAsc 排序
	 * @return 获取的List对象
	 */
	@SuppressWarnings("unchecked")
	public List<User> searchByProperty(RoleVo roleVo, int pageSize, int page, boolean isAsc) {
		Query query = createQuery(getHql(isAsc,roleVo));
		setParameters(query,roleVo);
		query.setFirstResult(pageSize * (page - 1));
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	/**
	 * 根据参数获取记录数
	 * @param roleVo 页面VO类
	 * @param pageSize 每页显示数
	 * @param page 页面
	 * @param isAsc 排序
	 * @return 记录数
	 */
	public int getCount(RoleVo roleVo, int pageSize, int page, boolean isAsc) {
		Query query = createQuery(getHql(isAsc,roleVo));
		setParameters(query,roleVo);
		return ((query.getResultList().size() -1 )/pageSize + 1);
	}

	/**
	 * HQL 语句构造
	 * @param isAsc 排序
	 * @param roleVo 页面VO类
	 * @return HQL
	 */
	private String getHql(boolean isAsc,RoleVo roleVo) {
		StringBuilder hql = new StringBuilder(" FROM Role AS o WHERE 1 =1 ");
		if(roleVo.getName() != null && roleVo.getName() !="")
			hql.append("And o.name like :name ");
		return hql.toString();
	}
	

	private Query createQuery(String hql) {
		return em.createQuery(hql);
	}
	

	/**
	 * 参数赋值
	 * @param query
	 * @param roleVo
	 */
	private void setParameters(Query query,RoleVo roleVo) {
		if(roleVo.getName() != null && roleVo.getName() !="")
			query.setParameter("name", "%" + roleVo.getName() + "%");
	}
	
}
