package com.ritu.nanning.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ritu.nanning.entity.Testwj;
import com.ritu.nanning.utils.base.EntityDao;
/**
 * @function 外键测试JPA持久层
 * @author cheng.G.Y 
 * @date 2016-04-17
 * @latitude 1.0
 */
public interface TestwjDao extends PagingAndSortingRepository<Testwj, Long>, EntityDao<Testwj,Long>{
	
	
	public Testwj findById(Long id);//根据ID查找
	
//	public Page<Testwj> findByIdIn(List<Long> ids, Pageable pageRequest);
	
//	@Query( "select* from Testwj u where u.Id = ?1")
//	public Testwj findById(Long id);//根据ID查找
}
