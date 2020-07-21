package com.lwhtarena.mapper;

import com.lwhtarena.entity.Permission;
import com.lwhtarena.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liwh
 * @Title: UserMapper
 * @Package com.lwhtarena.mapper
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/21 22:38
 */
public interface UserMapper {

    /**查询用户信息**/
    @Select(" select * from sys_user where username = #{userName}")
    User findByUsername(@Param("userName") String userName);

    /**查询用户的权限**/
    @Select(" select permission.* from sys_user user" + " inner join sys_user_role user_role"
            + " on user.id = user_role.user_id inner join "
            + "sys_role_permission role_permission on user_role.role_id = role_permission.role_id "
            + " inner join sys_permission permission on role_permission.perm_id = permission.id where user.username = #{userName};")
    List<Permission> findPermissionByUsername(@Param("userName") String userName);
}
