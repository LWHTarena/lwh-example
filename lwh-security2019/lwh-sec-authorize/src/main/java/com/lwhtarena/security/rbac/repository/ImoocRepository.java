package com.lwhtarena.security.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author liwh
 * @Title: ImoocRepository
 * @Package com.lwhtarena.security.rbac.repository
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:29
 */
@NoRepositoryBean
public interface ImoocRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
