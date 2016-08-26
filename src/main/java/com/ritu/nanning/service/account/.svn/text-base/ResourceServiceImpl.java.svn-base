package com.ritu.nanning.service.account;

import com.ritu.nanning.entity.Resource;
import com.ritu.nanning.repository.ResourceDao;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>User: 
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service

public class ResourceServiceImpl implements ResourceService {

    private ResourceDao resourceDao;


    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }
    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if(resource != null && !resource.getPermission().equals(null)) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }


	@Override
	public Resource findOne(Long resourceId) {
		return resourceDao.findOne(resourceId);
	}

}
