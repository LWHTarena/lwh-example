package com.lwhtarena.commonsConfiguration2;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class ConfigurationTest2 {

    @Test
    public void testProperties() throws Exception {
        Configurations configs = new Configurations();
        PropertiesConfiguration configuration =configs.properties("com/lwhtarena/commonsConfiguration/configuration.properties");
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
        Configurations configs = new Configurations();
        XMLConfiguration configuration =configs.xml("com/lwhtarena/commonsConfiguration/configuration.xml");

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
        List<HierarchicalConfiguration<ImmutableNode>> servlets = configuration
                .configurationsAt("servlet");
        for (HierarchicalConfiguration servlet : servlets) {
            System.out
                    .println("servlet-name:" + servlet.getString("servlet-name"));
            System.out.println("servlet-class:"
                    + servlet.getString("servlet-class"));
        }
        // 解析servlet mapping
        System.out.println("-----------------parse servlet mapping");
        List<HierarchicalConfiguration<ImmutableNode>> servletMappings = configuration
                .configurationsAt("servlet-mapping");
        for (HierarchicalConfiguration servletMapping : servletMappings) {
            System.out.println("servlet-name:"
                    + servletMapping.getString("servlet-name"));
            System.out.println("url-pattern:"
                    + servletMapping.getString("url-pattern"));
        }
    }
}
