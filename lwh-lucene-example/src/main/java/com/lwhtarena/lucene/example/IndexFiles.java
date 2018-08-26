package com.lwhtarena.lucene.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.charset.StandardCharsets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * 创建索引
 */
public class IndexFiles {

    /**
     * 使用给定的编写器索引给定文件，或者如果给定目录，则对在给定目录下找
     * 到的文件和目录进行递归。
     * @param writer
     * @param path
     */
    public static void indexDocs(final IndexWriter writer, Path path) throws IOException {
        if(Files.isDirectory(path)){
            Files.walkFileTree(path,new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    try {
                        indexDoc(writer,file,attrs.lastModifiedTime().toMillis());
                    }catch (IOException ignore) {
                        // don't index files that can't be read.
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        }else{
            indexDoc(writer,path,Files.getLastModifiedTime(path).toMillis());
        }

    }

    /**
     * Indexes a single document
     * @param writer
     * @param file
     * @param lastModifield
     */
    public static void indexDoc(IndexWriter writer, Path file, long lastModifield) throws IOException {
        try (InputStream stream =Files.newInputStream(file)){

            /**
             * 1、创建新的索引文档
             */
            Document doc =new Document();

            /**
             * 2、将文件的路径添加为名为“path”的字段。使用索引（即可搜索）的字段，
             * 但不要将字段标记为单独的字，也不要索引术语频率或位置信息：
             */
            Field pathField = new StringField("path",file.toString(),Field.Store.YES);
            doc.add(pathField);

            doc.add(new LongPoint("modified",lastModifield));

            doc.add(new TextField("contents",new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));

            if(writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE){
                System.out.println("adding "+file);
                writer.addDocument(doc);
            }else {
                System.out.println("updating " +file);
                writer.updateDocument(new Term("path",file.toString()),doc);
            }
        }

    }

    public static void main(String[] args) {
        String usage = "java org.apache.lucene.demo.IndexFiles"
                + " [-index INDEX_PATH] [-docs DOCS_PATH] [-update]\n\n"
                + "This indexes the documents in DOCS_PATH, creating a Lucene index"
                + "in INDEX_PATH that can be searched with SearchFiles";
        System.out.println(usage);

        String indexPath ="index";
        String docsPath =null;
        boolean create =true;

        for(int i=0;i<args.length;i++){
            if("-index".equals(args[i])){
                indexPath =args[i+1];
                i++;
            }else if("-docs".equals(args[i])){
                create =false;
            }
        }

        if (docsPath == null){
            System.err.println("Usage:"+usage);
            System.exit(1);
        }

        Path docDir = Paths.get(docsPath);

        if(!Files.isReadable(docDir)){
            System.out.println("Document directory '" +docDir.toAbsolutePath()+ "' does not exist or is not readable, please check the path");
            System.exit(1);
        }

        Date start = new Date();

        try {
            System.out.println("Indexing to directory '" + indexPath + "'...");
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

            if(create){
                /**
                 * Create a new index in the directory, removing any
                 * previously indexed documents:
                 * Add new documents to an existing index:
                 */
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            }else{
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            }

            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(writer, docDir);

            writer.close();

            Date end = new Date();
            System.out.println(end.getTime() - start.getTime() + " total milliseconds");
        } catch (IOException e) {
            System.out.println(" caught a " + e.getClass() + " with message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
