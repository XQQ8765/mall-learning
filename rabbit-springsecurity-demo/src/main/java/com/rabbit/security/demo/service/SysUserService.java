package com.rabbit.security.demo.service;

import com.rabbit.security.demo.mbg.model.SysUser;

public interface SysUserService {
    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser selectByName(String userName);
}
