package com.lwhtarena.entity;

import lombok.Data;

/**
 * @author liwh
 * @Title: Role
 * @Package com.lwhtarena.entity
 * @Description: 角色信息表
 * @Version 1.0.0
 * @date 2020/7/21 22:34
 */
@Data
public class Role {
    /**角色ID**/
    private Integer id;
    /**角色名**/
    private String roleName;
    /**角色说明**/
    private String roleDesc;
}
