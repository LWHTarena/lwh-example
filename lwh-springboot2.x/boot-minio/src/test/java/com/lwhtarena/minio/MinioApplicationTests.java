package com.lwhtarena.minio;

import com.lwhtarena.minio.conf.MinioData;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/01 04:34:01
 * @description
 */
@SpringBootTest
class MinioApplicationTests {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioData minioData;

    /*创建一个Bucket 相当于根目录结构*/
    @Test
    void makeBucket() throws Exception {
        String name =minioData.getBucketName();
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
        if(!found){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
        }
    }

    @Test
    void removeAllBucketNotification() throws Exception {
//        minioClient.removeAllBucketNotification(minioData.getBucketName());
        // minioClient.removeBucket(minioData.getBucketName());
    }

}
