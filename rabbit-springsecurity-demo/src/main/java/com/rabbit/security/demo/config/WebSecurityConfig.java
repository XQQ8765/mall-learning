package com.rabbit.security.demo.config;

import com.rabbit.security.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

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
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;

    //登录成功处理逻辑
    @Autowired
    private CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    //登录失败处理逻辑
    @Autowired
    private CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    //登出成功处理逻辑
    @Autowired
    private CustomizeLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    //访问决策管理器
    @Autowired
    private CustomizeAccessDecisionManager  accessDecisionManager;

    //实现权限拦截
    @Autowired
    CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    private CustomizeAbstractSecurityInterceptor securityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                ////设置不拦截页面，可直接通过，路径访问 "/", "/index" 则不拦截
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/r/getUser").hasAuthority("query_user")
                .anyRequest().authenticated()
                //.antMatchers("/**/*").denyAll()

        // 九、实现基于JDBC的动态权限控制
        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//访问决策管理器
                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
                        return o;
                    }
                })

        //登入, enable form login. Will provide spring security default login page. visit via: /login
        .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")   // 访问指定页面，用户未登入，跳转至登入页面，如果登入成功，跳转至用户访问指定页面，用户访问登入页面，默认的跳转页面
                .failureUrl("/login").permitAll()
            //.successHandler(authenticationSuccessHandler)//登录成功处理逻辑
            //.failureHandler(authenticationFailureHandler)//登录失败处理逻辑

        //登出
         .and().logout()
                .permitAll()//允许所有用户
                .logoutSuccessHandler(logoutSuccessHandler)//登出成功处理逻辑
                .deleteCookies("JSESSIONID")//登出之后删除cookie

        //异常处理(权限拒绝、登录失效等)
        .and().exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)//匿名用户访问无权限资源时的异常处理  ???Will cause /login 404

        //会话管理
        .and().sessionManagement().
                maximumSessions(1).//同一账号同时登录最大用户数
                expiredSessionStrategy(sessionInformationExpiredStrategy);//会话信息过期策略会话信息过期策略(账号被挤下线)

        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);//增加到默认拦截链中
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
