package com.rabbit.security.demo.config;

import com.alibaba.fastjson.JSON;
import com.rabbit.security.demo.JsonResult;
import com.rabbit.security.demo.ResultCode;
import com.rabbit.security.demo.ResultTool;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 会话信息过期策略
 *
 * 处理账号被挤下线处理逻辑
 * 同样的，当账号异地登录导致被挤下线时也要返回给前端json格式的数据，比如提示"账号下线"、
 * "您的账号在异地登录，是否是您自己操作"或者"您的账号在异地登录,可能由于密码泄露，建议修改密码"等。
 * 这时就要实现SessionInformationExpiredStrategy（会话信息过期策略）来自定义会话过期时的处理逻辑。
 */
@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent)
            throws IOException, ServletException {
        JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
        HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
