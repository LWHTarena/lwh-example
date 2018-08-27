package com.lwhtarena.lucene.example4;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * <p>
 * <h2>简述：官方例子</h2>
 * <ol></ol>
 * <h2>功能描述：官方例子</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class IndexFiles {
    private IndexFiles() {
    }

    public static void main(String[] args) {
        //在运行是要添加参数如：-docs （你文件的路径）
        String usage = "java com.lwhtarena.lucene.example4.IndexFiles [-index INDEX_PATH] [-docs DOCS_PATH] [-update]" +
                "This indexes the documents in DOCS_PATH, creating a Lucene indexin INDEX_PATH that can be searched with SearchFiles";
        String indexPath = "index";
        String docsPath = null;
        boolean create = true;

        for(int docDir = 0; docDir < args.length; ++docDir) {
            if("-index".equals(args[docDir])) {
                indexPath = args[docDir + 1];
                ++docDir;
            } else if("-docs".equals(args[docDir])) {
                docsPath = args[docDir + 1];
                ++docDir;
            } else if("-update".equals(args[docDir])) {
                create = false;
            }
        }

        if(docsPath == null) {
            System.err.println("Usage: " + usage);
            System.exit(1);
        }

        Path var13 = Paths.get(docsPath, new String[0]);
        if(!Files.isReadable(var13)) {
            System.out.println("Document directory \'" + var13.toAbsolutePath() + "\' does not exist or is not readable, please check the path");
            System.exit(1);
        }

        Date start = new Date();

        try {
            System.out.println("Indexing to directory \'" + indexPath + "\'...");
            //打开文件路径
            FSDirectory e = FSDirectory.open(Paths.get(indexPath, new String[0]));
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            if(create) {
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            } else {
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            }

            IndexWriter writer = new IndexWriter(e, iwc);
            indexDocs(writer, var13);
            writer.close();
            Date end = new Date();
            System.out.println(end.getTime() - start.getTime() + " total milliseconds");
        } catch (IOException var12) {
            System.err.println(" caught a " + var12.getClass() + "\n with message: " + var12.getMessage());
        }

    }

    static void indexDocs(final IndexWriter writer, Path path) throws IOException {
        if(Files.isDirectory(path, new LinkOption[0])) {
            /**遍历文件夹下的文件**/
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        IndexFiles.indexDoc(writer,file, attrs.lastModifiedTime().toMillis());
                    } catch (IOException var4) {
                        var4.printStackTrace();
                    }

                    /**
                     * FileVisitResult.CONTINUE 继续遍历
                     * FileVisitResult.TERMINATE 中止访问
                     * FileVisitResult.SKIP_SIBLINGS 不访问同级的文件或目录
                     * FileVisitResult.SKIP_SUBTREE 不访问子目录
                     */
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            indexDoc(writer, path, Files.getLastModifiedTime(path, new LinkOption[0]).toMillis());
        }

    }

    static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
        InputStream stream = Files.newInputStream(file, new OpenOption[0]);
        Throwable var5 = null;

        try {
            Document doc = new Document();
            StringField pathField = new StringField("path", file.toString(), Field.Store.YES);
            doc.add(pathField);
            doc.add(new LongPoint("modified", new long[]{lastModified}));
            doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));
            if(writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                System.out.println("adding " + file);
                writer.addDocument(doc);
            } else {
                System.out.println("updating " + file);
                writer.updateDocument(new Term("path", file.toString()), doc);
            }
        } catch (Throwable var15) {
            var5 = var15;
            try {
                throw var15;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } finally {
            if(stream != null) {
                if(var5 != null) {
                    try {
                        stream.close();
                    } catch (Throwable var14) {
                        var5.addSuppressed(var14);
                    }
                } else {
                    stream.close();
                }
            }

        }

    }
}