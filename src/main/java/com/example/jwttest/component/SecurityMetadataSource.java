package com.example.jwttest.component;

import cn.hutool.core.util.URLUtil;
import com.example.jwttest.user.entity.Permissions;
import com.example.jwttest.user.mapper.PermissionMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.Resource;
import java.util.*;

/**
 * 动态权限数据源，用于获取动态权限规则
 */
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    PermissionMapper permissionMapper;

    private List<ConfigAttribute> allConfigAttributes;


    public void clearDataSource() {
        allConfigAttributes.clear();
        allConfigAttributes = null;
    }

    /**
     * 用户访问的路径的权限配置信息
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        List<ConfigAttribute> needConfigAttributes = new ArrayList<>();
        //获取用户当前访问的路径
        String path = URLUtil.getPath(((FilterInvocation) o).getRequestUrl());
        PathMatcher pathMatcher = new AntPathMatcher();
        //configAttribute.getAttribute()存放的就是资源路径，直接比对即可
        //通过对比用户访问路径和已配置的全部路径，来确定访问路径是否已配置权限
        //已配置则返回该路径，未配置则返回空
        for (ConfigAttribute configAttribute : allConfigAttributes) {
            if (pathMatcher.match(configAttribute.getAttribute(), path)) {
                needConfigAttributes.add(configAttribute);
                break;
            }
        }
        return needConfigAttributes;
    }

    /**
     * 全部已配置权限的路径
     *
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        allConfigAttributes = new ArrayList<>();
        List<Permissions> permissionsList = permissionMapper.selectList(null);
        permissionsList.forEach(permissions -> {
//            ConfigAttribute configAttribute = () -> permissions.getId() + ":" + permissions.getPath() + ":" + permissions.getDescription();
            //这里只放入了资源路径
            ConfigAttribute configAttribute = permissions::getPath;
            allConfigAttributes.add(configAttribute);
        });
        return allConfigAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
