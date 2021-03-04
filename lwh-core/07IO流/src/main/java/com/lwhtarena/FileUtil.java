package com.lwhtarena;

import java.io.*;

/**
 * 文件操作
 * 1、文件拷贝
 * 2、文件夹拷贝 拒绝自己拷贝给自己
 * @author lwh
 * @folder com.lwhtarena
 * @date 2016/10/5.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class FileUtil {

    /**
     * 拷贝文件夹
     * @param srcPath
     * @param destPath
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copyDir(String  srcPath,String destPath) throws FileNotFoundException, IOException{
        //拒绝自己拷贝给自己
        if(srcPath.equals(destPath)){
            return ;
        }
        File src=new File(srcPath);
        File dest =new File(destPath);
        copyDir(src,dest);
    }

    /**
     * 拷贝文件夹
     * @param src
     * @param dest
     */
    public static void copyDir(File src,File dest){
        if(src.isDirectory()){//文件夹
            dest =new File(dest,src.getName());
            if(dest.getAbsolutePath().contains(src.getAbsolutePath())){
                System.out.println("父目录不能拷贝到子目录中");
                return;
            }
        }
        copyDirDetail(src,dest);
    }

    /**
     * 拷贝文件夹细节
     * @param src
     * @param dest
     */
    public static void copyDirDetail(File src,File dest){
        if (src.isFile()){//文件
            try {
                FileUtil.copyFile(src,dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件的拷贝
     * @param srcPath
     * @param destPath
     * @throws IOException
     */
    public static void copyFile(String srcPath,String destPath) throws IOException {
        copyFile(new File(srcPath),new File(destPath));
    }

    /**
     * 文件的拷贝
     * @param src
     * @param dest
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copyFile(File src,File dest) throws FileNotFoundException,IOException {
        if(! src.isFile()){ //不是文件或者为null
            System.out.println("只能拷贝文件");
            throw new IOException("只能拷贝文件");
        }
        //dest为已经存在的文件夹，不能建立于文件夹同名的文件
        if(dest.isDirectory()){
            System.out.println(dest.getAbsolutePath()+"不能建立于文件夹同名的文件");
            throw new IOException(dest.getAbsolutePath()+"不能建立于文件夹同名的文件");
        }


        //2、选择流
        InputStream is =new BufferedInputStream(new FileInputStream(src));
        OutputStream os =new BufferedOutputStream(new FileOutputStream(dest));
        //3、文件拷贝   循环+读取+写出
        byte[] flush =new byte[1024];
        int len =0;
        //读取
        while(-1!=(len=is.read(flush))){
            //写出
            os.write(flush, 0, len);
        }
        os.flush(); //强制刷出

        //关闭流
        os.close();
        is.close();
    }
}
