package com.ritu.nanning.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.entity.UserVo;

@Repository
public class UserImplDao {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 根据参数查询
	 * @param userVo 页面VO类
	 * @param pageSize 每页显示数
	 * @param page 页面
	 * @param isAsc 排序
	 * @return 获取的List对象
	 */
	@SuppressWarnings("unchecked")
	public List<User> searchByProperty(UserVo userVo, int pageSize, int page, boolean isAsc) {
		Query query = createQuery(getHql(isAsc,userVo));
		setParameters(query,userVo);
		query.setFirstResult(pageSize * (page - 1));
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	/**
	 * 根据参数获取记录数
	 * @param userVo 页面VO类
	 * @param pageSize 每页显示数
	 * @param page 页面
	 * @param isAsc 排序
	 * @return 记录数
	 */
	public int getCount(UserVo userVo, int pageSize, int page, boolean isAsc) {
		Query query = createQuery(getHql(isAsc,userVo));
		setParameters(query,userVo);
		System.out.println("000000000000000000000000:"+query.getMaxResults());
		return ((query.getResultList().size() -1 )/pageSize + 1);
	}

	/**
	 * HQL 语句构造
	 * @param isAsc 排序
	 * @param userVo 页面VO类
	 * @return HQL
	 */
	private String getHql(boolean isAsc,UserVo userVo) {
		StringBuilder hql = new StringBuilder(" FROM User AS o WHERE 1 =1 ");
		if(userVo.getLoginName() != null && userVo.getLoginName() !="")
			hql.append("And o.loginName like :loginName ");
		if(userVo.getRealName() != null && userVo.getRealName() !="")
			hql.append("And o.realName like :realName ");
		if(userVo.getRole() != null )
			hql.append("And o.role.name like :roleName ");
		  	hql.append("ORDER BY o.id "+(isAsc == true ? "ASC": "DESC"));
		return hql.toString();
	}
	

	private Query createQuery(String hql) {
		return em.createQuery(hql);
	}
	

	/**
	 * 参数赋值
	 * @param query
	 * @param userVo
	 */
	private void setParameters(Query query,UserVo userVo) {
		if(userVo.getLoginName() != null && userVo.getLoginName() !="")
			query.setParameter("loginName", "%" + userVo.getLoginName() + "%");
		if(userVo.getRealName() != null && userVo.getRealName() !="")
			query.setParameter("realName", "%" + userVo.getRealName() + "%");
		if(userVo.getRole() != null&&userVo.getRole().getName()!="")
			query.setParameter("roleName", "%" +userVo.getRole().getName()+"%");
		
	}
	
}
