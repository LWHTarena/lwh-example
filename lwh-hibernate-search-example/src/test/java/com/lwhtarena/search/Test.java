//package com.lwhtarena.search;
//
//import com.lwhtarena.search.pojo.Car;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.BooleanClause;
//import org.apache.lucene.search.BooleanQuery;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.util.Version;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.search.FullTextSession;
//import org.hibernate.search.Search;
//import org.hibernate.service.ServiceRegistry;
//import org.junit.Assert;
//import org.junit.Before;
//
//import java.util.List;
//
///**
// * <p>
// * <h2>简述：</h2>
// * <ol></ol>
// * <h2>功能描述：</h2>
// * <ol></ol>
// * </p>
// *
// * @Author: liwh
// * @Date :
// * @Version: 版本
// */
//public class Test {
//
//    private Car[] testCars = {
//            new Car("Shelby American", "GT 350", (short) 1967, "This is Tim's car!"),
//            new Car("Chevrolet", "Bel Air", (short) 1957, "This is a true classic")
//    };
//
//    private SessionFactory hibernateSessionFactory;
//
//    private Session hibernateSession;
//
//
//    /**
//     * 首先，我们需要加载Hibernate配置文件，然后创建一个数据库Session
//     * @throws Exception
//     */
//    @Before
//    public void setUp() throws Exception{
//        Configuration configuration =new Configuration();
//        configuration.configure("hibernate.cfg.xml");
//        /**
//         * 在hibernate4.2中我们使用如下方式创建ServiceRegistry对象
//         * ServiceRegistry serviceRegistry= new ServiceRegistrybuilder()
//         * .applySettings(configuration.getProperties()).buildServiceRegistry();
//         *
//         * 在hibernate5.0.2中buildServiceRegistry方法被替换掉了我们使用如下方式去创建ServiceRegistry对象
//         */
//        ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties())
//                .build();
//
//        hibernateSessionFactory =configuration.buildSessionFactory(serviceRegistry);
//        hibernateSession =hibernateSessionFactory.openSession();
//        //持久化一些Car对象
//        populateDBWithTestData();
//    }
//
//    @org.junit.Test
//    public void demo(){
//
//    }
//
//    /**
//     * 现在我们的两个测试用的Car对象已经保存到H2数据库中了，而且Lucene也已经对它们创建了
//     * 索引！我们可以通过Lucene来搜索Cars：
//     *
//     * “多条件查询” 搜索—BooleanQuery
//     * BooleanQuery也是实际开发过程中经常使用的一种Query。
//     * 它其实是一个组合的Query，在使用时可以把各种Query对象添加进去并标明它们之间的逻辑关系。
//     * 在本节中所讨论的所有查询类型都可以使用BooleanQuery综合起来。
//     * BooleanQuery本身来讲是一个布尔子句的容器，它提供了专门的API方法往其中添加子句，
//     * 并标明它们之间的关系，以下代码为BooleanQuery提供的用于添加子句的API接口：
//     * @throws Exception
//     */
//    @org.junit.Test
//    public void testUsingLuceneBooleanQueryReturningFullEntity() throws Exception{
//        FullTextSession fullTextSession = Search.getFullTextSession(hibernateSession);
//
//        BooleanQuery.Builder builder = new BooleanQuery.Builder();
//        TermQuery gt350TermQuery = new TermQuery(new Term("model", "GT 350"));
//        TermQuery belAirTermQuery = new TermQuery(new Term("model", "Bel Air"));
//        /**
//         * 1．MUST和MUST：取得连个查询子句的交集。
//         * 2．MUST和MUST_NOT：表示查询结果中不能包含MUST_NOT所对应得查询子句的检索结果。
//         * 3．SHOULD与MUST_NOT：连用时，功能同MUST和MUST_NOT。
//         * 4．SHOULD与MUST连用时，结果为MUST子句的检索结果,但是SHOULD可影响排序。
//         * 5．SHOULD与SHOULD：表示“或”关系，最终检索结果为所有检索子句的并集。
//         * 6．MUST_NOT和MUST_NOT：无意义，检索无结果。
//         */
//        builder.add(gt350TermQuery, BooleanClause.Occur.SHOULD);
//        builder.add(belAirTermQuery, BooleanClause.Occur.SHOULD);
//        BooleanQuery booleanQuery =builder.build();
////        Query q = new QueryParser(Version.LUCENE_36, "cs-method", new StandardAnalyzer(Version.LUCENE_36)).parse(booleanQuery.toString());
//
//
//        org.hibernate.Query hibernateQuery = fullTextSession.createFullTextQuery(booleanQuery, Car.class);
//        List<Car> searchResults = hibernateQuery.list();
//
//        boolean foundShelby = false;
//        boolean foundBelAir = false;
//        for (Car car : searchResults) {
//            System.out.println("Car found, id=" + car.getId() + ", model=" + car.getModel());
//            if (car.getModel().equals("GT 350")) {
//                foundShelby = true;
//            } else if (car.getModel().equals("Bel Air")) {
//                foundBelAir = true;
//            }
//        }
//        Assert.assertEquals(2, searchResults.size());
//        Assert.assertTrue(foundShelby && foundBelAir);
//    }
//
//    /**
//     * 持久化一些Car对象
//     */
//    private void populateDBWithTestData(){
//        Transaction tx = hibernateSession.beginTransaction();
//
//        hibernateSession.save(testCars[0]);
//        hibernateSession.save(testCars[1]);
//
//        tx.commit();
//    }
//
//
//}
