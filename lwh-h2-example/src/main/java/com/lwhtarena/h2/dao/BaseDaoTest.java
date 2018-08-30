package com.lwhtarena.h2.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:conf/spring.xml" , "classpath:conf/spring-mybatis.xml" })
//public class BaseDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
//
//    @Before
//    public void setUp() throws Exception {
//        String appfunctionSql = getClass().getResource("/conf/sql/appfunction.sql" ).toURI().toString().substring(6);
//        String areaSql = getClass().getResource("/conf/sql/ddc_area.sql" ).toURI().toString().substring(6);
//        String ddcSql = getClass().getResource("/conf/sql/ddc_all.sql" ).toURI().toString().substring(6);
//        String dataSql = getClass().getResource("/conf/sql/data.sql" ).toURI().toString().substring(6);
//        // System.out.println(appfunctionSql);
//        // System.out.println(areaSql);
//        // System.out.println(ddcSql);
//        // System.out.println(dataSql);
//
//        BasicDataSource dataSource = (BasicDataSource) applicationContext.getBean("MyDataSource" );
//        // System.out.println(dataSource.getUrl());
//
//        Connection conn = dataSource.getConnection();
//        Statement st = conn.createStatement();
//        st.execute( "drop all objects;");// 这一句可以不要
//
//        st.execute( "runscript from '" + appfunctionSql + "'");
//        st.execute( "runscript from '" + areaSql + "'" );
//        st.execute( "runscript from '" + ddcSql + "'" );
//        st.execute( "runscript from '" + dataSql + "'" );
//        st.close();
//        conn.close();
//    }
//
//    @Test
//    public void test_1() {
//    }
//
//}
