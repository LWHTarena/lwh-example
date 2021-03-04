package com.lwhtarena.commonsConfiguration;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Test;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class ConfigurationTest {

    @Test
    public void testProperties() throws Exception {
        Class<?> clazz = getClass();
        URL url = clazz.getResource("configuration.properties");
        PropertiesConfiguration configuration = new PropertiesConfiguration(url);
        // 遍历配置的键
        for (Iterator<String> iterator = configuration.getKeys(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        System.out.println(configuration.getString("userId"));
        System.out.println(configuration.getInt("age"));
        System.out.println(configuration.getString("descript"));
    }

    @Test
    public void testXML() throws Exception {
        Class<?> clazz = getClass();
        URL url = clazz.getResource("configuration.xml");
        XMLConfiguration configuration = new XMLConfiguration(url);
        System.out.println("RootElementName:"
                + configuration.getRootElementName());
        System.out.println("id:" + configuration.getProperty("[@id]"));
        System.out.println("version:" + configuration.getProperty("[@version]"));
        System.out.println("xmlns:xsi:"
                + configuration.getProperty("[@xmlns:xsi]"));
        System.out.println("xmlns:" + configuration.getProperty("[@xmlns]"));
        System.out.println("xsi:schemaLocation:"
                + configuration.getProperty("[@xsi:schemaLocation]"));
        System.out.println("display-name:"
                + configuration.getProperty("display-name"));

        // 解析servlet
        System.out.println("-----------------parse servlet");
        List<HierarchicalConfiguration> servlets = configuration
                .configurationsAt("servlet");
        for (HierarchicalConfiguration servlet : servlets) {
            System.out
                    .println("servlet-name:" + servlet.getString("servlet-name"));
            System.out.println("servlet-class:"
                    + servlet.getString("servlet-class"));
        }
        // 解析servlet mapping
        System.out.println("-----------------parse servlet mapping");
        List<HierarchicalConfiguration> servletMappings = configuration
                .configurationsAt("servlet-mapping");
        for (HierarchicalConfiguration servletMapping : servletMappings) {
            System.out.println("servlet-name:"
                    + servletMapping.getString("servlet-name"));
            System.out.println("url-pattern:"
                    + servletMapping.getString("url-pattern"));
        }
    }
}
