package com.zc.cris.springboot.componenetUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录检查
 */
public class MyLoginHandlerInterceptor implements HandlerInterceptor {

    // 目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        String loginUser = (String) request.getSession().getAttribute("loginUser");

        // 如果当前访问请求中没有用户登录信息，即用户没有登录,返回登录页面
        if(StringUtils.isEmpty(loginUser)){
            // 错误提示消息
            request.setAttribute("msg", "请登录后访问！");
            // 转发至登录页面
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
        // 已登录，放行
        return true;
    }
}
