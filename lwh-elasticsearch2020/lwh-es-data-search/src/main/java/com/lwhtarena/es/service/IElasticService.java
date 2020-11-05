package com.lwhtarena.es.service;

import com.lwhtarena.es.bean.Article;

import java.util.Iterator;
import java.util.List;

/**
 * @author liwh
 * @Title: IElasticService
 * @Package com.lwhtarena.es.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/5 23:48
 */
public interface IElasticService {

    public void save(Article article);

    public void saveAll(List<Article> list);

    public Iterator<Article> findAll();

    public List<Article> findArticleByTitle(String title);

}