package com.rabbit.security.demo.config;

import com.alibaba.fastjson.JSON;
import com.rabbit.security.demo.JsonResult;
import com.rabbit.security.demo.ResultTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 登出成功处理逻辑
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = Optional.ofNullable(authentication)
                .map(auth -> (User) auth.getPrincipal())
                .map(user -> user.getUsername())
                .orElse(null);
        LOGGER.info("User:" + username + " logout successfully.");

        JsonResult result = ResultTool.success();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
