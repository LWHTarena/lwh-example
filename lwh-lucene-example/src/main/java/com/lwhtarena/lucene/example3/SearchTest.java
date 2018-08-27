package com.lwhtarena.lucene.example3;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;

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
public class SearchTest {

    /**
     * 索引目录地址
     * 查询方法
     * @throws IOException
     */
    public static void testTermQuery() throws IOException {
        // 创建查询对象，根据文件名称域搜索匹配文件名称的文档
        //Query query = new TermQuery(new Term("fileName", "aa.txt"));
        Query query = new TermQuery(new Term("auto", "auto"));
        // 指定索引目录
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(IndexUtils.indexFolder));
        // 定义IndexReader
        IndexReader reader = DirectoryReader.open(directory);
        // 创建indexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        // 执行搜索
        TopDocs topDocs = indexSearcher.search(query, 100);
        // 提取搜索结果
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        System.out.println("共搜索到总记录数：" + topDocs.totalHits);
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 文档id
            int docID = scoreDoc.doc;
            // 得到文档
            Document doc = indexSearcher.doc(docID);
            // 输出 文件内容
            IndexUtils.printDocumentOfFile(doc);
        }

    }

    /**
     * 更新索引： Lucene其实并未提供更新索引的方法,这里的更新操作内部是先删除再添加的方式
     */
    public static void updateIndex(IndexWriter indexWriter, Document doc, Term term) {
        try {
            indexWriter.updateDocument(term, doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除索引: 在执行完该方法后,再执行本类的TermQuery()方法,得到数据
     * 这说明此时删除的文档并没有被完全删除,而是存储在一个回收站中,它是可以恢复的 ，将回车站数据清空即可
     */
    public void deleteIndex(IndexWriter indexWriter,Term term) {
        try {
            indexWriter.deleteDocuments(term);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交索引内容的变更情况
     */
    public void commitIndex(IndexWriter indexWriter) {
        try {
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        testTermQuery();
    }

}
