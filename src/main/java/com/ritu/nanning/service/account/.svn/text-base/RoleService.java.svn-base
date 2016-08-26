package com.ritu.nanning.service.account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.RoleVo;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.entity.UserVo;
import com.ritu.nanning.repository.RoleDao;
import com.ritu.nanning.repository.RoleImplDao;
import com.ritu.nanning.repository.UserImplDao;
import com.ritu.nanning.utils.PageSetting;
import com.ritu.nanning.utils.base.JunitServiceBase;

@Component
@Transactional(readOnly = true)
public class RoleService extends JunitServiceBase{

	private RoleDao roleDao;
	private RoleImplDao roleImplDao;
	
	@Cacheable(value = "cache", key = "'role'+#id")
	public Role findById(Long id) {
		return roleDao.findById(id);
	}

	public Role findById1(Long id) {
		return roleDao.findById1(id);
	}
	
	
	public  boolean  countByName(String name){
		return roleDao.countByName(name)==0l?true:false;
	}
	
	public Page<Role> findById(List<Long> ids) {
		return roleDao.findByIdIn(ids, new PageSetting(1, 20, false));
	}

	public Iterable<Role> findAll() {
		return roleDao.findAll();
	}

	public List<Role> findAll1() {
        return  roleDao.findAll1();
    }
	
	public Page<Role> findAll(int page, String... fields) {
		return roleDao.findAll(new PageSetting(page, 10, fields));
	}

	public Page<Role> findByName(String name, int page, String... fields) {
		return roleDao.findByName(name, new PageSetting(page, 10, fields));
	}

	@CacheEvict(value = "cache", allEntries = true)
	@Transactional(readOnly = false)
	public void update(Role role) {
		roleDao.save(setOther(role));
	}

	@CacheEvict(value = "cache", allEntries = true)
	@Transactional(readOnly = false)
	public void add(Role role) {
		roleDao.save(setOther(role));
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		roleDao.delete(id);
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Autowired
	public void setRoleImplDao(RoleImplDao roleImplDao) {
		this.roleImplDao = roleImplDao;
	}

	public List<User> findByPropertysList (int page,RoleVo roleVo){
		return roleImplDao.searchByProperty(roleVo, 10, page, true);	
	}
	
	public int findByPropertysCount (int page,RoleVo roleVo){
		return roleImplDao.getCount(roleVo, 10, page, true);	
	}
	
	 
	
	
	
	public Role setOther(Role role){
//		if(role.getPadManagement() != null ){
//			role.setAlarmsetManagement(role.getPadManagement());
//		}else{
//			role.setAlarmsetManagement(false);
//		}
//		
//		if(role.getOtherManagement() != null ){
//			role.setSystemSetManagement(role.getOtherManagement());
//			role.setAuthorityManagement(role.getOtherManagement());
//			role.setFoodManagement(role.getOtherManagement());
//			role.setInterfaceManagement(role.getOtherManagement());
//			role.setOrderManagement(role.getOtherManagement());
//			role.setSortManagement(role.getOtherManagement());
//			role.setTableManagement(role.getOtherManagement());
//			role.setRankManagement(role.getOtherManagement());
//			role.setSystemSetManagement(role.getOtherManagement());
//			role.setPrintSpoolerManagement(role.getOtherManagement());
//			role.setPublicityManagement(role.getOtherManagement());
//			role.setTeaManagement(role.getOtherManagement());
//			role.setNoticeManagement(role.getOtherManagement());
//		}else{
//			role.setSystemSetManagement(false);
//			role.setAuthorityManagement(false);
//			role.setFoodManagement(false);
//			role.setInterfaceManagement(false);
//			role.setOrderManagement(false);
//			role.setSortManagement(false);
//			role.setTableManagement(false);
//			role.setRankManagement(false);
//			role.setSystemSetManagement(false);
//			role.setPrintSpoolerManagement(false);
//			role.setPublicityManagement(false);
//			role.setTeaManagement(false);
//			role.setNoticeManagement(false);
//		}
			
			
			
		return role;
	}
	
	public static void main(String[] args) {
		 String abc = "121,11,123";
         String[] arr = abc.split(",");
         List<Long> list = new ArrayList();
         for(String s : arr){
        	 list.add(Long.parseLong(s));
         }
		System.out.println(list);
	}
}
