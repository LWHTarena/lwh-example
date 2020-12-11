package com.lwhtarena.es.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author liwh
 * @Title: ElasticsearchConfig
 * @Package com.lwhtarena.es.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/5 23:45
 */
@Slf4j
//@Configuration
@EnableElasticsearchRepositories(basePackages = "com.lwhtarena.es.service")
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    @Value("${elasticsearch.search.pool.size}")
    private Integer threadPoolSearchSize;

//    @Bean
//    public Client client() throws Exception {
//        Settings esSettings = Settings.builder()
//                .put("cluster.name", esClusterName)
//                .put("client.transport.sniff", true)
//                .put("thread_pool.search.size", threadPoolSearchSize)
//                .build();
//        return new PreBuiltTransportClient(esSettings)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(esHost), esPort));
//    }
//
//    @Bean(name="elasticsearchTemplate")
//    public ElasticsearchOperations elasticsearchTemplateCustom() throws Exception {
//        ElasticsearchTemplate elasticsearchTemplate;
//        try {
//            elasticsearchTemplate = new ElasticsearchTemplate(client());
//            return elasticsearchTemplate;
//        } catch (Exception e) {
//            return new ElasticsearchTemplate(client());
//        }
//    }
}
