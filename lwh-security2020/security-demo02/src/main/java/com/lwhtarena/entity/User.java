package com.lwhtarena.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author liwh
 * @Title: User
 * @Package com.lwhtarena.entity
 * @Description: 用户信息表
 * @Version 1.0.0
 * @date 2020/7/21 22:33
 */
@Data
public class User implements UserDetails {

    /**用户ID**/
    private Integer id;
    /**用户名**/
    private String username;
    /**真实姓名**/
    private String realname;
    /**面膜**/
    private String password;
    /**创建日期**/
    private Date createDate;
    /**最后登录时间**/
    private Date lastLoginTime;
    /**是否可用**/
    private boolean enabled;
    /**是否过期**/
    private boolean accountNonExpired;
    /**是否锁定**/
    private boolean accountNonLocked;
    /**证书是否过期**/
    private boolean credentialsNonExpired;

    /**用户所有权限**/
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
