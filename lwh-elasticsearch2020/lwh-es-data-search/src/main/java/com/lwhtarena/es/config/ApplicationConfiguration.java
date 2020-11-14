package com.lwhtarena.es.config;

import com.lwhtarena.es.bean.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * @author liwh
 * @Title: ApplicationConfiguration
 * @Package com.lwhtarena.es.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/14 17:58
 */
@SpringBootApplication
public class ApplicationConfiguration {

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    ElasticsearchRepository elasticsearchRepository;


    @PreDestroy
    public void deleteIndex() {
        elasticsearchOperations.indexOps(Conference.class).delete();
    }

    @PostConstruct
    public void insertDataSample(){
        elasticsearchRepository.deleteAll();
        elasticsearchOperations.indexOps(Conference.class).refresh();

        // Save data sample
        elasticsearchRepository.save(Conference.builder().date("2014-11-06").name("Spring eXchange 2014 - London")
                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        elasticsearchRepository.save(Conference.builder().date("2014-12-07").name("Scala eXchange 2014 - London")
                .keywords(Arrays.asList("scala", "play", "java")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        elasticsearchRepository.save(Conference.builder().date("2014-11-20").name("Elasticsearch 2014 - Berlin")
                .keywords(Arrays.asList("java", "elasticsearch", "kibana")).location(new GeoPoint(52.5234051D, 13.4113999))
                .build());
        elasticsearchRepository.save(Conference.builder().date("2014-11-12").name("AWS London 2014")
                .keywords(Arrays.asList("cloud", "aws")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        elasticsearchRepository.save(Conference.builder().date("2014-10-04").name("JDD14 - Cracow")
                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(50.0646501D, 19.9449799)).build());

   }
}
