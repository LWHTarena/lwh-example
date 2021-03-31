package com.lwhtarena.minio.conf;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/01 04:32:14
 * @description
 */
@Slf4j
@Configuration
public class MinioConfig {

    @Autowired
    private MinioData minioData;


    /*初始化minio客户端,不用每次都初始化*/
    @Bean
    public MinioClient minioClient() {
        try {

//            MinioClient minioClient =
//                    MinioClient.builder()
//                            .endpoint("https://play.min.io")
//                            .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
//                            .build();
            return MinioClient.builder()
                    .endpoint(minioData.getUrl())
                    .credentials(minioData.getAccessKey(),minioData.getSecretKey()).build();
        } catch (final Exception e) {
            log.error("初始化minio出现异常:{}", e.fillInStackTrace());
            throw new RuntimeException(e.fillInStackTrace());
        }
    }

}
