package com.example.jwttest;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jwttest.user.controller.UserController;
import com.example.jwttest.user.entity.User;
import com.example.jwttest.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class JwtTestApplicationTests {

    @Autowired
    IUserService userService;

    @Resource
    UserController userController;

    @Test
    void contextLoads() {

    }

    @Test
    void test1() {
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userService.getList(page, null);
        userPage.getRecords().forEach(System.out::println);
        System.out.println(userPage.getPages() + "," + userPage.getCurrent() + "," + userPage.getSize() + "," + userPage.getTotal());
    }

    @Test
    void testReg() {
        String username = "666666";
        String password = "123456";
        System.out.println(userController.register(username, password).getMessage());
    }

    @Test
    void testLogin() {
        String username = "666666";
        String password = "123456";
        System.out.println(JSONUtil.toJsonStr(userController.login(username, password)));
    }
}
