package com.lwhtarena.lucene.example4;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * 在早期的Java版本中，如果需要对指定目录下的文件进行遍历，则需要用递归的方法来实现，这种方法有点复杂，而且灵活性也不高。
 * 而使用Java7中Files工具类中的walkFileTree()方法可以很容易的实现对目录下的所有文件进行遍历。这个方法需要一个Path和一
 * 个FileVisitor参数。其中Path是要遍历的路径，而FileVisitor则可以看成的一个文件访问器，它主要提供的四个方法，这在程序中
 * 可以看到。而FileVisitor四个方法返回的都是FileVisitResult对象，它是一个枚举类，代表的是返回之后的一些后续的操作。
 * FileVisitResult主要包含四个常见的操作。
 *
 * FileVisitResult.CONTINUE 继续遍历
 * FileVisitResult.TERMINATE 中止访问
 * FileVisitResult.SKIP_SIBLINGS 不访问同级的文件或目录
 * FileVisitResult.SKIP_SUBTREE 不访问子目录
 * 通过创建SimpleFileVisitor对象来对文件进行遍历即可，它是FileVisitor的实现类，这样可以有选择的重写指定的方法
 *
 * 下面的程序是使用FileVisitor对目录进行遍历，该程序还简单实现了在指定的目录下查找文件。
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class FileVisitorTest {

    public static void main(String[] args) throws IOException {
        // 使用FileVisitor对目录进行遍历
        Files.walkFileTree(Paths.get("d:", "lucene"), new SimpleFileVisitor<Path>() {

            // 在访问子目录前触发该方法
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问" + dir + "目录");
                return FileVisitResult.CONTINUE;
            }

            // 在访问文件时触发该方法
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问" + file + "文件");
                if (file.endsWith("FilesTest.java")) {
                    System.out.println("------已找到FilesTest.java,文件内容-----");
                    List<String> list = Files.readAllLines(file);
                    // 打印出文件的内容
                    System.out.println(list);
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            // 在访问失败时触发该方法
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                // 写一些具体的业务逻辑
                return super.visitFileFailed(file, exc);
            }

            // 在访问目录之后触发该方法
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                // 写一些具体的业务逻辑
                return super.postVisitDirectory(dir, exc);
            }
        });
    }
}
