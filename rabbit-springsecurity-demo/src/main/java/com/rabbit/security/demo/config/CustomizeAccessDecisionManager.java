package com.rabbit.security.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 访问决策管理器
 */
@Component
public class CustomizeAccessDecisionManager implements AccessDecisionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {//See https://www.cnblogs.com/datamining-bio/p/13831000.html Optional类与使用==判断null有什么区别？使用Optional类有什么优势？
        Object principal = Optional.ofNullable(authentication)
                .map(auth -> auth.getPrincipal())
                .orElse(null);
        LOGGER.info("Verify whether Principal:" + principal + " has permission to visit:" + object);

        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needRole = ca.getAttribute();
            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    LOGGER.info("权限满足，访问:" + object + " 需要权限:" + needRole + ", 允许访问");
                    return;
                }
            }
        }
        LOGGER.warn("权限不足，访问:" + object + " 需要如下权限:"
                + configAttributes.stream().map(ca -> ca.getAttribute()).collect(Collectors.joining(",", "(", ")")));
        throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
