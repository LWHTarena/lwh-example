package com.lwhtarena;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author liwh
 * @Title: ESTest
 * @Package com.lwhtarena
 * @Description: 测试
 * @Version 1.0.0
 * @date 2020/4/23 04:09
 */
public class ESTest {

    /**
     * 从es中查询数据
     * es7 之后废弃
     */
    @Deprecated
    @Test
    public void test1() throws UnknownHostException {
        //指定es集群
        Settings settings = Settings.builder().put("cluster.name","elasticsearch").build();

        //创建访问es服务器的客户端
        TransportClient client =new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.31.120"),9300));

        //查询
        GetResponse response =client.prepareGet("ecommerce","_doc","1").execute().actionGet();

        //得到查询出的数据
        System.out.println(response.getSourceAsString());

        client.close();
    }


}
