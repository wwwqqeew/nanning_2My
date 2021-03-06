package com.ritu.nanning.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.nanning.entity.Department;
import com.ritu.nanning.repository.DepartmentDao;
import com.ritu.nanning.utils.DateUtil;
import com.ritu.nanning.utils.base.BaseService;
import com.ritu.nanning.utils.base.EntityDao;
import com.ritu.nanning.utils.base.EntityImplDao;
import com.ritu.nanning.utils.excel.ImportMsg;

/**
 * @function department业务层
 * @author cheng.G.Y
 * @date 2014-09-25
 * @latitude 1.0
 */
@Component
@Transactional(readOnly = true)
public class DepartmentService extends BaseService<Department,Long>{

	private DepartmentDao departmentDao;//JPA持久层对象（调用的是JPA的方法）

	private EntityImplDao departmentImplDao;//持久层对象（调用的是自己写的方法的方法）
	
	@Override
	protected EntityDao getEntityDao() {
		return this.departmentDao;
	}
	
	//用于在公共方法中：分页排序
	@Override
	protected PagingAndSortingRepository getPASRDao() {
		return this.departmentDao;
	}
	
	//用于在公共方法中：调用自己写的方法
	@Override
	protected EntityImplDao getEntityImplDao() {
		return departmentImplDao;
	}
	
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@Autowired
	public void setDepartmentImplDao(EntityImplDao departmentImplDao) {
		//this.departmentImplDao = departmentImplDao;
	}

	@Override
	protected ImportMsg importExcel(List<List<String>> list) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
