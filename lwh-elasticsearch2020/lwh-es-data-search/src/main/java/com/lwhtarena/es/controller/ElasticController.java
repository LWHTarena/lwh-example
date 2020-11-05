package com.lwhtarena.es.controller;

import com.lwhtarena.es.bean.Article;
import com.lwhtarena.es.service.IElasticService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author liwh
 * @Title: ElasticController
 * @Package com.lwhtarena.es.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/5 23:50
 */
@Log4j2
@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private IElasticService elasticService;

    @GetMapping("/init")
    public void init(){
        String title = "关注“Java精选”微信公众号";
        String content = "Java精选专注程序员推送一些Java开发知识，包括基础知识、各大流行框架" +
                "（Mybatis、Spring、Spring Boot等）、大数据技术（Storm、Hadoop、MapReduce、Spark等）、" +
                "数据库（Mysql、Oracle、NoSQL等）、算法与数据结构、面试专题、面试技巧经验、职业规划以及" +
                "优质开源项目等。其中一部分由小编总结整理，另一部分来源于网络上优质资源，希望对大家的学习" +
                "和工作有所帮助。";
        Article article = createArticle(title, content);
        elasticService.save(article);

        title = "关注素文宅博客";
        content = "素文宅博客主要关于一些Java技术类型文章分享。";
        article = createArticle(title, content);
        List<Article> list = new ArrayList<>();
        list.add(article);
        elasticService.saveAll(list);
    }

    public static Article createArticle(String title,String content){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        return article;
    }

    @GetMapping("/all")
    public Iterator<Article> all(){
        return elasticService.findAll();
    }

    @GetMapping("/key")
    public List<Article> all(String title){
        return elasticService.findArticleByTitle(title);
    }

}
