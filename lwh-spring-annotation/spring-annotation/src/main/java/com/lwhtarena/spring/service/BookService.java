package com.lwhtarena.spring.service;

import com.lwhtarena.spring.dao.BookDao;
import org.springframework.stereotype.Service;
import javax.inject.Inject;

/**
 * @author liwh
 * @Title: BookService
 * @Package com.lwhtarena.spring.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 16:49
 */
@Service
public class BookService {
    //@Qualifier("bookDao")
    //@Autowired(required=false)
    //@Resource(name="bookDao2")
    @Inject
    private BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService [bookDao=" + bookDao + "]";
    }

}
