package com.lwhtarena.esapi;

import com.alibaba.fastjson.JSON;
import com.lwhtarena.esapi.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: TestApiApplication
 * @Package com.lwhtarena.esapi
 * @Description: 版本7.x 高级客户端测试API
 *  使用ElasticSearch 的java rest-client，不是data-elasticsearch
 * @Version 1.0.0
 * @date 2020/8/22 17:47
 */
@SpringBootTest
public class TestApiApplication {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws IOException {
        /**1、创建索引请求**/
        CreateIndexRequest request = new CreateIndexRequest("jd_goods");
        /**2、执行请求**/
        CreateIndexResponse response =
                restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    /**
     * 测试获取索引
     */
    @Test
    public void testGetIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("jd_goods");
        /**索引是否存在**/
        boolean bool = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(bool);
    }

    /**
     * 测试删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("jd_goods");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    /**
     * 添加创建文档
     */
    @Test
    public void createDocument() throws IOException {
        User user = new User("狂神说", 3, "广州市天河区上元岗四横路110号");
        IndexRequest request = new IndexRequest("kuang_index");

        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        request.source(JSON.toJSONString(user), XContentType.JSON);

        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(response.status());
    }

    /**
     * 获取文档
     */
    @Test
    public void getDocument() throws IOException {
        GetRequest request = new GetRequest("liwh_demo1", "1");
//        request.fetchSourceContext(new FetchSourceContext(false));
        boolean bool = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(bool);
        if(bool){
        }
    }

    // 测试添加文档
    @Test
    public void testAddDocument() throws IOException {
        // 创建对象
        User user = new User("狂神说", 3,"天河区一横路2号");
        // 创建请求
        IndexRequest request = new IndexRequest("kuang_index");

        // 设置规则put /kuang_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        // 将我们的数据放入请求
        request.source(JSON.toJSONString(user), XContentType.JSON);
        // 客户端发送请求,获取相应的结果
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(response.status());
    }

    // 测试获取文档，判断是否存在 get /kuang_shen/_doc/1
    @Test
    public void testIsExists() throws IOException {
        GetRequest getRequest = new GetRequest("kuang_index", "1");
        // 不获取返回的_source的上下文了
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 获取文档信息
    @Test
    public void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("kuang_index", "1");
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());// 打印文档的内容
        System.out.println(response);// 返回的全部内容和命令格式一样的
    }

    // 更新文档信息
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("kuang_index", "1");
        updateRequest.timeout("1s");
        User user = new User("狂神说java", 18,"天河区一横路2号");
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());// 打印文档的内容
        System.out.println(response);// 返回的全部内容和命令格式一样的
    }

    // 删除文档信息
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("kuang_index", "1");
        deleteRequest.timeout("1s");
        DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());// 打印文档的内容
        System.out.println(response);// 返回的全部内容和命令格式一样的
    }

    // 批量插入，真实项目
    @Test
    public void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> userList = new ArrayList<>();

        userList.add(new User("kuangshen4", 3,"天河区一横路2号"));
        userList.add(new User("kuangshen5", 3,"天河区一横路5号"));
        userList.add(new User("kuangshen6", 3,"天河区一横路6号"));
        userList.add(new User("haha4", 3,"天河区一横路3号"));
        userList.add(new User("haha5", 3,"天河区一横路1号"));
        userList.add(new User("haha6", 3,"天河区一横路565号"));

        // 批处理请求
        for (int i = 0; i < userList.size(); i++) {
            // 批量更新和删除在这里操作就可以
            bulkRequest.add(
                    new IndexRequest("kuang_index")
                            .id("" + (i + 1))//可以不设置Id，会给你一个随机Id
                            .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }

        BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());
        System.out.println(response.hasFailures());// 是否失败，返回false代表成功
    }

    // 查询
    @Test
    public void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("kuang_index");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 高亮
//        sourceBuilder.highlighter();

        // 查询条件，使用QueryBuilders 工具实现
        // 精确查询
//        QueryBuilders.termQuery("name", "haha4");
//        QueryBuilders.matchAllQuery() 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "haha4");
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.from(0);
        sourceBuilder.size(3);

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=======================");
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
