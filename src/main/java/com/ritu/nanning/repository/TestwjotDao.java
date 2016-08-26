package com.ritu.nanning.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ritu.nanning.entity.Testwjot;
import com.ritu.nanning.utils.base.EntityDao;
/**
 * @function 外键测试JPA持久层
 * @author cheng.G.Y 
 * @date 2016-04-22
 * @latitude 1.0
 */
public interface TestwjotDao extends PagingAndSortingRepository<Testwjot, Long>, EntityDao<Testwjot,Long>{
	
	
	public Testwjot findById(Long id);//根据ID查找
	
//	public Page<Testwjot> findByIdIn(List<Long> ids, Pageable pageRequest);
	
//	@Query( "select* from Testwjot u where u.Id = ?1")
//	public Testwjot findById(Long id);//根据ID查找
}
