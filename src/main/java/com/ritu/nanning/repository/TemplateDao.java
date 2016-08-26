package com.ritu.nanning.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ritu.nanning.entity.Template;
import com.ritu.nanning.utils.base.EntityDao;
/**
 * @function [例子]JPA持久层
 * @author cheng.G.Y 
 * @date 2016-06-14
 * @latitude 1.0
 */
public interface TemplateDao extends PagingAndSortingRepository<Template, Integer>, EntityDao<Template,Integer>{
	
	
	public Template findById(Integer id);//根据ID查找
	
//	public Page<Template> findByIdIn(List<Long> ids, Pageable pageRequest);
	
//	@Query( "select* from Template u where u.Id = ?1")
//	public Template findById(Integer id);//根据ID查找
}
