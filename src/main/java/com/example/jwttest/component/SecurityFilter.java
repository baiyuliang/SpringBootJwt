package com.example.jwttest.component;

import cn.hutool.core.util.URLUtil;
import com.example.jwttest.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 动态权限过滤器，用于实现基于路径的动态权限过滤
 */

public class SecurityFilter extends AbstractSecurityInterceptor implements Filter {

    @Resource
    private SecurityMetadataSource securityMetadataSource;
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Resource
    public void setAccessDecisionManager(SecurityAccessDecisionManager securityAccessDecisionManager) {
        super.setAccessDecisionManager(securityAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);

        //仿照OncePerRequestFilter，解决Filter执行两次的问题
        //执行两次原因：SecurityConfig中，@Bean和addFilter相当于向容器注入了两次
        //解决办法：1是去掉@Bean，但Filter中若有引用注入容器的其它资源，则会报错
        //        2就是request中保存一个Attribute来判断该请求是否已执行过
        String alreadyFilteredAttributeName = getAlreadyFilteredAttributeName();
        boolean hasAlreadyFilteredAttribute = request.getAttribute(alreadyFilteredAttributeName) != null;
        if (hasAlreadyFilteredAttribute) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        request.setAttribute(alreadyFilteredAttributeName, Boolean.TRUE);

        System.out.println("SecurityFilter：" + request.getRequestURI());

        //OPTIONS请求直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        //白名单请求直接放行
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String path : ignoreUrlsConfig.getUrls()) {
            if (pathMatcher.match(contextPath + path, request.getRequestURI())) {
                System.out.println("白名单通过..." + request.getRequestURI());
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
                return;
            }
        }
        //此处会调用AccessDecisionManager中的decide方法进行鉴权操作
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {
        securityMetadataSource.clearDataSource();
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return securityMetadataSource;
    }


    protected String getAlreadyFilteredAttributeName() {
        return this.getClass().getName() + ".FILTERED";
    }
}
