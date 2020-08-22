package com.lwhtarena.elasticsearch.high;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liwh
 * @Title: ElasticSearchHighDemo
 * @Package com.lwhtarena.elasticsearch.high
 * @Description: 高级客户端
 * @Version 1.0.0
 * @date 2020/4/6 21:28
 */
public class ElasticSearchHighDemo {
    private RestHighLevelClient restClient;

    private static String IP ="192.168.31.120";
    private static Integer PORT =9200;
    static String PROTOCOL ="http";

    @BeforeEach
    public void init(){
        RestClientBuilder builder =RestClient.builder(new HttpHost(IP,PORT,PROTOCOL));
        this.restClient =new RestHighLevelClient(builder);
    }

    @AfterEach
    public void after() throws IOException {
        this.restClient.close();
    }

    /**
     * 新增文档，同步操作
     * @throws IOException
     */
    @Test
    public void testCreate() throws IOException {
        Map<String,Object> data =new HashMap<>();
        data.put("id","2005");
        data.put("title","广州大道 天寿路789号 三房一厅");
        data.put("price","10000");

        IndexRequest indexRequest =new IndexRequest("lwh").id("1").source(data);
        IndexResponse indexResponse =this.restClient.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println("id -> "+indexResponse.getId());
        System.out.println("index -> "+indexResponse.getIndex());
        System.out.println("type -> "+indexResponse.getType());
        System.out.println("version -> "+indexResponse.getVersion());
        System.out.println("result -> "+indexResponse.getResult());
        System.out.println("sharedInfo -> "+indexResponse.getShardInfo());
    }

    /**
     * 新增文档，异步操作
     */
    @Test
    public void testCreateAsync() throws IOException {
        Map<String,Object> data =new HashMap<>();
        data.put("id","2005");
        data.put("title","广州大道 元岗横路114号 两房一厅");
        data.put("price","5000");

        IndexRequest indexRequest =new IndexRequest("lwh").id("2").source(data);

        this.restClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                System.out.println("id -> "+indexResponse.getId());
                System.out.println("index -> "+indexResponse.getIndex());
                System.out.println("type -> "+indexResponse.getType());
                System.out.println("version -> "+indexResponse.getVersion());
                System.out.println("result -> "+indexResponse.getResult());
                System.out.println("sharedInfo -> "+indexResponse.getShardInfo());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println(e);
            }
        });

        try {
            TimeUnit.SECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() throws IOException {
        GetRequest getRequest =new GetRequest("lwh","house","-GOlT3EB0NZH8kXSANkA");

        //指定返回的字段
        String[] includes =new String[]{"title","id"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext =new FetchSourceContext(true,includes,excludes);

        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse getResponse =this.restClient.get(getRequest,RequestOptions.DEFAULT);

        System.out.println("数据 => "+getResponse.getSource());

    }

    /**
     * 判断是否存在
     */
    @Test
    public void testExists() throws IOException {
        GetRequest getRequest =new GetRequest("lwh","house","-GOlT3EB0NZH8kXSANkA");

        //指定返回的字段
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        boolean isExists =this.restClient.exists(getRequest,RequestOptions.DEFAULT);

        System.out.println("是否存在 => "+isExists);
    }

    /**
     * 删除数据
     */
    @Test
    public void testDelete() throws IOException {
        DeleteRequest deleteRequest =new DeleteRequest("lwh","house","-GOlT3EB0NZH8kXSANkA");

        DeleteResponse deleteResponse =this.restClient.delete(deleteRequest,RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());//OK or NOT_FOUND
    }

    /**
     * 更新数据
     */
    @Test
    public void testUpdate() throws IOException {
        UpdateRequest updateRequest =new UpdateRequest("lwh","house","-WPiT3EB0NZH8kXSbNn7");

        Map<String,Object> data =new HashMap<>();
        data.put("title","珠江新城");
        data.put("price","$50000");

        updateRequest.doc(data);
        UpdateResponse updateResponse =this.restClient.update(updateRequest,RequestOptions.DEFAULT);
        System.out.println("version -> "+ updateResponse.getVersion());
    }

    /**
     * 测试搜索
     */
    @Test
    public void testSearch() throws IOException {
        SearchRequest searchRequest =new SearchRequest("lwh");
        searchRequest.types("house");

        SearchSourceBuilder sourceBuilder =new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("title","广州大道"));
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        SearchResponse response =this.restClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println("搜索到 "+response.getHits().getTotalHits()+" 条数据。");

        SearchHits hits =response.getHits();
        hits.forEach(p -> System.out.println(p.getSourceAsString()));
    }
}
