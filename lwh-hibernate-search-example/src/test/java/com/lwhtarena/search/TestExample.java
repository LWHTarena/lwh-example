package com.lwhtarena.search;

import com.lwhtarena.search.pojo.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;

import java.util.List;

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
public class TestExample {


    private Session  hibernateSession=HbnUtils.getSession();


    /**
     * 创建索引
     *
     * 1、Using Hibernate Session to index data：
     *
     * FullTextSession fullTextSession = Search.getFullTextSession(session);
     * fullTextSession.createIndexer().startAndWait();
     *
     * 2、Using JPA to index data：
     *
     * EntityManager em = entityManagerFactory.createEntityManager();
     * FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
     * fullTextEntityManager.createIndexer().startAndWait();
     */
    @Test
    public void createIndex(){
        try {
            FullTextSession fullTextSession = Search.getFullTextSession(hibernateSession);
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void searching(){
        FullTextSession fullTextSession = Search.getFullTextSession(hibernateSession);
        Transaction tx = fullTextSession.beginTransaction();

// create native Lucene query using the query DSL
// alternatively you can write the Lucene query using the Lucene query parser
// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Book.class).get();
        org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("title", "subtitle", "authors.name")
                .matching("Java rocks!")
                .createQuery();

// wrap Lucene query in a org.hibernate.Query
        org.hibernate.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Book.class);

// execute search
        List result = hibQuery.list();

        tx.commit();
        hibernateSession.close();
    }
}
