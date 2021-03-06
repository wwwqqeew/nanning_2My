package com.ritu.nanning.service.modules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.nanning.entity.Resource;
import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.entity.UserVo;
import com.ritu.nanning.repository.ResourceDao;
import com.ritu.nanning.repository.RoleDao;
import com.ritu.nanning.repository.UserDao;
import com.ritu.nanning.repository.UserImplDao;
import com.ritu.nanning.service.account.RoleService;
import com.ritu.nanning.utils.PageSetting;
import com.ritu.nanning.utils.base.BaseService;
import com.ritu.nanning.utils.base.EntityDao;
import com.ritu.nanning.utils.base.EntityImplDao;
import com.ritu.nanning.utils.excel.ImportMsg;

@Component
@Transactional(readOnly=true)
public class UserService extends BaseService<User, Long>{
	private UserDao userDao;
	private UserImplDao userImplDao;
	
	@Override
	protected EntityDao getEntityDao() {
		return this.userDao;
	}
	@Override
	protected PagingAndSortingRepository getPASRDao() {
		// TODO Auto-generated method stub
		return this.userDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setUserImplDao(UserImplDao userImplDao) {
		this.userImplDao = userImplDao;
	}
	
	public User findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}
	

	public User findByNumber(String number){
		return userDao.findByNumbers( number);
	};
	
	public Page<User> findByNumberAndLoginNameAndRole_id( String number,String loginName,Long role_id,int page,String... fields) {
		return userDao.findByRole_idAndNumbersLikeAndLoginNameLike( "%" +number+"%", "%"+loginName+"%", new PageSetting(page, 10, fields));
	}
	
	public List<User> findByPropertysList (int page,UserVo userVo){
		return userImplDao.searchByProperty(userVo, 10, page, true);	
	}
	
	public int findByPropertysCount (int page,UserVo userVo){
		return userImplDao.getCount(userVo, 10, page, true);	
	}
	@Override
	protected EntityImplDao getEntityImplDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public List<User> findAll() {
        return userDao.findAll();
    }
	@Override
	protected ImportMsg importExcel(List<List<String>> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
