package com.lwhtarena.commonsConfiguration2;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;
import org.junit.Test;

public class ConfigTest2 {

    @Test
    public void test2() {
        try {
            Configurations configs = new Configurations();
            XMLConfiguration config = configs.xml(this.getClass().getClassLoader().getResource("token.xml"));
            {
                // 使用默认的符号定义创建一个表达式引擎
                DefaultExpressionEngine engine = new DefaultExpressionEngine(
                        DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
                // 指定表达式引擎
                config.setExpressionEngine(engine);
                System.out.println(config.getBoolean("token.device.validate"));
                System.out.println(config.getInt("token.person.expire"));
                System.out.println(config.getString("token.person.expire[@description]"));
            }
            {
                // 使用 XPath表达式引擎
                // 请注意这里路径分隔符和attribute标签与上面使用DefaultExpressionEngine是不同的
                XPathExpressionEngine xpathEngine = new XPathExpressionEngine();
                config.setExpressionEngine(xpathEngine);
                System.out.println(config.getBoolean("token/device/validate"));
                System.out.println(config.getInt("token/person/expire"));
                System.out.println(config.getString("token/person/expire/@description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
