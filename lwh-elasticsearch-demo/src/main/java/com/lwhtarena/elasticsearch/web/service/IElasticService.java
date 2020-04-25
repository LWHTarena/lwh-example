package com.lwhtarena.elasticsearch.web.service;

import com.lwhtarena.elasticsearch.web.entity.DocBean;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * @author liwh
 * @Title: IElasticService
 * @Package com.lwhtarena.elasticsearch.web.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/4/11 01:31
 */
public interface IElasticService {

    void createIndex();

    void deleteIndex(String index);

    void save(DocBean docBean);

    void saveAll(List<DocBean> list);

    Iterator<DocBean> findAll();

    Page<DocBean> findByContent(String content);

    Page<DocBean> findByFirstCode(String firstCode);

    Page<DocBean> findBySecordCode(String secordCode);

    Page<DocBean> query(String key);
}
