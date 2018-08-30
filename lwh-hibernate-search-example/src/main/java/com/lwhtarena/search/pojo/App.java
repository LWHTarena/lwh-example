package com.lwhtarena.search.pojo;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：创建实体类</h2>
 * <ol>有了实体类型后，我们需要告诉Hibernate Search如何来利用Lucene对该实体进行管理</ol>
 * <li>添加@Indexed</li>
 * <li>向具体的字段添加@Field注解</li>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@Entity
@Indexed  //这个注解告诉Lucene去为App实体类型创建索引。注意，并不是每个实体类型都需要这个注解，只有确定将会作为搜索目标的实体类才需要使用它。
public class App implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Field
    private String name;   //app引用的名称

    /**
     * 这里我们向name和description字段添加了@Field注解，表示这两个字段将会作为
     * 搜索的目标字段。同时注意到image字段并没有被@Field标注，这是因为我们不需要
     * 将图片的名字也作为可搜索的字段。
     */
    @Column(length = 1000)
    @Field
    private String description;  //app的介绍

    @Column
    private String image;  //app图片的链接

    public App() {
    }

    public App(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
