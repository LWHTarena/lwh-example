package com.lwhtarena.lucene.example4;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
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
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class CreateIndex {
    public static void main(String[] args) throws IOException {
        //定义IndexWriter
        //index是一个相对路径，当前工程
        Path path = FileSystems.getDefault().getPath("d:\\lucene", "index");
        Directory directory = FSDirectory.open(path);
        //定义分词器
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer =new SmartChineseAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer).setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        //定义文档
        Document document = new Document();
        //定义文档字段
//        document.add(new LegacyLongField("id", 5499, Field.Store.YES));
        /**
         * 还要注意的是:
         *
         * 如果要存储，必须创建同名的StoredField类
         * 如果要排序使用，必须同时创建同名的StoredField类与NumericDocValuesField类
         */
        document.add(new NumericDocValuesField("id", 5499));
        document.add(new IntPoint("id",5499));
        document.add(new StoredField("id",5499));
        document.add(new Field("title", "小米6", TYPE_STORED));
        document.add(new Field("sellPoint", "骁龙835，6G内存，双摄！", TYPE_STORED));
        //写入数据
        indexWriter.addDocument(document);
        //添加新的数据
        document = new Document();
//        document.add(new LegacyLongField("id", 8324, Field.Store.YES));
        document.add(new NumericDocValuesField("id", 8324));
        document.add(new IntPoint("id",8324));
        document.add(new StoredField("id",8324));
        document.add(new Field("title", "OnePlus5", TYPE_STORED));
        document.add(new Field("sellPoint", "8核，8G运行内存", TYPE_STORED));
        indexWriter.addDocument(document);
        //提交
        indexWriter.commit();
        //关闭
        indexWriter.close();

    }
}
