package com.lwhtarena.es.service;

import com.lwhtarena.es.dao.UserRepository;
import com.lwhtarena.es.pojo.User;
import com.lwhtarena.es.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 05:31:16
 * @description
 */
@Service
public class UserserviceImpl implements Userservice {
    @Resource
    UserRepository userRepository;

    @Resource
    private ElasticsearchRestTemplate template;

    @Resource
    ElasticsearchOperations elasticsearchOperations;

    @Override
    public void init(List<User> users) {
        elasticsearchOperations.indexOps(User.class).refresh();
        this.userRepository.saveAll(users);
    }

    @Override
    public  List<User> testFindByAgeBetween(int i, int j) {
        List<User> list = this.userRepository.findByAgeBetween(i, j);
        return list;
    }

    @Override
    public  List<User> testFindByAgeBetween2(int i, int j) {
        List<User> list = this.userRepository.findByQuery(i, j);
        return list;
    }
}
