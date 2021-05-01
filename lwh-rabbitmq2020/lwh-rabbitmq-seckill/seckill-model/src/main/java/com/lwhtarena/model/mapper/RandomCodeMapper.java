package com.lwhtarena.model.mapper;


import com.lwhtarena.model.entity.RandomCode;

public interface RandomCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RandomCode record);

    int insertSelective(RandomCode record);

    RandomCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RandomCode record);

    int updateByPrimaryKey(RandomCode record);
}