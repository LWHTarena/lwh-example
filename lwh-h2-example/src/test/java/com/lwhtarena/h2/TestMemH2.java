package com.lwhtarena.h2;


import java.sql.*;
import java.util.UUID;

/**
 * <p>
 * <h2>简述：H2数据库的内存模式</h2>
 * <ol></ol>
 * <h2>功能描述：H2数据库的内存模式（数据只保存在内存中</h2>
 * <ol></ol>
 * </p>
 *
 * H2数据库被称为内存数据库，因为它支持在内存中创建数据库和表
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class TestMemH2 {


    //数据库连接URL，通过使用TCP/IP的服务器模式（远程连接），当前连接的是内存里面的gacl数据库
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/mem:gacl";
    //连接数据库时使用的用户名
    private static final String USER = "gacl";
    //连接数据库时使用的密码
    private static final String PASSWORD = "123";
    //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
    private static final String DRIVER_CLASS="org.h2.Driver";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        //如果存在USER_INFO表就先删除USER_INFO表
        stmt.execute("DROP TABLE IF EXISTS USER_INFO");
        //创建USER_INFO表
        stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
        //新增
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','大日如来','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','青龙','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','白虎','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','朱雀','女')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','玄武','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','苍狼','男')");
        //删除
        stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='大日如来'");
        //修改
        stmt.executeUpdate("UPDATE USER_INFO SET name='孤傲苍狼' WHERE name='苍狼'");
        //查询
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
        //遍历结果集
        while (rs.next()) {
            System.out.println(rs.getString("id") + "," + rs.getString("name")+ "," + rs.getString("sex"));
        }
        //释放资源
        stmt.close();
        //关闭连接
        conn.close();

    }


}
