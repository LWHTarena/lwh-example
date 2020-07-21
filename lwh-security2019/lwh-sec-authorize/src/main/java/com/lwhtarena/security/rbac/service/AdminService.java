package com.lwhtarena.security.rbac.service;

import com.lwhtarena.security.rbac.dto.AdminCondition;
import com.lwhtarena.security.rbac.dto.AdminInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author liwh
 * @Title: AdminService
 * @Package com.lwhtarena.security.rbac.service
 * @Description: 管理员服务
 * @Version 1.0.0
 * @date 2020/7/20 21:31
 */
public interface AdminService {

    /**
     * 创建管理员
     * @param adminInfo
     * @return
     */
    AdminInfo create(AdminInfo adminInfo);
    /**
     * 修改管理员
     * @param adminInfo
     * @return
     */
    AdminInfo update(AdminInfo adminInfo);
    /**
     * 删除管理员
     * @param id
     */
    void delete(Long id);
    /**
     * 获取管理员详细信息
     * @param id
     * @return
     */
    AdminInfo getInfo(Long id);
    /**
     * 分页查询管理员
     * @param condition
     * @return
     */
    Page<AdminInfo> query(AdminCondition condition, Pageable pageable);

}
