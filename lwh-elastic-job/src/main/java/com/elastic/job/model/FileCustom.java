package com.elastic.job.model;

/**
 * @author liwh
 * @Title: FileCustom
 * @Package com.elastic.job.model
 * @Description: 文件实体
 * @Version 1.0.0
 * @date 2020/10/29 01:42
 */
public class FileCustom {

    /**标识**/
    private String id;
    /**文件名**/
    private String name;
    /**文件类型，如text、images、radio、vedio**/
    private String type;
    private String context;
    private Boolean backedUp =false;

    public FileCustom() {
    }

    public FileCustom(String id, String name, String type, String context) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Boolean getBackedUp() {
        return backedUp;
    }

    public void setBackedUp(Boolean backedUp) {
        this.backedUp = backedUp;
    }
}
