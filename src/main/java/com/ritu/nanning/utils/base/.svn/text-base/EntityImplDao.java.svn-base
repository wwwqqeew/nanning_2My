package com.ritu.nanning.utils.base;

import javax.persistence.Query;



public interface EntityImplDao<E> {

	//String findByPropertys(Object testVo, int i, boolean b);

	String getHql(boolean isAsc, Object testVo);

	Query createQuery(String hql);

	void setParameters(Query query, Object testVo);


}