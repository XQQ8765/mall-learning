package com.rabbit.security.demo.service.impl;

import com.rabbit.security.demo.mbg.mapper.SysUserMapper;
import com.rabbit.security.demo.mbg.model.SysUser;
import com.rabbit.security.demo.mbg.model.SysUserExample;
import com.rabbit.security.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser selectByName(String userName) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(userName);

        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        if (sysUserList != null && sysUserList.size() > 0) {
            return sysUserList.get(0);
        }
        return null;
    }
}
