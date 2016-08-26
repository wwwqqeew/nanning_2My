package com.ritu.nanning.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.utils.base.EntityDao;

public interface RoleDao extends PagingAndSortingRepository<Role, Long>, EntityDao<Role,Long>{
	
	/**
	 * 根据ID列表获取结果
	 * @param ids ID列表获取
	 * @param pageRequest 参数
	 * @return  Page结果
	 */
	public Page<Role> findByIdIn(List<Long> ids, Pageable pageRequest);

	/**
	 * 根据Name获取Page结果
	 * @param name 角色名
	 * @param pageRequest Page参数
	 * @return Page结果
	 */
	public Page<Role> findByName(String name, Pageable pageRequest);

	/**
	 * 根据ID获取角色
	 * @param id ID 
	 * @return 获取的角色
	 */
	public Role findById(Long id);
	
	/**
	 * 获取NAME的记录条数
	 * @param name NAME
	 * @return 记录条数
	 */
	@Query( "select count (*) from Role u where u.name = ?1")
	public  Long  countByName(String name);
	
	@Query( "select id, role, description, resources, available from Role ")
	public List<Role> findAll1();
	
	@Query( "from Role r where r.id = ?1")
	public Role findById1(Long id);
}
