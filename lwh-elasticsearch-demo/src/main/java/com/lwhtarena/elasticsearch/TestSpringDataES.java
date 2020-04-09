package com.lwhtarena.elasticsearch;

import com.lwhtarena.elasticsearch.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liwh
 * @Title: TestSpringDataES
 * @Package com.lwhtarena.elasticsearch
 * @Description: Spring data elasticsearch 测试用例
 * @Version 1.0.0
 * @date 2020/4/9 23:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSpringDataES {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 测试创建索引
     */
    @Test
    public void testCreate(){
        /**
         * 创建索引，会根据item类的@Document注解信息来创建
         */
        elasticsearchTemplate.createIndex(Item.class);

        /**
         * 配置映射，会根据item类中的id，field等字段来自动完成映射
         */
        elasticsearchTemplate.putMapping(Item.class);
    }
}
