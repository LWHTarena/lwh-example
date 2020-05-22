package com.lwhtarena.spring.controller;

import com.lwhtarena.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author liwh
 * @Title: BookController
 * @Package com.lwhtarena.spring.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 16:51
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
