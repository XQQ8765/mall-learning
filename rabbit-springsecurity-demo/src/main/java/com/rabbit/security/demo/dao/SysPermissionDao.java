package com.rabbit.security.demo.dao;

import com.rabbit.security.demo.mbg.model.SysPermission;

import java.util.List;

public interface SysPermissionDao {
    List<SysPermission> selectListByUser();
}
