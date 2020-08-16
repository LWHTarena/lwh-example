package com.lwhtarena.kafka.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;

import java.util.Properties;

/**
 * @author liwh
 * @Title: AdminSample
 * @Package com.lwhtarena.kafka.admin
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/15 06:35
 */
public class AdminSample {
    /**
     * 设置AdminClient
     * @return
     */
    public static AdminClient adminClient(){
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.8:9092");

        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }
}
