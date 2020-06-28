package com.lwhtarena.springcloud.service.impl;

import com.lwhtarena.springcloud.dao.DeptDao;
import com.lwhtarena.springcloud.entities.Dept;
import com.lwhtarena.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liwh
 * @Title: DeptServiceImpl
 * @Package com.lwhtarena.springcloud.service.impl
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 08:43
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao dao;


    @Override
    public boolean add(Dept dept) {
        return dao.addDept(dept);

    }

    @Override
    public Dept get(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return dao.findAll();
    }
}
