package com.ritu.nanning.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ritu.nanning.entity.Department;
import com.ritu.nanning.utils.base.EntityDao;
/**
 * @function 电子围栏规则围栏JPA持久层
 * @author 
 * @date 2015-9-9
 * @latitude 1.0
 */
public interface DepartmentDao extends PagingAndSortingRepository<Department, Long>,EntityDao<Department,Long>{
	
	public Department findById(Long id);// 根据ID查找

	public Page<Department> findByIdIn(List<Long> ids, Pageable pageRequest);

}
