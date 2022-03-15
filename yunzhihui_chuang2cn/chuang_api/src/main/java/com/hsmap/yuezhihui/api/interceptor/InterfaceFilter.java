package com.hsmap.yuezhihui.api.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "InterfaceFilter")
public class InterfaceFilter implements Filter {
    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        LOGGER.info("拦截器生效。。。。。");
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 这里将原始request传入，读出流并存储
        ServletRequest requestWrapper = new  BodyReaderRequestWrapper(httpServletRequest);
        // 这里将原始request替换为包装后的request，此后所有进入controller的request均为包装后的
        filterChain.doFilter(requestWrapper, servletResponse);//
    }

    @Override
    public void destroy() {

    }
}
