package com.lwhtarena.es.service;

import com.lwhtarena.es.pojo.EsBean;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 04:25:54
 * @description
 */
public interface IesService {
    public Iterator<EsBean> findAll();
    public void saveAll(List<EsBean> list);
    public void create();
    public SearchHits testForHigh() throws IOException;
}
