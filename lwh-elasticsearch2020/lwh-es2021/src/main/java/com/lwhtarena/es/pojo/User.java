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
 * @date 2021/03/29 04:10:26
 * @description
 */
@Data
@NoArgsConstructor
@Document(indexName = "test_user")
public class User {
    @Id
    private long id;
    private int age;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    private String password;

    public User(long id, int age, String name, String password) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.password = password;
    }
}
