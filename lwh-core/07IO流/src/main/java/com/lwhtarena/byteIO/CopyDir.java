package com.lwhtarena.byteIO;

/**
 * @author lwh
 * @folder com.lwhtarena.byteIO
 * @date 2016/10/5.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

import com.lwhtarena.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件夹的拷贝
 * 1、文件 赋值 copyFile
 * 2、文件 创建 mkdirs（）
 * 3、递归查找子孙级
 */
public class CopyDir {

    public static void main(String[] args) {

    }

    /**
     * 拷贝文件夹
     * @param srcPath
     * @param destPath
     */
    public static void copyDir(String srcPath,String destPath){
        File src =new File(srcPath);
        File dest =new File(destPath);
        copyDir(src,dest);
    }

    /**
     * @param src
     * @param dest
     */
    private static void copyDir(File src, File dest) {
        if(src.isDirectory()){//文件夹
            dest =new File(dest,src.getName());
        }
        copyDirDetail(src,dest);
    }

    /**
     * 拷贝文件夹细节
     * @param src
     * @param dest
     */
    public static void copyDirDetail(File src,File dest){
        if(src.isFile()){ //文件
            try {
                FileUtil.copyFile(src, dest);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(src.isDirectory()){ //文件夹
            //确保目标文件夹存在
            dest.mkdirs();
            //获取下一级目录|文件
            for(File sub:src.listFiles()){
                copyDirDetail(sub,new File(dest,sub.getName()));
            }
        }
    }
}
