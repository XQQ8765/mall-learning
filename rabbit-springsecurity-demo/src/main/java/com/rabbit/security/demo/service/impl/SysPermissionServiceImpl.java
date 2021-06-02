package com.rabbit.security.demo.service.impl;

import com.rabbit.security.demo.dao.SysPermissionDao;
import com.rabbit.security.demo.mbg.model.SysPermission;
import com.rabbit.security.demo.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public List<SysPermission> selectListByUser(Integer userId) {
        return sysPermissionDao.selectListByUser();
    }

    @Override
    public List<SysPermission> selectListByPath(String requestUrl) {
        return sysPermissionDao.selectListByPath(requestUrl);
    }
}
