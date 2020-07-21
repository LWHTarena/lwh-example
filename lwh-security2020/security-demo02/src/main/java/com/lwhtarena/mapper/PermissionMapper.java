package com.lwhtarena.mapper;

import com.lwhtarena.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liwh
 * @Title: PermissionMapper
 * @Package com.lwhtarena.mapper
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/21 22:37
 */
public interface PermissionMapper {

    @Select(" select * from sys_permission ")
    List<Permission> findAllPermission();

}
