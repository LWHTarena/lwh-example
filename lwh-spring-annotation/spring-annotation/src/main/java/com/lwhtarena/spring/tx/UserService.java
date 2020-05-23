package com.lwhtarena.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liwh
 * @Title: UserService
 * @Package com.lwhtarena.spring.tx
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 09:24
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser(){
        userDao.insert();
        //otherDao.other();xxx
        System.out.println("˛ĺČëÍęłÉ...");
        int i = 10/0;
    }
}
