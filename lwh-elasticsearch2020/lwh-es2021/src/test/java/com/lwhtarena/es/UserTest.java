package com.lwhtarena.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lwhtarena.es.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 04:05:17
 * @description
 */
@Slf4j
@SpringBootTest
public class UserTest {
    @Resource
    private ElasticsearchRestTemplate template;

    @Resource
    private RestHighLevelClient client;

    @Test
    public void testCreateIndexAndDoc() throws IOException {

        //1.创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("test_user");
        //2.执行客户端请求
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        log.info("创建索引{}成功", response);
        System.out.println("response.isAcknowledged() = " + response.isAcknowledged());


    }

    @Test
    public void saveDoc() throws IOException {
        String index = "1";

        User user = new User();
        user.setId(1L);
        user.setAge(20);
        user.setName("狗蛋");
        user.setPassword("23243424");

        //创建请求
        IndexRequest request = new IndexRequest("test_user");

        //规则 put /test_index/_doc/1
        request.id(index);
        request.timeout(TimeValue.timeValueSeconds(1));
        //将数据放入请求 json
        request.source(user, XContentType.JSON);
        //客户端发送请求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        log.info("添加数据成功 索引为: {}, response 状态: {}, id为: {}",index,response.status().getStatus(), response.getId());
        log.info("=========={}",response.getId());

    }

    @Test
    public void saveDoc2() throws IOException {
        IndexRequest request = new IndexRequest("test_user");

        User user = new User();
        user.setId(1L);
        user.setAge(20);
        user.setName("狗蛋");
        user.setPassword("23243424");

//        String s = JSONUtil.pa

        request.id("1");
        String jsonString = "{" +
                "\"id\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";


//        request.source(jsonString, XContentType.JSON);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(user);
        request.source(stringObjectMap);

//        request.source()

        //客户端发送请求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        log.info("添加数据成功 索引为: {}, response 状态: {}, id为: {}","user",response.status().getStatus(), response.getId());
        log.info("=========={}",response.getId());


    }


    @Test
    public void testStrToBean() {
        User user = new User();
        user.setId(1L);
        user.setAge(20);
        user.setName("狗蛋");
        user.setPassword("23243424");

        JSONObject jsonObject = JSONUtil.parseObj(user);

        System.out.println(jsonObject.toString());
    }

    @Test
    public void saveDocRandomId() throws IOException {
        IndexRequest request = new IndexRequest("test_user");

        User user = new User();
        user.setId(2L);
        user.setAge(30);
        user.setName("狗蛋333");
        user.setPassword("23dsfasf243424");

//        request.id("1");

        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(user);
        request.source(stringObjectMap);

//        request.source()

        //客户端发送请求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        log.info("添加数据成功 索引为: {}, response 状态: {}, id为: {}","user",response.status().getStatus(), response.getId());
        log.info("=========={}",response.getId());

    }
}
