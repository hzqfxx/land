package com.test.land.landparent.admin.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpHandler;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.Order;

import com.test.land.landparent.admin.common.BodyReaderHttpServletRequestWrapper;
import com.test.land.landparent.admin.common.HttpHelper;

import lombok.extern.slf4j.Slf4j;

//设置执行顺序
@Order(1)
@WebFilter(filterName = "logFilter",urlPatterns = "/*")
@Slf4j
public class LogFilter implements Filter{

    /**
     * 过滤请求
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //转换成httpServletRequest来获取请求地址
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        ServletRequest servletRequest = new BodyReaderHttpServletRequestWrapper(httpServletRequest);

        //获取body中的内容
        String body = HttpHelper.getBodyString(servletRequest);

        StringBuffer url = httpServletRequest.getRequestURL();
        //String url = httpServletRequest.getRequestURL().append(StringUtils.isBlank(httpServletRequest.getQueryString()) ? "" : "?" + httpServletRequest.getQueryString()).toString();
        log.info(url+"================>"+body);

        //过滤非法内容和url
        String regex=".*<script>.*</script>.*";
        if(url.toString().toLowerCase().matches(regex)||body.toLowerCase().matches(regex)){
            log.info("请求地址或请求内容存在非法字符!");
            return ;
        }
        //放行
        filterChain.doFilter(servletRequest,response);
     }



    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
