package com.example.jwttest.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * 资源
 */
@Data
public class Permissions {

    private Integer id;

    @TableField("role_ids")
    private String roleIds;

    private String path;

    private String description;
}
