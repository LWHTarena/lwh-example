package com.lwhtarena.es.dao;

import com.lwhtarena.es.pojo.EsBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 04:27:27
 * @description
 */
@Service
public interface EsMapper extends ElasticsearchRepository<EsBean,Long> {
}
