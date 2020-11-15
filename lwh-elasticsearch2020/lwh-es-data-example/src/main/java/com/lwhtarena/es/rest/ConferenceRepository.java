package com.lwhtarena.es.rest;

import com.lwhtarena.es.Conference;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface ConferenceRepository extends ElasticsearchRepository<Conference, String> {

}
