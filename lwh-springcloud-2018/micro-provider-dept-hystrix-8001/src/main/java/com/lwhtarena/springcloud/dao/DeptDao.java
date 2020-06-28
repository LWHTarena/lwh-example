package com.lwhtarena.springcloud.dao;

import com.lwhtarena.springcloud.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liwh
 * @Title: DeptDao
 * @Package com.lwhtarena.springcloud.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 08:11
 */
@Mapper
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}
