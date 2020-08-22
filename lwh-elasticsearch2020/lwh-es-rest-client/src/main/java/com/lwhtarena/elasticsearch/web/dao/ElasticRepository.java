package com.lwhtarena.elasticsearch.web.dao;

import com.lwhtarena.elasticsearch.web.entity.DocBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author liwh
 * @Title: ElasticRepository
 * @Package com.lwhtarena.elasticsearch.web.dao
 * @Description: 持久层
 * @Version 1.0.0
 * @date 2020/4/11 01:28
 */
public interface ElasticRepository extends ElasticsearchRepository<DocBean, Long> {

    // 默认的注释
    // @Query("{\"bool\" : {\"must\" : {\"field\" : {\"content\" : \"?\"}}}}")
    Page<DocBean> findByContent(String content, Pageable pageable);

    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"firstCode.keyword\" : \"?\"}}}}")
    Page<DocBean> findByFirstCode(String firstCode, Pageable pageable);

    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"secordCode.keyword\" : \"?\"}}}}")
    Page<DocBean> findBySecordCode(String secordCode, Pageable pageable);
}
