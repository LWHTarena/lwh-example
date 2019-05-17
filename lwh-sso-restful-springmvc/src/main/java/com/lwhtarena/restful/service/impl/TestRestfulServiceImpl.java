package com.lwhtarena.restful.service.impl;

import com.lwhtarena.restful.service.TestRestfulService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@Service
public class TestRestfulServiceImpl implements TestRestfulService {

    @Override
    public String queryUserById(Long id) {
        System.out.println("queryUserById : " + id);
        return "{id:"+id+", name:admin}";
    }

    @Override
    public void saveUser(String user) {
        System.out.println("saveUser : " + user);
    }

    @Override
    public void updateUser(String user) {
        System.out.println("updateUser : " + user);
    }

    @Override
    public void deleteUserById(Long id) {
        System.out.println("deleteUserById : " + id);
    }
}
