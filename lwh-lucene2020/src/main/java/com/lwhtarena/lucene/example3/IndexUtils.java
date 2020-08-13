package com.lwhtarena.lucene.example3;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <h2>简述：索引工具类</h2>
 * <ol></ol>
 * <h2>功能描述：索引工具类</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class IndexUtils {

    /**
     * 索引源，即源数据目录
     */
    public static String searchSource ="D:\\lucene\\searchsource";

    /**
     * 索引目标地址
     */
    public static String indexFolder ="D:\\lucene\\indexdata";

    /**
     * 从文件创建Document
     * @param folderPath
     * @return
     * @throws IOException
     */
    public static List<Document> file2Document(String folderPath) throws IOException{
        List<Document> list = new ArrayList<Document>();
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            checkDirectoryIsExist(folderPath);
        }
        // 获取目录 中的所有文件
        File[] files = folder.listFiles();
        for(File file:files){
            String fileName =file.getName();
            if(file.isFile()){

                //文件内容
                String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

                //文件路径
                String filePath =file.getAbsolutePath();

                //文件大下
                long fileSize =FileUtils.sizeOf(file);

                //创建文件
                Document doc =new Document();

                //创建field
                Field field_fileName = new StringField("fileName", fileName, Field.Store.YES);
                // 文件内容
                Field field_fileContent = new TextField("fileContent", fileContent, Field.Store.YES);
                // 文件大小
                Field field_fileSize = new TextField("fileSize", String.valueOf(fileSize), Field.Store.YES);
                // 文件路径
                Field field_filePath = new StoredField("filePath", filePath);

                // 自定义检索条件
                Field auto_field = new TextField("auto", "auto", Field.Store.YES);

                // 将各各Field添加到文档中
                doc.add(field_fileName);
                doc.add(field_fileContent);
                doc.add(field_fileSize);
                doc.add(field_filePath);
                doc.add(auto_field);
                list.add(doc);
            }
        }

        return list;

    }

    /****
     * 校验文件目录是否存在
     * @param path
     */
    public static void checkDirectoryIsExist(String path) {
        File file =new File(path);

        /***
         * 判断文件目录的父目录是否存在
         * 存在，再判断自己是否存在
         * 不存在，那么就创建
         * **/
        if(!file.getParentFile().exists()){
            System.out.println("不存在该父目录，立即创建");
            if(!file.getParentFile().mkdirs()){
                checkDirectoryIsExist(file.getParentFile().getAbsolutePath().toString());
            }else{
                System.out.println("创建本身目录");
                if (!file.exists()) {
                    file.mkdir();
                }
            }
        }else {
            System.out.println("存在父目录，校验自己是否存在");
            if(!file.exists()){
                file.mkdir();
            }
        }

    }


    public static void printDocumentOfFile(Document doc) {
        System.out.println("文件名称 =" + doc.get("fileName"));
        System.out.println("文件大小 =" + doc.get("fileSize"));
        System.out.println("文件内容 =" + doc.get("fileContent"));
    }

}
