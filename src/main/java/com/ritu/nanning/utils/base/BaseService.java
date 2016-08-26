package com.ritu.nanning.utils.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.PageSetting;
import com.ritu.nanning.utils.o;
import com.ritu.nanning.utils.excel.ImportMsg;

@Component
@Transactional(readOnly = true)
public abstract class BaseService<E, PK extends Serializable> {

	protected abstract EntityDao getEntityDao(); // 调用JPA的方法用

	protected abstract PagingAndSortingRepository getPASRDao(); // 分页用

	protected abstract EntityImplDao getEntityImplDao(); // 自己写的方法调用
	
	/**
	 * 导入方法（需要自己重写）
	 * @param list
	 * @return
	 */
	protected abstract ImportMsg importExcel(List<List<String>> list);

	public E findById(PK id) {
		return (E) getPASRDao().findOne(id);
	}

	public Iterable<E> findAll() {
		return getPASRDao().findAll();
	}

	public Page<E> findAll(int page, String... fields) {
		return getPASRDao().findAll(new PageSetting(page, 10, false, fields));
	}
   
	/**
	 * 
	 * @param page
	 * @param size
	 * @param isIdAsc
	 * @return
	 */
	public Page<E> findList(int page, int size, boolean isIdAsc) {

		Sort sort = new Sort(isIdAsc ? Direction.ASC : Direction.DESC, "id");
		Pageable pageable = new PageRequest(page, size);
		Page<E> elements = getPASRDao().findAll(pageable);
		return elements;
	}

	@CacheEvict(value = "cache", allEntries = true)
	@Transactional(readOnly = false)
	public void update(E entity) {
		getPASRDao().save(entity);
	}

	@CacheEvict(value = "cache", allEntries = true)
	@Transactional(readOnly = false)
	public E add(E entity) {
		return (E) getPASRDao().save(entity);
	}

	/**
	 * 根据实体模糊查询分页
	 * 
	 * @param Vo实体
	 * @param page
	 *            页码
	 * @param pageSize
	 *            每页数目
	 * @param isAsc
	 *            倒序
	 * @return {"success":true,"data":[对象实体],"page":当前页数,"totalPages":总页数}
	 */
	public String findByPropertys(Object testVo, int page, int pageSize,
			boolean isAsc) {
		Query query = getEntityImplDao().createQuery(
				getEntityImplDao().getHql(isAsc, testVo));
		getEntityImplDao().setParameters(query, testVo);
		int count = getCount(query.getResultList(), pageSize, page);
		query.setFirstResult(pageSize * (page - 1));
		query.setMaxResults(pageSize);
		return JsonBuilder.toJson(true, query.getResultList(), page, count);
	}

	/**
	 * 根据实体获取所有的数据
	 * 
	 * @param Vo实体
	 * @param isAsc
	 *            倒序
	 * @return {"success":true,"data":[对象实体],"page":当前页数,"totalPages":总页数}
	 */
	public String findList(Object testVo, boolean isAsc) {
		Query query = getEntityImplDao().createQuery(
				getEntityImplDao().getHql(isAsc, testVo));
		getEntityImplDao().setParameters(query, testVo);
		return JsonBuilder.toJson(true, query.getResultList());
	}

	/**
	 * 根据实体模糊查询所有
	 * 
	 * @param Vo实体
	 * @param isAsc
	 *            倒序
	 * @return
	 */
	public List<E> findByPropertys(Object testVo, boolean isAsc) {
		Query query = getEntityImplDao().createQuery(
				getEntityImplDao().getHql(isAsc, testVo));
		getEntityImplDao().setParameters(query, testVo);
		return (List<E>) query.getResultList();
	}
	
	public void dd() {
		o.o(111);
	}

	/**
	 * 根据List获取数据总条数
	 * 
	 * @param list
	 * @param pageSize
	 * @param page
	 * @return
	 */
	private int getCount(List list, int pageSize, int page) {
		return ((list.size() - 1) / pageSize + 1);// 页数计算及返回
	}

	@Transactional(readOnly = false)
	public void delete(PK id) {
		getPASRDao().delete(id);
	}

	// @Transactional(readOnly=true)
	// public boolean isUnique(E entity, String uniquePropertyNames) {
	// return getEntityDao().isUnique(entity, uniquePropertyNames);
	// }
}
