package com.rabbit.macro.mall.tiny.dao.impl;

import com.rabbit.macro.mall.tiny.dao.UmsAdminRoleRelationDao;
import com.rabbit.macro.mall.tiny.mbg.model.UmsPermission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UmsAdminRoleRelationDaoImpl implements UmsAdminRoleRelationDao {
    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return null;
    }
}
