package com.ritu.nanning.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ritu.nanning.entity.Track;
import com.ritu.nanning.utils.base.EntityDao;
/**
 * @function 轨迹JPA持久层
 * @author cheng.G.Y 
 * @date 2016-04-03
 * @latitude 1.0
 */
public interface TrackDao extends PagingAndSortingRepository<Track, Long>, EntityDao<Track,Long>{
	
	
	public Track findById(Long id);//根据ID查找
	
//	public Page<Track> findByIdIn(List<Long> ids, Pageable pageRequest);
	
//	@Query( "select* from Track u where u.Id = ?1")
//	public Track findById(Long id);//根据ID查找
}
