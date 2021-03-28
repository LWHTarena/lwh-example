package com.lwhtarena.jd.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: ElasticSearchClientConfig
 * @Package com.lwhtarena.jd.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/23 13:39
 */

// 狂神的Spring两步骤
// 1.找对象
// 2.放到spring中
// 3.如果是springboot，先分析源码
// xxxx AutoConfiguration xxxProperfile
@Configuration
public class ElasticSearchClientConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.56.15", 9200, "http"))
        );
        return client;
    }
}
