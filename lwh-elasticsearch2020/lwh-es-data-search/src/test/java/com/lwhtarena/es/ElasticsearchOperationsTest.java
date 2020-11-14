package com.lwhtarena.es;

import com.lwhtarena.es.bean.Conference;
import com.lwhtarena.es.config.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author liwh
 * @Title: ElasticsearchOperationsTest
 * @Package com.lwhtarena.es
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/14 17:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class ElasticsearchOperationsTest {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    ElasticsearchOperations operations;

    @Test
    public void textSearch() throws ParseException {

        String expectedDate = "2014-10-29";
        String expectedWord = "java";
        CriteriaQuery query = new CriteriaQuery(
                new Criteria("keywords").contains(expectedWord).and(new Criteria("date").greaterThanEqual(expectedDate)));

        SearchHits<Conference> result = operations.search(query, Conference.class, IndexCoordinates.of("conference-index"));

        assertThat(result).hasSize(3);

        for (SearchHit<Conference> conference : result) {
            assertThat(conference.getContent().getKeywords()).contains(expectedWord);
            assertThat(format.parse(conference.getContent().getDate())).isAfter(format.parse(expectedDate));
        }
    }

    @Test
    public void geoSpatialSearch() {

        GeoPoint startLocation = new GeoPoint(50.0646501D, 19.9449799D);
        String range = "330mi"; // or 530km
        CriteriaQuery query = new CriteriaQuery(new Criteria("location").within(startLocation, range));

        SearchHits<Conference> result = operations.search(query, Conference.class, IndexCoordinates.of("conference-index"));

        assertThat(result).hasSize(2);
    }
}
