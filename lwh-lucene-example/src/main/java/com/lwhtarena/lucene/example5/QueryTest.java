package com.lwhtarena.lucene.example5;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：索引 查询</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class QueryTest {
    public static void main(String[] args) throws IOException, ParseException {
        Path indexPath = FileSystems.getDefault().getPath("d:\\index\\");
        Directory dir = FSDirectory.open(indexPath);
        // 分词器
        Analyzer analyzer = new StandardAnalyzer();

        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        String[] queryFields = {"Title", "Content", "LastModified"};
        String queryString = "sean";
        // 同时查询多个域
        QueryParser parser = new MultiFieldQueryParser(queryFields, analyzer);
        Query query = parser.parse(queryString);
        TopDocs topDocs = searcher.search(query, 10);
        if(topDocs.totalHits > 0)
            for(ScoreDoc scoreDoc : topDocs.scoreDocs){
                int docNum = scoreDoc.doc;
                Document doc = searcher.doc(docNum);
                System.out.println(doc.toString());
            }

    }
}
