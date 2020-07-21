package com.lwhtarena.security.rbac.repository;

import com.lwhtarena.security.rbac.domain.Admin;
import org.springframework.stereotype.Repository;

/**
 * @author liwh
 * @Title: AdminRepository
 * @Package com.lwhtarena.security.rbac.repository
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:25
 */
@Repository
public interface AdminRepository extends ImoocRepository<Admin> {

    Admin findByUsername(String username);

}
