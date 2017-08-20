package com.nidone.fitness.api.web.interceptor;

import com.common.web.url.JdUrl;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shilun on 2016/8/22.
 */
public class BaseHandlerInterceptor extends HandlerInterceptorAdapter {
    private static Long version;

    @Resource
    private JdUrl homeModule;

    static {
        /** 版本时间戳 */
        version = System.currentTimeMillis();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String domain = homeModule.getTarget("/").toString();
        request.setAttribute("ver", version);
        request.setAttribute("baseUrl",domain);

        return super.preHandle(request, response, handler);
    }
}
