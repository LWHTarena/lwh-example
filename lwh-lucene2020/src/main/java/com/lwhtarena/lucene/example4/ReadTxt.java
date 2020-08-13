package com.lwhtarena.lucene.example4;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.apache.lucene.document.StringField.TYPE_STORED;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 * 文件索引建立与搜索
 * 导入一百万的数据创建索引
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class ReadTxt {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("","index");
        String extPath = "D:\\2018lwh\\lwh-example\\lwh-lucene-example\\src\\main\\resources\\ext.dic";
        Directory directory = FSDirectory.open(path);
        //定义分词器
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer).setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);


        String filePath = "H:\\myfile\\品高\\茂名全量地址20170401（boss+）.csv";
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis,"GBK");
        BufferedReader br = new BufferedReader(isr);
        String content;
        String levelOne = "";
        String levelTwo = "";
        String levelThree = "";
        String levelFour = "";
        String levelFive = "";
        int i = 0;
       /* while ((content = br.readLine()) != null){
            if (i == 1000) {
                break;
            }
            String[] split = content.split(",");
            String tempOne = "";
            String tempTwo = "";
            String tempThree = "";
            String tempFour = "";
            String tempFive = "";
            if (i == 1) {
                levelOne = split[2];
                levelTwo = split[3];
                levelThree = split[4];
                levelFour = split[5];
                levelFive = split[6];
            }

            tempOne = split[2];
            tempTwo = split[3];
            tempThree = split[4];
            tempFour = split[5];
            tempFive = split[6];

            StringBuilder sb = new StringBuilder();
            //使用equals如存在""避免放在前面
            if (levelOne != null && levelOne != "" && tempOne!= "" && tempOne != null) {
                if(!tempOne.equals(levelOne)) {
                    sb.append("\n" + levelOne);
                    levelOne = tempOne;
                    System.out.println("11" + levelOne+tempOne);
                }
            }
            if (levelTwo != null && levelTwo != "" && tempTwo!= ""&& tempTwo != null) {
                if(!tempTwo.equals(levelTwo)) {
                    sb.append("\n" + levelTwo);
                    levelTwo = tempTwo;
                }
            }
            if (levelThree != null && levelThree != ""&& tempThree != ""&& tempThree != null) {
                if(!tempThree.equals(levelThree)) {
                    sb.append("\n" + levelThree);
                    levelThree = tempThree;
                }
            }
            if (levelFour != null && levelFour != ""&& tempFour != "" && tempFour != null) {
                if(!tempFour.equals(levelFour)) {
                    sb.append("\n" + levelFour);
                    levelFour = tempFour;
                }
            }
            if (levelFive != null && levelFive != "" && tempFive != "" && tempFive != null) {
                if(!tempFive.equals(levelFive)) {
                    sb.append("\n" + levelFive);
                    levelFive = tempFive;
                }
            }
            if(i == 422){
                System.out.println("address" + sb.toString()+tempFive+levelFive);
            }

//            System.out.println("address" + sb.toString()+tempFive+levelFive);
            if (sb != null){
                //以追加的形式写入
                FileOutputStream fos = new FileOutputStream(extPath,true);
                OutputStreamWriter osr = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osr);
                bw.write(sb.toString(),0,sb.length());
                bw.close();
            }
            i++;
        }*/

        long start = System.currentTimeMillis();
        System.out.println("start:"+ start);
        while ((content = br.readLine()) != null) {
            //第一行不记录
            /*if(i == 0){
                continue;
            }*/
           /* if (i == 1000) {
                break;
            }*/

            //定义文档
            Document document = new Document();
            //读取每一行
//            System.out.println(content);
            String[] split = content.split(",");
            String id = split[0];
            String address = split[1];


//            System.out.println(id + ":" + address);
            document.add(new Field("id",id,TYPE_STORED));
            document.add(new Field("address",address,TYPE_STORED));
            indexWriter.addDocument(document);
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println("end:"+ end);
        float time = end - start;
        System.out.println("用时："+ time);
        //提交
        indexWriter.commit();
        //关闭
        indexWriter.close();
        br.close();
        isr.close();
        fis.close();
    }
}
