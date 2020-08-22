package com.lwhtarena.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author liwh
 * @Title: User
 * @Package com.lwhtarena.elasticsearch.entity
 * @Description: 用户对象
 * @Version 1.0.0
 * @date 2020/8/22 16:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "eslwh",type = "user",shards = 6,replicas = 1)
public class User {
    @Id
    private Long id;
    @Field(store = true)
    private String name;
    @Field
    private Integer age;
    @Field
    private String hobby;
}
