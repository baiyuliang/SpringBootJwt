package com.example.jwttest.user.controller;


import com.example.jwttest.api.CommonResult;
import com.example.jwttest.user.entity.User;
import com.example.jwttest.user.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author byl
 * @since 2021-09-14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;

    @PostMapping("/login")
    public CommonResult login(String username, String password) {
        String token = userService.login(username, password);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        if (token.equals("-1")) {
            return CommonResult.validateFailed("账号被禁用");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return CommonResult.success(tokenMap);
    }

    @PostMapping("/register")
    public CommonResult register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int result = userService.register(user);
        if (result == -1) {
            return CommonResult.failed("该账号已注册");
        }
        return CommonResult.success("注册成功");
    }

    @GetMapping("/testJwt")
    public CommonResult testJwt() {
        return CommonResult.success("有Token测试成功");
    }

    @GetMapping("/testNoJwt")
    public CommonResult testNoJwt() {
        return CommonResult.success("无Token测试成功");
    }

    @GetMapping("/testRes")
    public CommonResult testRes() {
        return CommonResult.success("权限测试成功");
    }
}

