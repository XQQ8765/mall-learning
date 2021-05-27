package com.rabbit.security.demo.config;

import com.alibaba.fastjson.JSON;
import com.rabbit.security.demo.JsonResult;
import com.rabbit.security.demo.ResultCode;
import com.rabbit.security.demo.ResultTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 匿名用户访问无权限资源时的异常
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        User userDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            userDetails = (User) authentication.getPrincipal();
        }

        String username = Optional.ofNullable(userDetails).map( u -> u.getUsername()).orElse(null);
        LOGGER.warn("User:" + username + ", Authentication failed, e:" + e);
        JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
