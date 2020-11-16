package com.lwhtarena.es.rest;

import com.lwhtarena.es.Conference;
import com.lwhtarena.es.ElasticsearchAvailable;
import com.lwhtarena.es.SkuInfo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * @author liwh
 * @Title: ElasticsearchOperationsTest
 * @Package com.lwhtarena.es
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/14 22:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class ElasticsearchOperationsTest {

    public static @ClassRule
    ElasticsearchAvailable elasticsearchAvailable = ElasticsearchAvailable.onLocalhost();

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void textSearch() throws ParseException {

        String expectedDate = "2014-10-29";
        String expectedWord = "java";
        CriteriaQuery query = new CriteriaQuery(
                new Criteria("keywords").contains(expectedWord).and(new Criteria("date").greaterThanEqual(expectedDate)));

        SearchHits<Conference> result = operations.search(query, Conference.class, IndexCoordinates.of("conference-index"));

        assertThat(result).hasSize(3);

        for (SearchHit<Conference> conference : result) {
            System.out.println(conference.getContent().toString());
            assertThat(conference.getContent().getKeywords()).contains(expectedWord);
            assertThat(format.parse(conference.getContent().getDate())).isAfter(format.parse(expectedDate));
        }
    }

    @Test
    public void test(){
        Map<String,String> searchMap =new HashMap<String, String>();
        Map<String,Object> resultMap = new HashMap<String, Object>();


        //构建查询
        //构建查询条件封装对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        //按照关键字查询
        if (StringUtils.isNotEmpty(searchMap.get("keywords"))){
            boolQuery.must(QueryBuilders.matchQuery("name",searchMap.get("keywords")).operator(Operator.AND));
        }

        //按照品牌进行过滤查询
        if (StringUtils.isNotEmpty(searchMap.get("brand"))){
            boolQuery.filter(QueryBuilders.termQuery("brandName",searchMap.get("brand")));
        }

        //按照规格进行过滤查询
        for (String key : searchMap.keySet()) {
            if (key.startsWith("spec_")){
                String value = searchMap.get(key).replace("%2B","+");
                //spec_网络制式
                boolQuery.filter(QueryBuilders.termQuery(("specMap."+key.substring(5)+".keyword"),value));
            }
        }

        //按照价格进行区间过滤查询
        if (StringUtils.isNotEmpty(searchMap.get("price"))){
            String[] prices = searchMap.get("price").split("-");
            // 0-500 500-1000
            if (prices.length == 2){
                boolQuery.filter(QueryBuilders.rangeQuery("price").lte(prices[1]));
            }
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(prices[0]));
        }
        nativeSearchQueryBuilder.withQuery(boolQuery);

        //按照品牌进行分组(聚合)查询
        String skuBrand="skuBrand";
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(skuBrand).field("brandName"));

        //按照规格进行聚合查询
        String skuSpec="skuSpec";
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(skuSpec).field("spec.keyword"));

        //开启分页查询
        String pageNum = searchMap.get("pageNum"); //当前页
        String pageSize = searchMap.get("pageSize"); //每页显示多少条
        if (StringUtils.isEmpty(pageNum)){
            pageNum ="1";
        }
        if (StringUtils.isEmpty(pageSize)){
            pageSize="30";
        }
        //设置分页
        //第一个参数:当前页 是从0开始
        //第二个参数:每页显示多少条
        nativeSearchQueryBuilder.withPageable(PageRequest.of(Integer.parseInt(pageNum)-1,Integer.parseInt(pageSize)));

        //按照相关字段进行排序查询
        // 1.当前域 2.当前的排序操作(升序ASC,降序DESC)
        if (StringUtils.isNotEmpty(searchMap.get("sortField")) && StringUtils.isNotEmpty(searchMap.get("sortRule"))){
            if ("ASC".equals(searchMap.get("sortRule"))){
                //升序
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort((searchMap.get("sortField"))).order(SortOrder.ASC));
            }else{
                //降序
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort((searchMap.get("sortField"))).order(SortOrder.DESC));
            }
        }

        //设置高亮域以及高亮的样式
        HighlightBuilder.Field field = new HighlightBuilder.Field("name")//高亮域
                .preTags("<span style='color:red'>")//高亮样式的前缀
                .postTags("</span>");//高亮样式的后缀
        nativeSearchQueryBuilder.withHighlightFields(field);

        //开启查询
        /**
         * 第一个参数: 条件构建对象
         * 第二个参数: 查询操作实体类
         * 第三个参数: 查询结果操作对象
         */
        SearchHits<SkuInfo> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), SkuInfo.class);
        Map<String, Aggregation> aggregationMap = searchHits.getAggregations().asMap();


        //封装最终的返回结果
        //总记录数
//        resultMap.put("total",resultInfo.getTotalElements());
        resultMap.put("total",searchHits.getTotalHits());
        //总页数
        resultMap.put("totalPages",searchHits.getTotalHits());
        //数据集合
        resultMap.put("rows",searchHits.getSearchHits());

        //封装品牌的分组结果
        ParsedStringTerms brandTerms = (ParsedStringTerms) aggregationMap.get(skuBrand);
        List<String> brandList = brandTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
        resultMap.put("brandList",brandList);

        //封装规格分组结果
        ParsedStringTerms specTerms= (ParsedStringTerms) aggregationMap.get(skuSpec);
        List<String> specList = specTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
        resultMap.put("specList",specList);

        //当前页
        resultMap.put("pageNum",pageNum);

        for (String s:brandList){
            System.out.println(s);
        }

        for(String s:specList){
            System.out.println(s);
        }
        System.out.println(resultMap.toString());

    }
}
