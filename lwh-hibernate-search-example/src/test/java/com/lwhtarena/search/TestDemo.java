package com.lwhtarena.search;

import com.lwhtarena.search.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class TestDemo {

    private User[] arr = {
            new User("李文浩", "天河区慧通产业园区B2栋"),
            new User("liwenhao", "元岗横路6号")
    };

    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() //configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    @Test
    public void createTable() {
//        Session hibernateSession =HbnUtils.getSession();
        Session hibernateSession = sessionFactory.openSession();

        Transaction tx = hibernateSession.beginTransaction();
        hibernateSession.save(arr[0]);
        hibernateSession.save(arr[1]);
        tx.commit();
    }


}
