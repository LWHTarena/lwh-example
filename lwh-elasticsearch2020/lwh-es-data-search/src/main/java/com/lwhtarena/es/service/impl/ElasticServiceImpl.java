package com.lwhtarena.es.service.impl;

import com.lwhtarena.es.bean.Article;
import com.lwhtarena.es.dao.ElasticRepository;
import com.lwhtarena.es.service.IElasticService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liwh
 * @Title: ElasticServiceImpl
 * @Package com.lwhtarena.es.service.impl
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/5 23:49
 */
@Service("elasticService")
public class ElasticServiceImpl implements IElasticService {

    @Autowired
    private ElasticRepository elasticRepository;


    @Override
    public void save(Article article) {
        elasticRepository.deleteAll();
        elasticRepository.save(article);
    }

    @Override
    public void saveAll(List<Article> list) {
        elasticRepository.saveAll(list);
    }

    @Override
    public Iterator<Article> findAll() {
        return elasticRepository.findAll().iterator();
    }

    @Override
    public List<Article> findArticleByTitle(String title) {
        //matchQuery 对关键字分词后进行搜索
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", title);
        QueryBuilders.commonTermsQuery("title",title);
        Iterable<Article> search = elasticRepository.search(matchQueryBuilder);
        Iterator<Article> iterator = search.iterator();
        List<Article> list = new ArrayList<>();
        while (iterator.hasNext()){
            Article next = iterator.next();
            list.add(next);
        }
        return list;
    }
}