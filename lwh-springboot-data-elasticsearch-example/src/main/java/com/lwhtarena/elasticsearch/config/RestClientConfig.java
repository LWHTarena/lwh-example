package com.lwhtarena.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author liwh
 * @Title: RestClientConfig
 * @Package com.lwhtarena.elasticsearch.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/4/11 01:14
 */

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.31.120:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
