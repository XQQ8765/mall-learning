package com.rabbit.security.demo.config;

import com.rabbit.security.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 创建WebSecurityConfig继承WebSecurityConfigurerAdapter类，并实现configure(AuthenticationManagerBuilder auth)和 configure(HttpSecurity http)方法。后续我们会在里面加入一系列配置，包括配置认证方式、登入登出、异常处理、会话管理等。
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };


    //匿名用户访问无权限资源时的异常
    @Autowired
    CustomizeAuthenticationEntryPoint authenticationEntryPoint;

    //登录成功处理逻辑
    @Autowired
    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    //登录失败处理逻辑
    @Autowired
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/r/getUser").hasAuthority("query_user")
        //登入, enable form login. Will provide spring security default login page. visit via: /login
        .and().formLogin().permitAll()
            .successHandler(authenticationSuccessHandler)//登录成功处理逻辑
            .failureHandler(authenticationFailureHandler);//登录失败处理逻辑
        //异常处理(权限拒绝、登录失效等)
        //.and().exceptionHandling()
        //    .authenticationEntryPoint(authenticationEntryPoint);//匿名用户访问无权限资源时的异常处理
        //.antMatchers("/**/*").denyAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }
}
