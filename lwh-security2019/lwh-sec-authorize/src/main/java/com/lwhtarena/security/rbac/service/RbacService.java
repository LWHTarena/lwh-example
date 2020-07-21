package com.lwhtarena.security.rbac.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liwh
 * @Title: RbacService
 * @Package com.lwhtarena.security.rbac.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:31
 */
public interface RbacService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
