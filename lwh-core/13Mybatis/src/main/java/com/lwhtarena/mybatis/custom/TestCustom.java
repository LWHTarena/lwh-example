package com.lwhtarena.mybatis.custom;

import com.lwhtarena.mybatis.custom.dao.UserDao;
import com.lwhtarena.mybatis.custom.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author liwh
 * @Title: TestCustom
 * @Package com.lwhtarena.mybatis.custom
 * @Description: 整合Spring的原理之前理解原生的MyBatis执行原理是非常有必要的
 *
 * MyBatis工作流程简述
 * 一、传统工作模式：
 * <blockquote><pre>
 * 1、创建SqlSessionFactoryBuilder对象，调用build(inputstream)方法读取并解析配置文件，返回SqlSessionFactory对象
 * 2、由SqlSessionFactory创建SqlSession 对象，没有手动设置的话事务默认开启
 * 3、调用SqlSession中的api，传入Statement Id和参数，内部进行复杂的处理，最后调用jdbc执行SQL语句，封装结果返回。
 * </pre></blockquote>
 *
 * public static void main(String[] args) {
 * 		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
 * 		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
 * 		SqlSession sqlSession = factory.openSession();
 *
 * 		String name = "tom";
 * 		List<User> list = sqlSession.selectList("com.lwhtarena.mapper.UserMapper.getUserByName",params);
 * }
 *
 * 二、使用Mapper接口：
 * 由于面向接口编程的趋势，MyBatis也实现了通过接口调用mapper配置文件中的SQL语句
 *
 * public static void main(String[] args) {
 * 		//前三步都相同
 * 		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
 * 		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
 * 		SqlSession sqlSession = factory.openSession();
 *
 * 		//这里不再调用SqlSession 的api，而是直接获得了接口对象，调用接口中的方法。
 * 		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
 * 		List<User> list = mapper.getUserByName("tom");
 * }
 * @Version 1.0.0
 * @date 2020/6/19 23:02
 */
public class TestCustom {

    /**
     * 一、传统工作模式：
     * <blockquote><pre>
     * 1、创建SqlSessionFactoryBuilder对象，调用build(inputstream)方法读取并解析配置文件，返回SqlSessionFactory对象
     * 2、由SqlSessionFactory创建SqlSession 对象，没有手动设置的话事务默认开启
     * 3、调用SqlSession中的api，传入Statement Id和参数，内部进行复杂的处理，最后调用jdbc执行SQL语句，封装结果返回。
     * </pre></blockquote>
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        InputStream inputStream =Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();

        User user = new User(1, "李文浩", 29);
        //插入数据
        sqlSession.insert("com.lwhtarena.mybatis.custom.dao.UserDao.saveUser",user);
        //提交事务
        sqlSession.commit();

        /****------------查看Configuration下的mappedStatements集合都有哪些mappedStatement-------------*****/
        Collection<MappedStatement> statements = sqlSession.getConfiguration().getMappedStatements();
        statements.forEach(obj -> System.out.println(obj.getId()+"+++ "+obj.getResource()+"===="+obj.getBoundSql(user).getSql()));

        //关闭Session
        sqlSession.close();
    }

    /**
     * 二、使用Mapper接口：
     * 由于面向接口编程的趋势，MyBatis也实现了通过接口调用mapper配置文件中的SQL语句
     *
     * @throws IOException
     */
    @Test
    public void testSelect() throws IOException {
        InputStream inputStream =Resources.getResourceAsStream("mybatis-config2.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();

        //这里不再调用SqlSession 的api，而是直接获得了接口对象，调用接口中的方法。
        UserDao mapper = sqlSession.getMapper(UserDao.class);

 		User user = mapper.getUserByName("张三");
        System.out.println(user.toString());
    }
}
