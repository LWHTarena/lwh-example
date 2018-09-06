package com.lwhtarena.search.pojo;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <p>
 * <h2>简述：JPA注解实体</h2>
 * <ol></ol>
 * <h2>功能描述：Hibernate Search的注解</h2>
 * <ol>@Indexed: 标明这个实体需要被Lucene创建索引，从而使之可以被检索</ol>
 * <ol>@Analyzer: 告诉Hibernate Search来标记它的域以及更新Lucene索引的时候使用哪个Lucene分析器。</ol>
 *
 * 注意：你以后检索的时候，使用一个与Lucene为你将要检索的文件创建索引的时候使用的分析器相同的分析器是
 * 非常重要的。然后使用一个不同的分析器可能也会返回我们想要的结果，但是这得不到保证，所以，总是先研究你
 * 选择的用来创建索引和检索的分析器，然后再做出明智的选择。
 *
 * <ol>@DocumentId:标明Car的id字段应该被用作Lucene索引中文档的ID，这几乎总是和数据库中实体的主键是同一个字段。</ol>
 * <ol>@Field: 告诉Hibernate Search为该字段创建爱你索引，并且提供一些其他信息，比如该字段在索引中需要被如何处置。</ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@Entity
@Indexed
@Analyzer(impl = StandardAnalyzer.class)
public class Car {

    @Id
    @GeneratedValue
    @DocumentId
    private Long id;

    @Column
    @Field(store = Store.YES)
    private String make;

    @Column
    @Field(store = Store.YES)
    private String model;

    @Column
    @Field(store = Store.YES)
    private short year;

    @Column
    @Field(store = Store.NO)
    private String description;

    public Car() {
    }

    public Car(String make, String model, short year, String description) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
