package com.ritu.nanning.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.ritu.nanning.entity.Equipment;
import com.ritu.nanning.repository.EquipmentDao;
import com.ritu.nanning.utils.base.BaseService;
import com.ritu.nanning.utils.base.EntityDao;
import com.ritu.nanning.utils.base.EntityImplDao;
import com.ritu.nanning.utils.excel.ImportMsg;

@Service
public class EquipmentService extends BaseService<Equipment, Long> {

	@Autowired
	private EquipmentDao equipmentDao;

	@Override
	protected EntityDao getEntityDao() {
		// TODO Auto-generated method stub
		return this.equipmentDao;
	}

	@Override
	protected PagingAndSortingRepository getPASRDao() {
		// TODO Auto-generated method stub
		return this.equipmentDao;
	}

	@Override
	protected EntityImplDao getEntityImplDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ImportMsg importExcel(List<List<String>> list) {
		// TODO Auto-generated method stub
		return null;
	}



}
