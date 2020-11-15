package com.lwhtarena.es.example;

import com.lwhtarena.es.Conference;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author liwh
 * @Title: ConferenceRepository3
 * @Package com.lwhtarena.es.example
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/15 16:52
 */
public interface ConferenceRepository3 extends ElasticsearchRepository<Conference, String> {
}
