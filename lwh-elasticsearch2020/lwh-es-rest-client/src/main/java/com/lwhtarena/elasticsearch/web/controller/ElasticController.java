package com.lwhtarena.elasticsearch.web.controller;

import com.lwhtarena.elasticsearch.web.entity.DocBean;
import com.lwhtarena.elasticsearch.web.service.IElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liwh
 * @Title: ElasticController
 * @Package com.lwhtarena.elasticsearch.web.controller
 * @Description: 逻辑
 *   测试
 *   http://localhost:8080/ems/elastic/init
 *   http://localhost:8080/ems/elastic/all
 * @Version 1.0.0
 * @date 2020/4/11 01:26
 */
@RestController
@RequestMapping("/elastic")
public class ElasticController {
    @Autowired
    private IElasticService elasticService;

    @GetMapping("/init")
    public void init(){
        elasticService.createIndex();
        List<DocBean> list =new ArrayList<DocBean>();
        list.add(new DocBean(1L,"XX0193","XX8064","xxxxxx",1));
        list.add(new DocBean(2L,"XX0210","XX7475","xxxxxxxxxx",1));
        list.add(new DocBean(3L,"XX0257","XX8097","xxxxxxxxxxxxxxxxxx",1));
        elasticService.saveAll(list);

    }

    @GetMapping("/all")
    public Iterator<DocBean> all(){
        return elasticService.findAll();
    }

}
