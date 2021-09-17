package com.example.jwttest.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author byl
 * @since 2021-09-14
 */
@Data
public class User implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    private String username;

    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;

    @TableField("role_id")
    private Integer roleId;
}
