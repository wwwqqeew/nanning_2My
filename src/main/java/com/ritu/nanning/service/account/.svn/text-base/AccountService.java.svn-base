package com.ritu.nanning.service.account;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.nanning.entity.Resource;
import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.repository.ResourceDao;
import com.ritu.nanning.repository.RoleDao;
import com.ritu.nanning.repository.UserDao;
import com.ritu.nanning.service.account.ShiroDbRealm.ShiroUser;
import com.ritu.nanning.utils.Digests;
import com.ritu.nanning.utils.Encodes;
import com.ritu.nanning.utils.PageSetting;

@Component
@Transactional(readOnly = true)
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	private UserDao userDao;
	private RoleDao roleDao;
	private ResourceDao resourceDao;
	/**
	 * 获取所以用户
	 * @return 用户对象列表
	 */
	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	/**
	 * 根据ID获取用户对象
	 * @param id ID
	 * @return 用户对象
	 */
	public User getUser(Long id) {
		return userDao.findOne(id);
	}
	/**
	 * 根据ID列表获取用户对象
	 * @param ids ＩＤ列表
	 * @return　Page对象
	 */
	public Page<User> findById(List<Long> ids) {
		return userDao.findByIdIn(ids, new PageSetting(1, 20, false));
	}

	/**
	 * 获取所有用户
	 * @return
	 */
	public Iterable<User> findAll() {
		return userDao.findAll();
	}
	
	/**
	 * 根据页面获取列表
	 * @param page 页面
	 * @param fields 排序参数
	 * @return Page
	 */
	public Page<User> findAll(int page, String... fields) {
		return userDao.findAll(new PageSetting(page, 10, fields));
	}
	
	/**
	 * 根据编号获取
	 * @param number 编号
	 * @return 用户对象
	 */
	public User findUserByNumber(String number) {
		return userDao.findByNumbers(number);
	}
	/**
	 * 根据登录获取对象
	 * @param loginName 登录名
	 * @return 获取的对象
	 */
	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}
	
	/**
	 * 根据登录名获取对象数
	 * @param loginName 登录名
	 * @return 对象数
	 */
	public Long getCountByLoginName(String loginName){
		return userDao.getCountByLoginName(loginName);
	}

	/**
	 * 根据登录名和编号、页面获取页面显示
	 * @param name 登录名
	 * @param number 编号
	 * @param page 页面
	 * @param fields 分页参数
	 * @return Page
	 * 
	 */
	public Page<User> findByLoginNameAndNumber(String name, String number, int page, String... fields) {
		if ((name == null || name.equalsIgnoreCase("")) && (number == null || number.equalsIgnoreCase(""))) {
			return userDao.findAll(new PageSetting(page, 10, fields));
		} else if ((name == null || name.equalsIgnoreCase("")) || (number == null || number.equalsIgnoreCase(""))) {
			return userDao.findByLoginNameOrNumbers(name == null ? "" : name, number == null ? "" : number,
					new PageSetting(page, 10, fields));
		} else {
			return userDao.findByLoginNameAndNumbers(name, number, new PageSetting(page, 10, fields));
		}
	}

	//注册用户
	@Transactional(readOnly = false)
	public void registerUser(User user) {
		entryptPassword(user);
		user.setRegisterDate(new Date(System.currentTimeMillis()));
		userDao.save(user);
	}
	
	//添加角色
	@Transactional(readOnly = false)
	public void addRole(Role role) {
		roleDao.save(role);
	}
	@Transactional(readOnly = false)
	public void updateUser(User user) {
		User oldUser = null;
		if (userDao.exists(user.getId())) {
			oldUser = userDao.findOne(user.getId());
			if (StringUtils.isNotBlank(user.getPlainPassword())) {
				entryptPassword(user);
			}
			if (!StringUtils.isNotBlank(user.getPassword())) {
				user.setPassword(oldUser.getPassword());
				user.setSalt(oldUser.getSalt());
			}
			if (user.getRegisterDate() == null) {
				user.setRegisterDate(oldUser.getRegisterDate());
			}
			if (StringUtils.isNotBlank(user.getLoginName())) {
				user.setLoginName(oldUser.getLoginName());
			}
			if (StringUtils.isNotBlank(user.getNumbers())) {
				user.setNumbers(oldUser.getNumbers());
			}
			if (user.getRole() == null) {
				user.setRole(oldUser.getRole());
			}
			userDao.save(user);
		}
	}

	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userDao.delete(id);

	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1l;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	public boolean isExist(Long id) {
		return userDao.exists(id);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
	//根据用户查询权限
	public Set<String> findPermissions(User user){
        return findPermissions(user.getRole().getId());
	}
	
	//根据角色id获取权限id
	 public Set<String> findPermissions(Long roleId) {
	        Set<Long> resourceIds = new HashSet<Long>();
	            Role role = findById1(roleId);
	            
	            if(role != null) {
	            	 String[] arr = role.getResources().split(",");
	                 List<Long> list = new ArrayList();
	                 for(String s : arr){
	                	 list.add(Long.parseLong(s));
	                 }
	                resourceIds.addAll(list);
	            }
	            System.out.println("resourceIds:"+resourceIds);
	        return findPermissions(resourceIds);
	    }
	 
	 //根据权限id查询具体权限名称,[21,41]
	 public Set<String> findPermissions(Set<Long> resourceIds) {
	        Set<String> permissions = new HashSet<String>();
	        for(Long resourceId : resourceIds) {
	            Resource resource = findOne(resourceId);
	            if(resource != null && !resource.getPermission().equals(null)) {
	            	String[] arr = resource.getPermission().split(",");
	            	for(String s : arr){
	            		permissions.add(s);
	                 }
	            }
	        }
	        System.out.println("permissions:"+permissions);
	        return permissions;
	    }
	 
	 
	 public Role findById1(Long id) {
			//return roleDao.findById1(id);
			return roleDao.findById(id);
		}
	 
	 public Resource findOne(Long resourceId) {
			//return resourceDao.findOne(resourceId);
			return resourceDao.findById(resourceId);
		}
	 
	 public List<Resource> findAllRecourse(){
		 return resourceDao.findAll();
	 }
	 
	  public List<Resource> findMenus(Set<String> permissions) {
	        List<Resource> allResources = findAllRecourse();
	        List<Resource> menus = new ArrayList<Resource>();
	        for(Resource resource : allResources) {
	            if(resource.isRootNode()) {
	                continue;
	            }
	           /* if(!resource.getType().equals("menu")&&!resource.getType().equals("nomenu")){
	            	 continue;
	            }*/
	            
	            if(!hasPermission(permissions, resource)) {
	                continue;
	            }
	            menus.add(resource);
	        }
	        return menus;
	    }
	  
	  private boolean hasPermission(Set<String> permissions, Resource resource) {
	        if(StringUtils.isEmpty(resource.getPermission())) {
	            return true;
	        }
	        for(String permission : permissions) {
	            WildcardPermission p1 = new WildcardPermission(permission);
	            String[] arr = resource.getPermission().split(",");
	            for(String s : arr){
	          	  WildcardPermission p2 = new WildcardPermission(s);
	          	  if(p1.implies(p2) || p2.implies(p1)){
	          		return true;
	          	  }
	            }
	        }
	        return false;
	    }
	  
	  public static void main(String[] args) {
		  //role:*
		  //user:*
		  String[] arr = "user:*,role:*".split(",");
      	
		  String permission = "user:";
		  WildcardPermission p1 = new WildcardPermission(permission);
          for(String s : arr){
        	  WildcardPermission p2 = new WildcardPermission(s);
        	  if(p1.implies(p2) || p2.implies(p1)){
        		  System.out.println(p1.implies(p2) || p2.implies(p1));
        	  }
        	 
             }
		
	}
}