package com.rabbit.security.demo.service.impl;

import com.rabbit.security.demo.mbg.model.SysPermission;
import com.rabbit.security.demo.mbg.model.SysUser;
import com.rabbit.security.demo.service.SysPermissionService;
import com.rabbit.security.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 四、用户登录认证逻辑：UserDetailsService
 * 1、创建自定义UserDetailsService
 * 这是实现自定义用户认证的核心逻辑，loadUserByUsername(String username)的参数就是登录时提交的用户名，返回类型是一个叫UserDetails 的接口，需要在这里构造出他的一个实现类User，这是Spring security提供的用户信息实体。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private SysUserService sysUserService;
   @Autowired
   private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //需要构造出 org.springframework.security.core.userdetails.User 对象并返回
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户不能为空");
        }
        SysUser sysUser = sysUserService.selectByName(username);
        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //获取该用户所拥有的权限
        List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(sysUser.getId());
        // 声明用户授权
        sysPermissions.forEach(sysPermission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
            grantedAuthorities.add(grantedAuthority);
        });

        return new User(sysUser.getAccount(), sysUser.getPassword(), sysUser.getEnabled(),
                sysUser.getNotExpired(), sysUser.getCredentialsNotExpired(), sysUser.getAccountNotLocked(),
                grantedAuthorities);
    }
}
