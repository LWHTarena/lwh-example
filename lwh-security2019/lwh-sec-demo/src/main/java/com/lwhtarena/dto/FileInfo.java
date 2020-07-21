package com.lwhtarena.dto;

/**
 * @author liwh
 * @Title: FileInfo
 * @Package com.lwhtarena.dto
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:25
 */
public class FileInfo {
    public FileInfo(String path){
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
