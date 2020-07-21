package com.lwhtarena.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author liwh
 * @Title: MockServer
 * @Package com.lwhtarena.wiremock
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:31
 */
public class MockServer {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        configureFor(8062);
        removeAllMappings();

        mock("/order/1", "01");
        mock("/order/2", "02");
    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }

}
