package com.lwhtarena.kafka.admin;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author liwh
 * @Title: AdminSample
 * @Package com.lwhtarena.kafka.admin
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/15 06:35
 */
public class AdminSample {

    public final static String TOPIC_NAME="liwh-topic";

    /**
     * 设置AdminClient
     * @return
     */
    public static AdminClient adminClient(){
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.0.15:9092");

        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }

    /**
     * 创建topic实例
     */
    public static void createTopic(){
        AdminClient adminClient = adminClient();
        //副本因子
        short rs =1;
        NewTopic newTopic = new NewTopic(TOPIC_NAME, 1, rs);
        CreateTopicsResult topics = adminClient.createTopics(Arrays.asList(newTopic));
        System.out.println("创建topic的结果："+topics);
    }

    /**
     * 获取topic列表
     */
    public static void topicLists() throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
        listTopicsOptions.listInternal(true);
        ListTopicsResult listTopicsResult = adminClient.listTopics(listTopicsOptions);
        Set<String> names = listTopicsResult.names().get();
        Collection<TopicListing> topicListings = listTopicsResult.listings().get();
        KafkaFuture<Map<String, TopicListing>> mapKafkaFuture = listTopicsResult.namesToListings();
        names.stream().forEach(System.out::println);
        topicListings.stream().forEach((topicList)->{
            System.out.println(topicList);
        });
    }

    /**
     * 增加partition数量
     * @param partitions
     * @throws Exception
     */
    public static void incrPartition(int partitions) throws Exception{


    }


    public static void main(String[] args) {
        //1、创建topic实例
        createTopic();
    }
}
