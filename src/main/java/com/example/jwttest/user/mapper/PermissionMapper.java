package com.example.jwttest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwttest.user.entity.Permissions;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author byl
 * @since 2021-09-14
 */
public interface PermissionMapper extends BaseMapper<Permissions> {

    @Select("select * from permissions where role_ids in ")
    List<Permissions> getList();
}
