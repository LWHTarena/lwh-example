package com.lwhtarena.minio.controller;

import com.lwhtarena.minio.conf.MinioData;
import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/01 04:26:49
 * @description
 */
@RestController
@RequestMapping("/minio")
public class MinioController {
    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioData minioData;

    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        String bucketName = minioData.getBucketName();
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        } else {
            // 若不存在bucket，则新建
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            // 得到文件流
            final InputStream is = file.getInputStream();
            // 文件名
            final String fileName = file.getOriginalFilename();
            // 把文件放到minio的boots桶里面
//            PutObjectOptions putObjectOptions = new PutObjectOptions(is.available(), -1);
//            putObjectOptions.setContentType("image/jpeg");//把这玩意去掉的话，浏览器访问就会变成下载图片
//            minioClient.putObject( bucketName, fileName, is, putObjectOptions);
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                    .object(fileName).stream(is, -1, 10485760).contentType("image/jpeg").build());
            //拿到远程访问地址
//            String url = minioClient.getObjectUrl(bucketName, fileName);
            String url =minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(fileName).build());
            // 关闭输入流
            is.close();
            return url;
        }
    }

    @GetMapping(value = "/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) throws Exception {
        InputStream in = null;
        StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder().bucket(minioData.getBucketName()).object(fileName).build());
        response.setContentType(statObjectResponse.contentType());
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        in =minioClient.getObject(GetObjectArgs.builder().bucket(minioData.getBucketName()).object(fileName).build());
        IOUtils.copy(in, response.getOutputStream());
        in.close();
    }

    @GetMapping(value = "/delete")
    @SneakyThrows(Exception.class)
    public String delete(@RequestParam("fileName") String fileName) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(minioData.getBucketName()).object(fileName).build());
        return "删除成功.";
    }
}

