package com.rabbit.security.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 九、实现基于JDBC的动态权限控制
 *
 * 我们需要实现一个AccessDecisionManager（访问决策管理器），在里面我们对当前请求的资源进行权限判断，判断当前登录用户是否拥有该权限，如果有就放行，
 * 如果没有就抛出一个"权限不足"的异常。不过在实现AccessDecisionManager之前我们还需要做一件事，那就是拦截到当前的请求，
 * 并根据请求路径从数据库中查出当前资源路径需要哪些权限才能访问，然后将查出的需要的权限列表交给AccessDecisionManager去处理后续逻辑。
 * 那就是需要先实现一个SecurityMetadataSource，翻译过来是"安全元数据源"，我们这里使用他的一个子类FilterInvocationSecurityMetadataSource。
 * 在自定义的SecurityMetadataSource编写好之后，我们还要编写一个拦截器，增加到Spring security默认的拦截器链中，以达到拦截的目的。
 * 同样的最后需要在WebSecurityConfig中注入，并在configure(HttpSecurity http)方法中然后声明
 * ————————————————
 * 原文链接：https://blog.csdn.net/I_am_Hutengfei/article/details/100561564/
 */
@Component
public class CustomizeAbstractSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public void setMyAccessDecisionManager(CustomizeAccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }

    private void invoke(FilterInvocation fi) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
