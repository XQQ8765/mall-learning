package com.rabbit.security.demo.service;

import com.rabbit.security.demo.mbg.model.SysPermission;

import java.util.List;

public interface SysPermissionService {
    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectListByUser(Integer userId);


    List<SysPermission> selectListByPath(String requestUrl);
}
