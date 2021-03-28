package com.lwhtarena.es.dao;

import com.lwhtarena.es.pojo.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 05:28:47
 * @description
 */
public interface UserRepository extends ElasticsearchRepository<User, Long> {
    /**
     * 根据年龄区间查询
     * @param age1
     * @param age2
     * @return
     */
    List<User> findByAgeBetween(Integer age1, Integer age2);


    @Query("{\n" +
            "    \"range\": {\n" +
            "      \"age\": {\n" +
            "        \"gte\": \"?0\",\n" +
            "        \"lte\": \"?1\"\n" +
            "      }\n" +
            "    }\n" +
            "  }")
    List<User> findByQuery(Integer age1, Integer age2);
}
