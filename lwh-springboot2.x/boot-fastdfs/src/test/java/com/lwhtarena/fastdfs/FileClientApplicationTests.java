package com.lwhtarena.fastdfs;

import com.lwhtarena.fastdfs.utils.FastDFSClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/01 02:11:05
 * @description
 */

@SpringBootTest
public class FileClientApplicationTests {

    @Test
    public void Upload() {
        String fileUrl = this.getClass().getResource("/test.png").getPath();
        System.out.println(fileUrl);
        File file = new File(fileUrl);
        String str = FastDFSClient.uploadFile(file);
        FastDFSClient.getResAccessUrl(str);
    }

    @Test
    public void Delete() {
        FastDFSClient.deleteFile("group1/M00/00/00/rBEAClu8OiSAFbN_AAbhXQnXzvw031.jpg");
    }
}
