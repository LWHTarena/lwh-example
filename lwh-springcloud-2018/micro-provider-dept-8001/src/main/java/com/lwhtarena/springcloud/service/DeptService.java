package com.lwhtarena.springcloud.service;

import com.lwhtarena.springcloud.entities.Dept;

import java.util.List;

/**
 * @author liwh
 * @Title: DeptService
 * @Package com.lwhtarena.springcloud.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 08:42
 */
public interface DeptService {
    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();
}
