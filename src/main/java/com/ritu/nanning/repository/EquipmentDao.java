package com.ritu.nanning.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ritu.nanning.entity.Equipment;
import com.ritu.nanning.utils.base.EntityDao;

@Repository
public interface EquipmentDao extends
		PagingAndSortingRepository<Equipment, Long> ,EntityDao<Equipment, Long>{
	
}
