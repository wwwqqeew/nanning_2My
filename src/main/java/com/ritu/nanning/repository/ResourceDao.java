package com.ritu.nanning.repository;

import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.entity.Resource;
import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.utils.base.EntityDao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <p>Resource: 
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ResourceDao extends PagingAndSortingRepository<Resource, Long>,EntityDao<Resource,Long>{

	/**
	 * 根据ID列表获取结果
	 * @param ids ID列表获取
	 * @param pageRequest 参数
	 * @return  Page结果
	 */
	public Page<Resource> findByIdIn(List<Long> ids, Pageable pageRequest);

	/**
	 * 根据Name获取Page结果
	 * @param name 角色名
	 * @param pageRequest Page参数
	 * @return Page结果
	 */
	public Page<Resource> findByName(String name, Pageable pageRequest);

	/**
	 * 根据ID获取角色
	 * @param id ID 
	 * @return 获取的角色
	 */
	public Resource findById(Long id);
	
   // @Query( "select id, name, url, permission, parent_id, parent_ids, available,group from Resource")
	public  List<Resource> findAll();
    
    
    @Query( "select id, name, url, permission, parent_id, parent_ids, available,group from Resource r where r.id = ?1")
    public Resource findOne(Long resourceId);
}
