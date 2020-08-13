package com.lwhtarena.lucene.example;

import junit.runner.Version;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

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
public class TestLucene {
    //1.创建directory,保存索引,可以保存在内存中也可以保存在硬盘上
    //保存在内存中使用Directory directory=new RAMDirectory();
    File indexDir = new File(this.getClass().getClassLoader().getResource("").getFile());//new File("F:\\lucene\\index")

    /**
     * 创建索引
     */
    @org.junit.Test
    public void createIndex() throws IOException {
//        Directory index = new RAMDirectory();
        /**
         * 版本变更说明:
         *   在4.10.3中,FSDirectory.open的参数是file对象,
         *   在6.6以后,开始使用Path类(java.nio.file)
         *   File对象转Path的方法:
         *         File file = new File(文件路径);
         *         Path path = file.toPath();
         */
        Directory index = FSDirectory.open(indexDir.toPath());
        /**
         *
         * 从6.6开始,IndexWriterConfig就不在需要制定版本了
         * 而且有了无参构造方法,默认使用的分词器就是StandardAnalyzer.
         *  即表示,如果你不想更改分词器,就使用无参构造
         */
        StandardAnalyzer analyzer = new StandardAnalyzer();
        //2.1创建indexwriterConfig,并指定分词器版本
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        // 1. create the index
        IndexWriter w = new IndexWriter(index, config);
        addDoc(w, "Lucene in Action", "193398817");
        addDoc(w, "Lucene for Dummies", "55320055Z");
        addDoc(w, "Managing Gigabytes", "55063554A");
        addDoc(w, "The Art of Computer Science", "9900333X");
        addDoc(w, "李文浩测试lucene The Art of Computer Science", "9900333W");
        w.close();
    }

    private void addDoc(IndexWriter w, String title, String isbn) throws IOException {

//        File docDirectory=new File("F:\\lucene\\example");
//           for(File file: docDirectory.listFiles()){
//            doc=new Document();
//            //创建搜索域,并说明是否进行分词
//            doc.add(new TextField("content",new FileReader(file)));
//            doc.add(new StringField("filename", file.getName(), Store.YES));
//            doc.add(new StringField("path",file.getAbsolutePath(), Store.YES));
//            //写入文档
//            writer.addDocument(doc);
//        }

        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        // use a string field for isbn because we don't want it tokenized
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }

    /**
     * 检索
     * @throws IOException
     */
    @org.junit.Test
    public void search() throws IOException {
        // 2. query
//        String querystr = "lucene";//检索的字
        String querystr = "李文浩";//检索的字

        // the "title" arg specifies the default field to use
        // when no field is explicitly specified in the query.
        Query q = null;
        try {
            StandardAnalyzer analyzer = new StandardAnalyzer();
            //4创建搜索用的query,指定搜索域
            q = new QueryParser("title", analyzer).parse(querystr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. search
        int hitsPerPage = 10;
        //.1创建索引在的文件夹
        //Directory indexDirectory=FSDirectory.open(new File("F:\\lucene\\index"));
        Directory index = FSDirectory.open(indexDir.toPath());
        //2.创建indexReader
        IndexReader reader = DirectoryReader.open(index);
        //根据indexReader创建indexSeacher
        IndexSearcher searcher = new IndexSearcher(reader);
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;

        // 4. display results
        System.out.println("Found " + hits.length + " hits.");
        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
        }

        // reader can only be closed when there
        // is no need to access the documents any more.
        reader.close();
        //答案
        //1. 193398817  Lucene in Action
        //2. 55320055Z  Lucene for Dummies
    }
    /**
     * 分词
     */
    @Test
    public void cutWords() throws IOException {
//        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
//        CJKAnalyzer analyzer = new CJKAnalyzer(Version.LUCENE_46);
        SimpleAnalyzer analyzer = new SimpleAnalyzer();
        String text = "Spark是当前最流行的开源大数据内存计算框架，采用Scala语言实现，由UC伯克利大学AMPLab实验室开发并于2010年开源。";
        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                System.out.println(charTermAttribute.toString());
            }
            tokenStream.end();
        } finally {
            tokenStream.close();
            analyzer.close();
        }
    }
}
