package com.lwhtarena.elasticsearch.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author liwh
 * @Title: DocBean
 * @Package com.lwhtarena.elasticsearch.web.entity
 * @Description: 文档
 * @Version 1.0.0
 * @date 2020/4/11 01:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "ems",type = "_doc")
public class DocBean {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String firstCode;

    @Field(type = FieldType.Keyword)
    private String secordCode;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    @Field(type = FieldType.Integer)
    private Integer type;
}
