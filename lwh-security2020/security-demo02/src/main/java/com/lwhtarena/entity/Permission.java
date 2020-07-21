package com.lwhtarena.entity;

import lombok.Data;

/**
 * @author liwh
 * @Title: Permission
 * @Package com.lwhtarena.entity
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/21 22:35
 */

@Data
public class Permission {
    /**权限ID**/
    private Integer id;
    /**权限名称**/
    private String permName;
    /**权限标识**/
    private String permTag;
    /**请求url**/
    private String url;
}
