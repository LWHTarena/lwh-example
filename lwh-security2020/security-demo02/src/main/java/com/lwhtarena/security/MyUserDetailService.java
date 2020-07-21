package com.lwhtarena.security;

import com.lwhtarena.entity.Permission;
import com.lwhtarena.entity.User;
import com.lwhtarena.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liwh
 * @Title: MyUserDetailService
 * @Package com.lwhtarena.security
 * @Description: 设置动态用户数据
 * @Version 1.0.0
 * @date 2020/7/21 22:40
 */

@Component
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    /**查询用户信息**/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username======： "+username);
        /**1、根据用户查询用户信息**/
        User user = userMapper.findByUsername(username);
        /**2、根据用户查询用户对应权限**/
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<Permission> listPermission = userMapper.findPermissionByUsername(username);
        for (Permission permission : listPermission) {
            authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
        }
        /**3、设置用户权限**/
        user.setAuthorities(authorities);
        return user;
    }

}

