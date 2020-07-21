package com.lwhtarena.security.rbac.repository.spec;

import com.lwhtarena.security.rbac.domain.Admin;
import com.lwhtarena.security.rbac.dto.AdminCondition;
import com.lwhtarena.security.rbac.repository.support.ImoocSpecification;
import com.lwhtarena.security.rbac.repository.support.QueryWraper;

/**
 * @author liwh
 * @Title: AdminSpec
 * @Package com.lwhtarena.security.rbac.repository.spec
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:25
 */
public class AdminSpec extends ImoocSpecification<Admin, AdminCondition> {

    public AdminSpec(AdminCondition condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWraper<Admin> queryWraper) {
        addLikeCondition(queryWraper, "username");
    }

}
