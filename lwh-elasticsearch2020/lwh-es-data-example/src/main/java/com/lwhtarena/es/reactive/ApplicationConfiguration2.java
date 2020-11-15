package com.lwhtarena.es.reactive;

import com.lwhtarena.es.Conference;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import reactor.test.StepVerifier;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author liwh
 * @Title: ApplicationConfiguration2
 * @Package com.lwhtarena.es.reactive
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/15 16:25
 */

@SpringBootApplication
public class ApplicationConfiguration2 {
    @Autowired
    ReactiveElasticsearchOperations operations;
    @Autowired
    ConferenceRepository2 repository;

    @PostConstruct
    public void insertDataSample() {

        try {
            RestClients.create(ClientConfiguration.localhost()).rest().indices()
                    .create(new CreateIndexRequest("conference-index"), RequestOptions.DEFAULT);
        } catch (IOException | ElasticsearchStatusException e) {
            // just ignore it
        }

        // Remove all documents
        repository.deleteAll().subscribe();

        // Save data sample
        repository
                .save(Conference.builder().date("2014-11-06").name("Spring eXchange 2014 - London")
                        .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(51.500152D, -0.126236D)).build())
                .then().as(StepVerifier::create).verifyComplete();
        repository
                .save(Conference.builder().date("2014-12-07").name("Scala eXchange 2014 - London")
                        .keywords(Arrays.asList("scala", "play", "java")).location(new GeoPoint(51.500152D, -0.126236D)).build())
                .then().as(StepVerifier::create).verifyComplete();
        repository.save(Conference.builder().date("2014-11-20").name("Elasticsearch 2014 - Berlin")
                .keywords(Arrays.asList("java", "elasticsearch", "kibana")).location(new GeoPoint(52.5234051D, 13.4113999))
                .build()).then().as(StepVerifier::create).verifyComplete();
        repository.save(Conference.builder().date("2014-11-12").name("AWS London 2014")
                .keywords(Arrays.asList("cloud", "aws")).location(new GeoPoint(51.500152D, -0.126236D)).build()).then()
                .as(StepVerifier::create).verifyComplete();
        repository.save(Conference.builder().date("2014-10-04").name("JDD14 - Cracow")
                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(50.0646501D, 19.9449799)).build()).then()
                .as(StepVerifier::create).verifyComplete();
    }
}
