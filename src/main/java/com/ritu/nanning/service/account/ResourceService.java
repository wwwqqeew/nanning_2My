package com.ritu.nanning.service.account;

import com.ritu.nanning.entity.Resource;
import com.ritu.nanning.entity.Role;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

/**
 * <p>User: 
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ResourceService {



    Resource findOne(Long resourceId);
    List<Resource> findAll();
    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);
    
}
