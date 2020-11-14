package com.lwhtarena.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Christoph Strobl
 */
interface ConferenceRepository extends ElasticsearchRepository<Conference, String> {

}
