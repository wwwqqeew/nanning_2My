package com.ritu.nanning.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.ritu.nanning.entity.User;
import com.ritu.nanning.utils.base.EntityDao;

public interface UserDao extends PagingAndSortingRepository<User, Long>, EntityDao<User,Long>{

	/**
	 * 根据ID列表获取结果
	 * @param ids ID列表获取
	 * @param pageRequest 参数
	 * @return  Page结果
	 */
	public Page<User> findByIdIn(List<Long> ids, Pageable pageRequest);
	/**
	 * 根据登录名获取用户
	 * @param loginName 登录名
	 * @return 用户对象
	 */
	public User findByLoginName(String loginName);

	/**
	 * 根据编号获取用户
	 * @param number 编号
	 * @return 用户对象
	 */
	public User findByNumbers(String number);

	/**
	 * 根据登录名和编号获取Page
	 * @param loginName 登录名
	 * @param number 编号
	 * @param pageRequest 参数
	 * @return Page对象
	 */
	public Page<User> findByLoginNameAndNumbers(String loginName, String number, Pageable pageRequest);

	/**
	 * 根据登录名或者编号获取Page
	 * @param loginName 登录名
	 * @param number 编号
	 * @param pageRequest 参数
	 * @return Page对象
	 */
	public Page<User> findByLoginNameOrNumbers(String loginName, String number, Pageable pageRequest);
	
	/**
	 * 根据登录名获取用户数
	 * @param loginName 登录名
	 * @return 用户数
	 */
	@Query( "select count(*) from User where loginName = ?1" )
	public Long getCountByLoginName(String loginName);

	
	/**
	 * 根据编号和登录名获取Page
	 * @param number 编号
	 * @param loginName 登录名
	 * @param pageRequest 参数
	 * @return Page对象
	 */
	@Query( "from User u where u.numbers like ?1 and u.loginName like ?2" )
	public Page<User> findByRole_idAndNumbersLikeAndLoginNameLike( String number,String loginName,Pageable pageRequest);
	
	@Query( "id,numbers as organization_id,username,password,salt,role_id as roleIdsStr locked from User " )
	public List<User> findAll();
}
