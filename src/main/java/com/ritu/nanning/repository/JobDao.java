package com.ritu.nanning.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ritu.nanning.entity.Job;
/**
 * @function 电子围栏规则围栏JPA持久层
 * @author 
 * @date 2015-9-9
 * @latitude 1.0
 */
public interface JobDao extends PagingAndSortingRepository<Job, Long>{
	
	public Job findById(Long id);// 根据ID查找

	public Page<Job> findByIdIn(List<Long> ids, Pageable pageRequest);


}
