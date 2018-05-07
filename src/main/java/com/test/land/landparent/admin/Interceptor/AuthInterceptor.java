package com.test.land.landparent.admin.Interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.land.landparent.admin.common.constants.Constants;
import com.test.land.landparent.admin.common.utils.token.AssertionHolder;
import com.test.land.landparent.admin.common.utils.token.TokenManager;
import com.test.land.landparent.admin.entity.AuthModel;

import lombok.extern.slf4j.Slf4j;

/**
 * 认证拦截器
 */
@Slf4j
@Configuration
public class AuthInterceptor implements HandlerInterceptor{

    @Autowired
    private TokenManager tokenManager;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("auth start!");
        //获取请求中的token
        String token = httpServletRequest.getHeader(Constants.AUTHORIZATION);
        String url = httpServletRequest.getRequestURI();

        if(token==null){
            //用户未登录访问
            log.error("用户未登陆访问===>地址为"+url);
            httpServletResponse.getWriter().write("用户未登陆");
            httpServletResponse.getWriter().flush();
            //关闭流
            httpServletResponse.getWriter().close();
            return Boolean.FALSE;
        }

        //验证token
        AuthModel authModel = tokenManager.getAuthModel(token);

        if(authModel==null||!tokenManager.checkAuthModel(authModel)){
            //token已过期
            log.error("token 已过期!");
            return Boolean.FALSE;
        }

        //设置认证用户,方便全局取出
        AssertionHolder.setAuthModel(authModel);
        log.info("auth end !  auth success!");

        return true;
    }




    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
