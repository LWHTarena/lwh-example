package com.lwhtarena.security.rbac.repository;

import com.lwhtarena.security.rbac.domain.Resource;
import org.springframework.stereotype.Repository;


/**
 * @author liwh
 * @Title: ResourceRepository
 * @Package com.lwhtarena.security.rbac.repository
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:29
 */
@Repository
public interface ResourceRepository extends ImoocRepository<Resource> {

    Resource findByName(String name);

}
