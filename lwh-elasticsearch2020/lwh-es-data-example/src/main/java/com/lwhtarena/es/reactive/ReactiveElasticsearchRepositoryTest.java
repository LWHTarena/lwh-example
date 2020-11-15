package com.lwhtarena.es.reactive;

import com.lwhtarena.es.Conference;
import com.lwhtarena.es.ElasticsearchAvailable;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author liwh
 * @Title: ReactiveElasticsearchRepositoryTest
 * @Package com.lwhtarena.es.reactive
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/15 16:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration2.class)
public class ReactiveElasticsearchRepositoryTest {

    public static @ClassRule
    ElasticsearchAvailable elasticsearchAvailable = ElasticsearchAvailable.onLocalhost();

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    ConferenceRepository2 repository;

    @Test
    public void textSearch() {

        String expectedDate = "2014-10-29";
        String expectedWord = "java";

        repository.findAllByKeywordsContainsAndDateAfter(expectedWord, expectedDate) //
                .as(StepVerifier::create) //
                .consumeNextWith(it -> verify(it, expectedWord, expectedDate)) //
                .consumeNextWith(it -> verify(it, expectedWord, expectedDate)) //
                .consumeNextWith(it -> verify(it, expectedWord, expectedDate)) //
                .verifyComplete();
    }

    void verify(Conference it, String expectedWord, String expectedDate) {

        assertThat(it.getKeywords()).contains(expectedWord);
        try {
            assertThat(format.parse(it.getDate())).isAfter(format.parse(expectedDate));
        } catch (ParseException e) {
            fail("o_O", e);
        }
    }
}
