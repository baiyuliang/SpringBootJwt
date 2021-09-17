package com.example.jwttest.component;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * 动态权限决策管理器，用于判断用户是否有访问权限
 */
public class SecurityAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        System.out.println("SecurityAccessDecisionManager...");
        // 当接口未被配置资源时直接放行
        if (CollUtil.isEmpty(configAttributes)) return;
        //authentication.getAuthorities()：在获取UserDetails验证jwt时赋予的路径访问权限
        //configAttribute.getAttribute(): 在SecurityMetadataSource中获取的用户访问路径所需要的权限
        System.out.println("用户拥有的路径访问权限：" + authentication.getAuthorities().toString());
        for (ConfigAttribute configAttribute : configAttributes) {
            //将访问所需资源或用户拥有资源进行比对
            String needAuthority = configAttribute.getAttribute();
            System.out.println("用户访问的路径：" + needAuthority);
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                    System.out.println("security权限验证通过...");
                    return;
                }
            }
        }
        System.out.println("security权限验证失败...");
        throw new AccessDeniedException("AccessDeniedException");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
