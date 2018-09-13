package com.lwhtarena.sqlite;

import java.sql.*;

public class TestSQLite {
    public static void main(String[] args) {
        try {
            //连接sqlite的jdbc
            Class.forName("org.sqlite.JDBC");

            //建立一个数据库lwh.db，如果不存在就在当前目录下创建
            Connection connection = DriverManager.getConnection("jdbc:sqlite:lwh.db");
            Statement statement =connection.createStatement();

            statement.executeUpdate( "create table tbl1(name varchar(20), salary int);" );//创建一个表，两列
            statement.executeUpdate( "insert into tbl1 values('ZhangSan',8000);" ); //插入数据
            statement.executeUpdate( "insert into tbl1 values('LiSi',7800);" );
            statement.executeUpdate( "insert into tbl1 values('WangWu',5800);" );
            statement.executeUpdate( "insert into tbl1 values('ZhaoLiu',9100);" );

            ResultSet rs = statement.executeQuery("select * from tbl1;"); //查询数据
            while (rs.next()){//将查询到的数据打印出来
                System.out.print("name = " + rs.getString("name") + " "); //列属性一
                System.out.println("salary = " + rs.getString("salary")); //列属性二
            }
            rs.close();
            connection.close();  //结束数据库的连接

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
