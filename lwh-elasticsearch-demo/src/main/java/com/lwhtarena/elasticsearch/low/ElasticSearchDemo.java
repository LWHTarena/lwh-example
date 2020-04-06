package com.lwhtarena.elasticsearch.low;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liwh
 * @Title: ElasticSearchDemo
 * @Package com.lwhtarena.elasticsearch.low
 * @Description: 编写测试用例
 * @Version 1.0.0
 * @date 2020/4/6 18:47
 *
 * @Before, @BeforeClass, @BeforeEach 和 @BeforeAll之间的不同
 * @BeforeAll，@BeforeClass
 *  在当前类的所有测试方法之前执行。
 *  注解在静态方法上。
 *  此方法可以包含一些初始化代码。
 *
 * @Before, @BeforeEach
 *  在每个测试方法之前执行。
 *  注解在非静态方法上。
 *  可以重新初始化测试方法所需要使用的类的某些属性。
 */
public class ElasticSearchDemo {

    private static final ObjectMapper MAPPER =new ObjectMapper();

    private RestClient restClient;

    private static String IP ="192.168.31.120";
    private static Integer PORT =9200;
    static String PROTOCOL ="http";


    @BeforeEach
    public void init(){
        RestClientBuilder builder =RestClient.builder(new HttpHost(IP,PORT,PROTOCOL));

        builder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(Node node) {
                System.out.println("出错了 -> "+ node);
            }
        });

        this.restClient =builder.build();
    }

    @AfterEach
    public void after(){
        try {
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询集群状态
     */
    @Test
    public void testGetInfo() throws IOException {
        Request request =new Request("GET", "/_cluster/state");
        request.addParameter("pretty","true");
        Response response =this.restClient.performRequest(request);

        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 新增数据
     */
    @Test
    public void testCreateData() throws IOException {
        Request request =new Request("POST","/lwh/house");
        Map<String,Object> data =new HashMap<>();
        data.put("id","2002");
        data.put("title","拎包入住");
        data.put("price","4000");

        request.setJsonEntity(MAPPER.writeValueAsString(data));
        Response response =this.restClient.performRequest(request);

        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));

    }

    /**
     * 根据id查询数据
     */
    @Test
    public void testQueryData() throws IOException {
        Request request =new Request("GET","/lwh/house/-GOlT3EB0NZH8kXSANkA");

        Response response =this.restClient.performRequest(request);
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 搜索数据
     */
    @Test
    public void testSearchData() throws IOException {
        Request request =new Request("POST","/lwh/house/_search");
        String searchJson ="{\"query\":{\"match\":{\"title\":\"拎包入住\"}}}";//{"query":{"match":{"title":"拎包入住"}}}
//        String searchJson2 ="{\"query\":{\"match\":{\"title\":\"李文浩\"}}}";//{"query":{"match":{"title":"拎包入住"}}}
        request.setJsonEntity(searchJson);
        request.addParameter("pretty","true");
        Response response =this.restClient.performRequest(request);

        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


}
