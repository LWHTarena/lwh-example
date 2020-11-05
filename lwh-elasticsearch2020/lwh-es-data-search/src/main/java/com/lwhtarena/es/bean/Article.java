package com.lwhtarena.es.bean;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author liwh
 * @Title: Article
 * @Package com.lwhtarena.es.bean
 * @Description:
 *
 * 关于上面类中使用的相关spring-boot-starter-data-elasticsearch引入包的注解含义，在文章开篇处简单介绍了，下面详细讲述一下各个参数的含义：
 *
 * @Document 作用在类，标记实体类为文档对象，一般有两个属性
 *
 * indexName：对应索引库名称
 * type：对应在索引库中的类型
 * shards：分片数量，默认分5片
 * replicas：副本数量，默认1份
 * @Id 作用在成员变量，标记一个字段作为id主键
 *
 * @Field 作用在成员变量，标记为文档的字段，并指定字段映射属性
 *
 * type：字段类型，取值是枚举：FieldType
 * index：是否索引，布尔类型，默认是true
 * store：是否存储，布尔类型，默认是false
 * analyzer和searchAnalyzer中参数名称保持一直，ik_max_word可以改成ik_smart。
 *
 * ik_max_word和ik_smart的区别？
 *
 * ik_max_word参数采用穷尽式的分词，比如“我爱家乡”，可能会分出“我”，“我爱”，“家乡”等，而ik_smart参数分的会比较粗，如上语句可能会分出“我爱”，“家乡”这样。如果想要搜索出的结果尽可能全，可以使用ik_max_word参数，如果需要结果尽可能精确，可以使用ik_smart参数。
 *
 * @Version 1.0.0
 * @date 2020/11/5 23:46
 */
@Document(indexName = "blog",type = "article")
public class Article {
    /**
     * 主键ID
     */
    @Field(type = FieldType.Keyword)
    private String id;

    /**
     * 文章标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;

    /**
     * 文章内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
