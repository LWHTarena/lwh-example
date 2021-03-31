package com.lwhtarena.fastdfs.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/01 03:07:58
 * @description FastDFS工具类
 */
@Component
public class FastdfsClientUtil {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;


    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),
                file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }


    /**
     * 下载文件
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     * @throws IOException
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path,
                downloadByteArray);
        return bytes;
    }

    /**
     * 删除文件
     *
     * @Param fileUrl 文件访问地址
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            e.printStackTrace();
        }
    }


    /**
     * 上传图片，并生成缩略图
     */
    public Map<String, String> uploadImageAndCrtThumbImage(MultipartFile file) throws IOException {
        Map<String, String> map = new HashMap<>();
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(),
                file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);

        // 这里需要一个获取从文件名的能力，所以从文件名配置以后就最好不要改了
        String slavePath = thumbImageConfig.getThumbImagePath(storePath.getPath());
        map.put("path", storePath.getFullPath());  //原路径图片地址
        map.put("slavePath", slavePath);            //缩略图图片地址
        return map;
    }

}
