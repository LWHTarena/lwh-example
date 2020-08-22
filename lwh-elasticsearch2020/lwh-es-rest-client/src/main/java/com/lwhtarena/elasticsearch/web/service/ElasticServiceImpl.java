package com.lwhtarena.elasticsearch.web.service;

import com.lwhtarena.elasticsearch.web.dao.ElasticRepository;
import com.lwhtarena.elasticsearch.web.entity.DocBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author liwh
 * @Title: ElasticServiceImpl
 * @Package com.lwhtarena.elasticsearch.web.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/4/11 01:30
 */
@Service("elasticService")
public class ElasticServiceImpl implements IElasticService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private ElasticRepository elasticRepository;

    private Pageable pageable = PageRequest.of(0, 10);

    @Override
    public void createIndex() {
        elasticsearchTemplate.createIndex(DocBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(index);
    }

    @Override
    public void save(DocBean docBean) {
        elasticRepository.save(docBean);
    }

    @Override
    public void saveAll(List<DocBean> list) {
        elasticRepository.saveAll(list);
    }

    @Override
    public Iterator<DocBean> findAll() {
        return elasticRepository.findAll().iterator();
    }

    @Override
    public Page<DocBean> findByContent(String content) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<DocBean> findByFirstCode(String firstCode) {
        // TODO Auto-generated method stub
        return elasticRepository.findByContent(firstCode, pageable);
    }

    @Override
    public Page<DocBean> findBySecordCode(String secordCode) {
        // TODO Auto-generated method stub
        return elasticRepository.findBySecordCode(secordCode, pageable);
    }

    @Override
    public Page<DocBean> query(String key) {
        // TODO Auto-generated method stub
        return elasticRepository.findByContent(key, pageable);
    }

}
