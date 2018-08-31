package com.lwhtarena.test;

import java.sql.*;

/**
 * <p>
 * <h2>简述：HSQL 的 Memory-Only内存模式</h2>
 * <ol></ol>
 * <h2>功能描述：HSQL 的 Memory-Only内存模式</h2>
 * <ol></ol>
 * </p>
 *  当随即访问内存，数据库不固定时，可以采用内存的方式运行数据库，由于没有数据写到
 *  硬盘上，这种方式使用在应用数据和applets 和特殊应用的内部进程中使用。这里也把
 *  代码粘贴出来，以做备忘。
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class hsqlmain {

    private static Connection connection;

    public static void main(String[] args) {
        System.out.println("-----------------------------");
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:lwhtarena", "sa", "");
            Statement statement =null;
            ResultSet resultSet =null;

            statement =connection.createStatement();
            String sql1 = "create table dong_test(xh varchar(10));";
            String sql2 = "insert into dong_test(xh) values('tom'); insert into dong_test(xh) values('mary')";
            String sql3 = "select * from dong_test";
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            resultSet = statement.executeQuery(sql3);
            while (resultSet.next()) {
                System.out.println(">>> " + resultSet.getString(1));
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
