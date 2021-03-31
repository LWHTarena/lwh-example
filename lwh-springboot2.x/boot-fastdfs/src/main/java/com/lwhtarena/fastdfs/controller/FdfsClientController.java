package com.lwhtarena.fastdfs.controller;

import com.lwhtarena.fastdfs.utils.FastdfsClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/04/01 03:04:38
 * @description FastDFS上传文件控制器
 */
@Controller
public class FdfsClientController {
    private static Logger logger = LoggerFactory.getLogger(FdfsClientController.class);

    @Autowired
    private FastdfsClientUtil fastdfsClientUtil;


    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model view) {
        try {
            String path = fastdfsClientUtil.uploadFile(file);
            System.out.println("path:" + path);
            view.addAttribute("uri", path);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }

    /**
     * 下载文件
     *
     * @param filePath
     * @param response
     * @return
     */
    @RequestMapping("/downloadFile")
    public void downloadFile(@RequestParam("filePath") String filePath, HttpServletResponse response) {
        int index = filePath.lastIndexOf("/");
        String fileName = filePath.substring(index + 1);
        try {
            byte[] bytes = fastdfsClientUtil.downloadFile(filePath);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            byte[] buff = new byte[1024];
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
            os.close();
            bis.close();
            logger.info("Download  successfully!");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    @RequestMapping("/deleteFile")
    @ResponseBody
    public String deleteFile(@RequestParam("filePath") String filePath) {
        fastdfsClientUtil.deleteFile(filePath);

        return  "删除文件成功";
    }

    /**
     * 上传图片，并且生成缩略图
     */

    @RequestMapping("/uploadImageAndCrtThumbImage")
    public String uploadImageAndCrtThumbImage(@RequestParam("file") MultipartFile file, Model view) {

        try {
            //上传图片，并且生成缩略图的方法
            Map<String, String> map = fastdfsClientUtil.uploadImageAndCrtThumbImage(file);

            view.addAttribute("uri", map.get("path"));//原图片地址
            view.addAttribute("slavePath", map.get("slavePath"));  //缩略图地址

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }
}
