package com.lwhtarena.search;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * <p>
 * <h2>简述：Hibernate工具类之获取SessionFactory</h2>
 * <ol></ol>
 * <h2>功能描述：Hibernate工具类之获取SessionFactory</h2>
 * <ol></ol>
 *
 * Hibernate工具类之获取SessionFactory和Session
 * 由于SessionFactory是重量级的，也就是说创建SessionFactory需要消耗大量的资源，所以在整个
 * 应用程序中最好只存在一个SessionFactory，这就需要把获取SessionFactory的方法单独抽取出来
 * 封装成一个工具类
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class HbnUtils {

    private static SessionFactory sessionFactory;
    /**
     * @return Session
     */
    public static Session getSession(){
        return getSessionFactory().openSession();
    }
    /**
     * SessionFactory是重量级的
     * 最好做成单例模式
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory(){
        //保证SessionFactory为单例
        if (sessionFactory == null ||sessionFactory.isClosed()) {
            Configuration configuration =new Configuration();
            ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
                    .configure()
//                    .applySettings(configuration.getProperties())
                    .build();
//            sessionFactory = configuration.configure().buildSessionFactory(serviceRegistry);
            sessionFactory = new MetadataSources( serviceRegistry ).buildMetadata().buildSessionFactory();

        }
        return sessionFactory;
    }
}
