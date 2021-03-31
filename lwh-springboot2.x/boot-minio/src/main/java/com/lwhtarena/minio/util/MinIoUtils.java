//package com.lwhtarena.minio.util;
//
//import com.lwhtarena.minio.conf.MinIoProperties;
//import io.minio.MinioClient;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.InputStream;
//
///**
// * @author liwh
// * @version 1.0
// * @date 2021/04/01 04:04:14
// * @description
// */
//@Component
//@Configuration
//@EnableConfigurationProperties({MinIoProperties.class})
//public class MinIoUtils {
//    private MinIoProperties minIo;
//
//    public MinIoUtils(MinIoProperties minIo) {
//        this.minIo = minIo;
//    }
//
//    private MinioClient instance;
//
//    @PostConstruct
//    public void init() {
//        try {
//            instance = new MinioClient(minIo.getEndpoint(),minIo.getAccessKey(),minIo.getSecretKey());
//        } catch (InvalidPortException e) {
//            e.printStackTrace();
//        } catch (InvalidEndpointException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 判断 bucket是否存在
//     * @param bucketName
//     * @return
//     */
//    public boolean bucketExists(String bucketName){
//        try {
//            return instance.bucketExists(bucketName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 创建 bucket
//     * @param bucketName
//     */
//    public void makeBucket(String bucketName){
//        try {
//            boolean isExist = instance.bucketExists(bucketName);
//            if(!isExist) {
//                instance.makeBucket(bucketName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 文件上传
//     * @param bucketName
//     * @param objectName
//     * @param filename
//     */
//    public void putObject(String bucketName, String objectName, String filename){
//        try {
//            instance.putObject(bucketName,objectName,filename,null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 文件上传
//     * @param bucketName
//     * @param objectName
//     * @param stream
//     */
//    public void putObject(String bucketName, String objectName, InputStream stream){
//        try {
//            instance.putObject(bucketName,objectName,stream,null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 删除文件
//     * @param bucketName
//     * @param objectName
//     */
//    public void removeObject(String bucketName, String objectName){
//        try {
//            instance.removeObject(bucketName,objectName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    //省略各种CRUD
//}
