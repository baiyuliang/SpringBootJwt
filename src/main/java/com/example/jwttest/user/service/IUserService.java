package com.example.jwttest.user.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jwttest.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author byl
 * @since 2021-09-14
 */
public interface IUserService extends IService<User> {

    Page<User> getList(Page<User> page, Wrapper<User> queryWrapper);

    String login(String username, String password);

    int register(User user);
}
