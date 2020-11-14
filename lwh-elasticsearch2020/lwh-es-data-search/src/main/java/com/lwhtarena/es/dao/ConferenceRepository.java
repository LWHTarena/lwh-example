package com.lwhtarena.es.dao;

import com.lwhtarena.es.bean.Conference;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author liwh
 * @Title: ConferenceRepository
 * @Package com.lwhtarena.es.dao
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/14 17:44
 */
public interface ConferenceRepository extends ElasticsearchRepository<Conference,String> {
}
