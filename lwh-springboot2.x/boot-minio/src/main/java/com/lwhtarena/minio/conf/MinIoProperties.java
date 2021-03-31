package com.lwhtarena.minio.conf;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/01 04:02:12
 * @description
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**实体类**/
@Data
@ConfigurationProperties(prefix = "min.io")
public class MinIoProperties {
    /**
     * minio地址+端口号
     */
    private String url;

    private String endpoint;

    /** minio用户名*/
    private String accessKey;

    /** minio密码*/
    private String secretKey;

    /**
     * 文件桶的名称
     */
    private String bucketName;
}
