package com.lwhtarena.es.service;

import com.lwhtarena.es.pojo.User;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 05:30:58
 * @description
 */
public interface Userservice {
    void init(List<User> users);

    List<User> testFindByAgeBetween(int i, int j);
    List<User> testFindByAgeBetween2(int i, int j);
}
