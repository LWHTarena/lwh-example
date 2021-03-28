package com.lwhtarena.esapi.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: ElasticseachConfig
 * @Package com.lwhtarena.esapi.config
 * @Description:
 * 两步骤：
 *  1、找对象
 *  2、把对象放在spring容器中
 *
 * @Version 1.0.0
 * @date 2020/8/22 17:38
 */
// 狂神的Spring两步骤
// 1.找对象
// 2.放到spring中
// 3.如果是springboot，先分析源码
// xxxx AutoConfiguration xxxProperfile
@Configuration //xml -> bean
public class ElasticseachConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        /**单机版**/
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.56.15",9200,"http")));
        return restHighLevelClient;
    }

}
