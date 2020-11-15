package com.lwhtarena.es.reactive;

import com.lwhtarena.es.Conference;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author liwh
 * @Title: ConferenceRepository
 * @Package com.lwhtarena.es.reactive
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/15 16:27
 */
public interface ConferenceRepository2 extends ReactiveCrudRepository<Conference, String> {

    Flux<Conference> findAllByKeywordsContainsAndDateAfter(String keyword, String Date);
}
