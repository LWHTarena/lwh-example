package com.lwhtarena.es.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 04:09:07
 * @description
 */
@Data
@NoArgsConstructor
@Document(indexName = "test")
public class EsBean {
    @Id
    private int id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String tags;

    public EsBean(int id,String name,String tags){
        this.id=id;
        this.name=name;
        this.tags=tags;
    }
}
