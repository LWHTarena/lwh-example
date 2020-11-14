package com.lwhtarena.es.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.assertj.core.api.Assumptions;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;

/**
 * @author liwh
 * @Title: ElasticsearchAvailable
 * @Package com.lwhtarena.es.utils
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/14 20:02
 */
public class ElasticsearchAvailable implements TestRule {

    private final String url;

    private ElasticsearchAvailable(String url) {
        this.url = url;
    }

    public static ElasticsearchAvailable onLocalhost() {
        return new ElasticsearchAvailable("http://192.168.1.8:19200");
    }

    @Override
    public Statement apply(Statement base, Description description) {

        return new Statement() {

            @Override
            public void evaluate() throws Throwable {

                checkServerRunning();
                base.evaluate();
            }
        };
    }

    private void checkServerRunning() {

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            CloseableHttpResponse response = client.execute(new HttpHead(url));
            if (response != null && response.getStatusLine() != null) {
                Assumptions.assumeThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
            }
        } catch (IOException e) {
            throw new AssumptionViolatedException(String.format("Elasticsearch Server seems to be down. %s", e.getMessage()));
        }
    }
}
