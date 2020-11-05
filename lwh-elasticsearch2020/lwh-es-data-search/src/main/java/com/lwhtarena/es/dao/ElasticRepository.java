package com.lwhtarena.es.dao;

import com.lwhtarena.es.bean.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author liwh
 * @Title: ElasticRepository
 * @Package com.lwhtarena.es.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/5 23:47
 */
public interface ElasticRepository extends ElasticsearchRepository<Article, String> {

}
