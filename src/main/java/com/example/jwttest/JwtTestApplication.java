package com.example.jwttest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.jwttest.*.mapper")
//JWT：Java web Token
//在客户端登录成功后，服务端生成token，返回给客户端，客户端保存在本地，服务端不保存任何信息
//服务端生成token规则：header.payload.signature=>HMACSHA256(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
//secret只保存在服务端
//缺点：因为服务端不保存状态，所以无法实现单线登录，即可以同时在n个设备上登录，每次登录都会返回一个新的token，
//     除了开始设置的token失效时间，其余时间都无法控制token的失效

//cookie+session:
//客户端登录成功后，服务端生成对应的sessionId，并通过cookie返回给客户端，客户端下次发送请求时会在cookie中自动将sessionId带上，
//服务端获取到sessionId后，便知道该用户的相关信息
//缺点：1.不易扩展 2.服务器内存开销大 3.需要解决session共享问题
public class JwtTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtTestApplication.class, args);
    }

}
