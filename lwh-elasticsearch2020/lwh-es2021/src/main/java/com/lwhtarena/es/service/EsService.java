package com.lwhtarena.es.service;

import com.lwhtarena.es.dao.EsMapper;
import com.lwhtarena.es.pojo.EsBean;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 04:24:24
 * @description
 */
@Service
public class EsService implements IesService {

    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    EsMapper mapper;

    @Override
    public Iterator<EsBean> findAll() {
        return mapper.findAll().iterator();
    }

    @Override
    public void saveAll(List<EsBean> list) {
        mapper.saveAll(list);
    }

    @Override
    public void create() {
        template.createIndex(EsBean.class);
    }

    /**
     * 自定义查询
     **/
    @Override
    public SearchHits testForHigh() throws IOException {
        String preTag = "<font color='#dd4b39'>";//google的色值
        String postTag = "</font>";

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .must(new MatchQueryBuilder("tags", "很帅"));

        /*自定义查询*/
        Query searchQuery = new NativeSearchQueryBuilder().   //总的查询
                withQuery(boolQueryBuilder).           //设置bool查询
                withHighlightFields(new HighlightBuilder.Field("name").preTags(preTag).postTags(postTag)).//设置高亮效果
                withHighlightFields(new HighlightBuilder.Field("tags").preTags(preTag).postTags(postTag)).build();

        SearchHits<EsBean> searchHits = template.search(searchQuery, EsBean.class);
        return searchHits;
    }
}
