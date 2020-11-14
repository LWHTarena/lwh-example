package com.lwhtarena.es.bean;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

/**
 * @author liwh
 * @Title: Conference
 * @Package com.lwhtarena.es.bean
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/14 17:15
 */

@Data
@Builder
@Document(indexName = "conference-index",type = "geo-class-type",shards = 1,replicas = 0,refreshInterval = "-1")
public class Conference {
    @Id
    private String id;
    private String name;
    @Field(type = FieldType.Date)
    private String date;
    private GeoPoint location;
    private List<String> keywords;

    public Conference() {
    }

    public Conference(String id, String name, String date, GeoPoint location, List<String> keywords) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.keywords = keywords;
    }
}
