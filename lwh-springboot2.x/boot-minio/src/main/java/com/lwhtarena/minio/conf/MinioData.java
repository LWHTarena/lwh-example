package com.lwhtarena.minio.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/01 04:37:28
 * @description
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioData {

    /**
     * minio地址+端口号
     */
    private String url;

    /**
     * minio用户名
     */
    private String accessKey;

    /**
     * minio密码
     */
    private String secretKey;

    /**
     * 文件桶的名称
     */
    private String bucketName;
}
