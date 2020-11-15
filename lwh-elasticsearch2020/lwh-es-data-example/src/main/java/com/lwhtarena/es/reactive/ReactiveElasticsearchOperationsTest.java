package com.lwhtarena.es.reactive;

import com.lwhtarena.es.Conference;
import com.lwhtarena.es.ElasticsearchAvailable;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author liwh
 * @Title: ReactiveElasticsearchOperationsTest
 * @Package com.lwhtarena.es.reactive
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/15 16:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration2.class)
public class ReactiveElasticsearchOperationsTest {
    public static @ClassRule
    ElasticsearchAvailable elasticsearchAvailable = ElasticsearchAvailable.onLocalhost();

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    ReactiveElasticsearchOperations operations;

    @Test
    public void textSearch() {

        String expectedDate = "2014-10-29";
        String expectedWord = "java";
        CriteriaQuery query = new CriteriaQuery(
                new Criteria("keywords").contains(expectedWord).and("date").greaterThanEqual(expectedDate));

        operations.search(query, Conference.class) //
                .as(StepVerifier::create) //
                .consumeNextWith(it -> verify(it, expectedWord, expectedDate)) //
                .consumeNextWith(it -> verify(it, expectedWord, expectedDate)) //
                .consumeNextWith(it -> verify(it, expectedWord, expectedDate)) //
                .verifyComplete();
    }

    void verify(SearchHit<Conference> hit, String expectedWord, String expectedDate) {

        assertThat(hit.getContent().getKeywords()).contains(expectedWord);
        try {
            assertThat(format.parse(hit.getContent().getDate())).isAfter(format.parse(expectedDate));
        } catch (ParseException e) {
            fail("o_O", e);
        }
    }
}
