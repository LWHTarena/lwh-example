package com.lwhtarena.file;

import java.io.Closeable;

/**
 * @author：liwenhao
 * @Date:2017/2/12
 * @description:<p>关闭流的工具类</p>
 * @version:v1.0
 */
public class FileUtil {

    /**
     * 工具类关闭流
     * 可变参数: ...  只能形参最后一个位置,处理方式与数组一致
     * @param io
     */
    public static void close(Closeable ... io){
        for (Closeable temp:io){
            try {
                if (null !=temp){
                    temp.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用泛型方法
     * @param io
     * @param <T>
     */
    public static <T extends Closeable> void closeAll(T ... io){
        for (Closeable temp:io){
            try {
                if(temp!=null){
                    temp.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
